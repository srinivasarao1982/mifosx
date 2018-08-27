package org.mifosplatform.portfolio.rblvalidation.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.commands.domain.CommandWrapper;
import org.mifosplatform.commands.service.CommandWrapperBuilder;
import org.mifosplatform.commands.service.PortfolioCommandSourceWritePlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.ToApiJsonSerializer;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.loanaccount.data.PartialLoanData;
import org.mifosplatform.portfolio.loanaccount.data.SequenceNumberData;
import org.mifosplatform.portfolio.loanaccount.service.PartialLoanReadPlatformService;
import org.mifosplatform.portfolio.rblvalidation.data.RblCrdeitResponseData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditBureauData;
import org.mifosplatform.portfolio.rblvalidation.service.RblCreditBurequReadPlatfoemServie;
import org.mifosplatform.portfolio.rblvalidation.service.RblDataReadplatformService;
import org.mifosplatform.portfolio.rblvalidation.service.RblEquifaxWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Path("/rblValidation")
@Component
@Scope("singleton")
public class RblValidationApiResource {

    private final PlatformSecurityContext context;
    private final RblDataReadplatformService rblDataReadplatformService;
    private final ToApiJsonSerializer<RblCreditBureauData> toApiJsonSerializer;
    private final ToApiJsonSerializer<RblCrdeitResponseData> toApiJsonSerializerResponse;
    private final RblCreditBurequReadPlatfoemServie rblCreditBurequReadPlatfoemServie;
    private final RblEquifaxWritePlatformService rblEquifaxWritePlatformService;
    private final ApiRequestParameterHelper apiRequestParameterHelper;

    
    @Autowired
    public RblValidationApiResource(final PlatformSecurityContext context, 
    		final RblDataReadplatformService rblDataReadplatformService,
    		final ToApiJsonSerializer<RblCreditBureauData> toApiJsonSerializer,
    		final ToApiJsonSerializer<RblCrdeitResponseData> toApiJsonSerializerResponse,
    		final RblCreditBurequReadPlatfoemServie rblCreditBurequReadPlatfoemServie,
    		final RblEquifaxWritePlatformService rblEquifaxWritePlatformService,
    		final ApiRequestParameterHelper apiRequestParameterHelper
            ) {
        this.context = context;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.rblDataReadplatformService=rblDataReadplatformService;
        this.toApiJsonSerializerResponse =toApiJsonSerializerResponse;
        this.rblCreditBurequReadPlatfoemServie=rblCreditBurequReadPlatfoemServie;
        this.rblEquifaxWritePlatformService=rblEquifaxWritePlatformService;
        this.apiRequestParameterHelper=apiRequestParameterHelper;
        
    }
    
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })

    public String getParatialLoan(@QueryParam("centerId") final Long centerId,@QueryParam("clientId") final Long clientId,
    		@QueryParam("fromDate") final String fromDate,@QueryParam("toDate") final String toDate,@QueryParam("value") final String valufor,@Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(RblApiValidationConstant.RBLDETAILS_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        List<RblCrdeitResponseData>rblCrdeitResponseData=new ArrayList<RblCrdeitResponseData>();
        List<RblCreditBureauData>rblCreditBureauData=new ArrayList<RblCreditBureauData>();
        List<RblCreditBureauData>rblCreditBureauErrorData=new ArrayList<RblCreditBureauData>();

        if(valufor.equalsIgnoreCase("request")){
        	rblCreditBureauData =this.rblCreditBurequReadPlatfoemServie.getbreauRequstData(centerId, clientId, fromDate, toDate);
        	return this.toApiJsonSerializer.serialize(settings, rblCreditBureauData,
        			RblApiValidationConstant.RBL_REQUEST_DATA_PARAMETERS);
        }
        else if(valufor.equalsIgnoreCase("response")){
        	rblCreditBureauErrorData =this.rblCreditBurequReadPlatfoemServie.getbreauResponseData(centerId, clientId, fromDate, toDate);
        	return this.toApiJsonSerializer.serialize(settings, rblCreditBureauData,
        			RblApiValidationConstant.RBL_REQUEST_DATA_PARAMETERS);
        }
        else{
        	rblCrdeitResponseData =this.rblCreditBurequReadPlatfoemServie.getbreauErrorData(centerId, clientId, fromDate, toDate);
        	return this.toApiJsonSerializerResponse.serialize(settings, rblCrdeitResponseData,
        			RblApiValidationConstant.RBLRESPONSE_DATA_PARAMETERS);

        	
        }
      }


   
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(final String apiRequestBodyAsJson,@QueryParam("clintId") String clintId) {
    	
        Response response = Response.status(400).build();
    	String [] stringclientsId =clintId.split(",");
       for(String clientsId :stringclientsId){
    	   Long clientId =Long.parseLong(clientsId);
    	   this.rblEquifaxWritePlatformService.rblequifaxIntregation(clientId);
       }
       response.status(200).build(); 
       return response;
		}

   



}

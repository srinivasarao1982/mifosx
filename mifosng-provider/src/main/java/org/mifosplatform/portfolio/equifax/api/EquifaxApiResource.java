package org.mifosplatform.portfolio.equifax.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.infrastructure.configuration.service.ConfigurationReadPlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.ToApiJsonSerializer;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.equifax.data.EquifaxErrorData;
import org.mifosplatform.portfolio.equifax.data.EquifaxRequestData;
import org.mifosplatform.portfolio.equifax.data.EquifaxResponseData;
import org.mifosplatform.portfolio.equifax.service.EquifaxClientDetailsService;
import org.mifosplatform.portfolio.equifax.service.EquifaxService;
import org.mifosplatform.portfolio.group.domain.GroupRepository;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoanRepository;
import org.mifosplatform.portfolio.rblvalidation.api.RblApiValidationConstant;
import org.mifosplatform.portfolio.rblvalidation.data.RblCrdeitResponseData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditBureauData;
import org.mifosplatform.portfolio.rblvalidation.data.RblValidatefileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/equifaxintregation")
@Component
@Scope("singleton")
public class EquifaxApiResource {
	private final PlatformSecurityContext context;    
    private final GroupRepository groupRepository;
    private final ConfigurationReadPlatformService configurationReadPlatformService;
    private final PartialLoanRepository partialLoanRepository;
    private final EquifaxService equifaxService;
    private final EquifaxClientDetailsService equifaxClientDetailsService;
    private final ToApiJsonSerializer<EquifaxRequestData> toApiJsonSerializer;
    private final ToApiJsonSerializer<EquifaxErrorData> toApiJsonValidateSerializer;
    private final ToApiJsonSerializer<EquifaxResponseData> toApiJsonSerializerResponse;
    private final ApiRequestParameterHelper apiRequestParameterHelper;

      
    @Autowired
    public EquifaxApiResource(final PlatformSecurityContext context,     		
    		final GroupRepository groupRepository,
    		final ConfigurationReadPlatformService configurationReadPlatformService,
    		final PartialLoanRepository partialLoanRepository,
    		final EquifaxService equifaxService,
    		final EquifaxClientDetailsService equifaxClientDetailsService,
    		final ToApiJsonSerializer<EquifaxRequestData> toApiJsonSerializer,
            final ToApiJsonSerializer<EquifaxErrorData> toApiJsonValidateSerializer,
            final ToApiJsonSerializer<EquifaxResponseData> toApiJsonSerializerResponse ,
            final ApiRequestParameterHelper apiRequestParameterHelper)  
          
        {
        this.context = context;     
        this.groupRepository=groupRepository;
        this.configurationReadPlatformService=configurationReadPlatformService;
        this.partialLoanRepository=partialLoanRepository;
        this.equifaxService=equifaxService;
        this.equifaxClientDetailsService=equifaxClientDetailsService;
        this.toApiJsonSerializer=toApiJsonSerializer;
        this.toApiJsonSerializerResponse=toApiJsonSerializerResponse;
        this.toApiJsonValidateSerializer=toApiJsonValidateSerializer;
        this.apiRequestParameterHelper=apiRequestParameterHelper;
      }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(final String apiRequestBodyAsJson,@QueryParam("clientId") String clientId,
    		@QueryParam("centerId") final Long centerId) {
        	
    	String [] stringclientsId =clientId.split(",");    	
    	for(int i=0 ;i<stringclientsId.length;i++){
    		Long clientIdforequifax =Long.parseLong(stringclientsId[i]);
        	this.equifaxService.getCreditBureauResult(clientIdforequifax,centerId);     
    	}
        Response response = Response.status(200).build();  	
        return response;			
	}
    
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getEquifaxData(@QueryParam("centerId") final Long centerId,@QueryParam("clientId") final Long clientId,
    		@QueryParam("fromDate") final String fromDate,@QueryParam("toDate") final String toDate,@QueryParam("valufor") final String valufor,@Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(EquifaxApiConstant.EQUIFAX_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        List<EquifaxErrorData>equifaxErrorData=new ArrayList<EquifaxErrorData>();
        List<EquifaxRequestData>equifaxRequestData=new ArrayList<EquifaxRequestData>();
        List<EquifaxResponseData>equifaxResponseData=new ArrayList<EquifaxResponseData>();

        if(valufor.equalsIgnoreCase("request")){
        	equifaxRequestData =this.equifaxClientDetailsService.getRequestData(centerId, fromDate, toDate);
        	return this.toApiJsonSerializer.serialize(settings, equifaxRequestData,
        			EquifaxApiConstant.EQUIFAX_REQUEST_DATA_PARAMETERS);
        }
        else if(valufor.equalsIgnoreCase("response")){
        	equifaxResponseData =this.equifaxClientDetailsService.getEquifaxResponseData(centerId, fromDate, toDate);
        	return this.toApiJsonSerializerResponse.serialize(settings, equifaxResponseData,
        			EquifaxApiConstant.EQUIFAXRESPONSE_DATA_PARAMETERS);
        	   }
        else{
        	
        	equifaxErrorData =this.equifaxClientDetailsService.getEquifaxErrorData(centerId, fromDate, toDate);
        	return this.toApiJsonValidateSerializer.serialize(settings, equifaxErrorData,
        			EquifaxApiConstant.EQUIFAXERROR_DATA_PARAMETERS);
	
        }
      }
        

}

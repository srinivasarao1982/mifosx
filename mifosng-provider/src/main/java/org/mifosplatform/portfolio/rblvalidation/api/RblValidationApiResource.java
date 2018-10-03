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
import org.mifosplatform.infrastructure.configuration.data.GlobalConfigurationPropertyData;
import org.mifosplatform.infrastructure.configuration.service.ConfigurationReadPlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.ToApiJsonSerializer;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.group.domain.GroupRepository;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.loanaccount.data.PartialLoanData;
import org.mifosplatform.portfolio.loanaccount.data.SequenceNumberData;
import org.mifosplatform.portfolio.loanaccount.service.PartialLoanReadPlatformService;
import org.mifosplatform.portfolio.rblvalidation.data.RblCrdeitResponseData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditBureauData;
import org.mifosplatform.portfolio.rblvalidation.data.RblLosFileData;
import org.mifosplatform.portfolio.rblvalidation.data.RblValidatefileData;
import org.mifosplatform.portfolio.rblvalidation.exception.CbCheckStatus;
import org.mifosplatform.portfolio.rblvalidation.exception.GrtNotCompletedException;
import org.mifosplatform.portfolio.rblvalidation.service.LosFileReadPlatformService;
import org.mifosplatform.portfolio.rblvalidation.service.RblCreditBurequReadPlatfoemServie;
import org.mifosplatform.portfolio.rblvalidation.service.RblDataReadplatformService;
import org.mifosplatform.portfolio.rblvalidation.service.RblDataValidatorService;
import org.mifosplatform.portfolio.rblvalidation.service.RblEquifaxWritePlatformService;
import org.mifosplatform.portfolio.rblvalidation.service.RblLosFileGenerationService;
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
    private final ToApiJsonSerializer<RblValidatefileData> toApiJsonValidateSerializer;
    private final ToApiJsonSerializer<RblCrdeitResponseData> toApiJsonSerializerResponse;
    private final RblCreditBurequReadPlatfoemServie rblCreditBurequReadPlatfoemServie;
    private final RblEquifaxWritePlatformService rblEquifaxWritePlatformService;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final LosFileReadPlatformService losFileReadPlatformService;
    private final ToApiJsonSerializer<RblLosFileData> toApiFileJsonSerializerResponse;
    private final RblDataValidatorService rblDataValidatorService;
    private final RblLosFileGenerationService rblLosFileGenerationService;
    private final GroupRepository groupRepository;
    private final ConfigurationReadPlatformService configurationReadPlatformService;


    
    @Autowired
    public RblValidationApiResource(final PlatformSecurityContext context, 
    		final RblDataReadplatformService rblDataReadplatformService,
    		final ToApiJsonSerializer<RblCreditBureauData> toApiJsonSerializer,
    		final ToApiJsonSerializer<RblCrdeitResponseData> toApiJsonSerializerResponse,
    		final RblCreditBurequReadPlatfoemServie rblCreditBurequReadPlatfoemServie,
    		final RblEquifaxWritePlatformService rblEquifaxWritePlatformService,
    		final ApiRequestParameterHelper apiRequestParameterHelper,
    		final LosFileReadPlatformService losFileReadPlatformService,
    		final ToApiJsonSerializer<RblLosFileData> toApiFileJsonSerializerResponse,
    		final RblDataValidatorService rblDataValidatorService,
    		final RblLosFileGenerationService rblLosFileGenerationService,
    		final GroupRepository groupRepository,
    		final ConfigurationReadPlatformService configurationReadPlatformService,
    		final ToApiJsonSerializer<RblValidatefileData> toApiJsonValidateSerializer
            ) {
        this.context = context;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.rblDataReadplatformService=rblDataReadplatformService;
        this.toApiJsonSerializerResponse =toApiJsonSerializerResponse;
        this.rblCreditBurequReadPlatfoemServie=rblCreditBurequReadPlatfoemServie;
        this.rblEquifaxWritePlatformService=rblEquifaxWritePlatformService;
        this.apiRequestParameterHelper=apiRequestParameterHelper;
        this.losFileReadPlatformService=losFileReadPlatformService;
        this.toApiFileJsonSerializerResponse=toApiFileJsonSerializerResponse;
        this.rblDataValidatorService=rblDataValidatorService;
        this.rblLosFileGenerationService=rblLosFileGenerationService;
        this.groupRepository=groupRepository;
        this.configurationReadPlatformService=configurationReadPlatformService;
        this.toApiJsonValidateSerializer=toApiJsonValidateSerializer;
;

        
    }
    
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getParatialLoan(@QueryParam("centerId") final Long centerId,@QueryParam("clientId") final Long clientId,
    		@QueryParam("fromDate") final String fromDate,@QueryParam("toDate") final String toDate,@QueryParam("valufor") final String valufor,@QueryParam("clientcbcheck") final boolean clientcbcheck,@Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(RblApiValidationConstant.RBLDETAILS_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        List<RblCrdeitResponseData>rblCrdeitResponseData=new ArrayList<RblCrdeitResponseData>();
        List<RblCreditBureauData>rblCreditBureauData=new ArrayList<RblCreditBureauData>();
        List<RblCreditBureauData>rblCreditBureauErrorData=new ArrayList<RblCreditBureauData>();

        if(valufor.equalsIgnoreCase("request")){
        	rblCreditBureauData =this.rblCreditBurequReadPlatfoemServie.getbreauRequstData(centerId, clientId, fromDate, toDate,clientcbcheck);
        	return this.toApiJsonSerializer.serialize(settings, rblCreditBureauData,
        			RblApiValidationConstant.RBL_REQUEST_DATA_PARAMETERS);
        }
        else if(valufor.equalsIgnoreCase("response")){
        	rblCrdeitResponseData =this.rblCreditBurequReadPlatfoemServie.getbreauErrorData(centerId, clientId, fromDate, toDate,clientcbcheck);
        	return this.toApiJsonSerializerResponse.serialize(settings, rblCrdeitResponseData,
        			RblApiValidationConstant.RBLRESPONSE_DATA_PARAMETERS);
        	   }
        else{
        	
        	rblCreditBureauErrorData =this.rblCreditBurequReadPlatfoemServie.getbreauResponseData(centerId, clientId, fromDate, toDate,clientcbcheck);
        	return this.toApiJsonSerializer.serialize(settings, rblCreditBureauErrorData,
        			RblApiValidationConstant.RBL_REQUEST_DATA_PARAMETERS);
	
        }
      }
      
    @GET
    @Path("/validatefile")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getParatialLoan(@QueryParam("centerId") final Long centerId,
    		@QueryParam("fromDate") final String fromDate,@QueryParam("toDate") final String toDate,@QueryParam("fileType") final String fileType,@Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(RblApiValidationConstant.RBLDETAILS_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        List<RblValidatefileData>rblValidateData=new ArrayList<RblValidatefileData>();
        
        rblValidateData=this.rblCreditBurequReadPlatfoemServie.getValidateFileData(centerId, fromDate, toDate, fileType);
        return this.toApiJsonValidateSerializer.serialize(settings, rblValidateData,
    			RblApiValidationConstant.RBLRESPONSE_VALIDATE_DATA_PARAMETERS);
    }
    
   
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response create(final String apiRequestBodyAsJson,@QueryParam("clintId") String clintId,@QueryParam("isValidate") boolean isValidate) {
    	
        Response response = Response.status(200).build();    	
    	   this.rblEquifaxWritePlatformService.rblequifaxIntregation(clintId,isValidate);     
       return response;
		}

       // Los File Send And Receive Logic
    
    @GET
    @Path("/file")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getParatialLoan(@QueryParam("fromDate") final String fromDate,@QueryParam("toDate") final String toDate,@QueryParam("fileType") final String fileType,@Context final UriInfo uriInfo) {


        this.context.authenticatedUser().validateHasReadPermission(RblApiValidationConstant.RBLDETAILS_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        List<RblLosFileData>rblLosFileData=new ArrayList<RblLosFileData>();
        
        rblLosFileData =this.losFileReadPlatformService.readLosFile(fromDate,toDate,fileType);
        	return this.toApiFileJsonSerializerResponse.serialize(settings, rblLosFileData,
        			RblApiValidationConstant.RBLFILERESPONSE_DATA_PARAMETERS);
        }
      

      @POST
      @Path("/file")
      @Consumes({ MediaType.APPLICATION_JSON })
      @Produces({ MediaType.APPLICATION_JSON })
      public Response create(final String apiRequestBodyAsJson,@QueryParam("centerId") String centerId,@QueryParam("groupId") String groupId,@QueryParam("clintId") String clintId,
    		   @QueryParam("command") String command, @QueryParam("centerDatatobesent") boolean centerDatatobesent,
    		   @QueryParam("groupDatatobesend") boolean groupDatatobesend,@QueryParam("isImagetobesent") boolean isImagetobesent,@QueryParam("isreprocess") boolean isreprocess,@Context final UriInfo uriInfo) {
	
    	  if(command.equalsIgnoreCase("validate")){
    		  this.rblDataValidatorService.validateDatafortransfer(centerId, groupId, clintId);
     		// this.rblLosFileGenerationService.generateLosFile(clintId, centerId, groupId,centerDatatobesent,groupDatatobesend,isreprocess); 

    	  }
    	  else{
    		  GlobalConfigurationPropertyData  globalConfigurationPropertyData =this.configurationReadPlatformService.retrieveGlobalConfiguration((long)21);
    		  if(globalConfigurationPropertyData.isEnabled()){
    		  Group groupData=this.groupRepository.findCenterById(Long.parseLong(centerId));
    		  if(groupData.getIsgrtCompleted()==0){
    			  throw new GrtNotCompletedException(groupData.getName()+"-"+groupData.getId());
    		  }
    		  if(groupData.getIscbChecked()==0){
    			  throw new CbCheckStatus(groupData.getName()+"-"+groupData.getId());
    		  }
    		  }
    		 this.rblLosFileGenerationService.generateLosFile(clintId, centerId, groupId,centerDatatobesent,groupDatatobesend,isreprocess); 
    	  }
         Response response = Response.status(200).build();       
         return response;
	}


}

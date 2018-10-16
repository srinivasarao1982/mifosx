package org.mifosplatform.portfolio.loanaccount.api;

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
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.commands.domain.CommandWrapper;
import org.mifosplatform.commands.service.CommandWrapperBuilder;
import org.mifosplatform.commands.service.PortfolioCommandSourceWritePlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.ToApiJsonSerializer;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.data.PartialLoanData;
import org.mifosplatform.portfolio.loanaccount.data.SequenceNumberData;
import org.mifosplatform.portfolio.loanaccount.service.PartialLoanReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Path("/partialloan")
@Component
@Scope("singleton")
public class PartialLoanApiResource {


    private final PlatformSecurityContext context;
    private final PartialLoanReadPlatformService partialLoanReadPlatformService;
    private final ToApiJsonSerializer<PartialLoanData> toApiJsonSerializer;
    private final ToApiJsonSerializer<SequenceNumberData> seqnumbertoApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    
    @Autowired
    public PartialLoanApiResource(final PlatformSecurityContext context, 
    		final ToApiJsonSerializer<PartialLoanData> toApiJsonSerializer,
            final PartialLoanReadPlatformService partialLoanReadPlatformService,
            final ApiRequestParameterHelper apiRequestParameterHelper,
            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
            final ToApiJsonSerializer<SequenceNumberData> seqnumbertoApiJsonSerializer
            ) {
        this.context = context;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
        this.partialLoanReadPlatformService= partialLoanReadPlatformService;
        this.seqnumbertoApiJsonSerializer=seqnumbertoApiJsonSerializer;
          }
    
    @GET
    @Path("template/{parentId}/{isActive}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveTemplate(@Context final UriInfo uriInfo,@PathParam("parentId") final Long parentId,@PathParam("isActive") final Long isActive,@QueryParam("isDisburse") final Long isDisburse) {

        PartialLoanData partialLoanData = null;
        this.context.authenticatedUser().validateHasReadPermission(PartialLoanApiConstant.PARTIALLOAN_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        
         partialLoanData = this.partialLoanReadPlatformService.retrieveTemplate();
         List<Long>acceptedMember= this.partialLoanReadPlatformService.retriveAcceptedMember(parentId,isActive,isDisburse);
         PartialLoanData partialLoanDataforreturn=PartialLoanData.getAcceptedclientsId(partialLoanData.getStatus(),acceptedMember);
         return this.toApiJsonSerializer.serialize(settings, partialLoanDataforreturn,
        		  PartialLoanApiConstant.PARTIALLOAN_RESPONSE_DATA_PARAMETERS);
    }
    
    @GET
    @Path("{parentId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getParatialLoan(@PathParam("parentId") final Long parentId,@QueryParam("isSequenceNumber") final boolean isSequenceNumber,@QueryParam("isUpdateStatus") final boolean isUpdateStatus, @Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(PartialLoanApiConstant.PARTIALLOAN_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        List<PartialLoanData>partialLoanDatas=new ArrayList<PartialLoanData>();
        List<SequenceNumberData>sequenceNoDatas=new ArrayList<SequenceNumberData>();
        if(isSequenceNumber){
        	sequenceNoDatas=this.partialLoanReadPlatformService.retriveSequenceNumber(parentId,isUpdateStatus);
        	 return this.seqnumbertoApiJsonSerializer.serialize(settings, sequenceNoDatas,
             		  PartialLoanApiConstant.PARTIALLOAN_RESPONSE_DATA_PARAMETERS);
        
        }
       
        partialLoanDatas = this.partialLoanReadPlatformService.retrievepartialLoanDetails(parentId);
       
        
        return this.toApiJsonSerializer.serialize(settings, partialLoanDatas,
        		  PartialLoanApiConstant.PARTIALLOAN_RESPONSE_DATA_PARAMETERS);
    }


   
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String create(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
                .createPartialLoan() //
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }

    @PUT
    @Path("{clientId}/{groupId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String update(@PathParam("clientId") final Long clientId,@PathParam("groupId") final Long groupId, final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
                .updatePartialLoan(clientId,groupId) //
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }



}

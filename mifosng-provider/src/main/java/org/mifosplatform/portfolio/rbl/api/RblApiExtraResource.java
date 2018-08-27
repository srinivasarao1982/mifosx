package org.mifosplatform.portfolio.rbl.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.loanaccount.data.PartialLoanData;
import org.mifosplatform.portfolio.loanaccount.data.SequenceNumberData;
import org.mifosplatform.portfolio.loanaccount.service.PartialLoanReadPlatformService;
import org.mifosplatform.portfolio.rbl.data.RblCenterData;
import org.mifosplatform.portfolio.rbl.data.RblCustomerData;
import org.mifosplatform.portfolio.rbl.data.RblGroupData;
import org.mifosplatform.portfolio.rbl.data.RblLoanData;
import org.mifosplatform.portfolio.rbl.service.RblCenterReadPlatformService;
import org.mifosplatform.portfolio.rbl.service.RblCustomerReadPlatformService;
import org.mifosplatform.portfolio.rbl.service.RblGroupReadPlatformService;
import org.mifosplatform.portfolio.rbl.service.RblLoanReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Path("/rbl")
@Component
@Scope("singleton")
public class RblApiExtraResource {
	
	  private final PlatformSecurityContext context;
	    private final RblCustomerReadPlatformService rblCustomerReadPlatformService;
	    private final RblGroupReadPlatformService rblGroupReadPlatformService;
	    private final RblLoanReadPlatformService rblLoanReadPlatformService;
	    private final RblCenterReadPlatformService rblCenterReadPlatformService;
	    private final ToApiJsonSerializer<RblCenterData> toApiJsonSerializerrblCnter;
	    private final ToApiJsonSerializer<RblCustomerData> toApiJsonSerializerrblCustomer;
	    private final ToApiJsonSerializer<RblGroupData> toApiJsonSerializerrblgroup;
	    private final ToApiJsonSerializer<RblLoanData> toApiJsonSerializerrblloan;
	    private final ApiRequestParameterHelper apiRequestParameterHelper;
	    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
	    
	    @Autowired
	    public RblApiExtraResource(final PlatformSecurityContext context, 
	    		 final RblCenterReadPlatformService rblCenterReadPlatformService,
	             final RblCustomerReadPlatformService rblCustomerReadPlatformService,
	             final RblGroupReadPlatformService rblGroupReadPlatformService,
	             final RblLoanReadPlatformService rblLoanReadPlatformService,
	             final ToApiJsonSerializer<RblCenterData> toApiJsonSerializerrblCnter,
	             final ToApiJsonSerializer<RblCustomerData> toApiJsonSerializerrblCustomer,
	             final ToApiJsonSerializer<RblGroupData> toApiJsonSerializerrblgroup,
	             final ToApiJsonSerializer<RblLoanData> toApiJsonSerializerrblloan,
	             final ApiRequestParameterHelper apiRequestParameterHelper,
	             final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService
            ) {
	        this.context = context;
		    this.rblCustomerReadPlatformService=rblCustomerReadPlatformService;
		    this.rblGroupReadPlatformService=rblGroupReadPlatformService;
		    this.rblLoanReadPlatformService=rblLoanReadPlatformService;
		    this.rblCenterReadPlatformService=rblCenterReadPlatformService;
		    this.toApiJsonSerializerrblCnter=toApiJsonSerializerrblCnter;
		    this.toApiJsonSerializerrblCustomer=toApiJsonSerializerrblCustomer;
		    this.toApiJsonSerializerrblgroup=toApiJsonSerializerrblgroup;
		    this.toApiJsonSerializerrblloan=toApiJsonSerializerrblloan;
		    this.apiRequestParameterHelper=apiRequestParameterHelper;
		    this.commandsSourceWritePlatformService=commandsSourceWritePlatformService;
		
	            }
	    
	    @POST
	    @Path("/rblclients")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String createcustomer(final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .createRblCustomer() //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblCustomer.serialize(result);
	    }

	    @POST
	    @Path("/rblgroup")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String creategroup(final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .CreateRblGroup() //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblgroup.serialize(result);
	    }

	    @POST
	    @Path("/rblloan")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String createloan(final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .createRblLoan() //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblloan.serialize(result);
	    }

	    @POST
	    @Path("/rblcenter")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String createcenter(final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .createRblCenter() //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblCnter.serialize(result);
	    }

	    @PUT
	    @Path("/rblclients/{customerId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String updaterblCustomer(@PathParam("customerId") final Long customerId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .UpdateRblCustomer(customerId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblCustomer.serialize(result);
	    }
	    
	    @PUT
	    @Path("/rblgroup/{groupId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String updateRblGroup(@PathParam("groupId") final Long groupId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .UpdateRblGroup(groupId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblgroup.serialize(result);
	    }
	    
	    @PUT
	    @Path("/rblloan/{loanId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String updateRblLoan(@PathParam("loanId") final Long loanId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .UpdateRblLoan(loanId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblgroup.serialize(result);
	    }
	    
	    @PUT
	    @Path("/rblcenter/{centerId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String updateRblCenter(@PathParam("centerId") final Long centerId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .updateRblCenter(centerId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblCnter.serialize(result);
	    }

	    @DELETE
	    @Path("/rblclients/{customerId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String deleteRblCustomer(@PathParam("customerId") final Long customerId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .deleteRblCustomer(customerId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblCustomer.serialize(result);
	    }
	    
	    @DELETE
	    @Path("/rblgroup/{groupId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String deleteRblGroup(@PathParam("groupId") final Long groupId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .deleteRblGroup(groupId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblgroup.serialize(result);
	    }
	    
	    @DELETE
	    @Path("/rblloan/{loanId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String deleteRblLoan(@PathParam("loanId") final Long loanId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	               .deleteRblGroup(loanId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblloan.serialize(result);
	    }
	    
	    @DELETE
	    @Path("/rblcenter/{centerId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String deletRbl(@PathParam("centerId") final Long centerId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .deleteRblCenter(centerId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializerrblCnter.serialize(result);
	    }

	    @GET
	    @Path("/rblcenter/{centerId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String getParatialLoan(@PathParam("centerId") final Long centerId, @Context final UriInfo uriInfo) {

	        this.context.authenticatedUser().validateHasReadPermission(RblCenterDeatilsApiConstant.RBLCENTER_RESOURCE_NAME);
	        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
	        
	        RblCenterData rblCenterData=this.rblCenterReadPlatformService.retriveRblCenter(centerId);
	        return this.toApiJsonSerializerrblCnter.serialize(settings, rblCenterData,
	        		RblCenterDeatilsApiConstant.RBLCENTER_RESPONSE_DATA_PARAMETERS);
	    }
	    
	    @GET
	    @Path("/rblgroup/{groupId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String getRblGroup(@PathParam("groupId") final Long groupId, @Context final UriInfo uriInfo) {

	        this.context.authenticatedUser().validateHasReadPermission(RblGroupDetailsApiConstant.RBLGROUP_RESOURCE_NAME);
	        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
	        RblGroupData rblGroupData =this.rblGroupReadPlatformService.retriveRblGroup(groupId);
	        return this.toApiJsonSerializerrblgroup.serialize(settings, rblGroupData,
	        		RblGroupDetailsApiConstant.RBLGROUP_RESPONSE_DATA_PARAMETERS);
	    }
	    
	    @GET
	    @Path("/rblclients/{customerId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String getRblCustomer(@PathParam("customerId") final Long customerId, @Context final UriInfo uriInfo) {

	        this.context.authenticatedUser().validateHasReadPermission(RblCustomerDetailsApiConstant.RBLCUSTOMER_RESOURCE_NAME);
	        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
	        RblCustomerData rblCustomerData=this.rblCustomerReadPlatformService.retriveRblCustomerr(customerId);
	        return this.toApiJsonSerializerrblCustomer.serialize(settings, rblCustomerData,
	        		RblCustomerDetailsApiConstant.RBLCUSTOMER_RESPONSE_DATA_PARAMETERS);
	    }
	    @GET
	    @Path("/rblloan{loanId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String getRblLoan(@PathParam("loanId") final Long loanId, @Context final UriInfo uriInfo) {

	        this.context.authenticatedUser().validateHasReadPermission(RblLoanDetailsApiConstant.RBLLOAN_RESOURCE_NAME);
	        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
	        
	       
	        RblLoanData rblLoanData=this.rblLoanReadPlatformService.retriveRblLoan(loanId);	       
	        
	        return this.toApiJsonSerializerrblloan.serialize(settings, rblLoanData,
	        		RblLoanDetailsApiConstant.RBLLOAN_RESPONSE_DATA_PARAMETERS);
	    }

}

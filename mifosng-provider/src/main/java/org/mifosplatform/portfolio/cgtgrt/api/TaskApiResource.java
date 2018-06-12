package org.mifosplatform.portfolio.cgtgrt.api;

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
import org.mifosplatform.portfolio.cgtgrt.data.TaskConfigurationData;
import org.mifosplatform.portfolio.cgtgrt.data.Taskdata;
import org.mifosplatform.portfolio.cgtgrt.service.TaskConfigurationReadplatformService;
import org.mifosplatform.portfolio.cgtgrt.service.TaskReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Path("/task")
@Component
@Scope("singleton")
public class TaskApiResource {

    private final PlatformSecurityContext context;
    private final TaskConfigurationReadplatformService taskConfigurationReadplatformService;
    private final TaskReadPlatformService taskReadPlatformService;
    private final ToApiJsonSerializer<Taskdata> toApiJsonSerializer;
    private final ToApiJsonSerializer<TaskConfigurationData> taskconfigurationApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    
    @Autowired
    public TaskApiResource(final PlatformSecurityContext context, 
    		final ToApiJsonSerializer<Taskdata> toApiJsonSerializer,
    		final TaskConfigurationReadplatformService taskConfigurationReadplatformService,
    		final TaskReadPlatformService taskReadPlatformService,
    		final ApiRequestParameterHelper apiRequestParameterHelper,
            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
            final ToApiJsonSerializer<TaskConfigurationData> taskconfigurationApiJsonSerializer
            ) {
        this.context = context;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
        this.taskConfigurationReadplatformService =taskConfigurationReadplatformService;
        this.taskReadPlatformService=taskReadPlatformService;
        this.taskconfigurationApiJsonSerializer=taskconfigurationApiJsonSerializer;
        }
    
    
    @GET
    @Path("/template")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveTemplate(@Context final UriInfo uriInfo,@QueryParam("officeId") final Long officeId) {

        this.context.authenticatedUser().validateHasReadPermission(TaskApiConstant.TASK_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        
        Taskdata taskData = this.taskReadPlatformService.retrieveTemplate(officeId);
                  return this.toApiJsonSerializer.serialize(settings, taskData,
                		  TaskApiConstant.TASK_RESPONSE_DATA_PARAMETERS);
    }
    
    @GET
    @Path("/template/taskconfiguration")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveTemplatefortaskCongiguration(@Context final UriInfo uriInfo) {

    	TaskConfigurationData taskConfigurationData = null;
        this.context.authenticatedUser().validateHasReadPermission(TaskConfigurationApiConstant.TASKCONFIGURATION_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        
         taskConfigurationData = this.taskConfigurationReadplatformService.retrieveTemplate();
                  return this.taskconfigurationApiJsonSerializer.serialize(settings, taskConfigurationData,
                		  TaskConfigurationApiConstant.TASKCONFIGURATION_RESPONSE_DATA_PARAMETERS);
    }
    

    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String create(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
                .createTaskDetails() //
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @POST
    @Path("/taskconfiguration")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createtaskConfiguration(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
                .createTaskConfiguratioDetails() //
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }

    @PUT
    @Path("{taskId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String update(@PathParam("taskId") final Long taskId, final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
                .updateTaskDetails(taskId) //
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    @PUT
    @Path("/taskconfiguration/{taskconfigurationId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updatetaskconfiguration(@PathParam("taskconfigurationId") final Long taskconfigurationId, final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
                .updateTaskConfigurationDetails(taskconfigurationId) //
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @GET	   
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveOne(@QueryParam("taskId") final Long taskId,@Context final UriInfo uriInfo  ) {
        this.context.authenticatedUser().validateHasReadPermission(TaskApiConstant.TASK_RESOURCE_NAME);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());

        Taskdata taskdata = this.taskReadPlatformService.retrievetaskDetail(taskId);
        
        return this.toApiJsonSerializer.serialize(settings, taskdata, TaskApiConstant.TASK_RESPONSE_DATA_PARAMETERS);
    }

    @GET
    @Path("/retriveall")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveall(@QueryParam("centerId") final Long centerId,@Context final UriInfo uriInfo  ) {
        this.context.authenticatedUser().validateHasReadPermission(TaskApiConstant.TASK_RESOURCE_NAME);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());

       List<Taskdata> taskdatas = this.taskReadPlatformService.retrievetaskDetails(centerId);
        
        return this.toApiJsonSerializer.serialize(settings, taskdatas, TaskApiConstant.TASK_RESPONSE_DATA_PARAMETERS);
    }

    @GET
    @Path("/taskconfiguration")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveOnetaskconfiguration(@QueryParam("taskconfigurationId") final Long taskconfigurationId,@Context final UriInfo uriInfo  ) {
        this.context.authenticatedUser().validateHasReadPermission(TaskConfigurationApiConstant.TASKCONFIGURATION_RESOURCE_NAME);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());

        TaskConfigurationData taskConfigurationData = this.taskConfigurationReadplatformService.retrievetaskConfigurationDetails(taskconfigurationId);
        
        return this.taskconfigurationApiJsonSerializer.serialize(settings, taskConfigurationData, TaskConfigurationApiConstant.TASKCONFIGURATION_RESPONSE_DATA_PARAMETERS);
    }
    
    @GET
    @Path("/alltaskconfiguration")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrivealltaskConfiguration(@Context final UriInfo uriInfo  ) {
        this.context.authenticatedUser().validateHasReadPermission(TaskConfigurationApiConstant.TASKCONFIGURATION_RESOURCE_NAME);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());

        List<TaskConfigurationData> taskConfigurationDatas = this.taskConfigurationReadplatformService.retrieveDetails();
        
        return this.taskconfigurationApiJsonSerializer.serialize(settings, taskConfigurationDatas, TaskConfigurationApiConstant.TASKCONFIGURATION_RESPONSE_DATA_PARAMETERS);
    }
    
   


}

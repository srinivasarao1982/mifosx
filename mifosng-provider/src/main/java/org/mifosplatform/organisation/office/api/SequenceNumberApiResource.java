package org.mifosplatform.organisation.office.api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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
import org.mifosplatform.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.mifosplatform.infrastructure.core.service.SearchParameters;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.data.OfficeData;
import org.mifosplatform.organisation.office.data.SequenceNumberData;
import org.mifosplatform.organisation.office.service.OfficeReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/sequenceNumber")
@Component
@Scope("singleton")
public class SequenceNumberApiResource {
	
	 private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id", "entityId", "sequenceNo"));

	    private final String resourceNameForPermissions = "OFFICE";

	    private final PlatformSecurityContext context;
	    private final OfficeReadPlatformService readPlatformService;
	    private final DefaultToApiJsonSerializer<SequenceNumberData> toApiJsonSerializer;
	    private final ApiRequestParameterHelper apiRequestParameterHelper;
	    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;

	    @Autowired
	    public SequenceNumberApiResource(final PlatformSecurityContext context, final OfficeReadPlatformService readPlatformService,
	            final DefaultToApiJsonSerializer<SequenceNumberData> toApiJsonSerializer, final ApiRequestParameterHelper apiRequestParameterHelper,
	            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService) {
	        this.context = context;
	        this.readPlatformService = readPlatformService;
	        this.toApiJsonSerializer = toApiJsonSerializer;
	        this.apiRequestParameterHelper = apiRequestParameterHelper;
	        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
	    }

	    @GET
	    @Path("{entityId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String retreiveOffice(@PathParam("entityId") final Long entityId, @Context final UriInfo uriInfo) {

	        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());

	        SequenceNumberData sequenceNumberData = this.readPlatformService.retriveSequenceNumber(entityId);
	       
	        return this.toApiJsonSerializer.serialize(settings, sequenceNumberData, this.RESPONSE_DATA_PARAMETERS);
	    }
	    
	    @PUT
	    @Path("{id}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String updateOffice(@PathParam("id") final Long id, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .updateOffice(id) //
	                .withJson(apiRequestBodyAsJson) //
	                .build();

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializer.serialize(result);
	    }
}

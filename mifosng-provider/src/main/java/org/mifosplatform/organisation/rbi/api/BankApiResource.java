package org.mifosplatform.organisation.rbi.api;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.commands.service.PortfolioCommandSourceWritePlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.rbi.data.BankData;
import org.mifosplatform.organisation.rbi.service.BankReadPlatformService;
import org.mifosplatform.organisation.rbi.service.BankWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataParam;

@Path("/rbibankdetails")
@Component
@Scope("singleton")
public class BankApiResource {
	
	private final DefaultToApiJsonSerializer<BankData> toApiJsonSerializer;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final PlatformSecurityContext context;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final BankWritePlatformService bankWritePlatformService;
    private final BankReadPlatformService bankReadPlatformService;
    
    @Autowired
	public BankApiResource(DefaultToApiJsonSerializer<BankData> toApiJsonSerializer,
			PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
			PlatformSecurityContext context, ApiRequestParameterHelper apiRequestParameterHelper,
			BankWritePlatformService bankWritePlatformService, BankReadPlatformService bankReadPlatformService) {
		this.toApiJsonSerializer = toApiJsonSerializer;
		this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
		this.context = context;
		this.apiRequestParameterHelper = apiRequestParameterHelper;
		this.bankWritePlatformService = bankWritePlatformService;
		this.bankReadPlatformService = bankReadPlatformService;
	}
	
	@POST
	@Path("/upload")
	@Consumes({ MediaType.MULTIPART_FORM_DATA})
	@Produces({ MediaType.APPLICATION_JSON})
	public boolean uploadBankDetails(@Context final UriInfo uriInfo,@FormDataParam("file") final InputStream inputStream){
	    this.context.authenticatedUser().validateHasReadPermission(BankApiConstants.BANKDETAILS_RESOURCE_NAME);   
	    return this.bankWritePlatformService.uploadBankDetails(inputStream);
    }
	
	@GET
    @Path("{ifsccode}")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
	public String getBankDetails(@Context final UriInfo uriInfo,@PathParam("ifsccode") final String ifsccode){
		this.context.authenticatedUser().validateHasReadPermission(BankApiConstants.BANKDETAILS_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        BankData rbiBankData =  this.bankReadPlatformService.getBankDetailsByIfscCode(ifsccode);
		return this.toApiJsonSerializer.serialize(settings,rbiBankData,BankApiConstants.BANK_DETAILS_RESPONSE_DATA_PARAMETERS);
    }
}

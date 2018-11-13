package org.mifosplatform.portfolio.client.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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
import org.mifosplatform.portfolio.client.data.ClientBankDetailsData;
import org.mifosplatform.portfolio.client.data.ClientData;
import org.mifosplatform.portfolio.client.data.ClientDetailedData;
import org.mifosplatform.portfolio.client.service.ClientBankDetailsReadPlatformService;
import org.mifosplatform.portfolio.savings.data.SavingsAccountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/bankdetails")
@Component
@Scope("singleton")
public class ClientsBankDetailsApiResource {
	
	    private final PlatformSecurityContext context;
	    private final ClientBankDetailsReadPlatformService clientBankDetailsReadPlatformService;
	    private final ToApiJsonSerializer<ClientBankDetailsData> toApiJsonSerializer;
	    private final ApiRequestParameterHelper apiRequestParameterHelper;
	    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
	    
	    @Autowired
	    public ClientsBankDetailsApiResource(final PlatformSecurityContext context, 
	    		final ToApiJsonSerializer<ClientBankDetailsData> toApiJsonSerializer,
	            final ClientBankDetailsReadPlatformService clientBankDetailsReadPlatformService,
	            final ApiRequestParameterHelper apiRequestParameterHelper,
	            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService
	            ) {
	        this.context = context;
	        this.toApiJsonSerializer = toApiJsonSerializer;
	        this.apiRequestParameterHelper = apiRequestParameterHelper;
	        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
	        this.clientBankDetailsReadPlatformService=clientBankDetailsReadPlatformService;
	          }
	
	    
	    @POST
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String create(final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .createBankDetails() //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializer.serialize(result);
	    }

	    @PUT
	    @Path("{bankdetailsId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String update(@PathParam("bankdetailsId") final Long bankdetailsId, final String apiRequestBodyAsJson) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .updateBankDetails(bankdetailsId) //
	                .withJson(apiRequestBodyAsJson) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializer.serialize(result);
	    }

	    @DELETE
	    @Path("{bankdetailsId}")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String delete(@PathParam("bankdetailsId") final Long bankdetailsId) {

	        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
	                .deleteBankDetails(bankdetailsId) //
	                .build(); //

	        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

	        return this.toApiJsonSerializer.serialize(result);
	    }
	    @GET	   
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String retrieveOne(@QueryParam("clientId") final Long clientId,@QueryParam("bankId") final Long bankId, @Context final UriInfo uriInfo  ) {
	        this.context.authenticatedUser().validateHasReadPermission(ClientsBankDetailsApiConstants.BANKDETAILS_RESOURCE_NAME);

	        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());

	        ClientBankDetailsData clientBankDetailsData = this.clientBankDetailsReadPlatformService.retrieveClientBankDetails(clientId,bankId);
	        
	        return this.toApiJsonSerializer.serialize(settings, clientBankDetailsData, ClientsBankDetailsApiConstants.CLIENTBANK_DETAILS_RESPONSE_DATA_PARAMETERS);
	    }
   
	@GET
    @Path("{ifsccode}")
	@Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
	public String getBankDetails(@Context final UriInfo uriInfo,@PathParam("ifsccode") final String ifsccode){
		String url = "https://ifsc.firstatom.org/key/x2672hG76671l6xEqJjMi4iEp/ifsc/"+ifsccode;
		StringBuffer response = new StringBuffer();

		URL obj;
		try {
			obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		int responseCode = con.getResponseCode();		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return response.toString();
}
}

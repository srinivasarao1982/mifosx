package org.mifosplatform.batch.command.internal;

import javax.ws.rs.core.UriInfo;

import org.mifosplatform.batch.command.CommandStrategy;
import org.mifosplatform.batch.domain.BatchRequest;
import org.mifosplatform.batch.domain.BatchResponse;
import org.mifosplatform.batch.exception.ErrorHandler;
import org.mifosplatform.batch.exception.ErrorInfo;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CreatePartialLoanCommandStartegy  implements CommandStrategy {
	
	private final PartialLoanApiResource partialLoanApiResource;
	
	 @Autowired
	    public CreatePartialLoanCommandStartegy(final PartialLoanApiResource partialLoanApiResource) {
	        this.partialLoanApiResource = partialLoanApiResource;
	    }

	 @Override
	    public BatchResponse execute(final BatchRequest request, @SuppressWarnings("unused") UriInfo uriInfo) {

	        final BatchResponse response = new BatchResponse();
	        final String responseBody;

	        response.setRequestId(request.getRequestId());
	        response.setHeaders(request.getHeaders());

	        // Try-catch blocks to map exceptions to appropriate status codes
	        try {

	            // Calls 'create' function from 'ClientsApiResource' to create a new
	            // client
	            responseBody = partialLoanApiResource.create(request.getBody());

	            response.setStatusCode(200);
	            // Sets the body of the response after the successful creation of
	            // the client
	            response.setBody(responseBody);

	        } catch (RuntimeException e) {

	            // Gets an object of type ErrorInfo, containing information about
	            // raised exception
	            ErrorInfo ex = ErrorHandler.handler(e);

	            response.setStatusCode(ex.getStatusCode());
	            response.setBody(ex.getMessage());
	        }

	        return response;
	    }

	


}

package org.mifosplatform.portfolio.equifax.service;

import org.mifosplatform.portfolio.equifax.data.RequestBody;

public interface EquifaxClientDetailsService {	 
	public RequestBody getRequestBody(Long clientId );

}

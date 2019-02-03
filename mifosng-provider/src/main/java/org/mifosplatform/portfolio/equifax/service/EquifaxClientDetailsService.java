package org.mifosplatform.portfolio.equifax.service;

import java.util.List;

import org.mifosplatform.portfolio.equifax.data.EquifaxErrorData;
import org.mifosplatform.portfolio.equifax.data.EquifaxRequestData;
import org.mifosplatform.portfolio.equifax.data.EquifaxResponseData;
import org.mifosplatform.portfolio.equifax.data.RequestBody;

public interface EquifaxClientDetailsService {	 
	 RequestBody getRequestBody(Long clientId );
	 List<EquifaxRequestData> getRequestData(Long centerId,String fromDate,String todate );
	 List<EquifaxErrorData> getEquifaxErrorData(Long centerId,String fromDate,String toDate);
	 List<EquifaxResponseData> getEquifaxResponseData(Long centerId,String fromData,String toDate); 
}

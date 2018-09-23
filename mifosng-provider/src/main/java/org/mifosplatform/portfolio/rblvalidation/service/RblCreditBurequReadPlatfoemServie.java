package org.mifosplatform.portfolio.rblvalidation.service;

import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.portfolio.rblvalidation.data.RblCrdeitResponseData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditBureauData;
import org.mifosplatform.portfolio.rblvalidation.data.RblEquifaxData;
import org.mifosplatform.portfolio.rblvalidation.data.RblValidatefileData;

public interface RblCreditBurequReadPlatfoemServie {

	List<RblCreditBureauData> getbreauRequstData(final Long cemterId,final Long clientId,String fromDate,String toDate,final boolean clientcbcheck);
	List<RblCreditBureauData> getbreauResponseData(final Long cemterId,final Long clientId,String fromDate,String toDate,final boolean clientcbcheck);
	List<RblCrdeitResponseData> getbreauErrorData(final Long cemterId,final Long clientId,String fromDate,String toDate,final boolean clientcbcheck);
    List<RblValidatefileData>getValidateFileData(final Long centerId,final String fromDate,final String toDate,final String fileType);
	RblEquifaxData generateDataforCreditBureau(final Long clientId);
    
    
   
}

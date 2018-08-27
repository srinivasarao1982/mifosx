package org.mifosplatform.portfolio.rblvalidation.service;

import java.util.List;

import org.mifosplatform.portfolio.rblvalidation.data.RblCrdeitResponseData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditBureauData;
import org.mifosplatform.portfolio.rblvalidation.data.RblEquifaxData;

public interface RblCreditBurequReadPlatfoemServie {

	List<RblCreditBureauData> getbreauRequstData(final Long cemterId,final Long clientId,String fromDate,String toDate);
	List<RblCreditBureauData> getbreauResponseData(final Long cemterId,final Long clientId,String fromDate,String toDate);
	List<RblCrdeitResponseData> getbreauErrorData(final Long cemterId,final Long clientId,String fromDate,String toDate);

	RblEquifaxData generateDataforCreditBureau(final Long clientId);
}

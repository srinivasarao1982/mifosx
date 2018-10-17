package org.mifosplatform.portfolio.rbl.service;

import org.mifosplatform.portfolio.rbl.data.RblLoanData;

public interface RblLoanReadPlatformService {
	
	RblLoanData retriveRblLoan(final Long customerId);


}

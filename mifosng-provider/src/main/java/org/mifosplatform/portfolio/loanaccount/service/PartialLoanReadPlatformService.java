package org.mifosplatform.portfolio.loanaccount.service;

import java.util.List;

import org.mifosplatform.portfolio.loanaccount.data.PartialLoanData;

public interface PartialLoanReadPlatformService {
	PartialLoanData retrieveTemplate();
    List<PartialLoanData> retrievepartialLoanDetails(final Long parentId) ;
    List<Long>retriveAcceptedMember(final Long parentId,final Long isActive);

}

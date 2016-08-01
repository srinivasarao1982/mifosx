package org.mifosplatform.portfolio.loanaccount.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class DeleteLoanException extends AbstractPlatformDomainRuleException {
   
	public DeleteLoanException() {
        super("error.msg.loan.cannot.delete", "Loan Cannot be deleted once it disbursed." ,
                 " Loan Cannot be deleted once it disbursed.");
    }
}

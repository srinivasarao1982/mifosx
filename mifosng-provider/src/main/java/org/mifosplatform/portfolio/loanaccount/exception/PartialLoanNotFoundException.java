package org.mifosplatform.portfolio.loanaccount.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class PartialLoanNotFoundException extends  AbstractPlatformResourceNotFoundException {

    public PartialLoanNotFoundException(final Long id) {
        super("error.msg.loan.id.invalid", "Partial Loan with identifier " + id + " does not exist", id);
    }
}

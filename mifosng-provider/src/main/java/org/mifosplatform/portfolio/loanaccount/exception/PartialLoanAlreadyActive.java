package org.mifosplatform.portfolio.loanaccount.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class PartialLoanAlreadyActive extends  AbstractPlatformResourceNotFoundException {

    public PartialLoanAlreadyActive(final Long clientId) {
        super("error.msg.partialloan.id.active", "Partial Loan for client with " + clientId + " is Active exist", clientId);
    }
}

package org.mifosplatform.portfolio.account.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class AccountClosedException extends AbstractPlatformDomainRuleException {

    public AccountClosedException() {
        super("error.msg.account.closed.exception", "Cannot undo this Repayment as the account is closed");
    }
}

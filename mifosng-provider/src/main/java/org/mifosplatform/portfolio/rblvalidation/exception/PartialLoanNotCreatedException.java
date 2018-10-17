package org.mifosplatform.portfolio.rblvalidation.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class PartialLoanNotCreatedException extends AbstractPlatformDomainRuleException {

    public PartialLoanNotCreatedException(final String msg) {
        super("error.msg.partialloan.not.created." + "Partital Loan Not Created for " + msg  , msg,msg);
    }
}

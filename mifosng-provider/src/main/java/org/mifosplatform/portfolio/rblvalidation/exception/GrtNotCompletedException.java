package org.mifosplatform.portfolio.rblvalidation.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class GrtNotCompletedException extends AbstractPlatformDomainRuleException {

    public GrtNotCompletedException(final String centerName ) {
        super("error.msg.grt.notCompleted" , " grt is not completed for center"+centerName,centerName);
    }
}

package org.mifosplatform.portfolio.rblvalidation.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class CbCheckStatus extends AbstractPlatformDomainRuleException {

    public CbCheckStatus(final String centerName ) {
        super("error.msg.cbstatus.notChecked" , " Cb Status is not checked for center"+centerName,centerName);
    }
}

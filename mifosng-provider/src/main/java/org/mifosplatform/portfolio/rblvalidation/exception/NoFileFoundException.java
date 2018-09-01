package org.mifosplatform.portfolio.rblvalidation.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class NoFileFoundException extends AbstractPlatformDomainRuleException {

    public NoFileFoundException(final String fromDate,final String toDate) {
        super("error.msg.no.fileFound.date." + "no file present between " + fromDate +" and "+toDate , fromDate,toDate);
    }
}

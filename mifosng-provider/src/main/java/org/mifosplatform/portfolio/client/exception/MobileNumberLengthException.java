package org.mifosplatform.portfolio.client.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class MobileNumberLengthException extends AbstractPlatformDomainRuleException {

    public MobileNumberLengthException(final int length) {
        super("error.msg.mobileNO.length", "Invalid Mobile Number it must be  10 digit length is :" + length +" digit", length);
    }

} 

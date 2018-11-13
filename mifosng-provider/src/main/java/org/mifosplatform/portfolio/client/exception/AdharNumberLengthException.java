package org.mifosplatform.portfolio.client.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class AdharNumberLengthException extends AbstractPlatformDomainRuleException {

    public AdharNumberLengthException(final int length) {
        super("error.msg.adharNumber.length", "Invalid Adhar Number it must be  12 digit length is :" + length +" digit", length);
    }
} 
 

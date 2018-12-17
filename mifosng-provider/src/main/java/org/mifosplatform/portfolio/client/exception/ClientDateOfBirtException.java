package org.mifosplatform.portfolio.client.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class ClientDateOfBirtException  extends AbstractPlatformDomainRuleException {

    public ClientDateOfBirtException(final int length) {
        super("error.msg.Client.dateOfBirth", "Invalid Date Of Birth Age Must be Between 18 to 58 :" + length, length);
    }
} 

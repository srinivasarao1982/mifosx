package org.mifosplatform.portfolio.equifax.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class EquifaxErrorNotFoundException  extends AbstractPlatformDomainRuleException {

    public EquifaxErrorNotFoundException(final Long id) {
        super("error.msg.equifaxerror.notfound", "Equifax Error with dosent exist for  :" + id , id);
    }
} 

package org.mifosplatform.portfolio.equifax.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class EquifaxResponseNotFoundException  extends AbstractPlatformDomainRuleException {

    public EquifaxResponseNotFoundException(final Long id) {
        super("error.msg.equifaxresponse.notfound", "Equifax Response with dosent exist for  :" + id , id);
    }
} 

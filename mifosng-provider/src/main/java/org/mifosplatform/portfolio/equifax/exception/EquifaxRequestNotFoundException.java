package org.mifosplatform.portfolio.equifax.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class EquifaxRequestNotFoundException extends AbstractPlatformDomainRuleException {

    public EquifaxRequestNotFoundException(final Long id) {
        super("error.msg.equifaxresquest.notfound", "Equifax Request with dosent exist for  :" + id , id);
    }
} 

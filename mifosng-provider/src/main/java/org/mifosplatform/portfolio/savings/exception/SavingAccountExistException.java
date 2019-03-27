package org.mifosplatform.portfolio.savings.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class SavingAccountExistException extends AbstractPlatformDomainRuleException {

    public SavingAccountExistException(Long clintId) {
        super("error.msg.saving.account.exist","clients have alreay"+clintId+" open Saving Account" , clintId);
    }

}
 

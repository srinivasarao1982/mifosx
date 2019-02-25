package org.mifosplatform.portfolio.savings.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class SavingAccountExistException extends AbstractPlatformDomainRuleException {

    public SavingAccountExistException() {
        super("error.msg.saving.account.exist","clients have alreay  open Saving Account" , "clients have alreay  open Saving Account");
    }

}
 

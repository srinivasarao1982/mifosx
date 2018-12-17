package org.mifosplatform.organisation.rbi.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class BankNotFoundException extends AbstractPlatformResourceNotFoundException{
	public BankNotFoundException(final String ifsc) {
        super("error.msg.bank.details.not.found", "Bank with ifsc code " + ifsc + " does not exist", ifsc);
    }
}

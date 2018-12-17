package org.mifosplatform.organisation.rbi.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class ErrorInBankDetailsUpload extends AbstractPlatformResourceNotFoundException{
	public ErrorInBankDetailsUpload() {
        super("error.msg.error.in.bank.details.upload", "Error in bank details upload","");
    }
}

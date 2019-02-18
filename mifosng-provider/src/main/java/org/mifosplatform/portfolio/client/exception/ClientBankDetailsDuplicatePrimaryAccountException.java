package org.mifosplatform.portfolio.client.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class ClientBankDetailsDuplicatePrimaryAccountException extends AbstractPlatformResourceNotFoundException{

	public ClientBankDetailsDuplicatePrimaryAccountException(final String accountNumber){
		super( "Customer has already primary account with bank account number: " + accountNumber, "Customer has already primary account with bank account number: " + accountNumber, accountNumber);
	}
}

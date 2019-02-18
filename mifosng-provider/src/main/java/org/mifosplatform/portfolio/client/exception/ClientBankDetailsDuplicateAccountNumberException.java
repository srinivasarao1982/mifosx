package org.mifosplatform.portfolio.client.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class ClientBankDetailsDuplicateAccountNumberException  extends AbstractPlatformResourceNotFoundException{

	 public ClientBankDetailsDuplicateAccountNumberException(final String accountNumber) {
	        super( "Duplicate Account Number : " + accountNumber, "Duplicate Account Number: " + accountNumber, accountNumber);
	    }
}

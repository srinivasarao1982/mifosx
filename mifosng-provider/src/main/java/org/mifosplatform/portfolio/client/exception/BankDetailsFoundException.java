package org.mifosplatform.portfolio.client.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class BankDetailsFoundException  extends AbstractPlatformResourceNotFoundException {

    public BankDetailsFoundException(final Long id) {
        super("error.msg.bankdetail.id.invalid", "BankDeatails with identifier " + id + " does not exist", id);
    }
}

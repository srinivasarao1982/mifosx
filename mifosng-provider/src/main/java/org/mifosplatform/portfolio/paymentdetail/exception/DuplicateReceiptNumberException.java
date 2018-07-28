package org.mifosplatform.portfolio.paymentdetail.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class DuplicateReceiptNumberException extends AbstractPlatformDomainRuleException {

    public DuplicateReceiptNumberException(final String receiptNumber) {
        super("error.msg.duplicate.receipt.number"," Duplicate Receipt Number ", receiptNumber);
    }
}

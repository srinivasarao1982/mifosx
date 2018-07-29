package org.mifosplatform.portfolio.paymentdetail.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class ReceiptNumberMandatoryException extends AbstractPlatformDomainRuleException {

    public ReceiptNumberMandatoryException(final String receiptNumber) {
        super("error.msg.receipt.number.mandatory"," Receipt Number is Mandatory " +receiptNumber, receiptNumber);
    }
}

package org.mifosplatform.portfolio.paymentdetail.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class ReceiptNumberMustBeNumericException extends AbstractPlatformDomainRuleException {

    public ReceiptNumberMustBeNumericException(final String receiptNumber) {
        super("error.msg.receipt.number.numeric","Receipt Number must be numeric  "+receiptNumber, receiptNumber);
    }
} 

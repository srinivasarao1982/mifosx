package org.mifosplatform.portfolio.rbl.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class MandatoryParameterException extends AbstractPlatformResourceNotFoundException {

    public MandatoryParameterException(final String mandatoryparametr) {
        super("error.msg.parameter.mandatory.invalid", mandatoryparametr +"canNot be blank or null", mandatoryparametr);
    }
}

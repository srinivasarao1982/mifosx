package org.mifosplatform.portfolio.rbl.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class MustbeNumericException extends AbstractPlatformResourceNotFoundException {

    public MustbeNumericException(final String mandatoryparametr) {
        super("error.msg.parameter.mustbenumeric.invalid", mandatoryparametr +"Must be Numeric", mandatoryparametr);
    }

}

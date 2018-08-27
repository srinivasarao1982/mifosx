package org.mifosplatform.portfolio.rbl.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class MustbeBetweenException  extends AbstractPlatformResourceNotFoundException {

    public MustbeBetweenException(final String mandatoryparametr,int i,int j) {
        super("error.msg.mustbe.between .invalid", mandatoryparametr +"must be between"+i+"and"+j, mandatoryparametr);
    }
}

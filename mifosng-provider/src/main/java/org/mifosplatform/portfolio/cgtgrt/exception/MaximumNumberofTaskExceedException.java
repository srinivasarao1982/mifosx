package org.mifosplatform.portfolio.cgtgrt.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class MaximumNumberofTaskExceedException extends AbstractPlatformResourceNotFoundException {
    public MaximumNumberofTaskExceedException(final Long fieldValue ) {
        super("error.msg.nooftaskexceed", "no of task Increase" , fieldValue);
    }
} 
 

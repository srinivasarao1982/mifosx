package org.mifosplatform.portfolio.cgtgrt.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class TaskIsInActiveStateException extends AbstractPlatformResourceNotFoundException {
    public TaskIsInActiveStateException(final String fieldValue ) {
        super("error.msg.task.is .Active", fieldValue+ "Task is in Active State for this Center"  , fieldValue);
    }
} 

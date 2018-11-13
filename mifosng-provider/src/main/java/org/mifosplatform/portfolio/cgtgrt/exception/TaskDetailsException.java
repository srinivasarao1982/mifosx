package org.mifosplatform.portfolio.cgtgrt.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class TaskDetailsException extends AbstractPlatformResourceNotFoundException {
    public TaskDetailsException(final Long id) {
        super("error.msg.task.id.invalid", "Task Details Cannnot be null" + id ,  id);
    }
}

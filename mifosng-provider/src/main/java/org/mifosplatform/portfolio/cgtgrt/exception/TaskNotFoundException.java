package org.mifosplatform.portfolio.cgtgrt.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class TaskNotFoundException  extends AbstractPlatformResourceNotFoundException {
    public TaskNotFoundException(final Long id) {
        super("error.msg.task.id.invalid", "Task with identifier " + id + " does not exist", id);
    }
}

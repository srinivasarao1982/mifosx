package org.mifosplatform.portfolio.cgtgrt.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class TaskOrderException  extends AbstractPlatformResourceNotFoundException {
    public TaskOrderException(final String taskType) {
        super("error.msg.task.order.invalid", "Order of Task is MissMatch create confingure no of taskType " + taskType , taskType);
    }
}

package org.mifosplatform.portfolio.cgtgrt.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class TaskConfigurationExistException extends AbstractPlatformResourceNotFoundException {
    public TaskConfigurationExistException(final String taskType) {
        super("error.msg.task.configurationexist", "Task Configuration  is already exist for center of tasktype" + taskType , taskType );
    }
}

package org.mifosplatform.portfolio.cgtgrt.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class TaskConfigurationNotExist extends AbstractPlatformResourceNotFoundException {
    public TaskConfigurationNotExist(final String taskType) {
        super("error.msg.task.configurationnotexist", "Task Configuration  is not exist for  tasktype" + taskType , taskType );
    }
}

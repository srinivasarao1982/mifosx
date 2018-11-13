package org.mifosplatform.portfolio.cgtgrt.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.cgtgrt.service.TaskWriteplatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@CommandType(entity = "TASK", action = "CREATE")
public class CreateTaskCommandHandler implements NewCommandSourceHandler {

    private final TaskWriteplatformService taskWriteplatformService;

    @Autowired
    public CreateTaskCommandHandler(final TaskWriteplatformService taskWriteplatformService) {
        this.taskWriteplatformService = taskWriteplatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.taskWriteplatformService.createTask( command);
    }
}

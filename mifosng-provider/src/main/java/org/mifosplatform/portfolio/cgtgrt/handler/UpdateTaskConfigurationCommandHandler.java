package org.mifosplatform.portfolio.cgtgrt.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.cgtgrt.service.Taskconfigurationwriteplatformservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@CommandType(entity = "TASKCONFIGURATION", action = "UPDATE")
public class UpdateTaskConfigurationCommandHandler implements NewCommandSourceHandler {

    private final Taskconfigurationwriteplatformservice taskconfigurationwriteplatformservice;

    @Autowired
    public UpdateTaskConfigurationCommandHandler(final Taskconfigurationwriteplatformservice taskconfigurationwriteplatformservice) {
        this.taskconfigurationwriteplatformservice = taskconfigurationwriteplatformservice;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.taskconfigurationwriteplatformservice.updateTaskConfiguration(command.entityId(), command);
    }
}


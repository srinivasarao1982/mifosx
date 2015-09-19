package org.nirantara.client.ext.service;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = "COCLIENT", action = "CREATE")
public class CreateCoClientCommandHandler implements NewCommandSourceHandler {

	private final CoClientWritePlatformService coClientWritePlatformService;

    @Autowired
    public CreateCoClientCommandHandler(final CoClientWritePlatformService coClientWritePlatformService) {
        this.coClientWritePlatformService = coClientWritePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.coClientWritePlatformService.createCoClient(command);
    }
}

package org.mifosplatform.portfolio.rbl.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.rbl.service.RblGroupWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = "RBLGROUP", action = "DELETE")
public class DeleteRblGroupCommandHandler implements NewCommandSourceHandler{
	 private final RblGroupWritePlatformService rblGroupWritePlatformService;

	    @Autowired
	    public DeleteRblGroupCommandHandler(final RblGroupWritePlatformService rblGroupWritePlatformService) {
	        this.rblGroupWritePlatformService = rblGroupWritePlatformService;
	    }

	    @Transactional
	    @Override
	    public CommandProcessingResult processCommand(final JsonCommand command) {

	        return this.rblGroupWritePlatformService.deleteRblGroup(command.getGroupId(),command);
	    }
}

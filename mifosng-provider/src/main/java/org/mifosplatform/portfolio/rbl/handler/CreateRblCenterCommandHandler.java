package org.mifosplatform.portfolio.rbl.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.rbl.service.RblCenterWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@CommandType(entity = "RBLCENTER", action = "CREATE")
public class CreateRblCenterCommandHandler implements NewCommandSourceHandler {
	 private final RblCenterWritePlatformService rblCenterWritePlatformService;

	    @Autowired
	    public CreateRblCenterCommandHandler(final RblCenterWritePlatformService rblCenterWritePlatformService) {
	        this.rblCenterWritePlatformService = rblCenterWritePlatformService;
	    }

	    @Transactional
	    @Override
	    public CommandProcessingResult processCommand(final JsonCommand command) {

	        return this.rblCenterWritePlatformService.createRblCenter(command);
	    }
}
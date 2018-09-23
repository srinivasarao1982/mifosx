package org.mifosplatform.portfolio.rbl.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.rbl.service.RblLoanWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = "RBLLOAN", action = "UPDATE")
public class UpdateRblLoanCommandHandler implements NewCommandSourceHandler {
	 private final RblLoanWritePlatformService rblLoanWritePlatformService;

	    @Autowired
	    public UpdateRblLoanCommandHandler(final RblLoanWritePlatformService rblLoanWritePlatformService) {
	        this.rblLoanWritePlatformService = rblLoanWritePlatformService;
	    }

	    @Transactional
	    @Override
	    public CommandProcessingResult processCommand(final JsonCommand command) {

	        return this.rblLoanWritePlatformService.updateRblLoan(command.entityId(),  command);
	    } 

}

package org.mifosplatform.portfolio.rbl.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.rbl.service.RblCustomerWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@CommandType(entity = "RBLCUSTOMER", action = "UPDATE")
public class UpdateRblCustomerCommandHandler  implements NewCommandSourceHandler {
	 private final RblCustomerWritePlatformService rblCustomerWritePlatformService;

	    @Autowired
	    public UpdateRblCustomerCommandHandler(final RblCustomerWritePlatformService rblCustomerWritePlatformService) {
	        this.rblCustomerWritePlatformService = rblCustomerWritePlatformService;
	    }

	    @Transactional
	    @Override
	    public CommandProcessingResult processCommand(final JsonCommand command) {

	        return this.rblCustomerWritePlatformService.updateRblCustomer(command.entityId(),  command);
	    }

}
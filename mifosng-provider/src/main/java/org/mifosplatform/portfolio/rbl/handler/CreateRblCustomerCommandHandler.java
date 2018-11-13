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
@CommandType(entity = "RBLCUSTOMER", action = "CREATE")
public class CreateRblCustomerCommandHandler implements NewCommandSourceHandler {
	 private final RblCustomerWritePlatformService rblCustomerWritePlatformService;

	    @Autowired
	    public CreateRblCustomerCommandHandler(final RblCustomerWritePlatformService rblCustomerWritePlatformService) {
	        this.rblCustomerWritePlatformService = rblCustomerWritePlatformService;
	    }

	    @Transactional
	    @Override
	    public CommandProcessingResult processCommand(final JsonCommand command) {

	        return this.rblCustomerWritePlatformService.createRblCustomer(command);
	    }
}

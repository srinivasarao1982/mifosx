package org.mifosplatform.portfolio.client.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.client.service.ClientbankDetailsWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@CommandType(entity = "BANKDETAILS", action = "CREATE")
public class CreateBankDetailCommandHandler implements NewCommandSourceHandler{
	
	public ClientbankDetailsWritePlatformService clientbankDetailsWritePlatformService;
	
	 @Autowired
	    public CreateBankDetailCommandHandler(final ClientbankDetailsWritePlatformService clientbankDetailsWritePlatformService) {
	        this.clientbankDetailsWritePlatformService = clientbankDetailsWritePlatformService;
	    }

	    @Transactional
	    @Override
	    public CommandProcessingResult processCommand(final JsonCommand command) {

	        return this.clientbankDetailsWritePlatformService.registerBankDetails(command);
	    }

}

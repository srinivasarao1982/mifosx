package org.mifosplatform.portfolio.rbl.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface RblCustomerWritePlatformService {
	CommandProcessingResult createRblCustomer(JsonCommand command);

    CommandProcessingResult updateRblCustomer(Long rblcustomerId, JsonCommand command);

    CommandProcessingResult deleteRblCustomer(Long rblcustomerId, JsonCommand command);


}

package org.mifosplatform.portfolio.rbl.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface RblGroupWritePlatformService {
	CommandProcessingResult createRblGroup(JsonCommand command);

    CommandProcessingResult updateRblGroup(Long rblcustomerId, JsonCommand command);

    CommandProcessingResult deleteRblGroup(Long rblcustomerId, JsonCommand command);


}

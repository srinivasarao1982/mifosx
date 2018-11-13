package org.mifosplatform.portfolio.rbl.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface RblCenterWritePlatformService {
	

	CommandProcessingResult createRblCenter(JsonCommand command);

    CommandProcessingResult updateRblCenter(Long rblcenterId, JsonCommand command);

    CommandProcessingResult deleteRblCenter(Long rblcenterId, JsonCommand command);


}

package org.mifosplatform.portfolio.cgtgrt.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface Taskconfigurationwriteplatformservice {

	CommandProcessingResult createTaskConfiguration(JsonCommand command);

    CommandProcessingResult updateTaskConfiguration(Long taskconfigurationId, JsonCommand command);

}

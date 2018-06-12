package org.mifosplatform.portfolio.cgtgrt.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface TaskWriteplatformService {

	CommandProcessingResult createTask(JsonCommand command);

    CommandProcessingResult updateTask(Long taskId, JsonCommand command);

}

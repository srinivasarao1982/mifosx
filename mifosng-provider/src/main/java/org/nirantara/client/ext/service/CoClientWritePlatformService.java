package org.nirantara.client.ext.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface CoClientWritePlatformService {

	CommandProcessingResult createCoClient(final JsonCommand command);

}

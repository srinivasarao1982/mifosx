package org.mifosplatform.portfolio.loanaccount.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface PartialLoanWriteplatformService {
	
	CommandProcessingResult createpartialLoan(JsonCommand command);

    CommandProcessingResult updatePartialLoan(Long clientId,Long groupId, JsonCommand command);

    CommandProcessingResult deletePartialLoan(Long clientId,Long groupId, JsonCommand command);


}

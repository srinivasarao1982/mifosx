package org.mifosplatform.portfolio.rbl.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface RblLoanWritePlatformService {
	CommandProcessingResult createRblLoan(JsonCommand command);

    CommandProcessingResult updateRblLoan(Long rblLoanId, JsonCommand command);

    CommandProcessingResult deleteRblRblLoan(Long rblLoanId, JsonCommand command);


}

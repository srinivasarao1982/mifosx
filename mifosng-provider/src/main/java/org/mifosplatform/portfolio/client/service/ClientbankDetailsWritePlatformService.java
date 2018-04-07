package org.mifosplatform.portfolio.client.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface ClientbankDetailsWritePlatformService {
	CommandProcessingResult registerBankDetails(JsonCommand command);

    CommandProcessingResult updateBankDetails(Long bankdetailsId, JsonCommand command);

    CommandProcessingResult deleteBankDetails(Long bankdetailsId);

}

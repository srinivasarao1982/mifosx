package org.mifosplatform.portfolio.client.service;

import org.mifosplatform.portfolio.client.data.ClientBankDetailsData;

public interface ClientBankDetailsReadPlatformService {
	ClientBankDetailsData retrieveClientBankDetails(Long clientId, Long bankdetailsId);
}

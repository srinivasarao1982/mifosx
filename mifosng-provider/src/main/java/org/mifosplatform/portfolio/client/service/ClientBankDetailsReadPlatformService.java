package org.mifosplatform.portfolio.client.service;

import java.util.List;

import org.mifosplatform.portfolio.client.data.ClientBankDetailsData;

public interface ClientBankDetailsReadPlatformService {
	ClientBankDetailsData retrieveClientBankDetails(Long bankdetailId);
	List<ClientBankDetailsData> retriveAllBankDetailsByClientId(Long clientId);
	ClientBankDetailsData retriveClientBankDetailsTemplate(String accountType);
}

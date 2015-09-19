package org.nirantara.client.ext.service;

import org.mifosplatform.portfolio.client.domain.Client;
import org.nirantara.client.ext.data.CoapplicantDetailsData;

public interface CoapplicantReadPlatformService {

	CoapplicantDetailsData retrieveCoapplicantDetailsDataTemplate(final Client client);

}

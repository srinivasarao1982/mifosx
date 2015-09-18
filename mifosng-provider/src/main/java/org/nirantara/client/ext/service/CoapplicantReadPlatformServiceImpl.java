package org.nirantara.client.ext.service;

import java.util.ArrayList;
import java.util.Collection;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.portfolio.client.api.ClientApiConstants;
import org.nirantara.client.ext.api.CoapplicantApiConstants;
import org.nirantara.client.ext.data.AddressExtData;
import org.nirantara.client.ext.data.CoapplicantData;
import org.nirantara.client.ext.data.CoapplicantDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoapplicantReadPlatformServiceImpl implements CoapplicantReadPlatformService {

	private final CodeValueReadPlatformService codeValueReadPlatformService;
	
	@Autowired
	public CoapplicantReadPlatformServiceImpl(final CodeValueReadPlatformService codeValueReadPlatformService){
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}
	
	@Override
	public CoapplicantDetailsData retrieveCoapplicantDetailsDataTemplate() {

		Collection<CodeValueData> spouseRelationShip = new ArrayList<>(
				this.codeValueReadPlatformService
						.retrieveCodeValuesByCode(CoapplicantApiConstants.SPOUSE_RELATIONSHIP));

		Collection<CodeValueData> addressTypes = new ArrayList<>(
				this.codeValueReadPlatformService
						.retrieveCodeValuesByCode(ClientApiConstants.CLIENT_ADDRESS_TYPE));

		Collection<CodeValueData> state = new ArrayList<>(
				this.codeValueReadPlatformService
						.retrieveCodeValuesByCode(ClientApiConstants.STATE));

		Collection<CodeValueData> district = new ArrayList<>(
				this.codeValueReadPlatformService
						.retrieveCodeValuesByCode(ClientApiConstants.DISTRICT));

		final CoapplicantData coapplicantData = null;
		final AddressExtData addressExtData = null;

		return CoapplicantDetailsData.fromCoapplicantDetailsData(
				spouseRelationShip, addressTypes, state, district,
				coapplicantData, addressExtData);
	}

}

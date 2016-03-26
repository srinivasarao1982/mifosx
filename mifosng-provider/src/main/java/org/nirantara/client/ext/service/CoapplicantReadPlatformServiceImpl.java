package org.nirantara.client.ext.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.portfolio.client.api.ClientApiConstants;
import org.mifosplatform.portfolio.client.domain.Client;
import org.nirantara.client.ext.api.CoapplicantApiConstants;
import org.nirantara.client.ext.data.CoapplicantData;
import org.nirantara.client.ext.data.CoapplicantDetailsData;
import org.nirantara.client.ext.domain.Coapplicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoapplicantReadPlatformServiceImpl implements CoapplicantReadPlatformService {

    private final CodeValueReadPlatformService codeValueReadPlatformService;

    @Autowired
    public CoapplicantReadPlatformServiceImpl(final CodeValueReadPlatformService codeValueReadPlatformService) {
        this.codeValueReadPlatformService = codeValueReadPlatformService;
    }

    @Override
    public CoapplicantDetailsData retrieveCoapplicantDetailsDataTemplate(final Client client) {

        Collection<CodeValueData> spouseRelationShip = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(CoapplicantApiConstants.SPOUSE_RELATIONSHIP));

        Collection<CodeValueData> addressTypes = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.CLIENT_ADDRESS_TYPE));

        Collection<CodeValueData> state = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.STATE));

        Collection<CodeValueData> district = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.DISTRICT));

        Collection<CodeValueData> salutations = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.CLIENT_SALUTATION));

        Collection<CodeValueData> genderOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.GENDER));

        Collection<CodeValueData> identityProof = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.IDENTITY_PROOF));

        Collection<CodeValueData> addressProof = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.ADDRESS_PROOF));

        List<CoapplicantData> coapplicantData = new ArrayList<>();
        if (client != null) {
            if (client.coapplicant() != null) {
                for (Coapplicant ca : client.coapplicant()) {
                    coapplicantData.add(CoapplicantData.formCoapplicantData(ca));
                }
            }
        }

        return CoapplicantDetailsData.fromCoapplicantDetailsData(spouseRelationShip, addressTypes, state, district, coapplicantData,
                salutations, genderOptions, identityProof, addressProof);
    }

}

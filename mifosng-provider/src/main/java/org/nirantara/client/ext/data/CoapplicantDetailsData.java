package org.nirantara.client.ext.data;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;

public class CoapplicantDetailsData {

    // Template
    private final Collection<CodeValueData> spouseRelationShip;
    private final Collection<CodeValueData> addressTypes;
    private final Collection<CodeValueData> state;
    private final Collection<CodeValueData> district;
    private final Collection<CodeValueData> salutations;
    private final Collection<CodeValueData> genderOptions;
    private final Collection<CodeValueData> identityProof;
    private final Collection<CodeValueData> addressProof;

    // Data
    private final List<CoapplicantData> coapplicantData;

    public static CoapplicantDetailsData fromCoapplicantDetailsData(final Collection<CodeValueData> spouseRelationShip,
            final Collection<CodeValueData> addressTypes, final Collection<CodeValueData> state, final Collection<CodeValueData> district,
            final List<CoapplicantData> coapplicantData, final Collection<CodeValueData> salutations,
            final Collection<CodeValueData> genderOptions, final Collection<CodeValueData> identityProof,
            final Collection<CodeValueData> addressProof) {
        return new CoapplicantDetailsData(spouseRelationShip, addressTypes, state, district, coapplicantData, salutations, genderOptions,
                identityProof, addressProof);
    }

    private CoapplicantDetailsData(final Collection<CodeValueData> spouseRelationShip, final Collection<CodeValueData> addressTypes,
            final Collection<CodeValueData> state, final Collection<CodeValueData> district, final List<CoapplicantData> coapplicantData,
            final Collection<CodeValueData> salutations, final Collection<CodeValueData> genderOptions,
            final Collection<CodeValueData> identityProof, final Collection<CodeValueData> addressProof) {
        this.spouseRelationShip = spouseRelationShip;
        this.addressTypes = addressTypes;
        this.state = state;
        this.district = district;
        this.coapplicantData = coapplicantData;
        this.salutations = salutations;
        this.genderOptions = genderOptions;
        this.identityProof = identityProof;
        this.addressProof = addressProof;
    }

    public Collection<CodeValueData> getSpouseRelationShip() {
        return spouseRelationShip;
    }

    public Collection<CodeValueData> getAddressTypes() {
        return addressTypes;
    }

    public Collection<CodeValueData> getState() {
        return state;
    }

    public Collection<CodeValueData> getDistrict() {
        return district;
    }

    public List<CoapplicantData> getCoapplicantData() {
        return coapplicantData;
    }

    public Collection<CodeValueData> getSalutations() {
        return this.salutations;
    }

    public Collection<CodeValueData> getGenderOptions() {
        return this.genderOptions;
    }

    public Collection<CodeValueData> getIdentityProof() {
        return this.identityProof;
    }

    public Collection<CodeValueData> getAddressProof() {
        return this.addressProof;
    }
}

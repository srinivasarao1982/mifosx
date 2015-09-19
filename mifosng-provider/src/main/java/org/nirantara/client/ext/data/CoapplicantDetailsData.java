package org.nirantara.client.ext.data;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;

public class CoapplicantDetailsData {
	
	//Template
	private final Collection<CodeValueData> spouseRelationShip;
	private final Collection<CodeValueData> addressTypes;
	private final Collection<CodeValueData> state;
	private final Collection<CodeValueData> district;
	
	//Data
	private final List<CoapplicantData> coapplicantData;
	
	public static CoapplicantDetailsData fromCoapplicantDetailsData(
			final Collection<CodeValueData> spouseRelationShip,
			final Collection<CodeValueData> addressTypes,
			final Collection<CodeValueData> state,
			final Collection<CodeValueData> district,
			final List<CoapplicantData> coapplicantData) {
		return new CoapplicantDetailsData(spouseRelationShip,addressTypes,state,district,coapplicantData);
	}
	
	private CoapplicantDetailsData(
			final Collection<CodeValueData> spouseRelationShip,
			final Collection<CodeValueData> addressTypes,
			final Collection<CodeValueData> state,
			final Collection<CodeValueData> district,
			final List<CoapplicantData> coapplicantData) {
		
		this.spouseRelationShip = spouseRelationShip;
		this.addressTypes = addressTypes;
		this.state = state;
		this.district = district;		
		this.coapplicantData = coapplicantData;
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
}

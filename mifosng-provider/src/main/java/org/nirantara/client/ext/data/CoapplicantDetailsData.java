package org.nirantara.client.ext.data;

import java.util.Collection;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;

public class CoapplicantDetailsData {
	
	//Template
	private final Collection<CodeValueData> spouseRelationShip;
	private final Collection<CodeValueData> addressTypes;
	private final Collection<CodeValueData> state;
	private final Collection<CodeValueData> district;
	
	//Data
	private final CoapplicantData coapplicantData;
	private final AddressExtData addressExtData;
	
	public static CoapplicantDetailsData fromCoapplicantDetailsData(
			final Collection<CodeValueData> spouseRelationShip,
			final Collection<CodeValueData> addressTypes,
			final Collection<CodeValueData> state,
			final Collection<CodeValueData> district,
			final CoapplicantData coapplicantData,
			final AddressExtData addressExtData) {
		return new CoapplicantDetailsData(spouseRelationShip,addressTypes,state,district,coapplicantData, addressExtData);
	}
	
	private CoapplicantDetailsData(
			final Collection<CodeValueData> spouseRelationShip,
			final Collection<CodeValueData> addressTypes,
			final Collection<CodeValueData> state,
			final Collection<CodeValueData> district,
			final CoapplicantData coapplicantData,
			final AddressExtData addressExtData) {
		
		this.spouseRelationShip = spouseRelationShip;
		this.addressTypes = addressTypes;
		this.state = state;
		this.district = district;		
		this.coapplicantData = coapplicantData;
		this.addressExtData = addressExtData;
	}
}

/**
 * 
 */
package org.mifosplatform.portfolio.client.data;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.nirantara.client.ext.data.AddressExtData;
import org.nirantara.client.ext.data.ClientDataExt;
import org.nirantara.client.ext.data.FamilyDetailsExtData;

/**
 * @author Srinivas Rao
 *
 */
public class ClientDetailedData {
	
	private final ClientData clientBasicDetails;
	private final ClientAdditionalDetails additionalDetails;
	private final ClientAddress address;
	private final ClientFamilyDetails familyDetails;
	private final ClientCFADetails cfaDetails;
	private final AgriOccupationDetails agriOccupation;
	private final ClientIdentifierData identifier;
	private final ClientKYCData kycDetails;
	
	// ext template
	private  final Collection<CodeValueData> salutation;
	private  final Collection<CodeValueData> maritalStatus;
	private  final Collection<CodeValueData> profession;
	private  final Collection<CodeValueData> educationQualification;
	private  final Collection<CodeValueData> annualIncome;
	private  final Collection<CodeValueData> landHolding;
	private  final Collection<CodeValueData> houseType;
	private  final Collection<CodeValueData> state;
	private  final Collection<CodeValueData> district;
	private  final Collection<CodeValueData> identityProof;
	private  final Collection<CodeValueData> addressProof;
	private  final Collection<CodeValueData> familyrelationShip;
	private  final Collection<CodeValueData> familyOccupation;
	private  final Collection<CodeValueData> yesOrNo;
	private  final Collection<CodeValueData> cfaOccupation;
	private  final Collection<CodeValueData> externalLoanstatus;
	
	//Nirantara
	private final ClientDataExt clientDataExt;
	private final List<AddressExtData> addressExtData;
	private final List<FamilyDetailsExtData> familyDetailsExtData;
	private final List<ClientIdentifierData> clientIdentifierData;
	
	public ClientDetailedData(ClientData clientBasicDetails,
			ClientAdditionalDetails additionalDetails, ClientAddress address,
			ClientFamilyDetails familyDetails, ClientCFADetails cfaDetails,
			AgriOccupationDetails agriOccupation,
			ClientIdentifierData identifier, ClientKYCData kycDetails,
			Collection<CodeValueData> salutation,
			Collection<CodeValueData> maritalStatus,
			Collection<CodeValueData> profession,
			Collection<CodeValueData> educationQualification,
			Collection<CodeValueData> annualIncome,
			Collection<CodeValueData> landHolding,
			Collection<CodeValueData> houseType,
			Collection<CodeValueData> state,
			Collection<CodeValueData> district,
			Collection<CodeValueData> identityProof,
			Collection<CodeValueData> addressProof,
			Collection<CodeValueData> familyrelationShip,
			Collection<CodeValueData> familyOccupation,
			Collection<CodeValueData> yesOrNo,
			Collection<CodeValueData> cfaOccupation,
			Collection<CodeValueData> externalLoanstatus,
			final ClientDataExt clientDataExt,
			final List<AddressExtData> addressExtData,
			final List<FamilyDetailsExtData> familyDetailsExtData, 
			final List<ClientIdentifierData> clientIdentifierData) {
		super();
		this.clientBasicDetails = clientBasicDetails;
		this.additionalDetails = additionalDetails;
		this.address = address;
		this.familyDetails = familyDetails;
		this.cfaDetails = cfaDetails;
		this.agriOccupation = agriOccupation;
		this.identifier = identifier;
		this.kycDetails = kycDetails;
		this.salutation = salutation;
		this.maritalStatus = maritalStatus;
		this.profession = profession;
		this.educationQualification = educationQualification;
		this.annualIncome = annualIncome;
		this.landHolding = landHolding;
		this.houseType = houseType;
		this.state = state;
		this.district = district;
		this.identityProof = identityProof;
		this.addressProof = addressProof;
		this.familyrelationShip = familyrelationShip;
		this.familyOccupation = familyOccupation;
		this.yesOrNo = yesOrNo;
		this.cfaOccupation = cfaOccupation;
		this.externalLoanstatus = externalLoanstatus;
		this.clientDataExt = clientDataExt;
		this.addressExtData = addressExtData;
		this.familyDetailsExtData = familyDetailsExtData;
		this.clientIdentifierData = clientIdentifierData;
	}

	public ClientData getClientBasicDetails() {
		return clientBasicDetails;
	}

	public ClientAdditionalDetails getAdditionalDetails() {
		return additionalDetails;
	}

	public ClientAddress getAddress() {
		return address;
	}

	public ClientFamilyDetails getFamilyDetails() {
		return familyDetails;
	}

	public ClientCFADetails getCfaDetails() {
		return cfaDetails;
	}

	public AgriOccupationDetails getAgriOccupation() {
		return agriOccupation;
	}

	public ClientIdentifierData getIdentifier() {
		return identifier;
	}

	public ClientKYCData getKycDetails() {
		return kycDetails;
	}

	public Collection<CodeValueData> getSalutation() {
		return salutation;
	}

	public Collection<CodeValueData> getMaritalStatus() {
		return maritalStatus;
	}

	public Collection<CodeValueData> getProfession() {
		return profession;
	}

	public Collection<CodeValueData> getEducationQualification() {
		return educationQualification;
	}

	public Collection<CodeValueData> getAnnualIncome() {
		return annualIncome;
	}

	public Collection<CodeValueData> getLandHolding() {
		return landHolding;
	}

	public Collection<CodeValueData> getHouseType() {
		return houseType;
	}

	public Collection<CodeValueData> getState() {
		return state;
	}

	public Collection<CodeValueData> getDistrict() {
		return district;
	}

	public Collection<CodeValueData> getIdentityProof() {
		return identityProof;
	}

	public Collection<CodeValueData> getAddressProof() {
		return addressProof;
	}

	public Collection<CodeValueData> getFamilyrelationShip() {
		return familyrelationShip;
	}

	public Collection<CodeValueData> getFamilyOccupation() {
		return familyOccupation;
	}

	public Collection<CodeValueData> getYesOrNo() {
		return yesOrNo;
	}

	public Collection<CodeValueData> getCfaOccupation() {
		return cfaOccupation;
	}

	public Collection<CodeValueData> getExternalLoanstatus() {
		return externalLoanstatus;
	}

	public ClientDataExt clientDataExt() {
		return clientDataExt;
	}

	public List<AddressExtData> addressExtData() {
		return addressExtData;
	}

	public List<FamilyDetailsExtData> familyDetailsExtData() {
		return this.familyDetailsExtData;
	}
	
	public List<ClientIdentifierData> clientIdentifierData() {
		return this.clientIdentifierData;
	}
	
}

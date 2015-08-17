package org.nirantara.client.ext.domain;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ClientExtAssembler {

	private final FromJsonHelper fromApiJsonHelper;
	private final CodeValueRepositoryWrapper codeValueRepository;

	@Autowired
	public ClientExtAssembler(final FromJsonHelper fromApiJsonHelper,
			final CodeValueRepositoryWrapper codeValueRepository) {
		this.fromApiJsonHelper = fromApiJsonHelper;
		this.codeValueRepository = codeValueRepository;
	}

	public ClientExt assembleClientExt(final JsonCommand command,
			final Client newClient) {

		final JsonObject formDataObject = new JsonParser()
				.parse(command.json()).getAsJsonObject();

		final JsonElement element = formDataObject.get("clientExt");

		final Long salutationId = this.fromApiJsonHelper.extractLongNamed(
				"salutation", element);
		CodeValue salutationCodeValue = null;
		if (salutationId != null) {
			salutationCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(salutationId);
		}

		final Long maritalStatusId = this.fromApiJsonHelper.extractLongNamed(
				"maritalStatus", element);
		CodeValue maritalStatusCodeValue = null;
		if (maritalStatusId != null) {
			maritalStatusCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(maritalStatusId);
		}

		final Long professionId = this.fromApiJsonHelper.extractLongNamed(
				"profession", element);
		CodeValue professionCodeValue = null;
		if (professionId != null) {
			professionCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(professionId);
		}

		final String professionOthers = this.fromApiJsonHelper
				.extractStringNamed("professionOthers", element);
		;

		final Long educationalQualificationId = this.fromApiJsonHelper
				.extractLongNamed("educationalQualification", element);
		CodeValue educationalQualificationCodeValue = null;
		if (educationalQualificationId != null) {
			educationalQualificationCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(educationalQualificationId);
		}

		final Long annualIncomeId = this.fromApiJsonHelper.extractLongNamed(
				"annualIncome", element);
		CodeValue annualIncomeCodeValue = null;
		if (annualIncomeId != null) {
			annualIncomeCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(annualIncomeId);
		}

		final Long landholdingId = this.fromApiJsonHelper.extractLongNamed(
				"landholding", element);
		CodeValue landholdingCodeValue = null;
		if (landholdingId != null) {
			landholdingCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(landholdingId);
		}

		final Long houseTypeId = this.fromApiJsonHelper.extractLongNamed(
				"houseType", element);
		CodeValue houseTypeCodeValue = null;
		if (houseTypeId != null) {
			houseTypeCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(houseTypeId);
		}

		final String aadhaarNo = this.fromApiJsonHelper.extractStringNamed(
				"aadhaarNo", element);

		final String panNo = this.fromApiJsonHelper.extractStringNamed("panNo",
				element);

		final Long panFormId = this.fromApiJsonHelper.extractLongNamed(
				"panForm", element);
		CodeValue panFormCodeValue = null;
		if (panFormId != null) {
			panFormCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(panFormId);
		}

		final String nregaNo = this.fromApiJsonHelper.extractStringNamed(
				"nregaNo", element);

		return ClientExt.createFrom(newClient, salutationCodeValue,
				maritalStatusCodeValue, professionCodeValue, professionOthers,
				educationalQualificationCodeValue, annualIncomeCodeValue,
				landholdingCodeValue, houseTypeCodeValue, aadhaarNo, panNo,
				panFormCodeValue, nregaNo);
	}

	public Address assembleAddress(JsonElement element, Client newClient) {

		final Long addressTypeId = this.fromApiJsonHelper.extractLongNamed(
				"addressType", element);
		CodeValue addressTypeCodeValue = null;
		if (addressTypeId != null) {
			addressTypeCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(addressTypeId);
		}

		final String houseNo = this.fromApiJsonHelper.extractStringNamed(
				"houseNo", element);

		final String streetNo = this.fromApiJsonHelper.extractStringNamed(
				"streetNo", element);

		final String areaLocality = this.fromApiJsonHelper.extractStringNamed(
				"areaLocality", element);

		final String landmark = this.fromApiJsonHelper.extractStringNamed(
				"landmark", element);

		final String villageTown = this.fromApiJsonHelper.extractStringNamed(
				"villageTown", element);

		final String taluka = this.fromApiJsonHelper.extractStringNamed(
				"taluka", element);

		final Long districtId = this.fromApiJsonHelper.extractLongNamed(
				"district", element);
		CodeValue districtCodeValue = null;
		if (districtId != null) {
			districtCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(districtId);
		}

		final Long stateId = this.fromApiJsonHelper.extractLongNamed("state",
				element);
		CodeValue stateCodeValue = null;
		if (stateId != null) {
			stateCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(stateId);
		}

		final Integer pinCode = this.fromApiJsonHelper
				.extractIntegerWithLocaleNamed("pinCode", element);

		final Long landlineNo = this.fromApiJsonHelper.extractLongNamed(
				"landlineNo", element);

		final Long mobileNo = this.fromApiJsonHelper.extractLongNamed(
				"mobileNo", element);

		return Address.createFrom(newClient, addressTypeCodeValue, houseNo,
				streetNo, areaLocality, landmark, villageTown, taluka,
				districtCodeValue, stateCodeValue, pinCode, landlineNo,
				mobileNo);
	}

	public FamilyDetails assembleFamilyDetails(final JsonElement element,
			final Client newClient) {

		final String firstname = this.fromApiJsonHelper.extractStringNamed(
				"firstname", element);

		final String middlename = this.fromApiJsonHelper.extractStringNamed(
				"middlename", element);

		final String lastname = this.fromApiJsonHelper.extractStringNamed(
				"lastname", element);

		final Long relationshipId = this.fromApiJsonHelper.extractLongNamed(
				"relationship", element);
		CodeValue relationshipCodeValue = null;
		if (relationshipId != null) {
			relationshipCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(relationshipId);
		}

		final Long genderId = this.fromApiJsonHelper.extractLongNamed("gender",
				element);
		CodeValue genderCodeValue = null;
		if (genderId != null) {
			genderCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(genderId);
		}

		final LocalDate dataOfBirth = this.fromApiJsonHelper
				.extractLocalDateNamed("dataOfBirth", element);

		final Integer age = this.fromApiJsonHelper
				.extractIntegerWithLocaleNamed("age", element);

		final Long occupationId = this.fromApiJsonHelper.extractLongNamed(
				"occupation", element);
		CodeValue occupationCodeValue = null;
		if (occupationId != null) {
			occupationCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(occupationId);
		}

		final Long educationalStatusId = this.fromApiJsonHelper
				.extractLongNamed("educationalStatus", element);
		CodeValue educationalStatusCodeValue = null;
		if (educationalStatusId != null) {
			educationalStatusCodeValue = this.codeValueRepository
					.findOneWithNotFoundDetection(educationalStatusId);
		}

		return FamilyDetails.createFrom(newClient, firstname, middlename,
				lastname, relationshipCodeValue, genderCodeValue, dataOfBirth,
				age, occupationCodeValue, educationalStatusCodeValue);
	}

}

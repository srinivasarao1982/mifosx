package org.nirantara.client.ext.domain;

import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;

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
		final JsonElement element = command.parsedJson();

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

		return ClientExt.createFrom(newClient, maritalStatusCodeValue,
				professionCodeValue, professionOthers,
				educationalQualificationCodeValue, annualIncomeCodeValue,
				landholdingCodeValue, houseTypeCodeValue, aadhaarNo, panNo,
				panFormCodeValue, nregaNo);
	}

}

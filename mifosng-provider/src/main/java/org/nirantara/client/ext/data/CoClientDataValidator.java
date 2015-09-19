package org.nirantara.client.ext.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.InvalidJsonException;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.nirantara.client.ext.api.CoapplicantApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;

@Component
public final class CoClientDataValidator {

	private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public CoClientDataValidator(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }

	public void validateForCreateCoClient(final String json) {
		
		if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final JsonElement element = this.fromApiJsonHelper.parse(json);
		
        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();

        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
                .resource(CoapplicantApiConstants.CO_APPLICANT_RESOURCE_NAME);
        
        final Long clientId = this.fromApiJsonHelper.extractLongNamed(CoapplicantApiConstants.clientId, element);
        baseDataValidator.reset().parameter(CoapplicantApiConstants.clientId).value(clientId).notNull().integerGreaterThanZero();
        
        throwExceptionIfValidationWarningsExist(dataValidationErrors);
	}
	
	private void throwExceptionIfValidationWarningsExist(
			final List<ApiParameterError> dataValidationErrors) {
		if (!dataValidationErrors.isEmpty()) {
			throw new PlatformApiDataValidationException(dataValidationErrors);
		}
	}
}

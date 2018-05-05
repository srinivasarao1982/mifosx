package org.mifosplatform.portfolio.loanaccount.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.InvalidJsonException;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
@Component
public class PartialLoanDataValidator {
	
	private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public PartialLoanDataValidator(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }
	
	 public void validateForCreateBankDetails(final JsonCommand command) {

	        final String json = command.json();

	        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

	        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
	        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, PartialLoanApiConstant.PARTIALLOAN_REQUEST_DATA_PARAMETERS);

	        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
	        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
	                .resource(PartialLoanApiConstant.PARTIALLOAN_RESOURCE_NAME);
	        final JsonElement element = command.parsedJson();

	          
	        final long clientId = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.clientidparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.clientidparamname).value(clientId).notNull()
	                .longGreaterThanZero();

	        final long groupId = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.groupidparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.groupidparamname).value(groupId).notNull()
	                .longGreaterThanZero();

	        final long productId = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.productidparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.productidparamname).value(productId).notNull()
	                .longGreaterThanZero();

	        final long loanofficerId = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.loanofficeridparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.loanofficeridparamname).value(loanofficerId).notNull()
	                .longGreaterThanZero();
	        
	        final long principal = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.principalparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.principalparamname).value(principal).notNull()
	                .longGreaterThanZero();	        
	        
	        
	      	        throwExceptionIfValidationWarningsExist(dataValidationErrors);

	    }
	    
	    public void validateUpdateBankDetails(final JsonCommand command) {

	    	final String json = command.json();

	        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

	        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
	        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, PartialLoanApiConstant.PARTIALLOAN_REQUEST_DATA_PARAMETERS);

	        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
	        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
	                .resource(PartialLoanApiConstant.PARTIALLOAN_RESOURCE_NAME);
	        final JsonElement element = command.parsedJson();

	        
	        
	        final long clientId = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.clientidparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.clientidparamname).value(clientId).notNull()
	                .longGreaterThanZero();

	        final long groupId = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.groupidparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.groupidparamname).value(groupId).notNull()
	                .longGreaterThanZero();

	        final long productId = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.productidparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.productidparamname).value(productId).notNull()
	                .longGreaterThanZero();
	        
	        final long status = this.fromApiJsonHelper.extractLongNamed(PartialLoanApiConstant.statusparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.statusparamname).value(status).notNull()
	                .longGreaterThanZero();

	        final String remark = this.fromApiJsonHelper.extractStringNamed(PartialLoanApiConstant.remarkparamname, element);
	        baseDataValidator.reset().parameter(PartialLoanApiConstant.remarkparamname).value(remark).notBlank();

	      	        throwExceptionIfValidationWarningsExist(dataValidationErrors);

	    }

	    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
	        if (!dataValidationErrors.isEmpty()) {
	            //
	            throw new PlatformApiDataValidationException(dataValidationErrors);
	        }
	    }

}

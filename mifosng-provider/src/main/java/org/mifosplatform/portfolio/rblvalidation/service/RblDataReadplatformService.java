package org.mifosplatform.portfolio.rblvalidation.service;

import java.util.List;

import org.mifosplatform.portfolio.rblvalidation.data.RblCenterValidateData;
import org.mifosplatform.portfolio.rblvalidation.data.RblGroupValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblLoanValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblSavingValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblclientDatValidation;

public interface RblDataReadplatformService {
	
	List<RblCenterValidateData>readRblCenterData(Long centerId);
	List<RblclientDatValidation>readRblClientData(String clientId);
	List<RblGroupValidationData>readRblGroupData(String centerId);
	List<RblLoanValidationData>readRblLoanData(String clientId);
	List<RblSavingValidationData>readRblSavingData(String clientId);

	

}

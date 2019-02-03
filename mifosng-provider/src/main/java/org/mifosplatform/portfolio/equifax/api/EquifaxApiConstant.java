package org.mifosplatform.portfolio.equifax.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EquifaxApiConstant {
	public static final String EQUIFAX_RESOURCE_NAME = "equifax";

	private Long centerId;	
    private Long clientId;	
    private String inquiryPurpose;	
    private String firstName;  
    private String mobilePhone;
    
	public static final String localeParamName = "locale";
	public static final String dateFormatParamName = "dateFormat";

	public static final String centerIdparamname = "centerId";
	public static final String clientIdparamname = "clientId";
	public static final String inquiryPurposeParamname = "inquiryPurpose";
	public static final String firstNameparamnam = "firstName";
	public static final String mobilePhoneNameparanname = "mobilePhone";
	public static final String errorparamaname = "error";
	
	public static final Set<String> EQUIFAX_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,
			dateFormatParamName, centerIdparamname, clientIdparamname, inquiryPurposeParamname, firstNameparamnam,
			mobilePhoneNameparanname, errorparamaname));

	public static final Set<String>  EQUIFAXRESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,
			dateFormatParamName, centerIdparamname, clientIdparamname, inquiryPurposeParamname, firstNameparamnam,
			mobilePhoneNameparanname, errorparamaname));
	
	public static final Set<String>  EQUIFAXERROR_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,
			dateFormatParamName, centerIdparamname, clientIdparamname, inquiryPurposeParamname, firstNameparamnam,
			mobilePhoneNameparanname, errorparamaname));;

	
}

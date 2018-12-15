package org.mifosplatform.organisation.rbi.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BankApiConstants {
	
	public static final String BANKDETAILS_RESOURCE_NAME = "bankdetails";
	public static final String localeParamName = "locale";
    public static final String dateFormatParamName = "dateFormat";
    
    //Request Parameters
    public static final String ifsccodeparamname="ifsccode";
    public static final String micrcodeparamname="micrcode";
    public static final String banknameparamname="bankname";
    public static final String branchnameparamname="branchname";
    public static final String branchaddressparamname="branchaddress";
    public static final String bankcontactparamname = "bankcontact";
    public static final String bankcityparamname = "bankcity";
    public static final String bankdisctrictparamname = "bankdisctrict";
    public static final String bankstateparamname = "bankstate";
    
    //Response Parameter
    public static final Set<String> BANK_DETAILS_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(banknameparamname,ifsccodeparamname,micrcodeparamname,branchnameparamname,branchaddressparamname,bankcontactparamname,bankcityparamname,bankdisctrictparamname,bankstateparamname));
}

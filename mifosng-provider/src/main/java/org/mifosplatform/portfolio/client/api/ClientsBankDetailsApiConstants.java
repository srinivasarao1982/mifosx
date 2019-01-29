package org.mifosplatform.portfolio.client.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClientsBankDetailsApiConstants {
    public static final String CLIENTSBANKDETAILS_RESOURCE_NAME = "clientbankdetails";
    public static final String BANKDETAILS_RESOURCE_NAME = "bankdetails";
    public static final String CLIENTBANKDETAIL_ACCOUNT_TYPE = "ClientBankDetailAccountType";

    
    public static final String localeParamName = "locale";
    public static final String dateFormatParamName = "dateFormat";

    
    //request Parameter
    public static final String beneficiarynameparamname ="beneficiaryname";
    public static final String ifsccodeparamname="ifsccode";
    public static final String micrcodeparamname="micrcode";
    public static final String banknameparamname="bankname";
    public static final String branchnameparamname="branchname";
    public static final String branchaddressparamname="branchaddress";
    public static final String lasttransactiondateparamname="lasttransactiondate";
    public static final String lasttransactionamountparamname="lasttransactionamount";
    public static final String accountnumberparamname="accountnumber";
    public static final String createdbyparamname="createdby";
    public static final String createddateparamaname="createddate";
    public static final String lastmodifybyparamname="lastmodifyby";
    public static final String lastmodifieddateparamname="lastmodifieddate";
    public static final String clientidparamname="clientId";
    public static final String beneficiarynameparamname1="beneficiaryname1";
    public static final String accountnumberparamname1="accountnumber1";
    public static final String lastmodifiedDateparamname="lastmodifiedDate";
    public static final String isPrimaryAccount = "isPrimaryAccount";
    public static final String accountTypeParamName = "accountType";
    public static final String accountTypeListParamName = "accountTypesList";
    
    //response Paramater
    public static final String idparamname="id";
    
    
    public static final Set<String> CLIENTBANK_DETAILS_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, beneficiarynameparamname, ifsccodeparamname, branchnameparamname, branchaddressparamname, lasttransactiondateparamname,lasttransactionamountparamname,micrcodeparamname,banknameparamname, accountnumberparamname, createdbyparamname, createddateparamaname,
    		lastmodifybyparamname, lastmodifieddateparamname,clientidparamname,beneficiarynameparamname1,accountnumberparamname1,isPrimaryAccount,accountTypeParamName));
  
    public static final Set<String> CLIENTBANK_DETAILS_UPDATE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(idparamname,localeParamName,dateFormatParamName, beneficiarynameparamname, ifsccodeparamname, branchnameparamname, branchaddressparamname, lasttransactiondateparamname,lasttransactionamountparamname, micrcodeparamname,banknameparamname,accountnumberparamname, createdbyparamname, createddateparamaname,
    		lastmodifybyparamname, lastmodifiedDateparamname,lastmodifieddateparamname,clientidparamname,beneficiarynameparamname1,accountnumberparamname1,isPrimaryAccount,accountTypeParamName));
  
    public static final Set<String> CLIENTBANK_DETAILS_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(idparamname,localeParamName,dateFormatParamName, beneficiarynameparamname, ifsccodeparamname, branchnameparamname, branchaddressparamname, lasttransactiondateparamname,lasttransactionamountparamname,micrcodeparamname,banknameparamname, accountnumberparamname, createdbyparamname, createddateparamaname,
    		lastmodifybyparamname, lastmodifieddateparamname,clientidparamname,isPrimaryAccount,accountTypeParamName,accountTypeListParamName));
  


}

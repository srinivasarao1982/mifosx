package org.mifosplatform.portfolio.rbl.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RblLoanDetailsApiConstant {
	

    public static final String RBLLOAN_RESOURCE_NAME = "rblloan";


    
    public static final String localeParamName = "locale";
    public static final String dateFormatParamName = "dateFormat";


	public static final String pslcodeparamName="pslcode";
	public static final String topuploanfl="topuploanflag";
	public static final String hospitalcashparamname="hosiptalcash";
	public static final String prepaidchargeparamname="prpaidcharge";
	public static final String loanIdparamname="loanId";
	public static final String idparamname="id";
	
	  public static final Set<String> RBLLOAN_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, pslcodeparamName, topuploanfl, hospitalcashparamname, prepaidchargeparamname, loanIdparamname,idparamname));
	  public static final Set<String> RBLLOAN_UPDATE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, pslcodeparamName, topuploanfl, hospitalcashparamname, prepaidchargeparamname, loanIdparamname,idparamname));
	  public static final Set<String> RBLLOAN_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, pslcodeparamName, topuploanfl, hospitalcashparamname, prepaidchargeparamname, loanIdparamname,idparamname));
    			  
	   	  
}

package org.mifosplatform.portfolio.rbl.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RblCustomerDetailsApiConstant {
    public static final String RBLCUSTOMER_RESOURCE_NAME = "rblcustomer";

	public static final String clintIdparamnam="clientId";
	public static final  String pensioncardnoparamName="pensioncard";
	public static final String addaharseedingconstantparamName="adharseedingconstant";
	public static final String healthparamName="health";
	public static final String languagparamName="language";
	public static final String cardIssueflagparamName="caedissueflag";
	public static final String cbcheckparamName="cbchck";
	public static final String renewalFlag="renwalflag";
	public static final String mothertoungparamname="mothertoung";
	public static final String gurdainnameparamName="gurdainName";
	public static final String gurdiandateofBirt="gurdiandateofBirth";
	public static final String gurdiangenderparamname="gurdiangender";
	public static final String mothertoungeparamname="mothertoung";
	public static final String spouseNameparamName="spouseName";
	public static final String spousedateofbirtparamname="spousedateofbIrt";
	
	public static final String localeParamName = "locale";
    public static final String dateFormatParamName = "dateFormat";

	  public static final Set<String> RBLCUSTOMER_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, clintIdparamnam, pensioncardnoparamName, addaharseedingconstantparamName, healthparamName, 
			  languagparamName,cardIssueflagparamName,cbcheckparamName,renewalFlag,mothertoungparamname,gurdainnameparamName,gurdiandateofBirt,gurdiangenderparamname,
			  mothertoungeparamname,spouseNameparamName,spousedateofbirtparamname));

	  public static final Set<String> RBLCUSTOMER_UPDATE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, clintIdparamnam, pensioncardnoparamName, addaharseedingconstantparamName, healthparamName, 
			  languagparamName,cardIssueflagparamName,cbcheckparamName,renewalFlag,mothertoungparamname,gurdainnameparamName,gurdiandateofBirt,gurdiangenderparamname,
			  mothertoungeparamname,spouseNameparamName,spousedateofbirtparamname));

	  public static final Set<String> RBLCUSTOMER_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, clintIdparamnam, pensioncardnoparamName, addaharseedingconstantparamName, healthparamName, 
			  languagparamName,cardIssueflagparamName,cbcheckparamName,renewalFlag,mothertoungparamname,gurdainnameparamName,gurdiandateofBirt,gurdiangenderparamname,
			  mothertoungeparamname,spouseNameparamName,spousedateofbirtparamname));

	

}

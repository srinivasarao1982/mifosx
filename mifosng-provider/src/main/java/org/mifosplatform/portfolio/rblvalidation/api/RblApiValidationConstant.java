package org.mifosplatform.portfolio.rblvalidation.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class RblApiValidationConstant {
	
	 public static final String RBLDETAILS_RESOURCE_NAME = "rbldetails";

	  public static final String RBLLOAN_RESOURCE_NAME = "rbl";
	  public static final String STATUSCODEVALUEPARAM_NAME = "CB Check Status";


	    
	    public static final String localeParamName = "locale";
	    public static final String dateFormatParamName = "dateFormat";

	public static final String barcodeparamname="barcodeNo";
	public static final String externalIdparamname="externalId";
	public static final String loanAmountParamname ="loanAmount";
	public static final String isRenewalLoanparamnam="isRenewalLoan";
	public static final String customerNameparanname="customerName";
	public static final String titleparamaname ="title";
	public static final String nameparamname="name";
	public static final String relationparamnam ="relation";
	public static final String line1paramname ="line1";
	public static final String line2paramname ="line2";
	public static final String line3paramname ="line3";
	public static final String citycodeparamname ="cityCode";
	public static final String cityNameparamname ="cityName";
	public static final String stateCodeparamname ="stateCode";
	public static final String pinparamname ="pin";
	public static final String operatingRegionparamname ="operatingRegionCode";
	public static final String operatingRegionParamname ="operatingRegionName";
	public static final String dateofbirthparamname ="dateOfBirth";
	public static final String branchcodeparamname ="branchCode";
	public static final String branchNameparamname ="branchName";

	//Response
	public static final  String  clientIdParamname ="clientId";
	public static final  String  requestIdparamname="requestUid";
	public static final  String serviceIdparamname ="serviceName";
	public static final  String chhanelIdparamaName ="chhanelId";
	public static final  String corIdparamname ="corId";
	public static final  String creditApprovdparamname ="credit_Approved";
	public static final  String rasonparamname="reason";
	public static final  String eliagableAmountparamname ="eligible_Amount";
	public static final  String jsonparamname ="json";
	
	//File Related Response
	public LocalDate fileDate;
	public String fileName;
	public String filePath;
	public String fileType;
	
	public static final String  fileDateParamname="filedate";
	public static final String fileNameparamname="fileName";
	public static final String  filepathparamname ="filePath";
	public static final String fileTypeparamname="fileType";
	
	 public static final Set<String> RBL_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, barcodeparamname,externalIdparamname,loanAmountParamname,isRenewalLoanparamnam,customerNameparanname,titleparamaname,nameparamname,relationparamnam,line1paramname,
				line2paramname,line3paramname,citycodeparamname,cityNameparamname,stateCodeparamname,pinparamname,operatingRegionparamname,operatingRegionParamname,
				dateofbirthparamname,branchcodeparamname,branchNameparamname ));
	  
	    public static final Set<String> RBLRESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName,  clientIdParamname,requestIdparamname,serviceIdparamname,chhanelIdparamaName,corIdparamname,creditApprovdparamname,rasonparamname,eliagableAmountparamname,jsonparamname));
	    public static final Set<String> RBLFILERESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName,  fileDateParamname,fileNameparamname,filepathparamname,fileTypeparamname));
 	  

}

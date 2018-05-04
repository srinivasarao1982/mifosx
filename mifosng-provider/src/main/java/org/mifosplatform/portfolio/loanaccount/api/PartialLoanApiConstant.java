package org.mifosplatform.portfolio.loanaccount.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

public class PartialLoanApiConstant {
    public static final String PARTIALLOANDETAILS_RESOURCE_NAME = "partialloandetails";

    public static final String PARTIALLOAN_RESOURCE_NAME = "partialloan";
    public static final String STATUSCODEVALUEPARAM_NAME = "CB Check Status";


    
    public static final String localeParamName = "locale";
    public static final String dateFormatParamName = "dateFormat";

    
    //request Parameter
    public static final String groupidparamname ="groupId";
    public static final String clientidparamname="clientId";
    public static final String productidparamname="productId";
    public static final String loanofficeridparamname="loanOfficerId";
    public static final String principalparamname="principal";
    public static final String fixedemiAmountparamname="fixedEmiAmount";
    public static final String loanpurposeparamname="loanPurposeId";
    public static final String rpdonumberparamname="externalId";
    public static final String officeidparamname="officeId";
    public static final String submitteddateparamname="submittedOnDate";
    public static final String loantenureparamname="loanTenure";
    public static final String statusparamname="status";
    public static final String remarkparamname="remark";
    public static final String isactiveparamname="isactive";
    public static final String freshimportparamname="freshImport";
   
    //response Paramater
    public static final String idparamname="id";
    public static final String clientNameparamName="clientName";
    public static final String responserpdonumberparamname="rpdoNumber";
    public static final String loanAmountParamName="loanAmount";
    public static final String subitteddateparamname="submittedDate";
    public static final String statusdataparamname="StatusData";
    public static final String isActiveparamaname="isActive";
    public static final String acceptedclientparamname="acceptedclientsId";
    
    public static final Set<String> PARTIALLOAN_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, groupidparamname, clientidparamname, productidparamname, loanofficeridparamname, principalparamname,fixedemiAmountparamname,loanpurposeparamname,rpdonumberparamname, officeidparamname, submitteddateparamname, loantenureparamname,
    		statusparamname, remarkparamname,clientidparamname,isactiveparamname,freshimportparamname));
  
    public static final Set<String> PARTIALLOAN_UPDATE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName,idparamname, groupidparamname, clientidparamname, productidparamname, loanofficeridparamname, principalparamname,fixedemiAmountparamname,loanpurposeparamname,rpdonumberparamname, officeidparamname, submitteddateparamname, loantenureparamname,
    		statusparamname, remarkparamname,clientidparamname,isactiveparamname,freshimportparamname));
  
    public static final Set<String> PARTIALLOAN_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName,idparamname, responserpdonumberparamname, clientidparamname, loanAmountParamName, loanofficeridparamname, principalparamname,fixedemiAmountparamname,loanpurposeparamname,rpdonumberparamname, officeidparamname, subitteddateparamname, statusdataparamname,
    		statusdataparamname,acceptedclientparamname, remarkparamname,clientidparamname,clientNameparamName,isActiveparamaname,isActiveparamaname));
  




}

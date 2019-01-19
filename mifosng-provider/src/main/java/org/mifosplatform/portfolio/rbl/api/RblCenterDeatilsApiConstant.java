package org.mifosplatform.portfolio.rbl.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RblCenterDeatilsApiConstant {
	
    public static final String RBLCENTER_RESOURCE_NAME = "rblCenter";

  
	 public static final String centerIdparamname="centerId";
	 public static final String maximumindividualParamName = "maximumindividual";
     public static final String meetingtimeParamName = "meetingTime";
     public static final String housenumberParamname = "houseNo";
     public static final String streetnumberParamname="streetNumber";
     public static final String arealocalityParamName="areaLocality";
     public static final String landmarkparamname="landmark";
     public static final String villageparamname="village";
     public static final String talukparamname="taluk";
     public static final String districtparamname="district";
     public static final String pincodeparamName="pincode";
     public static final String descriptionparamname="dscription";
     public static final String stateparamname="state";
 	public static final String distancefrombranch="distancefrombranch";

     

	 public static final String localeParamName = "locale";
	    public static final String dateFormatParamName = "dateFormat";

		  public static final Set<String> RBLCENTER_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, centerIdparamname, maximumindividualParamName, meetingtimeParamName, housenumberParamname, 
				  streetnumberParamname,arealocalityParamName,landmarkparamname,villageparamname,talukparamname,districtparamname,pincodeparamName,descriptionparamname,
				  stateparamname,distancefrombranch));
		  public static final Set<String> RBLCENTER_UPDATE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, centerIdparamname, maximumindividualParamName, meetingtimeParamName, housenumberParamname, 
				  streetnumberParamname,arealocalityParamName,landmarkparamname,villageparamname,talukparamname,districtparamname,pincodeparamName,descriptionparamname,
				  stateparamname,distancefrombranch));
		  public static final Set<String> RBLCENTER_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, centerIdparamname, maximumindividualParamName, meetingtimeParamName, housenumberParamname, 
				  streetnumberParamname,arealocalityParamName,landmarkparamname,villageparamname,talukparamname,districtparamname,pincodeparamName,descriptionparamname,
				  stateparamname,distancefrombranch));

	
}

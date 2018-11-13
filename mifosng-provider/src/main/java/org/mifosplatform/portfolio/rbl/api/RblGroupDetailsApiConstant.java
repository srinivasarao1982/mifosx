package org.mifosplatform.portfolio.rbl.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RblGroupDetailsApiConstant {
    public static final String RBLGROUP_RESOURCE_NAME = "RBLGROUP";

	
	 public static final String localeParamName = "locale";
	    public static final String dateFormatParamName = "dateFormat";

	public static final String maximumcenterparamname="maxcenter";
	public static final String grouptypeparamname="groupType";
	public static final String minnumberparamname="minNumber";
	public static final String maxnumberparamname="maxnumber";
	public static final String distancefrombranch="distancefrombranch";
	public static final String meetingtimeparamname="meetingTime";
	public static final String groupIdparamname="groupId";
	
	
	public static final Set<String> RBLGROUP_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, maximumcenterparamname, grouptypeparamname, minnumberparamname, maxnumberparamname, distancefrombranch,
			meetingtimeparamname,groupIdparamname));
	public static final Set<String> RBLGROUP_UPDATE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, maximumcenterparamname, grouptypeparamname, minnumberparamname, maxnumberparamname, distancefrombranch,
			meetingtimeparamname,groupIdparamname));
	
	public static final Set<String> RBLGROUP_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, maximumcenterparamname, grouptypeparamname, minnumberparamname, maxnumberparamname, distancefrombranch,
			meetingtimeparamname,groupIdparamname));
	
	 	
}

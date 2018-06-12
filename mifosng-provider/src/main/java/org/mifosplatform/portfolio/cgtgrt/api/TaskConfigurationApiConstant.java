package org.mifosplatform.portfolio.cgtgrt.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskConfigurationApiConstant {

	public static final String TASKCONFIGURATION_RESOURCE_NAME = "taskconfiguration";


    public static final String TASK_RESOURCE_NAME = "taskconfiguration";
    public static final String TASKDESCRIPTION = "Task Description";
    public static final String TASKCOMPLETEDBY="Task Completed By";

    
    public static final String localeParamName = "locale";
    public static final String dateFormatParamName = "dateFormat";

    
    //request Parameter
    public static final String tasktypeIdparamname ="tasktypeId";
    public static final String noofdaysparamname="noofdays";
    public static final String completedbyparamname="completedby";
    public static final String centertypeparamname="centertype";
    public static final String completeByparamname ="completedBy";
    public static final String noofdayparamname="noofdays";
    public static final String centerTypeParamname="centerType";
    public static final String taskconfigurationIdparamname="taskconfigurationId";
    public static final String tasktypeIdsparamname="tasktypeId";
    public static final String completedByIdsparamname="completedById";
    public static final String completedbysparamname="completedby";
    public static final String nooftaskparamname="nooftask";
   // public static final String centerparamname="centertype";
        

    public static final Set<String> TASKCONFIGURATION_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, tasktypeIdparamname, noofdaysparamname,completedbyparamname, centertypeparamname,nooftaskparamname));
	
    public static final Set<String> TASKCONFIGURATION_UPDATE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, tasktypeIdparamname, noofdaysparamname,completedbyparamname, centertypeparamname,completeByparamname,noofdayparamname,centerTypeParamname
    		,taskconfigurationIdparamname,tasktypeIdsparamname,completedByIdsparamname,completedbysparamname,nooftaskparamname));

    public static final Set<String> TASKCONFIGURATION_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, tasktypeIdparamname, noofdaysparamname,completedbyparamname, centertypeparamname,nooftaskparamname));

}

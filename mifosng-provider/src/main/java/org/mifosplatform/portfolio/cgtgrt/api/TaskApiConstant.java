package org.mifosplatform.portfolio.cgtgrt.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskApiConstant {
	    public static final String TASKDETAILS_RESOURCE_NAME = "taskdetails";


	    public static final String TASK_RESOURCE_NAME = "task";
	    public static final String TASKTYPE = "Task type";
	    public static final String TASKTIMEOPTIONS = "Task Time Options";

	    
	    public static final String localeParamName = "locale";
	    public static final String dateFormatParamName = "dateFormat";

	    
	    //request Parameter
	    public static final String staffIdparamname ="staffId";
	    public static final String officeidparamname="officeId";
	    public static final String centerIdparamname="centerId";
	    public static final String attendencedetailsIdparamname="attendencedetails";
	    public static final String tasktypeparamname="tasktype";
	    public static final String taskstatusparamname="taskstatus";
	    public static final String noteparamname="note";
	    public static final String expectedcompliteddate="expectedCompletedDate";	
	    public static final String taskextradeatilparamname="taskextradetails";
	    public static final String taskcompleteddateparamname="completedDate";
	    public static final String taskcreatedDate="taskcreatedDate";
	    public static final String taskdetailIdparamname="taskdetailsId";
	    public static final String idparamName="id";
	    public static final String taskTypeIdparamname ="taskTypeId";
	    public static final String staffIdIdparamname ="staffId";	
	    public static final String taskidparamname="taskId";  
	    public static final String taskTypeParmName= "taskType";
	    public static final String staffNameParamName="staffName";
	    public static final String taskcreatedParamName="taskcreatedDate";
	    public static final String statusparamName="status";
	    public static final String taskdetailsParamName="taskdetailsData";
	    public static final String clientAttendenceDataparamname="clientAttendenceData";
	    public static final String taskStartTimeParamName="taskStartTime";
	    public static final String tasklEndTimeParamName="taskEndTime";
	    public static final String plannedDateparamname="plannedDate";
	    //response Paramater
	   
	    public static final String staffNameparamname="staffName";
	    public static final String clientNameparamname="clientName";
	    public static final String tasknameparamname="taskname";
	    
	    public static final Set<String> TASK_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, staffIdparamname, officeidparamname,centerIdparamname, attendencedetailsIdparamname, tasktypeparamname, taskstatusparamname,noteparamname,expectedcompliteddate,taskextradeatilparamname,taskcompleteddateparamname,taskStartTimeParamName,tasklEndTimeParamName,plannedDateparamname));
	    		
	    public static final Set<String> TASK_UPDATE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, staffIdparamname, officeidparamname,centerIdparamname, attendencedetailsIdparamname, tasktypeparamname, taskstatusparamname,noteparamname,expectedcompliteddate,taskextradeatilparamname,taskcompleteddateparamname,taskidparamname,taskTypeParmName,taskTypeIdparamname,staffNameParamName,taskcreatedParamName,statusparamName,taskdetailsParamName,clientAttendenceDataparamname,taskStartTimeParamName,tasklEndTimeParamName,plannedDateparamname));

	    public static final Set<String> TASK_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName, staffIdparamname, officeidparamname,centerIdparamname, attendencedetailsIdparamname, tasktypeparamname, taskstatusparamname,noteparamname,expectedcompliteddate,staffNameparamname,clientNameparamname,tasknameparamname,taskextradeatilparamname,taskcompleteddateparamname,taskdetailIdparamname,taskcreatedDate,idparamName,taskTypeIdparamname,staffIdIdparamname,taskStartTimeParamName,tasklEndTimeParamName,plannedDateparamname));




}

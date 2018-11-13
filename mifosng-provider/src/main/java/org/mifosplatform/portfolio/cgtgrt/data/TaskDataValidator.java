package org.mifosplatform.portfolio.cgtgrt.data;

import static org.mifosplatform.portfolio.meeting.MeetingApiConstants.attendanceTypeParamName;
import static org.mifosplatform.portfolio.meeting.MeetingApiConstants.clientIdParamName;
import static org.mifosplatform.portfolio.meeting.MeetingApiConstants.clientsAttendanceParamName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.ApiParameterError;
import org.mifosplatform.infrastructure.core.data.DataValidatorBuilder;
import org.mifosplatform.infrastructure.core.exception.InvalidJsonException;
import org.mifosplatform.infrastructure.core.exception.PlatformApiDataValidationException;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.portfolio.cgtgrt.api.TaskApiConstant;
import org.mifosplatform.portfolio.cgtgrt.api.TaskConfigurationApiConstant;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskClientAttendence;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskConfiguration;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskConfigurationRepository;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskDetails;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskRepository;
import org.mifosplatform.portfolio.cgtgrt.domain.Tasks;
import org.mifosplatform.portfolio.cgtgrt.exception.FieldCannotbeBlankException;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskConfigurationNotExist;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskDetailsException;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskOrderException;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.exception.ClientNotFoundException;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.group.domain.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
@Component
public class TaskDataValidator {
	private final FromJsonHelper fromApiJsonHelper;
    private final CodeValueRepositoryWrapper codeValueRepositoryWrapper;
    private final ClientRepositoryWrapper clientRepositoryWrapper;
    private final TaskRepository taskRepository;
    private final TaskConfigurationRepository taskConfigurationRepository;
	private final GroupRepository groupRepository;

    



    @Autowired
    public TaskDataValidator(final FromJsonHelper fromApiJsonHelper,final CodeValueRepositoryWrapper codeValueRepositoryWrapper,
    		final ClientRepositoryWrapper clientRepositoryWrapper,final TaskRepository taskRepository,
    		final TaskConfigurationRepository taskConfigurationRepository,final GroupRepository groupRepository) {
        this.fromApiJsonHelper = fromApiJsonHelper;
	    this.codeValueRepositoryWrapper=codeValueRepositoryWrapper;
	    this.clientRepositoryWrapper=clientRepositoryWrapper;
	    this.taskRepository=taskRepository;
	    this.taskConfigurationRepository=taskConfigurationRepository;
	    this.groupRepository=groupRepository;
	    

    }
	
	 public void validateForCreate(final JsonCommand command) {

	        final String json = command.json();

	        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

	        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
	        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, TaskApiConstant.TASK_REQUEST_DATA_PARAMETERS);

	        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
	        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
	                .resource(TaskApiConstant.TASK_RESOURCE_NAME);
	        final JsonElement element = command.parsedJson();

	          
	        final Long officeId = this.fromApiJsonHelper.extractLongNamed(TaskApiConstant.officeidparamname, element);
	        baseDataValidator.reset().parameter(TaskApiConstant.officeidparamname).value(officeId).notNull()
	                .longGreaterThanZero();

	        final Long centerId = this.fromApiJsonHelper.extractLongNamed(TaskApiConstant.centerIdparamname, element);
	        baseDataValidator.reset().parameter(TaskApiConstant.centerIdparamname).value(centerId).notNull()
	                .longGreaterThanZero();

	        final Long staffId = this.fromApiJsonHelper.extractLongNamed(TaskApiConstant.staffIdparamname, element);
	        if(staffId==null){
				throw new FieldCannotbeBlankException("Task Completed By");
			}
	        baseDataValidator.reset().parameter(TaskApiConstant.staffIdparamname).value(staffId).notNull()
	                .longGreaterThanZero();
	       

	        
	        final Long taskTypeId = this.fromApiJsonHelper.extractLongNamed(TaskApiConstant.tasktypeparamname, element);
	        CodeValue taskType=null;
	        if(taskTypeId==null){
				throw new FieldCannotbeBlankException("taskTypeId");
			}else{
				taskType = this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(taskTypeId);

			}
	        //validate for ordering of task
			final Group group = this.groupRepository.findCenterById(centerId);
			long i=group.getIsnewCenter();
			int j= (int)i;
			Integer centerType= Integer.valueOf(j);
			TaskConfiguration taskConfiguration=this.taskConfigurationRepository.findTask(taskTypeId, centerType);
			if(taskConfiguration==null){
				throw new TaskConfigurationNotExist(taskType.label());
			}
			Long orderno=taskConfiguration.getOrder();
			orderno=orderno-1;
			while(orderno>0){
				TaskConfiguration taskConfigurationforOrderNo=this.taskConfigurationRepository.findTaskByOrderNo(orderno, centerType);
                 List<Tasks>noOfTask=this.taskRepository.findNoOfTask(centerId, taskConfigurationforOrderNo.getTasktype().getId());
				 if(taskConfigurationforOrderNo.getNoOfTask()!=noOfTask.size()){
					throw new TaskOrderException(taskConfigurationforOrderNo.getTasktype().label()); 
				 }
				 orderno--;
			}
	        
	        baseDataValidator.reset().parameter(TaskApiConstant.tasktypeparamname).value(taskTypeId).notNull()
	                .longGreaterThanZero();
	        
	       	        
	      	        throwExceptionIfValidationWarningsExist(dataValidationErrors);

	    }
	    
	    public void validateUpdate(final JsonCommand command) {

	    	final String json = command.json();

	        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

	        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
	        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, TaskApiConstant.TASK_UPDATE_DATA_PARAMETERS);

	        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
	        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
	                .resource(TaskApiConstant.TASK_RESOURCE_NAME);
	        final JsonElement element = command.parsedJson();

	       
	        
	        final Long staffId = this.fromApiJsonHelper.extractLongNamed(TaskApiConstant.staffIdparamname, element);
	        if(staffId==null){
				throw new FieldCannotbeBlankException("staffId");
			}
	        baseDataValidator.reset().parameter(TaskApiConstant.staffIdparamname).value(staffId).notNull()
	                .longGreaterThanZero();

	        final Long taskTypeId = this.fromApiJsonHelper.extractLongNamed(TaskApiConstant.tasktypeparamname, element);
	        if(taskTypeId==null){
				throw new FieldCannotbeBlankException("taskTypeId");
			}
	        baseDataValidator.reset().parameter(TaskApiConstant.tasktypeparamname).value(taskTypeId).notNull()
	                .longGreaterThanZero();
	   	      	        throwExceptionIfValidationWarningsExist(dataValidationErrors);

	    }

	    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
	        if (!dataValidationErrors.isEmpty()) {
	            //
	            throw new PlatformApiDataValidationException(dataValidationErrors);
	        }
	    }

  public Set<TaskDetails>	assembletaskDetails(final Tasks taskforUpdate,final JsonCommand command){
	  final String json = command.json();
      final JsonElement element = this.fromApiJsonHelper.parse(json);
      final JsonObject topLevelJsonElement = element.getAsJsonObject();
      final JsonArray array = topLevelJsonElement.get(TaskApiConstant.taskextradeatilparamname).getAsJsonArray();
      Set<TaskDetails> taskDetailsSet=new HashSet<TaskDetails>();
      for (int i = 0; i < array.size(); i++) {
          final JsonObject taskdetailObject = array.get(i).getAsJsonObject();
          final Long descriptionId = this.fromApiJsonHelper.extractLongNamed("id", taskdetailObject);
          if(descriptionId==null){
				throw new FieldCannotbeBlankException("description");
		  }
          CodeValue taskDescriptionDetails=null; 
          if(descriptionId!=null){
        	  taskDescriptionDetails=this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(descriptionId);
           }                    
          final String descriptionValue=this.fromApiJsonHelper.extractStringNamed("descriptionValue", taskdetailObject); 
          if(descriptionValue==null){
				throw new FieldCannotbeBlankException("description Value");
		  }
          TaskDetails taskdeatilsobject =TaskDetails.createTaskDetails(taskforUpdate, taskDescriptionDetails, descriptionValue);
          taskDetailsSet.add(taskdeatilsobject);
      }
       return taskDetailsSet;
	
  }
  
 public Set<TaskClientAttendence> assembletaskAttendence(final Tasks taskforUpdate,final JsonCommand command){
	 final String json = command.json();
     final JsonElement element = this.fromApiJsonHelper.parse(json);
     final JsonObject topLevelJsonElement = element.getAsJsonObject();
     final JsonArray array = topLevelJsonElement.get(TaskApiConstant.attendencedetailsIdparamname).getAsJsonArray();
     Set<TaskClientAttendence> taskAttendenceDetailsSet=new HashSet<TaskClientAttendence>();
     for (int i = 0; i < array.size(); i++) {
         final JsonObject taskdetailObject = array.get(i).getAsJsonObject();
         final Long clientId = this.fromApiJsonHelper.extractLongNamed("clientId", taskdetailObject);
         if(clientId==null){
				throw new FieldCannotbeBlankException("clientId");
		  }
         Client client=null; 
         if(clientId!=null){
        	 client=this.clientRepositoryWrapper.findOneWithNotFoundDetection(clientId);
          }                   
         final Integer attendanceTypeId = this.fromApiJsonHelper.extractIntegerSansLocaleNamed(attendanceTypeParamName, taskdetailObject);
         if(attendanceTypeId==null){
				throw new FieldCannotbeBlankException("attendanceTypeId");
		  }
         TaskClientAttendence taskClientAttendence=TaskClientAttendence.createClientAttendance(taskforUpdate, client, attendanceTypeId);
         taskAttendenceDetailsSet.add(taskClientAttendence);
     }
      return taskAttendenceDetailsSet;
	
 }
 
 public void validateForCreatetaskconfiguration(final JsonCommand command) {

 	final String json = command.json();

     if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

     final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
     this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json,TaskConfigurationApiConstant.TASKCONFIGURATION_REQUEST_DATA_PARAMETERS );
     final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
     final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors)
             .resource(TaskConfigurationApiConstant.TASKCONFIGURATION_RESOURCE_NAME);
     final JsonElement element = command.parsedJson();

     
     
     final Long tasktype = this.fromApiJsonHelper.extractLongNamed(TaskConfigurationApiConstant.tasktypeIdparamname, element);
     if(tasktype==null){
			throw new FieldCannotbeBlankException("taskTypeId");
		}
     baseDataValidator.reset().parameter(TaskConfigurationApiConstant.tasktypeIdparamname).value(tasktype).notNull()
             .longGreaterThanZero();
     
     final Long noofdays = this.fromApiJsonHelper.extractLongNamed(TaskConfigurationApiConstant.noofdaysparamname, element);
     if(noofdays==null){
			throw new FieldCannotbeBlankException("noofdays");
		}
     baseDataValidator.reset().parameter(TaskConfigurationApiConstant.noofdaysparamname).value(noofdays).notNull()
             .longGreaterThanZero();

     

 }	    
}

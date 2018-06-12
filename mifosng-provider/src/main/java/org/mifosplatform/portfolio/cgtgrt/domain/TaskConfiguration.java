package org.mifosplatform.portfolio.cgtgrt.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.portfolio.cgtgrt.api.TaskApiConstant;
import org.mifosplatform.portfolio.cgtgrt.api.TaskConfigurationApiConstant;
import org.mifosplatform.portfolio.cgtgrt.exception.FieldCannotbeBlankException;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskIsInActiveStateException;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_task_configuration")
public class TaskConfiguration extends AbstractPersistable<Long> {	
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name = "task_type" , nullable = false)
	private CodeValue tasktype;
	
	
	@Column(name = "no_of_days", nullable = false)
    private int noofdays;
	
	@ManyToOne
	@JoinColumn(name = "completed_by", nullable = false)
   private CodeValue completedby;
   
   @Column(name = "center_type", nullable = false)
   private Integer centerType;
   
   @Column(name = "no_of_task", nullable = false)
   private Long noOfTask;
   
   
   public TaskConfiguration() {
	super();
}

public static TaskConfiguration createTaskConfiguration(final CodeValue tasktype,final int noofdays,final CodeValue completedby, final int centerType,final Long noOfTask){
	   return new TaskConfiguration(tasktype,noofdays,completedby,centerType,noOfTask);
   }

   public TaskConfiguration(CodeValue tasktype, int noofdays, CodeValue completedby, Integer centerType,final Long noOfTask) {
		super();
		this.tasktype = tasktype;
		this.noofdays = noofdays;
		this.completedby = completedby;
		this.centerType = centerType;
		this.noOfTask=noOfTask;
	}
   
   public Map<String, Object> update(final JsonCommand command) {

       final Map<String, Object> actualChanges = new LinkedHashMap<>(9);

      
       if(command.longValueOfParameterNamed(TaskConfigurationApiConstant.tasktypeIdparamname)==null){
			throw new FieldCannotbeBlankException("taskType");
       }
       if(command.longValueOfParameterNamed(TaskConfigurationApiConstant.noofdaysparamname)==null){
			throw new FieldCannotbeBlankException("noofdays");
      }
       if(command.longValueOfParameterNamed(TaskConfigurationApiConstant.centertypeparamname)==null){
			throw new FieldCannotbeBlankException("centerType");
      }
       if(command.longValueOfParameterNamed(TaskConfigurationApiConstant.completedbyparamname)==null){
			throw new FieldCannotbeBlankException("Completed By");
      }
       
       if(command.longValueOfParameterNamed(TaskConfigurationApiConstant.nooftaskparamname)==null){
			throw new FieldCannotbeBlankException("no of task");
     }
       if (command.isChangeInLongParameterNamed(TaskConfigurationApiConstant.tasktypeIdparamname, this.tasktype.getId())) {
           final Long newValue = command.longValueOfParameterNamed(TaskConfigurationApiConstant.tasktypeIdparamname);
           actualChanges.put(TaskConfigurationApiConstant.tasktypeIdparamname, newValue);
       }
       
       if (command.isChangeInIntegerParameterNamed(TaskConfigurationApiConstant.noofdaysparamname, this.noofdays)) {
           final Integer newValue = command.integerValueOfParameterNamed(TaskConfigurationApiConstant.noofdaysparamname);
           actualChanges.put(TaskConfigurationApiConstant.noofdaysparamname, newValue);
           this.noofdays=newValue;
       }
       if (command.isChangeInLongParameterNamed(TaskConfigurationApiConstant.nooftaskparamname, this.noOfTask)) {
           final Long newValue = command.longValueOfParameterNamed(TaskConfigurationApiConstant.nooftaskparamname);
           actualChanges.put(TaskConfigurationApiConstant.nooftaskparamname, newValue);
           this.noOfTask=newValue;
       }
       if (command.isChangeInIntegerParameterNamed(TaskConfigurationApiConstant.centertypeparamname, this.centerType)) {
           final int newValue = command.integerValueOfParameterNamed(TaskConfigurationApiConstant.centertypeparamname);
           actualChanges.put(TaskConfigurationApiConstant.centertypeparamname, newValue);
           this.centerType=newValue;
       }
       
       if (command.isChangeInLongParameterNamed(TaskConfigurationApiConstant.completedbyparamname, this.completedby.getId())) {
           final Long newValue = command.longValueOfParameterNamed(TaskConfigurationApiConstant.completedbyparamname);
           actualChanges.put(TaskConfigurationApiConstant.completedbyparamname, newValue);
       }
  
      return actualChanges;
   }

	 

public CodeValue getTasktype() {
	return tasktype;
}



public void setTasktype(CodeValue tasktype) {
	this.tasktype = tasktype;
}



public int getNoofdays() {
	return noofdays;
}



public void setNoofdays(int noofdays) {
	this.noofdays = noofdays;
}



public CodeValue getCompletedby() {
	return completedby;
}



public void setCompletedby(CodeValue completedby) {
	this.completedby = completedby;
}



public Integer getCenterType() {
	return centerType;
}



public void setCenterType(Integer centerType) {
	this.centerType = centerType;
}



public static long getSerialversionuid() {
	return serialVersionUID;
}

public void updateTaskType(final CodeValue tasktype){
	this.tasktype=tasktype;
}
public void updateCompletedby(final CodeValue completedby){
	this.completedby=completedby;
}

public Long getNoOfTask() {
	return noOfTask;
}

public void setNoOfTask(Long noOfTask) {
	this.noOfTask = noOfTask;
}
  
   

}

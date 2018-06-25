package org.mifosplatform.portfolio.cgtgrt.data;

import java.util.Collection;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;

public class TaskConfigurationData {

	public String taskType;
	public String completedBy;
	public Integer noofdays;
	public String centerType;
	public Long taskconfigurationId; 
	public  Long tasktypeId;
	public Long completedById;
	private Long nooftask;
	private Long orderno;
	
	
	//Template
	public final Collection<CodeValueData>descriptions;
	public final Collection<CodeValueData>taskCompletedBy;
	
	public static TaskConfigurationData template(final Collection<CodeValueData>taskTypes,Collection<CodeValueData>taskCompletedBy ){
		 final String taskType =null;
		final String  completedBy=null;
		final Integer noofdays= null;
		final String centerType=null;
		final Long taskconfigurationId=null;
		final  Long tasktypeId=null;
		final Long completedById=null;
		final Long nooftask=null;
		final Long orderNo=null;
		 
		 return new TaskConfigurationData(taskTypes,taskCompletedBy,taskType,completedBy,noofdays,centerType,taskconfigurationId,tasktypeId,completedById,nooftask,orderNo);
	}
	
	public static TaskConfigurationData taskconfigurationData(final String taskType,final String completedBy,final int noofdays,final String centerType,final Long taskconfigurationId,final Long tasktypeId,final Long completedById,final Long nooftask,final Long orderNo){
		final Collection<CodeValueData>taskTypes =null;
		 final Collection<CodeValueData>taskCompletedBy=null;

		return new TaskConfigurationData(taskTypes,taskCompletedBy,taskType,completedBy,noofdays,centerType,taskconfigurationId,tasktypeId,completedById,nooftask,orderNo);
	}
	public TaskConfigurationData(final Collection<CodeValueData>descriptions,final Collection<CodeValueData>taskCompletedBy, final String taskType, String completedBy, Integer noofdays, String centerType,Long taskconfigurationId,final Long tasktypeId,final Long completedById ,final Long nooftask,final Long orderNo) {
		super();
		this.descriptions=descriptions;
		this.taskCompletedBy=taskCompletedBy;
		this.taskType = taskType;
		this.completedBy = completedBy;
		this.noofdays = noofdays;
		this.centerType = centerType;
		this.taskconfigurationId=taskconfigurationId;
		this.tasktypeId=tasktypeId;
		this.completedById=completedById;
		this.nooftask=nooftask;
		this.orderno=orderNo;
	}
	public Long getNooftask() {
		return nooftask;
	}

	public void setNooftask(Long nooftask) {
		this.nooftask = nooftask;
	}

	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}
	public int getNoofdays() {
		return noofdays;
	}
	public void setNoofdays(int noofdays) {
		this.noofdays = noofdays;
	}
	public String getCenterType() {
		return centerType;
	}
	public void setCenterType(String centerType) {
		this.centerType = centerType;
	}

	public Long getTaskconfigurationId() {
		return taskconfigurationId;
	}

	public void setTaskconfigurationId(Long taskconfigurationId) {
		this.taskconfigurationId = taskconfigurationId;
	}

	public Collection<CodeValueData> getDescriptions() {
		return descriptions;
	}

	public Collection<CodeValueData> getTaskCompletedBy() {
		return taskCompletedBy;
	}

	public void setNoofdays(Integer noofdays) {
		this.noofdays = noofdays;
	}

	public Long getTasktypeId() {
		return tasktypeId;
	}

	public void setTasktypeId(Long tasktypeId) {
		this.tasktypeId = tasktypeId;
	}

	public Long getCompletedById() {
		return completedById;
	}

	public void setCompletedById(Long completedById) {
		this.completedById = completedById;
	}

	public Long getOrderNo() {
		return orderno;
	}

	public void setOrderNo(Long orderNo) {
		this.orderno = orderNo;
	}
	
	
}

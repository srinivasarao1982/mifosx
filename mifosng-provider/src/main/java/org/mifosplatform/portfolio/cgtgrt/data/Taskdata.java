package org.mifosplatform.portfolio.cgtgrt.data;

import java.util.Collection;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.organisation.staff.data.StaffData;
import org.mifosplatform.organisation.staff.domain.Staff;

public class Taskdata {

	private final Long taskId;
	private final String taskType;
	private final Long taskTypeId;
	private final String staffName;
	private final Long staffId;
	private final LocalDate taskcreatedDate;
	private final LocalDate expectedCompletedDate;
	private final LocalDate completedDate;
	private final EnumOptionData status;
	private final String note;
	private final List<TaskDetailData> taskdetailsData;
	private final List<ClientAttendenceData> clientAttendenceData;
	private final Long taskStartTimeId;
	private final Long taskEndTimeId;
	private final String taskStartTime;
	private final String taskEndTime;

	// template
	private final Collection<StaffData> staffOptions;
	private final Collection<CodeValueData> tasktypeOptions;
	private final Collection<EnumOptionData> taskstatus;
	private final Collection<EnumOptionData> attendenceTypeOptions;
	private final Collection<CodeValueData> tasktimeOptions;

	public static Taskdata template(final Collection<StaffData> staffData, Collection<CodeValueData> tasktypeOptions,
			final Collection<EnumOptionData> taskstatus, final Collection<EnumOptionData> attendenceTypeOptions,
			final Collection<CodeValueData> tasktimeOptions) {
		final Long taskId = null;		
		final String taskType = null;
		final String staffName = null;
		final LocalDate taskcreatedDate=null;
		final LocalDate expectedCompletedDate = null;
		final LocalDate completedDate=null;
		final EnumOptionData status = null;
		final String note = null;
		final Long taskTypeId=null;
		final Long staffId=null;
		final Long taskStartTimeId=null;
		final Long taskEndTimeId=null;
		final String taskStartTime=null;
		final String taskEndTime=null;

		final List<TaskDetailData> taskdetailsData = null;
		final List<ClientAttendenceData> clientAttendenceData = null;
		return new Taskdata(taskId, taskType, staffName, taskcreatedDate,expectedCompletedDate,completedDate, status, note,
				taskdetailsData, clientAttendenceData, staffData, tasktypeOptions, taskstatus, attendenceTypeOptions,taskTypeId,staffId,tasktimeOptions,taskStartTimeId
				,taskEndTimeId,taskStartTime,taskEndTime);
	}
	
	public static Taskdata taskDetailseprate( final Long taskId,final String taskType,final String staff,final LocalDate taskcreatedDate,
			 final LocalDate expectedtaskcompletedDate,final LocalDate completedDate, final EnumOptionData status,final String note,final Long taskTypeId,final Long staffId
			 ,Long taskStartTimeId, Long taskEndTimeId,String taskStartTime,String taskEndTime){
		final Collection<StaffData> staffOptions = null;
		final Collection<CodeValueData> tasktypeOptions = null;
		final Collection<EnumOptionData> taskstatus = null;
		final Collection<EnumOptionData> attendenceTypeOptions = null;
		final List<TaskDetailData> taskdetailsData = null;
		final List<ClientAttendenceData> clientAttendenceData = null;
		final Collection<CodeValueData> tasktimeOptions=null;
		return new Taskdata(taskId,taskType,staff,taskcreatedDate,expectedtaskcompletedDate,completedDate,status,note,taskdetailsData, clientAttendenceData, staffOptions, tasktypeOptions, taskstatus,
				attendenceTypeOptions,taskTypeId,staffId,tasktimeOptions,taskStartTimeId,taskEndTimeId,taskStartTime,taskEndTime);

		   
	       }
	

	public static Taskdata taskDatdetails(final Taskdata taskData, final List<TaskDetailData> taskdetailsData,
			final List<ClientAttendenceData> clientAttendenceData) {
		final Collection<StaffData> staffOptions = null;
		final Collection<CodeValueData> tasktypeOptions = null;
		final Collection<EnumOptionData> taskstatus = null;
		final Collection<EnumOptionData> attendenceTypeOptions = null;
		final Collection<CodeValueData> tasktimeOptions=null;
		return new Taskdata(taskData.taskId, taskData.taskType, taskData.staffName,taskData.taskcreatedDate, taskData.expectedCompletedDate,taskData.completedDate, taskData.status, taskData.note,
				taskdetailsData, clientAttendenceData, staffOptions, tasktypeOptions, taskstatus,
				attendenceTypeOptions,taskData.taskTypeId,taskData.staffId,tasktimeOptions,taskData.taskStartTimeId,
				taskData.taskEndTimeId,taskData.taskStartTime,taskData.taskEndTime);
	}

	public Taskdata(Long taskId, String taskType, String staff, LocalDate taskcreatedDate,
			LocalDate expectedCompletedDate,LocalDate taskCompletedDate, EnumOptionData status, String note, List<TaskDetailData> taskdetailsData,
			List<ClientAttendenceData> clientAttendenceData, Collection<StaffData> staffOptions,
			Collection<CodeValueData> tasktypeOptions, Collection<EnumOptionData> taskstatus,
			Collection<EnumOptionData> attendenceTypeOptions,Long taskTypeId,Long staffId,
			Collection<CodeValueData> tasktimeOptions,Long taskStartTimeId,Long taskEndTimeId,
			String taskStartTime,String taskEndTime)
        {
		super();
		this.taskId = taskId;
		this.taskType = taskType;
		this.staffName = staff;
		this.taskcreatedDate = taskcreatedDate;
		this.expectedCompletedDate = expectedCompletedDate;
		this.completedDate=taskCompletedDate;
		this.status = status;
		this.note = note;
		this.taskdetailsData = taskdetailsData;
		this.clientAttendenceData = clientAttendenceData;
		this.staffOptions = staffOptions;
		this.tasktypeOptions = tasktypeOptions;
		this.taskstatus = taskstatus;
		this.attendenceTypeOptions = attendenceTypeOptions;
		this.taskTypeId=taskTypeId;
		this.staffId=staffId;
		this.tasktimeOptions=tasktimeOptions;
		this.taskStartTimeId=taskStartTimeId;
		this.taskEndTimeId=taskEndTimeId;
		this.taskStartTime=taskStartTime;
		this.taskEndTime=taskEndTime;
	}

	public Long getTaskId() {
		return taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public String getStaff() {
		return staffName;
	}

	public LocalDate getTaskcreatedDate() {
		return taskcreatedDate;
	}

	public LocalDate getExpectedCompletedDate() {
		return expectedCompletedDate;
	}

	public EnumOptionData getStatus() {
		return status;
	}

	public String getNote() {
		return note;
	}

	public List<TaskDetailData> getTaskdetailsData() {
		return taskdetailsData;
	}

	public List<ClientAttendenceData> getClientAttendenceData() {
		return clientAttendenceData;
	}

	public Collection<StaffData> getStaffOptions() {
		return staffOptions;
	}

	public Collection<CodeValueData> getTasktypeOptions() {
		return tasktypeOptions;
	}

	public Collection<EnumOptionData> getTaskstatus() {
		return taskstatus;
	}

	public Collection<EnumOptionData> getAttendenceTypeOptions() {
		return attendenceTypeOptions;
	}

	public String getStaffName() {
		return staffName;
	}

	public LocalDate getCompletedDate() {
		return completedDate;
	}

	public Long getTaskTypeId() {
		return taskTypeId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public Collection<CodeValueData> getTasktimeOptions() {
		return tasktimeOptions;
	}

	public Long getTaskStartTimeId() {
		return taskStartTimeId;
	}

	public Long getTaskEndTimeId() {
		return taskEndTimeId;
	}

	public String getTaskStartTime() {
		return taskStartTime;
	}

	public String getTaskEndTime() {
		return taskEndTime;
	}

	
	

}

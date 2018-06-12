package org.mifosplatform.portfolio.cgtgrt.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.staff.data.StaffData;
import org.mifosplatform.organisation.staff.service.StaffReadPlatformService;
import org.mifosplatform.portfolio.cgtgrt.api.TaskApiConstant;
import org.mifosplatform.portfolio.cgtgrt.data.ClientAttendenceData;
import org.mifosplatform.portfolio.cgtgrt.data.TaskDetailData;
import org.mifosplatform.portfolio.cgtgrt.data.Taskdata;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskStatus;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskStatusEnumerations;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskNotFoundException;
import org.mifosplatform.portfolio.meeting.attendance.service.AttendanceDropdownReadPlatformService;
import org.mifosplatform.portfolio.meeting.attendance.service.AttendanceEnumerations;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class TaskReadPlatformServiceImpl implements TaskReadPlatformService {
	
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;
	private final AttendanceDropdownReadPlatformService attendanceDropdownReadPlatformService;
	private final StaffReadPlatformService staffReadPlatformService;

	@Autowired
	public TaskReadPlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource,
			final AttendanceDropdownReadPlatformService attendanceDropdownReadPlatformService,
			final StaffReadPlatformService staffReadPlatformService) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
		this.attendanceDropdownReadPlatformService=attendanceDropdownReadPlatformService;
		this.staffReadPlatformService=staffReadPlatformService;
	}
	
	@Override
	public Taskdata retrieveTemplate(final Long officeId) {
		this.context.authenticatedUser();
		
		Collection<CodeValueData> tasktypeOptions = new ArrayList<>(this.codeValueReadPlatformService
				.retrieveCodeValuesByCode(TaskApiConstant.TASKTYPE));
		Collection<EnumOptionData> taskstatus = retrieveTaskStatusTypeOptions();
		Collection<EnumOptionData> attendenceTypeOptions=this.attendanceDropdownReadPlatformService.retrieveAttendanceTypeOptions();
		Collection<StaffData> staffOptions=this.staffReadPlatformService.retrieveAllStaffForDropdown(officeId);
		Taskdata taskdata=Taskdata.template(staffOptions, tasktypeOptions, taskstatus, attendenceTypeOptions);
		return taskdata;

	}
	
	
	@Override
    public Taskdata retrievetaskDetail(final Long taskId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final TaskDataMapper rm = new TaskDataMapper();
            final TaskDetailsMapper rm1=new TaskDetailsMapper();
            final ClientAttendenceDataMapper rm2 =new ClientAttendenceDataMapper(); 
            String Sql ="select"+rm.schema()+"where mt.id= ? ";
            List<Taskdata>taskDatas=new ArrayList<Taskdata>();
            List<TaskDetailData>taskDetailsData=new ArrayList<TaskDetailData>();
            List<ClientAttendenceData>clientAttendenceData=new ArrayList<ClientAttendenceData>();
            
            taskDatas = this.jdbcTemplate.query(Sql, rm, new Object[] { taskId});
            taskDetailsData=this.jdbcTemplate.query(rm1.schema().toString(),rm1,new Object[]{taskId});
            clientAttendenceData =this.jdbcTemplate.query(rm2.schema().toString(),rm2,new Object[]{taskId});
            Taskdata taskdata=taskDatas.get(0);
             return Taskdata.taskDatdetails(taskdata, taskDetailsData, clientAttendenceData);
            
            }catch (final EmptyResultDataAccessException e) {
            throw new TaskNotFoundException(taskId);
        }

    
	}
	@Override
    public List<Taskdata> retrievetaskDetails(final Long centerId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final TaskDataMapper rm = new TaskDataMapper();
            String Sql ="select"+rm.schema() +" where mt.center_id="+centerId;
            List<Taskdata>taskDatas=new ArrayList<Taskdata>();
            taskDatas = this.jdbcTemplate.query(Sql, rm );
            return taskDatas;
            }catch (final EmptyResultDataAccessException e) {
            throw new TaskNotFoundException(1l);
        }

    
	}
    private static final class TaskDataMapper implements RowMapper<Taskdata> {

        public TaskDataMapper() {}

        public String schema() {
            return   "  mt.id as taskId,mcv.code_value as taskType,mt.task_type as taskTypeId,s.id as staffId,mt.task_date as expectedcompletedDate,s.display_name as staffName, "
                    + " mt.task_completed_date as taskCompletedDate,mt.note as note,mt.task_status as taskStatus,mt.created_date as createdDate "
                    + " from m_task mt "
                    + " join m_code_value mcv on mt.task_type=mcv.id "
                    + " join m_staff s on s.id =mt.staff_id  ";
                  }

        @Override
        public Taskdata mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long taskId = JdbcSupport.getLong(rs, "taskId");
            final String taskType = rs.getString("taskType");
            final String note = rs.getString("note");
            final Integer taskStatus= JdbcSupport.getInteger(rs, "taskStatus");
            final LocalDate createdDate =JdbcSupport.getLocalDate(rs, "createdDate");
            final LocalDate taskCompletedDate =JdbcSupport.getLocalDate(rs, "taskCompletedDate");
            final LocalDate expectedCompletedDate =JdbcSupport.getLocalDate(rs, "expectedcompletedDate");
            final  EnumOptionData status=TaskStatusEnumerations.taskStatus(taskStatus);
            final String staffName=rs.getString("staffName");
            final Long taskTypeId =JdbcSupport.getLong(rs, "taskTypeId");
            final Long stffId=JdbcSupport.getLong(rs, "staffId");
            return Taskdata.taskDetailseprate( taskId, taskType, staffName, createdDate,expectedCompletedDate,taskCompletedDate, status, note,taskTypeId,stffId);
            		
        }

    }
    
    private static final class TaskDetailsMapper implements RowMapper<TaskDetailData> {

        public TaskDetailsMapper() {}

        public String schema() {
            return   "  select  mts.id as id,mcv.code_value as detailsdescription,mts.details_description as desid,"
                    + " mts.description_value as descriptionValue from  "
                    + " m_taskdetails mts join m_code_value mcv on mcv.id=mts.details_description "
                    + " where mts.task_id=? ";
               }

        @Override
        public TaskDetailData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long taskDetailsId = JdbcSupport.getLong(rs, "id");
            final String detailsDescription = rs.getString("detailsdescription");
            final String descriptionValue = rs.getString("descriptionValue"); 
            final Long id =JdbcSupport.getLong(rs, "desid");
            return TaskDetailData.createtaskdetails(taskDetailsId, detailsDescription, descriptionValue,id);
      }

    }
    

    private static final class ClientAttendenceDataMapper implements RowMapper<ClientAttendenceData> {

        public ClientAttendenceDataMapper() {}

        public String schema() {
            return   "  select mta.id as id,mc.display_name as clientName,mc.id as clientId,mta.attendance_type_enum  as attendenceType "
                    + " from m_taskattendence mta "
                    + " join m_client mc on mta.client_id = mc.id "
                    + " where mta.task_id=?  ";
               }

        @Override
        public ClientAttendenceData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long id =JdbcSupport.getLong(rs, "id");
        	final Long clientId = JdbcSupport.getLong(rs, "clientId");
            final String clientName = rs.getString("clientName");
            final Integer attendenceType =JdbcSupport.getInteger(rs, "attendenceType");
            final  EnumOptionData attendeceType =AttendanceEnumerations.attendanceType(attendenceType);
            return new ClientAttendenceData(id,clientId,clientName,attendeceType);

        }

    }
	  

    
	public List<EnumOptionData> retrieveTaskStatusTypeOptions() {
        return TaskStatusEnumerations.taskStatusType(TaskStatus.values());
    }

}

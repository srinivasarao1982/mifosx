package org.mifosplatform.portfolio.cgtgrt.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.staff.service.StaffReadPlatformService;
import org.mifosplatform.portfolio.cgtgrt.api.TaskConfigurationApiConstant;
import org.mifosplatform.portfolio.cgtgrt.data.TaskConfigurationData;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskNotFoundException;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class TaskConfigurationReadplatformServiceImpl implements TaskConfigurationReadplatformService{
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;
	private final StaffReadPlatformService staffReadPlatformService;

	@Autowired
	public TaskConfigurationReadplatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource,
			final StaffReadPlatformService staffReadPlatformService) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
		this.staffReadPlatformService=staffReadPlatformService;
	}
	
	@Override
	public TaskConfigurationData retrieveTemplate() {
		this.context.authenticatedUser();
		
		Collection<CodeValueData> tasktypeOptions = new ArrayList<>(this.codeValueReadPlatformService
				.retrieveCodeValuesByCode(TaskConfigurationApiConstant.TASKDESCRIPTION));
		Collection<CodeValueData> completedByOptions = new ArrayList<>(this.codeValueReadPlatformService
				.retrieveCodeValuesByCode(TaskConfigurationApiConstant.TASKCOMPLETEDBY));		
		
		TaskConfigurationData taskConfigurationData=TaskConfigurationData.template(tasktypeOptions, completedByOptions);
				return taskConfigurationData;

	}

	@Override
    public TaskConfigurationData retrievetaskConfigurationDetails(final Long taskconfigurationId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final TaskConfigurationDataMapper rm = new TaskConfigurationDataMapper();
             List<TaskConfigurationData>taskConfigureData=new ArrayList<TaskConfigurationData>();
                        
             taskConfigureData = this.jdbcTemplate.query(rm.schema().toString(), rm, new Object[] { taskconfigurationId});
              return     taskConfigureData.get(0);   
            }catch (final EmptyResultDataAccessException e) {
            throw new TaskNotFoundException(taskconfigurationId);
        }

    
	}
	private static final class TaskConfigurationDataMapper implements RowMapper<TaskConfigurationData> {

        public TaskConfigurationDataMapper() {}

        public String schema() {
            return   "  select mtc.id as tadkconfigurationId,if(mtc.center_type=1,'New','Old') as centerType,mtc.task_type as taskTypeId,mtc.completed_by as completedById,mtc.no_of_task as nooftask , "
                    + " mcv.code_value as taskType,mcv1.code_value as completedBy,mtc.no_of_days as noOfDays "
                    + " from m_task_configuration mtc join "
                    + " m_code_value mcv on mcv.id=mtc.task_type "
                    + " join m_code_value mcv1 on mcv1.id=mtc.completed_by  where mtc.id=? ";
                  }

        @Override
        public TaskConfigurationData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long taskconfigurationId = JdbcSupport.getLong(rs, "tadkconfigurationId");
            final String taskType = rs.getString("taskType");
            final String centerType = rs.getString("centerType");
            final String completedBy= rs.getString("completedBy");
            final int noOfdays=JdbcSupport.getInteger(rs, "noOfDays");
            final Long tasktypeId = JdbcSupport.getLong(rs, "taskTypeId");
            final Long completedById = JdbcSupport.getLong(rs, "completedById");
            final Long nooftask = JdbcSupport.getLong(rs, "nooftask");


           return TaskConfigurationData.taskconfigurationData(taskType, completedBy, noOfdays, centerType,taskconfigurationId,tasktypeId,completedById,nooftask);
            		
        }

    }
	@Override
    public List<TaskConfigurationData> retrieveDetails() {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final TaskDetailsMapper rm = new TaskDetailsMapper();
            List<TaskConfigurationData>taskDatas=new ArrayList<TaskConfigurationData>();
            taskDatas = this.jdbcTemplate.query(rm.schema().toString(), rm, new Object[] {});
            return taskDatas;
            }catch (final EmptyResultDataAccessException e) {
            throw new TaskNotFoundException(1l);
        }

    
	}
	
	private static final class TaskDetailsMapper implements RowMapper<TaskConfigurationData> {

        public TaskDetailsMapper() {}

        public String schema() {
            return   "  select mtc.id as tadkconfigurationId,if(mtc.center_type=1,'New','Old') as centerType, mtc.task_type as taskTypeId,mtc.completed_by as completedById,mtc.no_of_task as nooftask ,"
                    + " mcv.code_value as taskType,mcv1.code_value as completedBy,mtc.no_of_days as noOfDays "
                    + " from m_task_configuration mtc join "
                    + " m_code_value mcv on mcv.id=mtc.task_type "
                    + " join m_code_value mcv1 on mcv1.id=mtc.completed_by   ";
                  }

        @Override
        public TaskConfigurationData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long taskconfigurationId = JdbcSupport.getLong(rs, "tadkconfigurationId");
            final String taskType = rs.getString("taskType");
            final String centerType = rs.getString("centerType");
            final String completedBy= rs.getString("completedBy");
            final int noOfdays=JdbcSupport.getInteger(rs, "noOfDays");
            final Long tasktypeId = JdbcSupport.getLong(rs, "taskTypeId");
            final Long completedById = JdbcSupport.getLong(rs, "completedById");
            final Long nooftask = JdbcSupport.getLong(rs, "nooftask");


           return TaskConfigurationData.taskconfigurationData(taskType, completedBy, noOfdays, centerType,taskconfigurationId,tasktypeId,completedById,nooftask);
            		
        }

    }

    

}

package org.mifosplatform.portfolio.cgtgrt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.domain.Office;
import org.mifosplatform.organisation.office.domain.OfficeRepositoryWrapper;
import org.mifosplatform.organisation.staff.domain.Staff;
import org.mifosplatform.organisation.staff.domain.StaffRepositoryWrapper;
import org.mifosplatform.portfolio.cgtgrt.api.TaskApiConstant;
import org.mifosplatform.portfolio.cgtgrt.data.TaskDataValidator;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskClientAttendence;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskConfiguration;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskConfigurationRepository;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskDetails;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskRepository;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskRepositoryWrapper;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskStatus;
import org.mifosplatform.portfolio.cgtgrt.domain.Tasks;
import org.mifosplatform.portfolio.cgtgrt.exception.FieldCannotbeBlankException;
import org.mifosplatform.portfolio.cgtgrt.exception.MaximumNumberofTaskExceedException;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskIsInActiveStateException;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskOrderException;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.exception.DuplicateClientIdentifierException;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.group.domain.GroupRepository;
import org.mifosplatform.portfolio.group.domain.GroupRepositoryWrapper;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskWritePlatformServiceImpl implements TaskWriteplatformService {

	private final static Logger logger = LoggerFactory.getLogger(TaskWritePlatformServiceImpl.class);

	private final PlatformSecurityContext context;
	private final TaskRepositoryWrapper taskRepositoryWrapper;
	private final TaskDataValidator taskDataValidator;
	private final CodeValueRepositoryWrapper codeValueRepositoryWrapper;
	private final OfficeRepositoryWrapper officeRepositoryWrapper;
	private final GroupRepositoryWrapper groupRepositoryWrapper;
	private final GroupRepository groupRepository;
	private final StaffRepositoryWrapper staffRepositoryWrapper;
	private final TaskRepository taskRepository;
	private final TaskConfigurationRepository taskConfigurationRepository;

	@Autowired
	public TaskWritePlatformServiceImpl(final PlatformSecurityContext context,
			final ClientRepositoryWrapper clientRepositoryWrapper, final TaskRepositoryWrapper taskRepositoryWrapper,
			final TaskDataValidator taskDataValidator, final CodeValueRepositoryWrapper codeValueRepositoryWrapper,
			final OfficeRepositoryWrapper officeRepositoryWrapper, final GroupRepositoryWrapper groupRepositoryWrapper,
			final StaffRepositoryWrapper staffRepositoryWrapper, final LoanProductRepository loanProductRepository,
			final GroupRepository groupRepository,final TaskRepository taskRepository,
			final TaskConfigurationRepository taskConfigurationRepository) {
		this.context = context;
		this.codeValueRepositoryWrapper = codeValueRepositoryWrapper;
		this.taskRepositoryWrapper = taskRepositoryWrapper;
		this.groupRepositoryWrapper = groupRepositoryWrapper;
		this.staffRepositoryWrapper = staffRepositoryWrapper;
		this.officeRepositoryWrapper = officeRepositoryWrapper;
		this.taskDataValidator = taskDataValidator;
		this.groupRepository=groupRepository;
		this.taskRepository=taskRepository;
        this.taskConfigurationRepository=taskConfigurationRepository;
	}

	@Transactional
	@Override
	public CommandProcessingResult createTask(final JsonCommand command) {

		this.context.authenticatedUser();
		taskDataValidator.validateForCreate(command);

		try {

			final Long centerId = command.longValueOfParameterNamed(TaskApiConstant.centerIdparamname);
			if(centerId==null){
				throw new FieldCannotbeBlankException("centerId");
			}
			final Group group = this.groupRepository.findCenterById(centerId);
			final Long staffId = command.longValueOfParameterNamed(TaskApiConstant.staffIdparamname);
			if(staffId==null){
				throw new FieldCannotbeBlankException("staffI");
			}
			final Staff staff = this.staffRepositoryWrapper.findOneWithNotFoundDetection(staffId);
			final Long taskTypeId = command.longValueOfParameterNamed(TaskApiConstant.tasktypeparamname);
			if(taskTypeId==null){
				throw new FieldCannotbeBlankException("taskTypeId");
			}
			CodeValue taskType = null;
			if (taskTypeId != null) {
				taskType = this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(taskTypeId);
			}
			final Long officeId = command.longValueOfParameterNamed(TaskApiConstant.officeidparamname);
			final Office office = this.officeRepositoryWrapper.findOneWithNotFoundDetection(officeId);
			// LocalDate taskDate = command.localDateValueOfParameterNamed(TaskApiConstant.expectedcompliteddate);
			 LocalDate taskDate=LocalDate.now();
			 long i=group.getIsnewCenter();
				int j= (int)i;
				Integer centerType= Integer.valueOf(j);
			TaskConfiguration taskConfiguration=this.taskConfigurationRepository.findTask(taskTypeId, centerType);
			List<Tasks>tasks=this.taskRepository.findNoOfTask(centerId,taskTypeId);
			if(tasks!=null && taskConfiguration !=null){
			if(tasks.size()>=taskConfiguration.getNoOfTask()){
				throw new MaximumNumberofTaskExceedException(taskConfiguration.getNoOfTask());
			}
			}
			if(taskConfiguration!=null){
				
				taskDate=taskDate.plusDays(taskConfiguration.getNoofdays());
			}
			Integer status = TaskStatus.ACTIVE.getValue();
			final String note = command.stringValueOfParameterNamed(TaskApiConstant.noteparamname);
			Date taskCompletedDate = null;
			Tasks taskvalue=null;
              taskvalue=this.taskRepository.findTask(centerId, taskTypeId, status);
             if(taskvalue!=null){
            	 throw new TaskIsInActiveStateException(taskType.label());
             }
			Tasks task = Tasks.createtask(group, office, staff, taskType, note, taskDate.toDate(), taskCompletedDate,
					status);
			this.taskRepositoryWrapper.save(task);
			Set<TaskDetails> taskDetailsData = this.taskDataValidator.assembletaskDetails(task, command);
			task.updatetaskdeatisl(taskDetailsData);
			this.taskRepositoryWrapper.saveAndFlush(task);;

			return new CommandProcessingResultBuilder() //
					.withCommandId(command.commandId()) //
					.withOfficeId(task.getOffice().getId()) //
					.withEntityId(task.getId()) //
					.build();
		} catch (final DataIntegrityViolationException dve) {
			handleClientIdentifierDataIntegrityViolation(null, null, dve);
			return CommandProcessingResult.empty();
		}
	}

	@Transactional
	@Override
	public CommandProcessingResult updateTask(final Long taskId, final JsonCommand command) {

		try {
			this.context.authenticatedUser();
			final Tasks taskforUpdate = this.taskRepositoryWrapper.findOneWithNotFoundDetection(taskId);
			if (taskforUpdate != null) {
				this.taskDataValidator.validateUpdate(command);
				
				 //validate for ordering of task
				Long taskTypeId=command.longValueOfParameterNamed(TaskApiConstant.tasktypeparamname);
				final Long centerId = command.longValueOfParameterNamed(TaskApiConstant.centerIdparamname);
				if(centerId==null){
					throw new FieldCannotbeBlankException("centerId");
				}
				final Group group = this.groupRepository.findCenterById(centerId);
				long i=group.getIsnewCenter();
				int j= (int)i;
				Integer centerType= Integer.valueOf(j);
				TaskConfiguration taskConfiguration=this.taskConfigurationRepository.findTask(taskTypeId, centerType);
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
				// for Validata Purpose
				final Map<String, Object> changes = taskforUpdate.update(command);

				if (changes.containsKey(TaskApiConstant.taskstatusparamname)) {

					final Integer newValue = command.integerValueOfParameterNamed(TaskApiConstant.taskstatusparamname);
					if(newValue!=100){
						taskforUpdate.updateCompleteDate(new Date());
					}
					Integer status = null;
					if (newValue != null) {
						status = TaskStatus.fromInt(newValue).getValue();
					}
					taskforUpdate.updateStatus(status);
				}
				Set<TaskClientAttendence> taskClientAttendenceDetails = this.taskDataValidator
						.assembletaskAttendence(taskforUpdate, command);
				Set<TaskDetails> taskDetailsData = this.taskDataValidator.assembletaskDetails(taskforUpdate, command);
				taskforUpdate.updateattendencedeatisl(taskClientAttendenceDetails);
				taskforUpdate.updatetaskdeatisl(taskDetailsData);

				this.taskRepositoryWrapper.saveAndFlush(taskforUpdate);
			}
			return new CommandProcessingResultBuilder() //
					.withCommandId(command.commandId()) //
					.withEntityId(taskforUpdate.getId()) //
					.build();

		} catch (final DataIntegrityViolationException dve) {
			return CommandProcessingResult.empty();
		}
	}
	 private void handleClientIdentifierDataIntegrityViolation(final String accountNumber, final String  ifsc,
             final DataIntegrityViolationException dve) {

        if (dve.getMostSpecificCause().getMessage().contains("unique_loan_identifier")) {
            throw new DuplicateClientIdentifierException(accountNumber);
        } 
        logAsErrorUnexpectedDataIntegrityException(dve);
        throw new PlatformDataIntegrityException("error.msg.taskIdentifier.unknown.data.integrity.issue",
                "Unknown data integrity issue with resource.");
    }

    private void logAsErrorUnexpectedDataIntegrityException(final DataIntegrityViolationException dve) {
        logger.error(dve.getMessage(), dve);
    }

}

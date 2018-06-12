package org.mifosplatform.portfolio.cgtgrt.service;

import java.util.Map;

import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.domain.OfficeRepositoryWrapper;
import org.mifosplatform.organisation.staff.domain.StaffRepositoryWrapper;
import org.mifosplatform.portfolio.cgtgrt.api.TaskConfigurationApiConstant;
import org.mifosplatform.portfolio.cgtgrt.data.TaskDataValidator;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskConfiguration;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskConfigurationRepository;
import org.mifosplatform.portfolio.cgtgrt.domain.TaskConfigurationRepositoryWrapper;
import org.mifosplatform.portfolio.cgtgrt.exception.FieldCannotbeBlankException;
import org.mifosplatform.portfolio.cgtgrt.exception.TaskConfigurationExistException;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.exception.DuplicateClientIdentifierException;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.group.domain.GroupRepositoryWrapper;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TaskconfigurationwriteplatformserviceImpl  implements Taskconfigurationwriteplatformservice{
	
	private final static Logger logger = LoggerFactory.getLogger(TaskWritePlatformServiceImpl.class);

	private final PlatformSecurityContext context;
	private final TaskConfigurationRepositoryWrapper taskConfigurationRepositoryWrapper;
	private final TaskDataValidator taskDataValidator;
	private final CodeValueRepositoryWrapper codeValueRepositoryWrapper;
	private final OfficeRepositoryWrapper officeRepositoryWrapper;
	private final GroupRepositoryWrapper groupRepositoryWrapper;
	private final StaffRepositoryWrapper staffRepositoryWrapper;
	private final TaskConfigurationRepository taskConfigurationRepository;

	@Autowired
	public TaskconfigurationwriteplatformserviceImpl(final PlatformSecurityContext context,
			final ClientRepositoryWrapper clientRepositoryWrapper, TaskConfigurationRepositoryWrapper taskConfigurationRepositoryWrapper,
			final TaskDataValidator taskDataValidator, final CodeValueRepositoryWrapper codeValueRepositoryWrapper,
			final OfficeRepositoryWrapper officeRepositoryWrapper, final GroupRepositoryWrapper groupRepositoryWrapper,
			final StaffRepositoryWrapper staffRepositoryWrapper, final LoanProductRepository loanProductRepository,
			final TaskConfigurationRepository taskConfigurationRepository) {
		this.context = context;
		this.codeValueRepositoryWrapper = codeValueRepositoryWrapper;
		this.taskConfigurationRepositoryWrapper = taskConfigurationRepositoryWrapper;
		this.groupRepositoryWrapper = groupRepositoryWrapper;
		this.staffRepositoryWrapper = staffRepositoryWrapper;
		this.officeRepositoryWrapper = officeRepositoryWrapper;
		this.taskDataValidator = taskDataValidator;
		this.taskConfigurationRepository=taskConfigurationRepository;

	}

	@Transactional
	@Override
	public CommandProcessingResult createTaskConfiguration(final JsonCommand command) {

		this.context.authenticatedUser();
		taskDataValidator.validateForCreatetaskconfiguration(command);

		try {

			final Long taskTypeId = command.longValueOfParameterNamed(TaskConfigurationApiConstant.tasktypeIdparamname);
			if(taskTypeId==null){
				throw new FieldCannotbeBlankException("taskTypeId");
			}
			final CodeValue taskType = this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(taskTypeId);
			final Integer noofdays = command.integerValueOfParameterNamed(TaskConfigurationApiConstant.noofdaysparamname);
			if(Integer.valueOf(noofdays)==null){
				throw new FieldCannotbeBlankException("noofdays");
			}
			final Long completedById=command.longValueOfParameterNamed(TaskConfigurationApiConstant.completedbyparamname);
			if(completedById==null){
				throw new FieldCannotbeBlankException("completedById");
			}
			final CodeValue completedBy=this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(completedById);
			final Integer centerType=command.integerValueOfParameterNamed(TaskConfigurationApiConstant.centertypeparamname);
			if(centerType==null){
				throw new FieldCannotbeBlankException("centerType");
			}
			TaskConfiguration taskConfigurationcheck =null;
			taskConfigurationcheck=this.taskConfigurationRepository.findTask(taskTypeId, centerType);
			if(taskConfigurationcheck!=null){
				throw new TaskConfigurationExistException (taskType.label());
			}
			final Long nooftask = command.longValueOfParameterNamed(TaskConfigurationApiConstant.nooftaskparamname);
			if(nooftask==null){
				throw new TaskConfigurationExistException("nooftask");
			}

			TaskConfiguration taskConfiguration = TaskConfiguration.createTaskConfiguration(taskType, noofdays, completedBy, centerType,nooftask);
					
			this.taskConfigurationRepositoryWrapper.save(taskConfiguration);
			return new CommandProcessingResultBuilder() //
					.withCommandId(command.commandId()) //
					.withEntityId(taskConfiguration.getId()) //
					.build();
		} catch (final DataIntegrityViolationException dve) {
			handleClientIdentifierDataIntegrityViolation(null, null, dve);
			return CommandProcessingResult.empty();
		}
	}
	
	@Transactional
	@Override
	public CommandProcessingResult updateTaskConfiguration(final Long taskconfigurationId, final JsonCommand command) {

		try {
			this.context.authenticatedUser();
			final TaskConfiguration taskConfigurationforUpdate = this.taskConfigurationRepositoryWrapper.findOneWithNotFoundDetection(taskconfigurationId);
			if (taskConfigurationforUpdate != null) {
				//this.taskDataValidator.validateUpdate(command);
				final Map<String, Object> changes = taskConfigurationforUpdate.update(command);

				if (changes.containsKey(TaskConfigurationApiConstant.completedbyparamname)) {

					final Long newValue = command.longValueOfParameterNamed(TaskConfigurationApiConstant.completedbyparamname);
					final CodeValue completedby=this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(newValue);
					taskConfigurationforUpdate.updateCompletedby(completedby);
				}
				if (changes.containsKey(TaskConfigurationApiConstant.tasktypeIdparamname)) {

					final Long newValue = command.longValueOfParameterNamed(TaskConfigurationApiConstant.tasktypeIdparamname);
					final CodeValue taskType=this.codeValueRepositoryWrapper.findOneWithNotFoundDetection(newValue);
					taskConfigurationforUpdate.updateTaskType(taskType);
				}
				
				this.taskConfigurationRepositoryWrapper.saveAndFlush(taskConfigurationforUpdate);
			}
			return new CommandProcessingResultBuilder() //
					.withCommandId(command.commandId()) //
					.withEntityId(taskConfigurationforUpdate.getId()) //
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

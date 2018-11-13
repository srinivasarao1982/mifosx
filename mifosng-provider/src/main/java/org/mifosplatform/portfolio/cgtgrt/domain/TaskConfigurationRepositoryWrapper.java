package org.mifosplatform.portfolio.cgtgrt.domain;

import org.mifosplatform.portfolio.cgtgrt.exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TaskConfigurationRepositoryWrapper {
	
	private final TaskConfigurationRepository repository;

    @Autowired
    public TaskConfigurationRepositoryWrapper(final TaskConfigurationRepository repository) {
        this.repository = repository;
    }

    public TaskConfiguration findOneWithNotFoundDetection(final Long id) {
        final TaskConfiguration taskConfiguration = this.repository.findOne(id);
        if (taskConfiguration == null) { throw new TaskNotFoundException(id); }
        return taskConfiguration;
    }

   
    
    public void save(final TaskConfiguration taskConfiguration) {
        this.repository.save(taskConfiguration);
    }

    public void saveAndFlush(final TaskConfiguration taskConfiguration) {
        this.repository.saveAndFlush(taskConfiguration);
    }

    public void delete(final TaskConfiguration taskConfiguration) {
        this.repository.delete(taskConfiguration);
    }

}

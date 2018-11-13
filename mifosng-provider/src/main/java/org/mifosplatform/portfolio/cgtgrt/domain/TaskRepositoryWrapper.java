package org.mifosplatform.portfolio.cgtgrt.domain;

import org.mifosplatform.portfolio.cgtgrt.exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TaskRepositoryWrapper {
	 private final TaskRepository repository;

	    @Autowired
	    public TaskRepositoryWrapper(final TaskRepository repository) {
	        this.repository = repository;
	    }

	    public Tasks findOneWithNotFoundDetection(final Long id) {
	        final Tasks task = this.repository.findOne(id);
	        if (task == null) { throw new TaskNotFoundException(id); }
	        return task;
	    }

	   
	    
	    public void save(final Tasks task) {
	        this.repository.save(task);
	    }

	    public void saveAndFlush(final Tasks task) {
	        this.repository.saveAndFlush(task);
	    }

	    public void delete(final Tasks task) {
	        this.repository.delete(task);
	    }

}




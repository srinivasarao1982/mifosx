package org.mifosplatform.portfolio.rblvalidation.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SendFileRepositoryWrapper {
	private final SendFileRepository repository;

    @Autowired
    public SendFileRepositoryWrapper(final SendFileRepository repository) {
        this.repository = repository;
    }
    
    public void save(final SendFileRecord sendFileRecord) {
    	this.repository.save(sendFileRecord);
    }

    public void saveAndFlush(final SendFileRecord sendFileRecord) {
    	this.saveAndFlush(sendFileRecord);
    }

    public void delete(final SendFileRecord sendFileRecord) {
    	this.saveAndFlush(sendFileRecord);
    }

}

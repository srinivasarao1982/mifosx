package org.mifosplatform.portfolio.rblvalidation.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ReceiveFileRepositoryWrapper {
	private final ReceiveFileRepository repository;

    @Autowired
    public ReceiveFileRepositoryWrapper(final ReceiveFileRepository repository) {
        this.repository = repository;
    }
    
    public void save(final ReceiveFileRecord receiveFileRecord) {
    	this.repository.save(receiveFileRecord);
    }

    public void saveAndFlush(final ReceiveFileRecord receiveFileRecord) {
    	this.saveAndFlush(receiveFileRecord);
    }

    public void delete(final ReceiveFileRecord receiveFileRecord) {
    	this.saveAndFlush(receiveFileRecord);
    }


}

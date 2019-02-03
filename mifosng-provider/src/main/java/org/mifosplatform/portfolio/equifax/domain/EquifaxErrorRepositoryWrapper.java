package org.mifosplatform.portfolio.equifax.domain;

import org.mifosplatform.portfolio.equifax.exception.EquifaxErrorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquifaxErrorRepositoryWrapper {
	
	private final EquifaxErrorRepository repository;

    @Autowired
    public EquifaxErrorRepositoryWrapper(final EquifaxErrorRepository repository) {
        this.repository = repository;
    }

    public EquifaxError findOneWithNotFoundDetection(final Long id) {
        final EquifaxError equifaxError = this.repository.findOne(id);
        if (equifaxError == null) { throw new EquifaxErrorNotFoundException(id); }
        return equifaxError;
    }

    public void save(final EquifaxError equifaxError) {
        this.repository.save(equifaxError);
    }

    public void saveAndFlush(final EquifaxError equifaxError) {
        this.repository.saveAndFlush(equifaxError);
    }

    public void delete(final EquifaxError equifaxError) {
        this.repository.delete(equifaxError);
    }

}

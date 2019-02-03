package org.mifosplatform.portfolio.equifax.domain;


import org.mifosplatform.portfolio.equifax.exception.EquifaxRequestNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EquifaxRequestRepositoryWrapper {
	
	private final EquifaxRequestRepository repository;

    @Autowired
    public EquifaxRequestRepositoryWrapper(final EquifaxRequestRepository repository) {
        this.repository = repository;
    }

    public EquifaxRequest findOneWithNotFoundDetection(final Long id) {
        final EquifaxRequest equifaxRequest = this.repository.findOne(id);
        if (equifaxRequest == null) { throw new EquifaxRequestNotFoundException(id); }
        return equifaxRequest;
    }

    public void save(final EquifaxRequest equifaxRequest) {
        this.repository.save(equifaxRequest);
    }

    public void saveAndFlush(final EquifaxRequest equifaxRequest) {
        this.repository.saveAndFlush(equifaxRequest);
    }

    public void delete(final EquifaxRequest equifaxRequest) {
        this.repository.delete(equifaxRequest);
    }


}

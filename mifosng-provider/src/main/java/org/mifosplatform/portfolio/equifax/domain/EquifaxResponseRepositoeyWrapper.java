package org.mifosplatform.portfolio.equifax.domain;

import org.mifosplatform.portfolio.equifax.exception.EquifaxResponseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EquifaxResponseRepositoeyWrapper {
	
	private final EquifaxResponseRepository repository;

    @Autowired
    public EquifaxResponseRepositoeyWrapper(final EquifaxResponseRepository repository) {
        this.repository = repository;
    }

    public EquifaxResponse findOneWithNotFoundDetection(final Long id) {
        final EquifaxResponse equifaxResponse = this.repository.findOne(id);
        if (equifaxResponse == null) { throw new EquifaxResponseNotFoundException(id); }
        return equifaxResponse;
    }

    public void save(final EquifaxResponse equifaxResponse) {
        this.repository.save(equifaxResponse);
    }

    public void saveAndFlush(final EquifaxResponse equifaxResponse) {
        this.repository.saveAndFlush(equifaxResponse);
    }

    public void delete(final EquifaxResponse equifaxResponse) {
        this.repository.delete(equifaxResponse);
    }

}

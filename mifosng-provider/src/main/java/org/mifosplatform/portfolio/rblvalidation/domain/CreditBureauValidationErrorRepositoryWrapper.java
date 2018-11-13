package org.mifosplatform.portfolio.rblvalidation.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CreditBureauValidationErrorRepositoryWrapper {
	private final CreditBureauValidationErrorRepository repository;

    @Autowired
    public CreditBureauValidationErrorRepositoryWrapper(final CreditBureauValidationErrorRepository repository) {
        this.repository = repository;
    }
    
    public void save(final CreditBureauValidationError creditBureauValidationError) {
        this.repository.save(creditBureauValidationError);
    }

    public void saveAndFlush(final CreditBureauValidationError creditBureauValidationError) {
        this.repository.saveAndFlush(creditBureauValidationError);
    }

    public void delete(final CreditBureauValidationError creditBureauValidationError) {
        this.repository.delete(creditBureauValidationError);
    }


}

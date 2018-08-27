package org.mifosplatform.portfolio.rblvalidation.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CredeitBureauResponseRepositoryWrapper {
	private final CreditBureauResponseRepository repository;

    @Autowired
    public CredeitBureauResponseRepositoryWrapper(final CreditBureauResponseRepository repository) {
        this.repository = repository;
    }
    
    public void save(final CreditBureaoResponse creditBureaoResponse) {
        this.repository.save(creditBureaoResponse);
    }

    public void saveAndFlush(final CreditBureaoResponse creditBureaoResponse) {
        this.repository.saveAndFlush(creditBureaoResponse);
    }

    public void delete(final CreditBureaoResponse creditBureaoResponse) {
        this.repository.delete(creditBureaoResponse);
    }

}

package org.mifosplatform.portfolio.rblvalidation.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CreditBureauRequestRepositoryWrapper {

	private final CreditBureauRequestRepository repository;

    @Autowired
    public CreditBureauRequestRepositoryWrapper(final CreditBureauRequestRepository repository) {
        this.repository = repository;
    }
    
    public void save(final CreditBureaRequest creditBureaRequest) {
        this.repository.save(creditBureaRequest);
    }

    public void saveAndFlush(final CreditBureaRequest creditBureaRequest) {
        this.repository.saveAndFlush(creditBureaRequest);
    }

    public void delete(final CreditBureaRequest creditBureaRequest) {
        this.repository.delete(creditBureaRequest);
    }
}

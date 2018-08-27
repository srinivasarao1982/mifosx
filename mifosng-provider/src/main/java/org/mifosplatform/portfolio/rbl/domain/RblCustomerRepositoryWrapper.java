package org.mifosplatform.portfolio.rbl.domain;

import org.mifosplatform.portfolio.loanaccount.exception.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RblCustomerRepositoryWrapper{
	
	private final RblCustomerRepository repository;

    @Autowired
    public RblCustomerRepositoryWrapper(final RblCustomerRepository repository) {
        this.repository = repository;
    }

    public RblCustomer findOneWithNotFoundDetection(final Long id) {
        final RblCustomer rblCustomer = this.repository.findOne(id);
        if (rblCustomer == null) { throw new LoanNotFoundException(id); }
        return rblCustomer;
    }

  
    
    public void save(final RblCustomer rblCustomer) {
        this.repository.save(rblCustomer);
    }

    public void saveAndFlush(final RblCustomer rblCustomer) {
        this.repository.saveAndFlush(rblCustomer);
    }

    public void delete(final RblCustomer rblCustomer) {
        this.repository.delete(rblCustomer);
    }

	


}

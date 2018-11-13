package org.mifosplatform.portfolio.rbl.domain;

import org.mifosplatform.portfolio.loanaccount.exception.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RblLoanRepositoryWrapper  {
	
	private final RblLoanRepository repository;

    @Autowired
    public RblLoanRepositoryWrapper(final RblLoanRepository repository) {
        this.repository = repository;
    }

    public RblLoan findOneWithNotFoundDetection(final Long id) {
        final RblLoan rblGroup = this.repository.findByLoanId(id);
        if (rblGroup == null) { throw new LoanNotFoundException(id); }
        return rblGroup;
    }

  
    
    public void save(final RblLoan rblLoan) {
        this.repository.save(rblLoan);
    }

    public void saveAndFlush(final RblLoan rblLoan) {
        this.repository.saveAndFlush(rblLoan);
    }

    public void delete(final RblLoan rblLoan) {
        this.repository.delete(rblLoan);
    }

}

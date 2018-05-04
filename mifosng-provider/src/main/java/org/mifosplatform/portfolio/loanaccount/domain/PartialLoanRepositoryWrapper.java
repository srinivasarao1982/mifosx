package org.mifosplatform.portfolio.loanaccount.domain;


import org.mifosplatform.portfolio.loanaccount.exception.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PartialLoanRepositoryWrapper {
	 private final PartialLoanRepository repository;

	    @Autowired
	    public PartialLoanRepositoryWrapper(final PartialLoanRepository repository) {
	        this.repository = repository;
	    }

	    public PartialLoan findOneWithNotFoundDetection(final Long id) {
	        final PartialLoan partialLoan = this.repository.findOne(id);
	        if (partialLoan == null) { throw new LoanNotFoundException(id); }
	        return partialLoan;
	    }

	    public PartialLoan findActiveLoansByLoanIdAndGroupId(Long clientId, Long groupId,int status) {
	         final PartialLoan partialLoan = this.repository.findByClientIdAndGroupIdAndLoanStatus(clientId, groupId, status);
	        return partialLoan;
	    }
	    
	    public void save(final PartialLoan partialLoan) {
	        this.repository.save(partialLoan);
	    }

	    public void saveAndFlush(final PartialLoan partialLoan) {
	        this.repository.saveAndFlush(partialLoan);
	    }

	    public void delete(final PartialLoan partialLoan) {
	        this.repository.delete(partialLoan);
	    }

}

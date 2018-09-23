package org.mifosplatform.portfolio.rbl.domain;

import org.mifosplatform.portfolio.loanaccount.exception.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RblCenterRepositoryWrapper {

	 private final RblCenterRepository repository;

	    @Autowired
	    public RblCenterRepositoryWrapper(final RblCenterRepository repository) {
	        this.repository = repository;
	    }

	    public RblCenter findOneWithNotFoundDetection(final Long id) {
	        final RblCenter rblCenter = this.repository.findByCenterId(id);
	        if (rblCenter == null) { throw new LoanNotFoundException(id); }
	        return rblCenter;
	    }

	  
	    
	    public void save(final RblCenter rblCenter) {
	        this.repository.save(rblCenter);
	    }

	    public void saveAndFlush(final RblCenter rblCenter) {
	        this.repository.saveAndFlush(rblCenter);
	    }

	    public void delete(final RblCenter rblCenter) {
	        this.repository.delete(rblCenter);
	    }

		

	
}

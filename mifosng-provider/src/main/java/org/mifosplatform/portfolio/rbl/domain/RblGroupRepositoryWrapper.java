package org.mifosplatform.portfolio.rbl.domain;

import org.mifosplatform.portfolio.loanaccount.exception.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RblGroupRepositoryWrapper {
	
	private final RblGroupRepository repository;

    @Autowired
    public RblGroupRepositoryWrapper(final RblGroupRepository repository) {
        this.repository = repository;
    }

    public RblGroup findOneWithNotFoundDetection(final Long id) {
        final RblGroup rblGroup = this.repository.findByGroupId(id);
        if (rblGroup == null) { throw new LoanNotFoundException(id); }
        return rblGroup;
    }

  
    
    public void save(final RblGroup rblGroup) {
        this.repository.save(rblGroup);
    }

    public void saveAndFlush(final RblGroup rblGroup) {
        this.repository.saveAndFlush(rblGroup);
    }

    public void delete(final RblGroup rblGroup) {
        this.repository.delete(rblGroup);
    }
}

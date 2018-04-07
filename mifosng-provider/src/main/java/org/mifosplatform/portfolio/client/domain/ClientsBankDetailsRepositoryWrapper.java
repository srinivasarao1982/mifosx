package org.mifosplatform.portfolio.client.domain;

import org.mifosplatform.portfolio.client.exception.BankDetailsFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ClientsBankDetailsRepositoryWrapper {
	
	  private final ClientsBankDetailsRepositiry repository;

	    @Autowired
	    public ClientsBankDetailsRepositoryWrapper(final ClientsBankDetailsRepositiry repository) {
	        this.repository = repository;
	    }

	    public ClientBankDetails findOneWithNotFoundDetection(final Long id) {
	        final ClientBankDetails clientBankDetails = this.repository.findOne(id);
	        if (clientBankDetails == null) { throw new BankDetailsFoundException(id); }
	        return clientBankDetails;
	    }

	    public void save(final ClientBankDetails clientBankDetails) {
	        this.repository.save(clientBankDetails);
	    }

	    public void saveAndFlush(final ClientBankDetails clientBankDetails) {
	        this.repository.saveAndFlush(clientBankDetails);
	    }

	    public void delete(final ClientBankDetails clientBankDetails) {
	        this.repository.delete(clientBankDetails);
	    }


}

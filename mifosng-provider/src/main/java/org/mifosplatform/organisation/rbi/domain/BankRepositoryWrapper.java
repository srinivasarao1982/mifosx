package org.mifosplatform.organisation.rbi.domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankRepositoryWrapper {
	private BankRepository bankRepository;
    
	@Autowired
	public BankRepositoryWrapper(final BankRepository bankRepository){
		this.bankRepository = bankRepository;
	}
	public void save(final Bank bank) {
        this.bankRepository.save(bank);
    }
	
	public void saveAndFlush(final Bank bank) {
        this.bankRepository.saveAndFlush(bank);
    }
	
	public void delete(final Bank bank) {
	    this.bankRepository.delete(bank);
	}
}

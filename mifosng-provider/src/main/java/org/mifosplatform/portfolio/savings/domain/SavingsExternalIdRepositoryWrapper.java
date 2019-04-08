package org.mifosplatform.portfolio.savings.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsExternalIdRepositoryWrapper {
  private final SavingsExternalIdRepository repository;

  @Autowired
  public SavingsExternalIdRepositoryWrapper(final SavingsExternalIdRepository repository) {
    this.repository = repository;
  }


  public void save(final SavingsExternalId savingsExternalId) {
    this.repository.save(savingsExternalId);
  }

  public void delete(final SavingsExternalId savingsExternalId) {
    this.repository.delete(savingsExternalId);
  }

  public void saveAndFlush(final SavingsExternalId savingsExternalId) {
    this.repository.saveAndFlush(savingsExternalId);
  }

}

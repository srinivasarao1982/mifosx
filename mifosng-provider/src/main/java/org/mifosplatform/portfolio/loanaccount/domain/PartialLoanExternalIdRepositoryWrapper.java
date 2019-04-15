package org.mifosplatform.portfolio.loanaccount.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartialLoanExternalIdRepositoryWrapper {
  private final PartialLoanExternalIdRepository repository;

  @Autowired
  public PartialLoanExternalIdRepositoryWrapper(final PartialLoanExternalIdRepository repository) {
    this.repository = repository;
  }

  public void save(final PartialLoanExternalId partialLoanExternalId) {
    this.repository.save(partialLoanExternalId);
  }

  public void saveAndFlush(final PartialLoanExternalId partialLoanExternalId) {
    this.repository.saveAndFlush(partialLoanExternalId);
  }

  public void delete(final PartialLoanExternalId partialLoanExternalId) {
    this.repository.delete(partialLoanExternalId);
  }

}

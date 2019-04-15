package org.mifosplatform.portfolio.group.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterExternalIdRepositoryWrapper {

  private final CenterExternalIdRepository repository;

  @Autowired
  public CenterExternalIdRepositoryWrapper(final CenterExternalIdRepository repository) {
    this.repository = repository;
  }

  public void save(final CenterExternalId centerExternalId) {
    this.repository.save(centerExternalId);
  }

  public void saveAndFlush(final CenterExternalId centerExternalId) {
    this.repository.saveAndFlush(centerExternalId);
  }

  public void delete(final CenterExternalId centerExternalId) {
    this.repository.delete(centerExternalId);
  }

}

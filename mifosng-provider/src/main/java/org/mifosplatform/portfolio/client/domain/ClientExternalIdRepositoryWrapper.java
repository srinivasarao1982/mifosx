package org.mifosplatform.portfolio.client.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientExternalIdRepositoryWrapper {
  private final ClientExternalIdRepository repository;

  @Autowired
  public ClientExternalIdRepositoryWrapper(final ClientExternalIdRepository repository) {
    this.repository = repository;
  }

  public void save(final ClientExternalId clientExternalId) {
    this.repository.save(clientExternalId);
  }

  public void saveAndFlush(final ClientExternalId clientExternalId) {
    this.repository.saveAndFlush(clientExternalId);
  }

  public void delete(final ClientExternalId clientExternalId) {
    this.repository.delete(clientExternalId);
  }


}

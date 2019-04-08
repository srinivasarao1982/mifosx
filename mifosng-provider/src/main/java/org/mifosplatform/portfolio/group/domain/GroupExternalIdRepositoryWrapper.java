package org.mifosplatform.portfolio.group.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupExternalIdRepositoryWrapper {

  private final GroupExternalIdRepository repository;

  @Autowired
  public GroupExternalIdRepositoryWrapper(final GroupExternalIdRepository repository) {
    this.repository = repository;
  }
  public void save(final GroupExternalId centerExternalId) {
    this.repository.save(centerExternalId);
  }

  public void saveAndFlush(final GroupExternalId centerExternalId) {
    this.repository.saveAndFlush(centerExternalId);
  }

  public void delete(final GroupExternalId centerExternalId) {
    this.repository.delete(centerExternalId);
  }

}

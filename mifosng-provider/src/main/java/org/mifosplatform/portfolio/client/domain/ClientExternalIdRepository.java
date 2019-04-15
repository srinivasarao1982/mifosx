package org.mifosplatform.portfolio.client.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientExternalIdRepository extends JpaRepository<ClientExternalId, Long>, JpaSpecificationExecutor<ClientExternalId> {
    // no added behaviour
}
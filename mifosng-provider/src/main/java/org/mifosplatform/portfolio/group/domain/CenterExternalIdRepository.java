package org.mifosplatform.portfolio.group.domain;

import org.mifosplatform.portfolio.client.domain.ClientExternalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CenterExternalIdRepository extends JpaRepository<CenterExternalId, Long>, JpaSpecificationExecutor<CenterExternalId> {
    // no added behaviour
} 
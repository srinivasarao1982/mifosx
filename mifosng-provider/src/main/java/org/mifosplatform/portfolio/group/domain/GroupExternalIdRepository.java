package org.mifosplatform.portfolio.group.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupExternalIdRepository extends JpaRepository<GroupExternalId, Long>, JpaSpecificationExecutor<GroupExternalId> {
    // no added behaviour
} 
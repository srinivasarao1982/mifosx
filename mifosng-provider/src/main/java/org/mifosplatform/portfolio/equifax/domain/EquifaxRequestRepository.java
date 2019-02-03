package org.mifosplatform.portfolio.equifax.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquifaxRequestRepository extends JpaRepository<EquifaxRequest, Long>, JpaSpecificationExecutor<EquifaxRequest> {
    // no added behaviour
}

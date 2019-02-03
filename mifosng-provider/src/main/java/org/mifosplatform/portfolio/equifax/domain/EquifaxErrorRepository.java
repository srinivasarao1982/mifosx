package org.mifosplatform.portfolio.equifax.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquifaxErrorRepository  extends JpaRepository<EquifaxError, Long>, JpaSpecificationExecutor<EquifaxError> {
    // no added behaviour
} 

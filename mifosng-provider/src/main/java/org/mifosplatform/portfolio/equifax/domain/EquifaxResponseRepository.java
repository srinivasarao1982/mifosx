package org.mifosplatform.portfolio.equifax.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquifaxResponseRepository  extends JpaRepository<EquifaxResponse, Long>, JpaSpecificationExecutor<EquifaxResponse> {
    // no added behaviour
}

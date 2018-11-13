package org.mifosplatform.portfolio.rblvalidation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CreditBureauRequestRepository extends JpaRepository<CreditBureaRequest, Long>, JpaSpecificationExecutor<CreditBureaRequest> {


}

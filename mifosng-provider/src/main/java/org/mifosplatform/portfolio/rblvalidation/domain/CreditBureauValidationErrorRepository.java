package org.mifosplatform.portfolio.rblvalidation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CreditBureauValidationErrorRepository  extends JpaRepository<CreditBureauValidationError, Long>, JpaSpecificationExecutor<CreditBureauValidationError> {

}


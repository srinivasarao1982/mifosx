package org.mifosplatform.portfolio.rbl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RblLoanRepository extends JpaRepository<RblLoan, Long>, JpaSpecificationExecutor<RblLoan> {


}

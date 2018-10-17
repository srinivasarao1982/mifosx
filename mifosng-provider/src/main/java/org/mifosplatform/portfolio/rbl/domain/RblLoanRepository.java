package org.mifosplatform.portfolio.rbl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RblLoanRepository extends JpaRepository<RblLoan, Long>, JpaSpecificationExecutor<RblLoan> {

	@Query("from RblLoan rblLoan where rblLoan.loanId = :loanId")
	RblLoan findByLoanId(@Param("loanId") Long loanId);

}

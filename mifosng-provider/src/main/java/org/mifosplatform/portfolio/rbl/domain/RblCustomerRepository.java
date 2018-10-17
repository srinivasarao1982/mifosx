package org.mifosplatform.portfolio.rbl.domain;

import org.mifosplatform.portfolio.loanaccount.domain.PartialLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RblCustomerRepository extends JpaRepository<RblCustomer, Long>, JpaSpecificationExecutor<RblCustomer> {

	@Query("from RblCustomer rblCustomer where rblCustomer.client.id = :clientId")
	RblCustomer findByClientId(@Param("clientId") Long clientId);

}

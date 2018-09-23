package org.mifosplatform.portfolio.loanaccount.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartialLoanRepository extends JpaRepository<PartialLoan, Long>, JpaSpecificationExecutor<PartialLoan> {
	
	@Query("from PartialLoan partialLoan where partialLoan.client.id = :clientId and partialLoan.group.id = :groupId and partialLoan.isActive =:status and partialLoan.isDisubrse =:isDisburse")
	PartialLoan findByClientIdAndGroupIdAndLoanStatus(@Param("clientId") Long clientId,@Param("groupId") Long groupId,@Param("status") int status,@Param("isDisburse") int isDisburse);


}

package org.mifosplatform.portfolio.loanaccount.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PartialLoanExternalIdRepository extends JpaRepository<PartialLoanExternalId, Long>, JpaSpecificationExecutor<PartialLoanExternalId> {

}

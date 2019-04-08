package org.mifosplatform.portfolio.savings.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SavingsExternalIdRepository   extends JpaRepository<SavingsExternalId, Long>,
JpaSpecificationExecutor<SavingsExternalId> {


}

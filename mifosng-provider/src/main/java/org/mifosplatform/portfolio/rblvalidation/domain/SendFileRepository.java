package org.mifosplatform.portfolio.rblvalidation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SendFileRepository extends JpaRepository<SendFileRecord, Long>, JpaSpecificationExecutor<SendFileRecord> {

}


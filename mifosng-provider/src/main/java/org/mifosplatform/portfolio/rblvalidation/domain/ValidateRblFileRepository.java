package org.mifosplatform.portfolio.rblvalidation.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ValidateRblFileRepository  extends JpaRepository<ValidatefileRecord, Long>, JpaSpecificationExecutor<ValidatefileRecord> {


}

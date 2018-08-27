package org.mifosplatform.portfolio.rbl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RblCenterRepository extends JpaRepository<RblCenter, Long>, JpaSpecificationExecutor<RblCenter> {


}

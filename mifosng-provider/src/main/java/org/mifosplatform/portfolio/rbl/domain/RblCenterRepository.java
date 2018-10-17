package org.mifosplatform.portfolio.rbl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RblCenterRepository extends JpaRepository<RblCenter, Long>, JpaSpecificationExecutor<RblCenter> {

	@Query("from RblCenter rblCenter where rblCenter.centerId = :centerId")
	RblCenter findByCenterId(@Param("centerId") Long centerId);

}

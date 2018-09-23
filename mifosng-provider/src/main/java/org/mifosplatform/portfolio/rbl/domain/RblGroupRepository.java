package org.mifosplatform.portfolio.rbl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RblGroupRepository extends JpaRepository<RblGroup, Long>, JpaSpecificationExecutor<RblGroup> {

	@Query("from RblGroup rblGroup where rblGroup.groupId = :groupId")
	RblGroup findByGroupId(@Param("groupId") Long groupId);

}

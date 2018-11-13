package org.mifosplatform.portfolio.cgtgrt.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskConfigurationRepository extends JpaRepository<TaskConfiguration, Long>, JpaSpecificationExecutor<TaskConfiguration> {
	
	@Query("from TaskConfiguration taskConfiguration where taskConfiguration.tasktype.id = :tasktype and  taskConfiguration.centerType =:centertype")
	TaskConfiguration findTask(@Param("tasktype") Long tasktype ,@Param("centertype") Integer centertype);

	@Query("from TaskConfiguration taskConfiguration where taskConfiguration.order = :orderNo and  taskConfiguration.centerType =:centertype")
	TaskConfiguration findTaskByOrderNo(@Param("orderNo") Long orderNo ,@Param("centertype") Integer centertype);

}
 

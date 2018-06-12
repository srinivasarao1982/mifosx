package org.mifosplatform.portfolio.cgtgrt.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Tasks, Long>, JpaSpecificationExecutor<Tasks> {
	@Query("from Tasks tasks where tasks.group.id = :centerId and tasks.tasktype.id=:tasktype and tasks.taskstatus=:taskstatus")
    Tasks findTask(@Param("centerId") Long centerId,@Param("tasktype") Long tasktype ,@Param("taskstatus") Integer taskstatus);
   
	@Query("from Tasks tasks where tasks.group.id = :centerId and tasks.tasktype.id=:tasktype ")
    List<Tasks> findNoOfTask(@Param("centerId") Long centerId,@Param("tasktype") Long tasktype);

	
}

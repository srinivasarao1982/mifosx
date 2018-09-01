package org.mifosplatform.portfolio.rblvalidation.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ReceiveFileRepository extends JpaRepository<ReceiveFileRecord, Long>, JpaSpecificationExecutor<ReceiveFileRecord> {


    @Query("from  ReceiveFileRecord receiveFileRecord where receiveFileRecord.fileName =:receiveFile")
    List<ReceiveFileRecord> getExistingFile(@Param("receiveFile") String receiveFile);
}
 

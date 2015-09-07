package org.nirantara.client.ext.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NomineeDetailsRepository extends JpaRepository<NomineeDetails, Long>, JpaSpecificationExecutor<NomineeDetails> {

}

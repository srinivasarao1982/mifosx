package org.nirantara.client.ext.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoapplicantRepository extends JpaRepository<Coapplicant, Long>, JpaSpecificationExecutor<NomineeDetails> {

}

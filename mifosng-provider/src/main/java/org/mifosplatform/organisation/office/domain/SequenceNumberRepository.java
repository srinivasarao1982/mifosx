package org.mifosplatform.organisation.office.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SequenceNumberRepository extends JpaRepository<OrganasitionSequenceNumber, Long>,
JpaSpecificationExecutor<OrganasitionSequenceNumber> {

}

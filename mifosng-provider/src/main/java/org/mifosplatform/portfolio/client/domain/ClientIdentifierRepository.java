/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.client.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ClientIdentifierRepository extends JpaRepository<ClientIdentifier, Long>, JpaSpecificationExecutor<ClientIdentifier> {
    // no behaviour
	@Query("from  ClientIdentifier clientIdentifier where clientIdentifier.documentType =:documettype and clientIdentifier.documentKey =:documetkey")
	  List<ClientIdentifier>getclientIdentifier(Long documettype,String  documetkey );
}
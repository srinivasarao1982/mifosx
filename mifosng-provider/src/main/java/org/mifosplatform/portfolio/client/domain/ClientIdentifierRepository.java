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
import org.springframework.data.repository.query.Param;

public interface ClientIdentifierRepository extends JpaRepository<ClientIdentifier, Long>, JpaSpecificationExecutor<ClientIdentifier> {
    // no behaviour
	@Query("from  ClientIdentifier clientIdentifier where clientIdentifier.documentType.id =:documettype and clientIdentifier.documentKey =:documentKey")
	  List<ClientIdentifier>getclientIdentifier(@Param ("documettype")Long documettype,@Param("documentKey") String  documentKey );
	
	@Query("from  ClientIdentifier clientIdentifier where clientIdentifier.documentType.id =:documettype and clientIdentifier.documentKey =:documentKey and clientIdentifier.client.id =:clientId")
	  List<ClientIdentifier>getclientIdentifierwithclient(@Param ("documettype")Long documettype,@Param("documentKey") String  documentKey,@Param ("clientId")Long clientId );

}
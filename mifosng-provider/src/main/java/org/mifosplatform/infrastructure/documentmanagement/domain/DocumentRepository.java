/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.infrastructure.documentmanagement.domain;

import java.util.List;

import org.mifosplatform.portfolio.rblvalidation.domain.ReceiveFileRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {
	 @Query("from  Document document where document.parentEntityId =:parentEntityIds")
	    List<Document> getnoFile(@Param("parentEntityIds") Long parentEntityIds);

    // no added behaviour
}
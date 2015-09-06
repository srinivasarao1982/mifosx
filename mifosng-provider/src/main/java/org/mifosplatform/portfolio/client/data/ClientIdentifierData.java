/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.client.data;

import java.util.Collection;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.ClientIdentifier;

/**
 * Immutable data object represent client identity data.
 */
public class ClientIdentifierData {

    private final Long id;
    private final Long clientId;
    private final CodeValueData documentType;
    private final String documentKey;
    private final String description;
    private final Collection<CodeValueData> allowedDocumentTypes;
    private final Long documentTypeId;
    private final String documentTypeLabel;

    public static ClientIdentifierData singleItem(final Long id, final Long clientId, final CodeValueData documentType,
            final String documentKey, final String description) {
        return new ClientIdentifierData(id, clientId, documentType, documentKey, description, null, null, null);
    }

    public static ClientIdentifierData template(final Collection<CodeValueData> codeValues) {
        return new ClientIdentifierData(null, null, null, null, null, codeValues, null, null);
    }

    public static ClientIdentifierData template(final ClientIdentifierData data, final Collection<CodeValueData> codeValues) {
        return new ClientIdentifierData(data.id, data.clientId, data.documentType, data.documentKey, data.description, codeValues, null, null);
    }

    public ClientIdentifierData(final Long id, final Long clientId, final CodeValueData documentType, final String documentKey,
            final String description, final Collection<CodeValueData> allowedDocumentTypes,final Long documentTypeId,final String documentTypeLabel) {
        this.id = id;
        this.clientId = clientId;
        this.documentType = documentType;
        this.documentKey = documentKey;
        this.description = description;
        this.allowedDocumentTypes = allowedDocumentTypes;
        this.documentTypeId = documentTypeId;
        this.documentTypeLabel = documentTypeLabel;
    }

	public static ClientIdentifierData fromData(final ClientIdentifier clientIdentifier) {
		Long id = clientIdentifier.getId();
		final CodeValue documentType = clientIdentifier.getDocumentType();
		Long documentTypeId = null;
		if(clientIdentifier.documentTypeId() != null){
			documentTypeId = clientIdentifier.documentTypeId();
		}
		final String documentKey = clientIdentifier.documentKey();	
		return new ClientIdentifierData(id, null, null, documentKey, null, null, documentTypeId,documentType.label());
	}

	public Long getId() {
		return id;
	}

	public Long getClientId() {
		return clientId;
	}

	public CodeValueData getDocumentType() {
		return documentType;
	}

	public String getDocumentKey() {
		return documentKey;
	}

	public String getDescription() {
		return description;
	}

	public Collection<CodeValueData> getAllowedDocumentTypes() {
		return allowedDocumentTypes;
	}

	public Long getDocumentTypeId() {
		return documentTypeId;
	}

	public String getDocumentTypeLabel() {
		return documentTypeLabel;
	}
}
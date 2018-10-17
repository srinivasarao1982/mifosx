package org.mifosplatform.infrastructure.documentmanagement.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class DuplicateDocumentNameFoundException extends AbstractPlatformResourceNotFoundException {

    public DuplicateDocumentNameFoundException(final String entityType, final Long entityId, final String fileName) {
        super("Duplicate Document Name Found with name : "+ fileName + " Please verify if the same document is already uploaded","" );
    }
}

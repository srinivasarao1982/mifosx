package org.nirantara.client.ext.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class DuplicateExternalIdException extends AbstractPlatformResourceNotFoundException {

    public DuplicateExternalIdException(final String type) {
        super("error.msg.duplicateexternalId.key.field", type +" is Duplicate" , type);
    }
} 

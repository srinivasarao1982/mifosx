package org.nirantara.client.ext.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class DupicateDocumentException extends AbstractPlatformResourceNotFoundException {

    public DupicateDocumentException(final String documentKey) {
        super("error.msg.duplicate.document.key", "Duplicate DocumentKey " + documentKey , documentKey);
    }
} 

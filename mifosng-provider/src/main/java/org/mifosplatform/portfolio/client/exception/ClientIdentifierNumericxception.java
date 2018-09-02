package org.mifosplatform.portfolio.client.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class ClientIdentifierNumericxception extends AbstractPlatformResourceNotFoundException {

    public ClientIdentifierNumericxception(final String value) {
        super("error.msg.clintIdentifier.id.invalid", value+" value  must be numeric", value);
    }
}

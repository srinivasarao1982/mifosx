package org.nirantara.client.ext.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

/**
 * A {@link RuntimeException} thrown when Address
 * resources are not found.
 */
@SuppressWarnings("serial")
public class AddressNotFoundException extends AbstractPlatformResourceNotFoundException {

    public AddressNotFoundException(final Long id) {
        super("error.msg.address.id.invalid", "Address with identifier " + id + " does not exist", id);
    }
}

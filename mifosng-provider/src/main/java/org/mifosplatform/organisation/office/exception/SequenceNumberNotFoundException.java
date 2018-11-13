package org.mifosplatform.organisation.office.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class SequenceNumberNotFoundException  extends AbstractPlatformResourceNotFoundException {

    public SequenceNumberNotFoundException(final Long id) {
        super("error.msg.sequence.id.invalid", "Sequence  Number with identifier " + id + " does not exist", id);
    }
}

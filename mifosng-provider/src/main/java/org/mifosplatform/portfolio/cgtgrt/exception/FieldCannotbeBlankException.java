package org.mifosplatform.portfolio.cgtgrt.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class FieldCannotbeBlankException extends AbstractPlatformResourceNotFoundException {
    public FieldCannotbeBlankException(final String fieldValue ) {
        super("error.msg.field.blank.invalid", fieldValue+ " Cannot be blank" , fieldValue);
    }
} 

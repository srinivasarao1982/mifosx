package org.nirantara.client.ext.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class MandatoryFieldException  extends AbstractPlatformResourceNotFoundException {

    public MandatoryFieldException(final String type) {
        super("error.msg.mandatory.key.field", type +" is Mandatory field" , type);
    }
} 


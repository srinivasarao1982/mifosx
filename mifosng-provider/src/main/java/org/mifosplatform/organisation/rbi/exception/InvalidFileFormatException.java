package org.mifosplatform.organisation.rbi.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class InvalidFileFormatException extends AbstractPlatformResourceNotFoundException{
	public InvalidFileFormatException() {
        super("error.msg.invalid.file.format", "Invalid File Format.Only Excel file supported.","");
    }
}

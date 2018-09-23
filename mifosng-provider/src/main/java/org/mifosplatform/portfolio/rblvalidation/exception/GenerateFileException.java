package org.mifosplatform.portfolio.rblvalidation.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class GenerateFileException extends AbstractPlatformDomainRuleException {

    public GenerateFileException(final String exceptionMessage,final String type) {
        super("error.msg.file.exception" + "exceptionOcuur while generation file for "+type+" with id(+"+exceptionMessage+")",exceptionMessage ,type);
    }
}

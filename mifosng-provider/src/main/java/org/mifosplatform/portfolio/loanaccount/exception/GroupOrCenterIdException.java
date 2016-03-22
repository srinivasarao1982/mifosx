package org.mifosplatform.portfolio.loanaccount.exception;

import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class GroupOrCenterIdException extends AbstractPlatformDomainRuleException {
    public GroupOrCenterIdException() {
        super("error.msg.group.or.center:","groupId or centerId is mandatory", "groupId or centerId is mandatory");
    }

}

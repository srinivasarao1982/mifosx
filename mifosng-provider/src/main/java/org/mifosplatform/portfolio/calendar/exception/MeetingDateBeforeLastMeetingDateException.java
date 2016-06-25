package org.mifosplatform.portfolio.calendar.exception;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.exception.AbstractPlatformDomainRuleException;

public class MeetingDateBeforeLastMeetingDateException  extends AbstractPlatformDomainRuleException{
	
	 public MeetingDateBeforeLastMeetingDateException(final LocalDate date) {
	        super("validation.msg.meetingdatebeforelastmeetingDate.", "meeting date should be after  " + date , date);
	    }

}

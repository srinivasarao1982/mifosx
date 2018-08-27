package org.mifosplatform.portfolio.rblvalidation.data;

public class RblCreditReportBody {
	private RblCreditReportReply getConsumerCreditReportReply;

	public RblCreditReportReply getGetConsumerCreditReportReply() {
		return getConsumerCreditReportReply;
	}

	public void setGetConsumerCreditReportReply(RblCreditReportReply getConsumerCreditReportReply) {
		this.getConsumerCreditReportReply = getConsumerCreditReportReply;
	}

	public RblCreditReportBody(RblCreditReportReply getConsumerCreditReportReply) {
		super();
		this.getConsumerCreditReportReply = getConsumerCreditReportReply;
	}

	
	
	

}

package org.mifosplatform.portfolio.rblvalidation.data;

import org.joda.time.DateTime;

public class RblCreditBureauResponseData {
	
	private RblHeaderData Header;
	private RblCreditReportBody getConsumerCreditReportBody;
	
	
	public RblCreditBureauResponseData(RblHeaderData header, RblCreditReportBody getConsumerCreditReportBody) {
		super();
		Header = header;
		this.getConsumerCreditReportBody = getConsumerCreditReportBody;
	}
	public RblHeaderData getHeader() {
		return Header;
	}
	public void setHeader(RblHeaderData header) {
		Header = header;
	}
	public RblCreditReportBody getGetConsumerCreditReportBody() {
		return getConsumerCreditReportBody;
	}
	public void setGetConsumerCreditReportBody(RblCreditReportBody getConsumerCreditReportBody) {
		this.getConsumerCreditReportBody = getConsumerCreditReportBody;
	}
	
	

}

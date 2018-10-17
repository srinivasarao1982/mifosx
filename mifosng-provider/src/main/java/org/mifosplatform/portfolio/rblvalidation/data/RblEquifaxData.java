package org.mifosplatform.portfolio.rblvalidation.data;

public class RblEquifaxData {
	private RblHeaderData Header;
	private RblClientsData getConsumerCreditReportBody;
	
	
	public RblEquifaxData(RblHeaderData header, RblClientsData getConsumerCreditReportBody) {
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
	public RblClientsData getGetConsumerCreditReportBody() {
		return getConsumerCreditReportBody;
	}
	public void setGetConsumerCreditReportBody(RblClientsData getConsumerCreditReportBody) {
		this.getConsumerCreditReportBody = getConsumerCreditReportBody;
	}
	
	

}

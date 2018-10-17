package org.mifosplatform.portfolio.rblvalidation.data;

public class RblCreditReportReply {
	private String creditApproved;
	private String eligibleLoanAmount;
	private RblCreditDecisionReason creditDecisionReasons;
	
	
	public RblCreditReportReply(String creditApproved, String eligibleLoanAmount,
			RblCreditDecisionReason creditDecisionReasons) {
		super();
		this.creditApproved = creditApproved;
		this.eligibleLoanAmount = eligibleLoanAmount;
		this.creditDecisionReasons = creditDecisionReasons;
	}
	public String getCreditApproved() {
		return creditApproved;
	}
	public void setCreditApproved(String creditApproved) {
		this.creditApproved = creditApproved;
	}
	public String getEligibleLoanAmount() {
		return eligibleLoanAmount;
	}
	public void setEligibleLoanAmount(String eligibleLoanAmount) {
		this.eligibleLoanAmount = eligibleLoanAmount;
	}
	public RblCreditDecisionReason getCreditDecisionReasons() {
		return creditDecisionReasons;
	}
	public void setCreditDecisionReasons(RblCreditDecisionReason creditDecisionReasons) {
		this.creditDecisionReasons = creditDecisionReasons;
	}
	
	
}

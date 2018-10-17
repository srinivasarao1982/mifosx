package org.mifosplatform.portfolio.rblvalidation.data;

import org.joda.time.LocalDate;

public class RblCrdeitResponseData {
	private Long clientId;
	private String requestUid;
	private String serviceName;
	private String chhanelId;
	private String corId;
	private String credit_Approved;
	private String reason;
	private Long eligible_Amount;
	private String json;
	private LocalDate checkdate;
	
	public RblCrdeitResponseData(Long clientId, String requestUid, String serviceName, String chhanelId, String corId,
			String credit_Approved, String reason, Long eligible_Amount, String json,LocalDate checkdate) {
		super();
		this.clientId = clientId;
		this.requestUid = requestUid;
		this.serviceName = serviceName;
		this.chhanelId = chhanelId;
		this.corId = corId;
		this.credit_Approved = credit_Approved;
		this.reason = reason;
		this.eligible_Amount = eligible_Amount;
		this.json = json;
		this.checkdate=checkdate;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getRequestUid() {
		return requestUid;
	}
	public void setRequestUid(String requestUid) {
		this.requestUid = requestUid;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getChhanelId() {
		return chhanelId;
	}
	public void setChhanelId(String chhanelId) {
		this.chhanelId = chhanelId;
	}
	public String getCorId() {
		return corId;
	}
	public void setCorId(String corId) {
		this.corId = corId;
	}
	public String getCredit_Approved() {
		return credit_Approved;
	}
	public void setCredit_Approved(String credit_Approved) {
		this.credit_Approved = credit_Approved;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getEligible_Amount() {
		return eligible_Amount;
	}
	public void setEligible_Amount(Long eligible_Amount) {
		this.eligible_Amount = eligible_Amount;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public LocalDate getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(LocalDate checkdate) {
		this.checkdate = checkdate;
	}
	

}

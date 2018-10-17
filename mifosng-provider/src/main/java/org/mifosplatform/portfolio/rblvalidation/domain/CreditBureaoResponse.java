package org.mifosplatform.portfolio.rblvalidation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_creditBureau_response") 
public class CreditBureaoResponse extends AbstractAuditableCustom<AppUser, Long>{
	
	@Column(name = "center_id", nullable =false)
    private Long centerId;
	
	@Column(name = "client_id", nullable =false)
    private Long clientId;
	
    @Column(name = "request_uid", length = 256)
    private String RequestUUID;
    
    @Column(name = "service_name", nullable =false)
    private String ServiceName;
    
    @Column(name = "channel_id", nullable =false)
    private String channelId;
    
    @Column(name = "cor_Id", nullable =false)
    private String CorId;
    
    @Column(name = "credit_Approved", nullable =false)
    private String creditApproved;
    
    @Column(name = "reason", nullable =false)
    private String reason;
    
    @Column(name = "eligible_Amount", nullable =false)
    private String eligibleLoanAmount;
    
    @Column(name = "json", nullable =false)
    private String json;
    
    public static CreditBureaoResponse create(Long centerId, String requestUUID, String serviceName, String channelId, String corId,
			String creditApproved, String reason, String eligibleLoanAmount, String json,Long clientId){
    	return new CreditBureaoResponse(centerId,requestUUID,serviceName,channelId,corId,creditApproved,reason,eligibleLoanAmount,json,clientId);
    }

	public CreditBureaoResponse(Long centerId, String requestUUID, String serviceName, String channelId, String corId,
			String creditApproved, String reason, String eligibleLoanAmount, String json,Long clientId) {
		super();
		this.centerId = centerId;
		RequestUUID = requestUUID;
		ServiceName = serviceName;
		this.channelId = channelId;
		CorId = corId;
		this.creditApproved = creditApproved;
		this.reason = reason;
		this.eligibleLoanAmount = eligibleLoanAmount;
		this.json = json;
		this.clientId=clientId;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}

	public String getRequestUUID() {
		return RequestUUID;
	}

	public void setRequestUUID(String requestUUID) {
		RequestUUID = requestUUID;
	}

	public String getServiceName() {
		return ServiceName;
	}

	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCorId() {
		return CorId;
	}

	public void setCorId(String corId) {
		CorId = corId;
	}

	public String getCreditApproved() {
		return creditApproved;
	}

	public void setCreditApproved(String creditApproved) {
		this.creditApproved = creditApproved;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEligibleLoanAmount() {
		return eligibleLoanAmount;
	}

	public void setEligibleLoanAmount(String eligibleLoanAmount) {
		this.eligibleLoanAmount = eligibleLoanAmount;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
    
    
    
    
}

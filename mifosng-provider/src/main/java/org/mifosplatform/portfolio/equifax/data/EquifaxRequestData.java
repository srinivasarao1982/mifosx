package org.mifosplatform.portfolio.equifax.data;



public class EquifaxRequestData {
	
    private Long centerId;	
    private Long clientId;	
    private String inquiryPurpose;	
    private String firstName;  
    private String mobilePhone;
    private Long transactionAmount;
    
    
    public static EquifaxRequestData create(Long centerId, Long clientId, String inquiryPurpose, String firstName,
			String mobilePhone,Long transactionAmount){
    	     return new EquifaxRequestData (centerId,clientId,inquiryPurpose,firstName,mobilePhone,transactionAmount);
     }
    
	public EquifaxRequestData(Long centerId, Long clientId, String inquiryPurpose, String firstName,
			String mobilePhone,Long transactionAmount) {
		super();
		this.centerId = centerId;
		this.clientId = clientId;
		this.inquiryPurpose = inquiryPurpose;
		this.firstName = firstName;
		this.mobilePhone = mobilePhone;
		this.transactionAmount=transactionAmount;
	}
	public Long getCenterId() {
		return centerId;
	}
	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getInquiryPurpose() {
		return inquiryPurpose;
	}
	public void setInquiryPurpose(String inquiryPurpose) {
		this.inquiryPurpose = inquiryPurpose;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
    
    


}

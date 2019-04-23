package org.mifosplatform.portfolio.equifax.data;

public class EquifaxErrorData {
	public Long centerId;	
    public Long clientId;
    public String error;
    public Long transactionAmount;
    public String customerName;
    
    
    public static EquifaxErrorData create(Long centerId, Long clientId, String error,Long transactionAmount,String customerName){
    	  return new EquifaxErrorData(centerId,clientId,error,transactionAmount,customerName); 
    }
    
	public EquifaxErrorData(Long centerId, Long clientId, String error,Long transactionAmount,String customerName  ) {
		super();
		this.centerId = centerId;
		this.clientId = clientId;
		this.error = error;
		this.customerName=customerName;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
    
    

}
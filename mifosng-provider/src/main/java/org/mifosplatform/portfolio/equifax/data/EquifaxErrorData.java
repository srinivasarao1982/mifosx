package org.mifosplatform.portfolio.equifax.data;

public class EquifaxErrorData {
	public Long centerId;	
    public Long clientId;
    public String error;
    
    
    public static EquifaxErrorData create(Long centerId, Long clientId, String error){
    	  return new EquifaxErrorData(centerId,clientId,error); 
    }
    
	public EquifaxErrorData(Long centerId, Long clientId, String error) {
		super();
		this.centerId = centerId;
		this.clientId = clientId;
		this.error = error;
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
    
    

}

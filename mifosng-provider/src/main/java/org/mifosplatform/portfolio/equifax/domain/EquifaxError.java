package org.mifosplatform.portfolio.equifax.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.useradministration.domain.AppUser;
@SuppressWarnings("serial")
@Entity
@Table(name = "m_equifax_error") 
public class EquifaxError extends AbstractAuditableCustom<AppUser, Long> {
	
	@Column(name = "center_id", nullable =false)
    private Long centerId;
	
	@Column(name = "client_id", nullable =false)
    private Long clientId;	
        
    @Column(name = "error", nullable =false)
    private String error;
    
    
    public static  EquifaxError createEuifaxError(Long centerId, Long clientId, String error){
    	return new EquifaxError(centerId,clientId,error);
    }


	public EquifaxError(Long centerId, Long clientId, String error) {
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

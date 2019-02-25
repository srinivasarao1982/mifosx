package org.mifosplatform.portfolio.equifax.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_equifax_request") 
public class EquifaxRequest extends AbstractAuditableCustom<AppUser, Long>{
	 	
	@Column(name = "center_id", nullable =false)
    private Long centerId;
	
	@Column(name = "client_id", nullable =false)
    private Long clientId;	
	
	@Column(name = "inquiry_purpose", nullable =false)
    private String inquiryPurpose;
	
	@Column(name = "transaction_amount", nullable =false)
    private  Long transactionamount;
	
    @Column(name = "first_name", length = 256)
    private String firstName;
    
    @Column(name = "address_line1", nullable =false)
    private String addressline1;
    
    @Column(name = "state_code", nullable =false)
    private String state;
    
    @Column(name = "postal", nullable =false)
    private String postal;
    
    @Column(name = "date_of_birth", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @Column(name = "gender", nullable =false)
    private String gender;    
   
    @Column(name = "pan_id", nullable =false)
    private String panId;
    
    @Column(name = "mobile_phone", nullable =false)
    private String mobilePhone;
    
    @Column(name = "home_phone", nullable =false)
    private String homephone;
    
    public static EquifaxRequest create (Long centerId, Long clientId, String inquiryPurpose, Long transactionamount, String firstName,
			String addressline1, String state, String postal, Date dateOfBirth, String gender, String panId,
			String mobilePhone, String homephone){
    	return new EquifaxRequest(centerId,clientId,inquiryPurpose,transactionamount,firstName,addressline1,state,postal,dateOfBirth,
    			gender,panId,mobilePhone,homephone);
    }

	public EquifaxRequest(Long centerId, Long clientId, String inquiryPurpose, Long transactionamount, String firstName,
			String addressline1, String state, String postal, Date dateOfBirth, String gender, String panId,
			String mobilePhone, String homephone) {
		super();
		this.centerId = centerId;
		this.clientId = clientId;
		this.inquiryPurpose = inquiryPurpose;
		this.transactionamount = transactionamount;
		this.firstName = firstName;
		this.addressline1 = addressline1;
		this.state = state;
		this.postal = postal;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.panId = panId;
		this.mobilePhone = mobilePhone;
		this.homephone = homephone;
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

	public Long getTransactionamount() {
		return transactionamount;
	}

	public void setTransactionamount(Long transactionamount) {
		this.transactionamount = transactionamount;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPanId() {
		return panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

    
    
    
 }

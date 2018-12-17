package org.mifosplatform.organisation.rbi.domain;
import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;
@Entity
@Table(name="m_bank", uniqueConstraints = {@UniqueConstraint(columnNames = { "ifsc" }, name = "ifsc_UNIQUE")})
public class Bank extends AbstractPersistable<Long>{
	@Column(name = "bank", length = 500)
    private String bankName;
	
	@Column(name = "ifsc", length = 250)
    private String ifscCode;
	
	@Column(name = "micr_code", length = 250)
    private String micrCode;
	
	@Column(name = "branch", length = 250)
    private String branchName;
	
	@Column(name = "address", length = 500)
    private String address;
	
	@Column(name = "contact", length = 50)
    private String contact;
	
	@Column(name = "city", length = 50)
    private String city;
	
	@Column(name = "district", length = 50)
    private String district;
	
	@Column(name = "state", length = 50)
    private String state;

	public Bank(String bankName, String ifscCode, String micrCode, String branchName, String address, String contact,
			String city, String district, String state) {
		super();
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.micrCode = micrCode;
		this.branchName = branchName;
		this.address = address;
		this.contact = contact;
		this.city = city;
		this.district = district;
		this.state = state;
	}
    
	public static Bank createNew(final String bankName,final String ifscCode, final String micrCode,final String branchName, final String address,final String contact,
			final String city, final String district, final String state){
		return new Bank(bankName,ifscCode,micrCode,branchName,address,contact,city,district,state);
	}
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}

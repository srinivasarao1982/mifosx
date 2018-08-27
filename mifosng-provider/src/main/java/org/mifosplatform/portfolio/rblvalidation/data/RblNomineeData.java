package org.mifosplatform.portfolio.rblvalidation.data;

import java.util.Date;

import org.joda.time.DateTime;

public class RblNomineeData {
	private Long title;
    private String name;
    private String relation;
    private Date dateOfBirth;
    private Long gender;
    private String pan;
    private String minor;
    private String nomineeID;
    private String nomineeAddressID;
    private RblAddressData address;
    private RblGurdianData guardian;
    
    
	public RblNomineeData(Long title, String name, String relation, Date dateOfBirth, Long gender, String pan,
			String minor, String nomineeID, String nomineeAddressID, RblAddressData address, RblGurdianData guardian) {
		super();
		this.title = title;
		this.name = name;
		this.relation = relation;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.pan = pan;
		this.minor = minor;
		this.nomineeID = nomineeID;
		this.nomineeAddressID = nomineeAddressID;
		this.address = address;
		this.guardian = guardian;
	}
	public Long getTitle() {
		return title;
	}
	public void setTitle(Long title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getGender() {
		return gender;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public String getNomineeID() {
		return nomineeID;
	}
	public void setNomineeID(String nomineeID) {
		this.nomineeID = nomineeID;
	}
	public String getNomineeAddressID() {
		return nomineeAddressID;
	}
	public void setNomineeAddressID(String nomineeAddressID) {
		this.nomineeAddressID = nomineeAddressID;
	}
	public RblAddressData getAddress() {
		return address;
	}
	public void setAddress(RblAddressData address) {
		this.address = address;
	}
	public RblGurdianData getGuardian() {
		return guardian;
	}
	public void setGuardian(RblGurdianData guardian) {
		this.guardian = guardian;
	}
    
    

}

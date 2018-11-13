package org.mifosplatform.portfolio.rblvalidation.data;

import java.util.Date;

import org.joda.time.DateTime;

public class RblClientsData {
	private String barcodeNo;
    private String externalId;
    private Long loanAmount;
    private String isRenewalLoan;
    private String customerName;
    private String pan;
    private String mobileNo;
    private Date  dateOfBirth;
    private String branchCode;
    private String branchName;
    private String rationCardNo;
    private String voterId;   
    private String aadharNo;
    private String gender;
    private RblNomineeData nominee;
    private RblAddressData address;
    private RblOperatingRegion operatingRegion;
    
  
    
	public RblClientsData(String barcodeNo, String externalId, Long loanAmount, String isRenewalLoan,
			String customerName, String pan, String mobileNo, Date dateOfBirth, String branchCode,
			String branchName, String rationCardNo, String voterId, String aadharNo, String gender,
			RblNomineeData nominee, RblAddressData address, RblOperatingRegion operatingRegion) {
		super();
		this.barcodeNo = barcodeNo;
		this.externalId = externalId;
		this.loanAmount = loanAmount;
		this.isRenewalLoan = isRenewalLoan;
		this.customerName = customerName;
		this.pan = pan;
		this.mobileNo = mobileNo;
		this.dateOfBirth = dateOfBirth;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.rationCardNo = rationCardNo;
		this.voterId = voterId;
		this.aadharNo = aadharNo;
		this.gender = gender;
		this.nominee = nominee;
		this.address = address;
		this.operatingRegion = operatingRegion;
	}
	public String getBarcodeNo() {
		return barcodeNo;
	}
	public void setBarcodeNo(String barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public Long getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getIsRenewalLoan() {
		return isRenewalLoan;
	}
	public void setIsRenewalLoan(String isRenewalLoan) {
		this.isRenewalLoan = isRenewalLoan;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getRationCardNo() {
		return rationCardNo;
	}
	public void setRationCardNo(String rationCardNo) {
		this.rationCardNo = rationCardNo;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public RblNomineeData getNominee() {
		return nominee;
	}
	public void setNominee(RblNomineeData nominee) {
		this.nominee = nominee;
	}
	public RblAddressData getAddress() {
		return address;
	}
	public void setAddress(RblAddressData address) {
		this.address = address;
	}
	public RblOperatingRegion getOperatingRegion() {
		return operatingRegion;
	}
	public void setOperatingRegion(RblOperatingRegion operatingRegion) {
		this.operatingRegion = operatingRegion;
	}
    
	
}

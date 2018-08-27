package org.mifosplatform.portfolio.rblvalidation.data;

import org.joda.time.DateTime;

public class RblCreditBureauData {
	
	private String barcodeNo;
	private String externalId;
	private Long loanAmount;
	private String isRenewalLoan;
	private String customerName;
	private String title;
	private String name;
	private String relation;
	private String line1;
	private String line2;
	private String line3;
	private String cityCode;
	private String cityName;
	private String stateCode;
	private String pin;
	private String operatingRegionCode;
	private String operatingRegionName;
	private DateTime dateOfBirth;
	private String branchCode;
	private String branchName;
	
	public RblCreditBureauData(String barcodeNo, String externalId, Long loanAmount, String isRenewalLoan,
			String customerName, String title, String name, String relation, String line1, String line2, String line3,
			String cityCode, String cityName, String stateCode, String pin, String operatingRegionCode,
			String operatingRegionName, DateTime dateOfBirth, String branchCode, String branchName) {
		super();
		this.barcodeNo = barcodeNo;
		this.externalId = externalId;
		this.loanAmount = loanAmount;
		this.isRenewalLoan = isRenewalLoan;
		this.customerName = customerName;
		this.title = title;
		this.name = name;
		this.relation = relation;
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.stateCode = stateCode;
		this.pin = pin;
		this.operatingRegionCode = operatingRegionCode;
		this.operatingRegionName = operatingRegionName;
		this.dateOfBirth = dateOfBirth;
		this.branchCode = branchCode;
		this.branchName = branchName;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
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

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getOperatingRegionCode() {
		return operatingRegionCode;
	}

	public void setOperatingRegionCode(String operatingRegionCode) {
		this.operatingRegionCode = operatingRegionCode;
	}

	public String getOperatingRegionName() {
		return operatingRegionName;
	}

	public void setOperatingRegionName(String operatingRegionName) {
		this.operatingRegionName = operatingRegionName;
	}

	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(DateTime dateOfBirth) {
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
	
	
}

package org.mifosplatform.portfolio.equifax.data;

public class EquifaxHeaderData {
	public String CustomerId;
	public String UserId;
	public String Password;
	public String MemberNumber;
	public String SecurityCode;
	public String ProductCode;
	public String ProductVersion;
	public String ReportFormat;
	public String CustRefField;
	
	
	public static  EquifaxHeaderData createHeaderData(String customerId, String userId, String password, String memberNumber,
			String securityCode, String productCode, String productVersion, String reportFormat, String custRefField){
		
		return new EquifaxHeaderData (customerId,userId,password,memberNumber,securityCode,productCode,productVersion,
				reportFormat,custRefField);
	}
	public EquifaxHeaderData(String customerId, String userId, String password, String memberNumber,
			String securityCode, String productCode, String productVersion, String reportFormat, String custRefField) {
		super();
		CustomerId = customerId;
		UserId = userId;
		Password = password;
		MemberNumber = memberNumber;
		SecurityCode = securityCode;
		ProductCode = productCode;
		ProductVersion = productVersion;
		ReportFormat = reportFormat;
		CustRefField = custRefField;
	}
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getMemberNumber() {
		return MemberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		MemberNumber = memberNumber;
	}
	public String getSecurityCode() {
		return SecurityCode;
	}
	public void setSecurityCode(String securityCode) {
		SecurityCode = securityCode;
	}
	public String getProductCode() {
		return ProductCode;
	}
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}
	public String getProductVersion() {
		return ProductVersion;
	}
	public void setProductVersion(String productVersion) {
		ProductVersion = productVersion;
	}
	public String getReportFormat() {
		return ReportFormat;
	}
	public void setReportFormat(String reportFormat) {
		ReportFormat = reportFormat;
	}
	public String getCustRefField() {
		return CustRefField;
	}
	public void setCustRefField(String custRefField) {
		CustRefField = custRefField;
	}

	
	
}

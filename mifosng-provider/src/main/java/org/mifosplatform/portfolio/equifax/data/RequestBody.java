package org.mifosplatform.portfolio.equifax.data;

public class RequestBody {
	 public String InquiryPurpose;
	 public String TransactionAmount;
	 public String FirstName;
	 public String LastName;
	 public String AddrLine1;
	 public String State;
	 public String Postal;
	 public String DOB;
	 public String Gender;
	 public String PANId;
	 public String MobilePhone;
	 public String HomePhone;
	 
	 
	public RequestBody(String inquiryPurpose, String transactionAmount, String firstName, String lastName,
			String addrLine1, String state, String postal, String dOB, String gender, String pANId, String mobilePhone,
			String homePhone) {
		super();
		InquiryPurpose = inquiryPurpose;
		TransactionAmount = transactionAmount;
		FirstName = firstName;
		LastName = lastName;
		AddrLine1 = addrLine1;
		State = state;
		Postal = postal;
		DOB = dOB;
		Gender = gender;
		PANId = pANId;
		MobilePhone = mobilePhone;
		HomePhone = homePhone;
	}
	public String getInquiryPurpose() {
		return InquiryPurpose;
	}
	public void setInquiryPurpose(String inquiryPurpose) {
		InquiryPurpose = inquiryPurpose;
	}
	public String getTransactionAmount() {
		return TransactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		TransactionAmount = transactionAmount;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getAddrLine1() {
		return AddrLine1;
	}
	public void setAddrLine1(String addrLine1) {
		AddrLine1 = addrLine1;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPostal() {
		return Postal;
	}
	public void setPostal(String postal) {
		Postal = postal;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getPANId() {
		return PANId;
	}
	public void setPANId(String pANId) {
		PANId = pANId;
	}
	public String getMobilePhone() {
		return MobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}
	public String getHomePhone() {
		return HomePhone;
	}
	public void setHomePhone(String homePhone) {
		HomePhone = homePhone;
	}
	 

	 
}

package org.mifosplatform.organisation.rbi.data;

public class BankData {
	 @SuppressWarnings("unused")
	 private final long id;
	 @SuppressWarnings("unused")
	 private final String bankname;
	 @SuppressWarnings("unused")
	 private final String ifsccode;
	 @SuppressWarnings("unused")
	 private final String micrcode;
	 @SuppressWarnings("unused")
	 private final String branchname;
	 @SuppressWarnings("unused")
	 private final String branchaddress;
	 @SuppressWarnings("unused")
	 private final String bankcontact;
	 @SuppressWarnings("unused")
	 private final String bankcity;
	 @SuppressWarnings("unused")
	 private final String bankdisctrict;
	 @SuppressWarnings("unused")
	 private final String bankstate;
	 
	 
	public BankData(long id, String bankname, String ifsccode, String micrcode, String branchname, String branchaddress,
			String bankcontact, String bankcity, String bankdisctrict, String bankstate) {
		super();
		this.id = id;
		this.bankname = bankname;
		this.ifsccode = ifsccode;
		this.micrcode = micrcode;
		this.branchname = branchname;
		this.branchaddress = branchaddress;
		this.bankcontact = bankcontact;
		this.bankcity = bankcity;
		this.bankdisctrict = bankdisctrict;
		this.bankstate = bankstate;
	}


	public long getId() {
		return id;
	}


	public String getBankname() {
		return bankname;
	}


	public String getIfsccode() {
		return ifsccode;
	}


	public String getMicrcode() {
		return micrcode;
	}


	public String getBranchname() {
		return branchname;
	}


	public String getBranchaddress() {
		return branchaddress;
	}


	public String getBankcontact() {
		return bankcontact;
	}


	public String getBankcity() {
		return bankcity;
	}


	public String getBankdisctrict() {
		return bankdisctrict;
	}


	public String getBankstate() {
		return bankstate;
	}
    
}

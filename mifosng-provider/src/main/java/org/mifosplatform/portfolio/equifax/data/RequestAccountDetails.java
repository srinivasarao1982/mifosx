package org.mifosplatform.portfolio.equifax.data;

public class RequestAccountDetails {
	public String BranchIDMFI;
	public String KendraIDMFI;
	
	
	public String getBranchIDMFI() {
		return BranchIDMFI;
	}


	public void setBranchIDMFI(String branchIDMFI) {
		BranchIDMFI = branchIDMFI;
	}


	public String getKendraIDMFI() {
		return KendraIDMFI;
	}


	public void setKendraIDMFI(String kendraIDMFI) {
		KendraIDMFI = kendraIDMFI;
	}


	public RequestAccountDetails(String branchIDMFI, String kendraIDMFI) {
		super();
		BranchIDMFI = branchIDMFI;
		KendraIDMFI = kendraIDMFI;
	}
	

}

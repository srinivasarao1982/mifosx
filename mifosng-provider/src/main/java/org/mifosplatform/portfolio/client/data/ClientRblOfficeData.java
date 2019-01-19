package org.mifosplatform.portfolio.client.data;

public class ClientRblOfficeData {
	
	public String rblbranchNamer;
	public String branchCode;
	public String operatingRegion;
	public String bcBranchCode;
	public String collector;
	public String approver;
	public String cityCode;
	public String cityName;
	public String operatingRegionName;
	public String branchName;
	
	
	public static ClientRblOfficeData instancerblofficeData(String rblbranchNamer, String branchCode, String operatingRegion, String bcBranchCode,
			String collector, String approver, String cityCode, String cityName, String operatingRegionName,
			String branchName){
		return new ClientRblOfficeData(rblbranchNamer,branchCode,operatingRegion,bcBranchCode,
				collector,approver,cityCode,cityName,operatingRegionName,branchName);
		
	}
	public ClientRblOfficeData(String rblbranchNamer, String branchCode, String operatingRegion, String bcBranchCode,
			String collector, String approver, String cityCode, String cityName, String operatingRegionName,
			String branchName) {
		super();
		this.rblbranchNamer = rblbranchNamer;
		this.branchCode = branchCode;
		this.operatingRegion = operatingRegion;
		this.bcBranchCode = bcBranchCode;
		this.collector = collector;
		this.approver = approver;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.operatingRegionName = operatingRegionName;
		this.branchName = branchName;
	}
	

	
}

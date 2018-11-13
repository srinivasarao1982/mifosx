package org.mifosplatform.portfolio.rblvalidation.data;

public class RblOperatingRegion {
	
	private String operatingRegionCode;
    private String operatingRegionName;
    
    
	public RblOperatingRegion(String operatingRegionCode, String operatingRegionName) {
		super();
		this.operatingRegionCode = operatingRegionCode;
		this.operatingRegionName = operatingRegionName;
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
    

}

package org.mifosplatform.portfolio.rblvalidation.data;

public class RblAddressData {
	private String line1;
    private String line2;
    private String line3;    
    private String cityCode;
    private String cityName;
    private String stateCode;
    private String stateName;
    private String pin;
    
    
    
	public RblAddressData(String line1, String line2, String line3, String cityCode, String cityName, String stateCode,
			String stateName, String pin) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.pin = pin;
	}
	
	
	public RblAddressData(String line1, String line2, String line3, String cityCode, String cityName, String stateCode,
			String pin) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.stateCode = stateCode;
		this.pin = pin;
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
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
    
    

}

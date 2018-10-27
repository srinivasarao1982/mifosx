package org.mifosplatform.portfolio.rblvalidation.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_creditbureau_request") 
public class CreditBureaRequest extends AbstractAuditableCustom<AppUser, Long>{
	
	@Column(name = "center_id", nullable =false)
    private Long centerId;
	
	@Column(name = "client_id", nullable =false)
    private Long clientId;
	
    @Column(name = "barcode_no", length = 256)
    private String barcodeNo;
    
    @Column(name = "external_id", nullable =false)
    private String externalId;
    
    @Column(name = "is_Renewal_Loan", nullable =false)
    private String isRenewalLoan;
    
    @Column(name = "customer_Name", nullable =false)
    private String customerName;
    
    @Column(name = "loanAmount", nullable =false)
    private Long loanAmount;
    
    @Column(name = "title", nullable =false)
    private Long title;    
   
    @Column(name = "name", nullable =false)
    private String name;
    
    @Column(name = "relation", nullable =false)
    private String relation;
    
    @Column(name = "line1", nullable =false)
    private String line1;
    
    @Column(name = "line2", nullable =false)
    private String line2;
    
    @Column(name = "line3", nullable =false)
    private String line3;
    
    @Column(name = "city_code", nullable =false)
    private String cityCode;
    
    @Column(name = "city_Name", nullable =false)
    private String cityName;
   
    @Column(name = "state_code", nullable =false)
    private String stateCode;
    
    @Column(name = "pin", nullable =false)
    private String pin;
    
    @Column(name = "operating_RegionCode", nullable =false)
    private String operatingRegionCode;
   
    @Column(name = "operating_RegionName", nullable =false)
    private String operatingRegionName;
    
    @Column(name = "dateOfBirth", nullable =false)
    private Date dateOfBirth;
    
    @Column(name = "branch_code", nullable =false)
    private String branchCode;
   
    @Column(name = "branch_name", nullable =false)
    private String branchName;
    
    @Column(name = "json", nullable =false)
    private String json;
    
    
    public static CreditBureaRequest create (final Long centerId,final String barcodeNo,final String externalId, final String isRenewalLoan,
			final String customerName,final Long loanAmount,final Long title,final String name, final String relation,final String line1,final String line2,
			final String line3, final String cityCode, final String cityName, final String stateCode,final String pin,final String operatingRegionCode,
			final String operatingRegionName, final Date dateOfBirth,final String branchCode,final String branchName,final String json,Long clientId){
    
    	return new CreditBureaRequest( centerId,  barcodeNo,  externalId,  isRenewalLoan,
    			 customerName,  loanAmount,  title,  name,  relation,  line1,  line2,
    			 line3,  cityCode,  cityName,  stateCode,  pin,  operatingRegionCode,
    			 operatingRegionName,  dateOfBirth,  branchCode,  branchName,json,clientId);
        }

	public CreditBureaRequest(Long centerId, String barcodeNo, String externalId, String isRenewalLoan,
			String customerName, Long loanAmount, Long title, String name, String relation, String line1, String line2,
			String line3, String cityCode, String cityName, String stateCode, String pin, String operatingRegionCode,
			String operatingRegionName, Date dateOfBirth, String branchCode, String branchName,String json,Long clientId) {
		super();
		this.centerId = centerId;
		this.barcodeNo = barcodeNo;
		this.externalId = externalId;
		this.isRenewalLoan = isRenewalLoan;
		this.customerName = customerName;
		this.loanAmount = loanAmount;
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
		this.json=json;
		this.clientId=clientId;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
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

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

   
    
  
   

}

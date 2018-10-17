package org.mifosplatform.portfolio.rblvalidation.data;

import java.util.Date;

public class RblGroupValidationData {
	private String externalId;
	private String centerExternalId;
	private String centerName;
	private String maximuncenter;
	private String groupName;
	private String groupType;
	private Integer minumNumber;
	private Integer maximumNumber;
	private Date formationDate;
	private String meetinTime;
	private String meetingfrequency;
	private String distancefromBranch;
	private String branchCode;
	private String operatingRegionCode;
	
	
	
	public RblGroupValidationData(String externalId, String centerExternalId, String centerName, String maximuncenter,
			String groupName, String groupType, Integer minumNumber, Integer maximumNumber, Date formationDate,
			String meetinTime, String meetingfrequency, String distancefromBranch, String branchCode,
			String operatingRegionCode) {
		super();
		this.externalId = externalId;
		this.centerExternalId = centerExternalId;
		this.centerName = centerName;
		this.maximuncenter = maximuncenter;
		this.groupName = groupName;
		this.groupType = groupType;
		this.minumNumber = minumNumber;
		this.maximumNumber = maximumNumber;
		this.formationDate = formationDate;
		this.meetinTime = meetinTime;
		this.meetingfrequency = meetingfrequency;
		this.distancefromBranch = distancefromBranch;
		this.branchCode = branchCode;
		this.operatingRegionCode = operatingRegionCode;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getCenterExternalId() {
		return centerExternalId;
	}
	public void setCenterExternalId(String centerExternalId) {
		this.centerExternalId = centerExternalId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getMaximuncenter() {
		return maximuncenter;
	}
	public void setMaximuncenter(String maximuncenter) {
		this.maximuncenter = maximuncenter;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public Integer getMinumNumber() {
		return minumNumber;
	}
	public void setMinumNumber(Integer minumNumber) {
		this.minumNumber = minumNumber;
	}
	public Integer getMaximumNumber() {
		return maximumNumber;
	}
	public void setMaximumNumber(Integer maximumNumber) {
		this.maximumNumber = maximumNumber;
	}
	public Date getFormationDate() {
		return formationDate;
	}
	public void setFormationDate(Date formationDate) {
		this.formationDate = formationDate;
	}
	public String getMeetinTime() {
		return meetinTime;
	}
	public void setMeetinTime(String meetinTime) {
		this.meetinTime = meetinTime;
	}
	public String getMeetingfrequency() {
		return meetingfrequency;
	}
	public void setMeetingfrequency(String meetingfrequency) {
		this.meetingfrequency = meetingfrequency;
	}
	public String getDistancefromBranch() {
		return distancefromBranch;
	}
	public void setDistancefromBranch(String distancefromBranch) {
		this.distancefromBranch = distancefromBranch;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getOperatingRegionCode() {
		return operatingRegionCode;
	}
	public void setOperatingRegionCode(String operatingRegionCode) {
		this.operatingRegionCode = operatingRegionCode;
	}
	
	
	

}

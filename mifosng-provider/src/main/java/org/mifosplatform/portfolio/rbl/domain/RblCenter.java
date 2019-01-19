package org.mifosplatform.portfolio.rbl.domain;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.portfolio.rbl.api.RblCenterDeatilsApiConstant;
import org.mifosplatform.portfolio.rbl.api.RblGroupDetailsApiConstant;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_rblcenter") 
public class RblCenter extends AbstractAuditableCustom<AppUser, Long>{
	
	 @Column(name = "center_id", length = 20)
	 private Long centerId;
	 
	 @Column(name = "max_individual", length = 4)
	 private Long maximumIndividual;
    
	 
	 @Column(name = "meting_time", length = 256)
	 private String meetingTime;
	 

	 @Column(name = "house_no", length = 256)
	 private String houseNo;
	 
	 @Column(name = "street_no", length = 256)
	 private String streetNo;
	 
	 @Column(name = "area_loc", length = 256)
	 private String arelocality;
    
	 @Column(name = "landmark", length = 256)
	 private String landmark;
	 
	 @Column(name = "village", length = 256)
	 private String village;
	 
	 @Column(name = "taluk", length = 256)
	 private String taluk;
	 
	 @Column(name = "district", length = 6)
	 private Long district;
	 
	 @Column(name = "state", length = 6)
	 private Long state;
	 
	 @Column(name = "pin", length = 6)
	 private Long pin;
	 
	 @Column(name = "description", length = 30)
	 private String  description;
	 
	 @Column(name = "distance_from_branch", length = 8)
	 private Integer distanceFromCenter;

	 
	 public static RblCenter createrblcenter(Long centerId, Long maximumIndividual, String meetingTime, String houseNo, String streetNo,
				String arelocality, String landmark, String village, String taluk, Long district, Long state,Long pin,final String description
				,final Integer distanceFromCenter) {
           
		 return new RblCenter(centerId,maximumIndividual,meetingTime,houseNo,streetNo,arelocality,landmark,village,taluk,district,state,pin,description,distanceFromCenter);
   	 
    }
	 
	 
	 


	public Map<String, Object> update(final JsonCommand command) {

	        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);

	        if (command.isChangeInLongParameterNamed(RblCenterDeatilsApiConstant.centerIdparamname, this.centerId)) {
	            final Long newValue = command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.centerIdparamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.centerIdparamname, newValue);
	            this.centerId=newValue;
	        }
	        if (command.isChangeInLongParameterNamed(RblCenterDeatilsApiConstant.pincodeparamName, this.pin)) {
	            final Long newValue = command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.pincodeparamName);
	            actualChanges.put(RblCenterDeatilsApiConstant.pincodeparamName, newValue);
	            this.pin=newValue;
	        }
	        if (command.isChangeInLongParameterNamed(RblCenterDeatilsApiConstant.maximumindividualParamName, this.maximumIndividual)) {
	            final Long newValue = command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.maximumindividualParamName);
	            actualChanges.put(RblCenterDeatilsApiConstant.maximumindividualParamName, newValue);
	            this.maximumIndividual=newValue;
	        }
	        
	        if (command.isChangeInIntegerParameterNamed(RblCenterDeatilsApiConstant.distancefrombranch, this.distanceFromCenter)) {
	            final Integer newValue = command.integerValueOfParameterNamed(RblCenterDeatilsApiConstant.distancefrombranch);
	            actualChanges.put(RblCenterDeatilsApiConstant.distancefrombranch, newValue);
	            this.distanceFromCenter=newValue;
	        }
	        
	        if (command.isChangeInLongParameterNamed(RblCenterDeatilsApiConstant.stateparamname, this.state)) {
	            final Long newValue = command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.stateparamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.stateparamname, newValue);
	            this.state=newValue;
	        }
	        
	        if (command.isChangeInLongParameterNamed(RblCenterDeatilsApiConstant.districtparamname, this.district)) {
	            final Long newValue = command.longValueOfParameterNamed(RblCenterDeatilsApiConstant.districtparamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.districtparamname, newValue);
	            this.district=newValue;
	        }

	        if (command.isChangeInStringParameterNamed(RblCenterDeatilsApiConstant.meetingtimeParamName, this.meetingTime)) {
	            final String newValue = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.meetingtimeParamName);
	            actualChanges.put(RblCenterDeatilsApiConstant.meetingtimeParamName, newValue);
	            this.meetingTime = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(RblCenterDeatilsApiConstant.housenumberParamname, this.houseNo)) {
	            final String newValue = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.housenumberParamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.housenumberParamname, newValue);
	            this.houseNo = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(RblCenterDeatilsApiConstant.streetnumberParamname, this.streetNo)) {
	            final String newValue = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.streetnumberParamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.streetnumberParamname, newValue);
	            this.streetNo = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(RblCenterDeatilsApiConstant.arealocalityParamName, this.arelocality)) {
	            final String newValue = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.arealocalityParamName);
	            actualChanges.put(RblCenterDeatilsApiConstant.arealocalityParamName, newValue);
	            this.arelocality = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(RblCenterDeatilsApiConstant.villageparamname, this.village)) {
	            final String newValue = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.villageparamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.villageparamname, newValue);
	            this.village = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(RblCenterDeatilsApiConstant.talukparamname, this.taluk)) {
	            final String newValue = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.talukparamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.talukparamname, newValue);
	            this.taluk = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(RblCenterDeatilsApiConstant.villageparamname, this.village)) {
	            final String newValue = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.villageparamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.villageparamname, newValue);
	            this.village = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(RblCenterDeatilsApiConstant.descriptionparamname, this.description)) {
	            final String newValue = command.stringValueOfParameterNamed(RblCenterDeatilsApiConstant.descriptionparamname);
	            actualChanges.put(RblCenterDeatilsApiConstant.descriptionparamname, newValue);
	            this.description = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	      
	       return actualChanges;
	    }
	  
	   public RblCenter() {
			super();
		}
	public RblCenter(Long centerId, Long maximumIndividual, String meetingTime, String houseNo, String streetNo,
			String arelocality, String landmark, String village, String taluk, Long district, Long state,Long pin,String description
			,final Integer distanceFromCenter) {
		super();
		this.centerId = centerId;
		this.maximumIndividual = maximumIndividual;
		this.meetingTime = meetingTime;
		this.houseNo = houseNo;
		this.streetNo = streetNo;
		this.arelocality = arelocality;
		this.landmark = landmark;
		this.village = village;
		this.taluk = taluk;
		this.district = district;
		this.state = state;
		this.pin=pin;
		this.description=description;
		this.distanceFromCenter=distanceFromCenter;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}

	public Long getMaximumIndividual() {
		return maximumIndividual;
	}

	public void setMaximumIndividual(Long maximumIndividual) {
		this.maximumIndividual = maximumIndividual;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getArelocality() {
		return arelocality;
	}

	public void setArelocality(String arelocality) {
		this.arelocality = arelocality;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTaluk() {
		return taluk;
	}

	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}
    
	 
	

}

package org.mifosplatform.portfolio.rbl.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.rbl.api.RblGroupDetailsApiConstant;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_rblgroup") 
public class RblGroup extends AbstractAuditableCustom<AppUser, Long>{	
	
	
	@Column(name="group_Id")
	private Long groupId;
	
	@Column(name = "maximum_center", length = 8)
    private Long maximumCenter;
	
	@Column(name = "group_type", length = 8)
    private Integer groupType;
	
	@Column(name = "min_number", length = 8)
    private Integer minNumber;
	
	@Column(name = "max_numbeer", length = 8)
    private Integer maxNumber;
	
	@Column(name = "distance_from_center", length = 8)
    private Integer distanceFromCenter;
	
	 @Column(name = "meeting_time", length = 256)
	 private String meetingTime;
	 
	 
	 public static RblGroup createRblgroup(final Long groupId,final Long maximumCenter, final Integer groupType, final Integer minNumber, final Integer maxNumber,
				final Integer distanceFromCenter,final String meetingTime){
		 
		 return new  RblGroup(groupId,maximumCenter,groupType,minNumber,maxNumber,distanceFromCenter,meetingTime);
	 }
	 
	 
	 public RblGroup() {
		super();
	}


	public Map<String, Object> update(final JsonCommand command) {
			
	        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);

	        if (command.isChangeInLongParameterNamed(RblGroupDetailsApiConstant.maximumcenterparamname, this.maximumCenter)) {
	            final Long newValue = command.longValueOfParameterNamed(RblGroupDetailsApiConstant.maximumcenterparamname);
	            actualChanges.put(RblGroupDetailsApiConstant.maximumcenterparamname, newValue);
	            this.maximumCenter=newValue;
	           }
	     
	        
	        if (command.isChangeInIntegerParameterNamed(RblGroupDetailsApiConstant.distancefrombranch, this.distanceFromCenter)) {
	            final Integer newValue = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.distancefrombranch);
	            actualChanges.put(RblGroupDetailsApiConstant.distancefrombranch, newValue);
	            this.distanceFromCenter=newValue;
	        }

	        if (command.isChangeInIntegerParameterNamed(RblGroupDetailsApiConstant.grouptypeparamname, this.groupType)) {
	            final Integer newValue = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.grouptypeparamname);
	            actualChanges.put(RblGroupDetailsApiConstant.grouptypeparamname, newValue);
	            this.groupType=newValue;
	        }
	        
	        if (command.isChangeInIntegerParameterNamed(RblGroupDetailsApiConstant.minnumberparamname, this.minNumber)) {
	            final Integer newValue = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.minnumberparamname);
	            actualChanges.put(RblGroupDetailsApiConstant.minnumberparamname, newValue);
	            this.minNumber=newValue;
	        }
	        if (command.isChangeInIntegerParameterNamed(RblGroupDetailsApiConstant.maxnumberparamname, this.maxNumber)) {
	            final Integer newValue = command.integerValueOfParameterNamed(RblGroupDetailsApiConstant.maxnumberparamname);
	            actualChanges.put(RblGroupDetailsApiConstant.maxnumberparamname, newValue);
	            this.maxNumber=newValue;
	        }
		        
	        if (command.isChangeInStringParameterNamed(RblGroupDetailsApiConstant.meetingtimeparamname, this.meetingTime)) {
	            final String newValue = command.stringValueOfParameterNamed(RblGroupDetailsApiConstant.meetingtimeparamname);
	            actualChanges.put(RblGroupDetailsApiConstant.meetingtimeparamname, newValue);
	            this.meetingTime = StringUtils.defaultIfEmpty(newValue, null);
	        }	        
	        	       
	       return actualChanges;
	    }

	public RblGroup(Long groupId,Long maximumCenter, Integer groupType, Integer minNumber, Integer maxNumber,
			Integer distanceFromCenter, String meetingTime) {
		super();
		this.groupId=groupId;
		this.maximumCenter = maximumCenter;
		this.groupType = groupType;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		this.distanceFromCenter = distanceFromCenter;
		this.meetingTime = meetingTime;
	}

	public Long getMaximumCenter() {
		return maximumCenter;
	}

	public void setMaximumCenter(Long maximumCenter) {
		this.maximumCenter = maximumCenter;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public Integer getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(Integer minNumber) {
		this.minNumber = minNumber;
	}

	public Integer getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}

	public Integer getDistanceFromCenter() {
		return distanceFromCenter;
	}

	public void setDistanceFromCenter(Integer distanceFromCenter) {
		this.distanceFromCenter = distanceFromCenter;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	

}

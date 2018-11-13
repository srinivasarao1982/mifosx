package org.mifosplatform.portfolio.rbl.data;

public class RblGroupData {

	private final Long maxcenter;
	private final Integer groupType;
	private final Integer minNumber;
	private final Integer maxnumber;
	private final Integer distancefrombranch;
	private final String meetingTime;
	
	

	public static RblGroupData create(final Long maximumcenter, final Integer groupType, final Integer minNumber,
			final Integer maxNumber, final Integer distanceFromCenter, final String meetingTime) {

		return new RblGroupData(maximumcenter, groupType, minNumber, maxNumber, distanceFromCenter, meetingTime);

	}

	public RblGroupData(Long maximumcenter, Integer groupType, Integer minNumber, Integer maxNumber,
			Integer distanceFromCenter, String meetingTime) {
		super();
		this.maxcenter = maximumcenter;
		this.groupType = groupType;
		this.minNumber = minNumber;
		this.maxnumber = maxNumber;
		this.distancefrombranch = distanceFromCenter;
		this.meetingTime = meetingTime;
	}

	public Long getMaximumcenter() {
		return maxcenter;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public Integer getMinNumber() {
		return minNumber;
	}

	public Integer getMaxNumber() {
		return maxnumber;
	}

	public Integer getDistanceFromCenter() {
		return distancefrombranch;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

}

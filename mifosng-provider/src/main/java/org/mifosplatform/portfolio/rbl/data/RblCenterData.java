package org.mifosplatform.portfolio.rbl.data;

public class RblCenterData {

	private final Long maximumindividual;
	private final Long centerId;
	private final String meetingTime;
	private final String houseNo;
	private final String streetNumber;
	private final String areaLocality;
	private final String landmark;
	private final String village;
	private final String taluk;
	private final String stateName;
	private final Long state;
	private final Long district;
	private final String districtName;
	private final Long pincode;
	private final String dscription;
	
	 

	public static RblCenterData createrblcenter(final Long maximumindividual, final Long centerId,
			final String meetingtime, final String houseNumbr, final String streetNumber, final String areaLocality,
			final String landmark, final String village, final String taluk, final String district, final String state,
			final Long statId, final Long districtId,final String districtName, final Long pincode, final String description) {
		return new RblCenterData(maximumindividual, centerId, meetingtime, houseNumbr, streetNumber, areaLocality,
				landmark, village, taluk, district, state, statId, districtId, districtName, pincode, description);

	}

	public RblCenterData(Long maximumindividual, Long centerId, String meetingtime, String houseNumbr,
			String streetNumber, String areaLocality, String landmark, String village, String taluk, String district,
			String state, Long statId, Long districtId, final String districtName,Long pincode, String description) {
		super();
		this.maximumindividual = maximumindividual;
		this.centerId = centerId;
		this.meetingTime = meetingtime;
		this.houseNo = houseNumbr;
		this.streetNumber = streetNumber;
		this.areaLocality = areaLocality;
		this.landmark = landmark;
		this.village = village;
		this.taluk = taluk;
		this.district =districtId ;
		this.state = statId;;
		this.stateName = state;
		this.districtName=districtName;
		this.pincode = pincode;
		this.dscription = description;
	}

	public Long getMaximumindividual() {
		return maximumindividual;
	}

	public Long getCenterId() {
		return centerId;
	}

	

	public String getAreaLocality() {
		return areaLocality;
	}

	public String getLandmark() {
		return landmark;
	}

	public String getVillage() {
		return village;
	}

	public String getTaluk() {
		return taluk;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getStateName() {
		return stateName;
	}

	public Long getState() {
		return state;
	}

	public Long getDistrict() {
		return district;
	}

	public String getDistrictName() {
		return districtName;
	}

	public Long getPincode() {
		return pincode;
	}

	public String getDscription() {
		return dscription;
	}

	
}

package org.nirantara.client.ext.data;

import org.nirantara.client.ext.domain.Address;

public class AddressExtData {

	private final Long id;
	private final Long addressType;
	private final String addressTypeLable;
	private final String houseNo;
	private final String streetNo;
	private final String areaLocality;
	private final String landmark;
	private final String villageTown;
	private final String taluka;
	private final Long district;
	private final String districtLable;
	private final Long state;
	private final String stateLable;
	private final Integer pinCode;
	private final Long landlineNo;
	private final Long mobileNo;
	

	public static AddressExtData formAddressExtData(final Address address) {
		Long id = address.getId();
		Long addressType = null;
		String addressTypeLabel = null;
		if(address.getAddressType() != null){
			addressType = address.getAddressType().getId();
			addressTypeLabel = address.getAddressType().label();
		}
		String houseNo = address.getHouseNo();
		String streetNo = address.getStreetNo();
		String areaLocality = address.getAreaLocality();
		String landmark = address.getLandmark();
		String villageTown = address.getVillageTown();
		String taluka = address.getTaluka();
		Long district = null;
		String districtLable = null;
		if(address.getDistrict() != null){
			district = address.getDistrict().getId();
			districtLable = address.getDistrict().label();
		}
		Long state = null;
		String stateLabel = null;
		if(address.getState() != null){
			state = address.getState().getId();
			stateLabel = address.getState().label();
		}
		Integer pinCode = address.getPinCode();
		Long landlineNo = address.getLandlineNo();
		Long mobileNo = address.getMobileNo();
		
		return new AddressExtData(id, addressType, addressTypeLabel, houseNo,
				streetNo, areaLocality, landmark, villageTown, taluka,
				district, districtLable, state, stateLabel, pinCode,
				landlineNo, mobileNo);
	}

	private AddressExtData(final Long id, final Long addressType,
			final String addressTypeLabel, final String houseNo,
			final String streetNo, final String areaLocality,
			final String landmark, final String villageTown,
			final String taluka, final Long district,
			final String districtLable, final Long state,
			final String stateLabel, final Integer pinCode,
			final Long landlineNo, final Long mobileNo) {

		this.id =id;
		this.addressType = addressType;
		this.addressTypeLable = addressTypeLabel;
		this.houseNo = houseNo;
		this.streetNo = streetNo;
		this.areaLocality = areaLocality;
		this.landmark = landmark;
		this.villageTown = villageTown;
		this.taluka = taluka;
		this.district = district;
		this.districtLable = districtLable;
		this.state = state;
		this.stateLable = stateLabel;
		this.pinCode = pinCode;
		this.landlineNo = landlineNo;
		this.mobileNo = mobileNo;
	}

	public Long getId() {
		return id;
	}

	public Long getAddressType() {
		return addressType;
	}

	public String getAddressTypeLable() {
		return addressTypeLable;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public String getAreaLocality() {
		return areaLocality;
	}

	public String getLandmark() {
		return landmark;
	}

	public String getVillageTown() {
		return villageTown;
	}

	public String getTaluka() {
		return taluka;
	}

	public Long getDistrict() {
		return district;
	}

	public String getDistrictLable() {
		return districtLable;
	}

	public Long getState() {
		return state;
	}

	public String getStateLable() {
		return stateLable;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public Long getLandlineNo() {
		return landlineNo;
	}

	public Long getMobileNo() {
		return mobileNo;
	}
}

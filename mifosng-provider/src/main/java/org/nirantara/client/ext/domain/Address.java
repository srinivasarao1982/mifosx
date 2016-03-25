/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.nirantara.client.ext.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "n_address", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "client_id" }, name = "client_id_UNIQUE"),
		@UniqueConstraint(columnNames = { "client_id", "address_type_cv_id" }, name = "client_id_address_type_cv_id_UNIQUE") })
public class Address extends AbstractPersistable<Long> {

	@ManyToOne(optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_type_cv_id", nullable = false)
	private CodeValue addressType;

	@Column(name = "house_no", length = 20)
	private String houseNo;

	@Column(name = "street_no", length = 20)
	private String streetNo;

	@Column(name = "area_locality", length = 100)
	private String areaLocality;

	@Column(name = "landmark", length = 100)
	private String landmark;

	@Column(name = "village_town", length = 100)
	private String villageTown;

	@Column(name = "taluka", length = 100, nullable = false)
	private String taluka;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_cv_id")
	private CodeValue district;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_cv_id", nullable = false)
	private CodeValue state;

	@Column(name = "pin_code", length = 6, nullable = false)
	private Integer pinCode;

	@Column(name = "landline_no", length = 20)
	private Long landlineNo;

	@Column(name = "mobile_no", length = 11, nullable = false)
	private Long mobileNo;

	public static Address createFrom(final Client client,
			final CodeValue addressType, final String houseNo,
			final String streetNo, final String areaLocality,
			final String landmark, final String villageTown,
			final String taluka, final CodeValue district,
			final CodeValue state, final Integer pinCode,
			final Long landlineNo, final Long mobileNo) {

		return new Address(client, addressType, houseNo, streetNo,
				areaLocality, landmark, villageTown, taluka, district, state,
				pinCode, landlineNo, mobileNo);
	}

	protected Address() {
		//
	}

	private Address(final Client client, final CodeValue addressType,
			final String houseNo, final String streetNo,
			final String areaLocality, final String landmark,
			final String villageTown, final String taluka,
			final CodeValue district, final CodeValue state,
			final Integer pinCode, final Long landlineNo, final Long mobileNo) {

		this.client = client;
		this.addressType = addressType;
		this.houseNo = houseNo;
		this.streetNo = streetNo;
		this.areaLocality = areaLocality;
		this.landmark = landmark;
		this.villageTown = villageTown;
		this.taluka = taluka;
		this.district = district;
		this.state = state;
		this.pinCode = pinCode;
		this.landlineNo = landlineNo;
		this.mobileNo = mobileNo;

	}
	
	public void updateClient(final Client client) {
        this.client = client;
    }
	
	public Client getClient() {
		return client;
	}

	public CodeValue getAddressType() {
		return addressType;
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

	public CodeValue getDistrict() {
		return district;
	}

	public CodeValue getState() {
		return state;
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

	public void update(final CodeValue addressType,
			final String houseNo, final String streetNo,
			final String areaLocality, final String landmark,
			final String villageTown, final String taluka,
			final CodeValue district, final CodeValue state,
			final Integer pinCode, final Long landlineNo, final Long mobileNo) {
		// TODO Auto-generated method stub
		this.addressType = addressType;
		this.houseNo = houseNo;
		this.streetNo = streetNo;
		this.areaLocality = areaLocality;
		this.landmark = landmark;
		this.villageTown = villageTown;
		this.taluka = taluka;
		this.district = district;
		this.state = state;
		this.pinCode = pinCode;
		this.landlineNo = landlineNo;
		this.mobileNo = mobileNo;
	}

}

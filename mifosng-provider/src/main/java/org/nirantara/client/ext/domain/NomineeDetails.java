/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.nirantara.client.ext.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "n_nominee_details")
public class NomineeDetails extends AbstractPersistable<Long> {

	@ManyToOne(optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "salutation_cv_id", nullable = false)
	private CodeValue salutation;
	
	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gender_cv_id", nullable = false)
	private CodeValue gender;

	@Column(name = "age", length = 3, nullable = false)
	private Integer age;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "relationship_cv_id", nullable = false)
	private CodeValue relationship;

	@Column(name = "date_of_birth", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "guardian_name")
	private String guardianName;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "guardian_date_of_birth", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date guardianDateOfBirth;
	
	@Column(name = "guardian_relationship")
	private String guardianRelationship;

	public static NomineeDetails createFrom(final Client client,
			final CodeValue salutation, final String name,
			final CodeValue gender, final Integer age,
			final CodeValue relationship, final LocalDate dateOfBirth,
			final String guardianName, final String address,
			final LocalDate guardianDateOfBirth,final String guardianRelationship) {

		return new NomineeDetails(client, salutation, name, gender,
				age, relationship, dateOfBirth, guardianName, address,
				guardianDateOfBirth,guardianRelationship);
	}
	
	protected NomineeDetails() {
		//
	}
	
	private NomineeDetails(final Client client,
			final CodeValue salutation, final String name,
			final CodeValue gender, final Integer age,
			final CodeValue relationship, final LocalDate dateOfBirth,
			final String guardianName, final String address,
			final LocalDate guardianDateOfBirth,final String guardianRelationship) {
		
		this.client = client;
		this.salutation = salutation;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.relationship = relationship;
		if (dateOfBirth != null) {
			this.dateOfBirth = dateOfBirth.toDateTimeAtStartOfDay().toDate();
		}
		this.guardianName = guardianName;
		this.address = address;
		if (guardianDateOfBirth != null) {
			this.guardianDateOfBirth = guardianDateOfBirth.toDateTimeAtStartOfDay().toDate();
		}
		this.guardianRelationship =  guardianRelationship;
	}

	public void updateClient(final Client client) {
		this.client = client;		
	}

	public Client getClient() {
		return client;
	}

	public CodeValue getSalutation() {
		return salutation;
	}

	public String getName() {
		return name;
	}

	public CodeValue getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public CodeValue getRelationship() {
		return relationship;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public String getAddress() {
		return address;
	}

	public Date getGuardianDateOfBirth() {
		return guardianDateOfBirth;
	}

	public String getGuardianRelationship() {
		return guardianRelationship;
	}
	
	public void update(final Client client,
			final CodeValue salutation, final String name,
			final CodeValue gender, final Integer age,
			final CodeValue relationship, final LocalDate dateOfBirth,
			final String guardianName, final String address,
			final LocalDate guardianDateOfBirth,final String guardianRelationship) {
		
		this.client = client;
		this.salutation = salutation;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.relationship = relationship;
		if (dateOfBirth != null) {
			this.dateOfBirth = dateOfBirth.toDateTimeAtStartOfDay().toDate();
		}
		this.guardianName = guardianName;
		this.address = address;
		if (guardianDateOfBirth != null) {
			this.guardianDateOfBirth = guardianDateOfBirth.toDateTimeAtStartOfDay().toDate();
		}
		this.guardianRelationship =  guardianRelationship;
		
	}
	
}

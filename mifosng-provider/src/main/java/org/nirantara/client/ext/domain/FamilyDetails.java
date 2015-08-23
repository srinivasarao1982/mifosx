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
import javax.persistence.UniqueConstraint;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "n_family_details", uniqueConstraints = { @UniqueConstraint(columnNames = { "client_id" }, name = "FK1_n_family_details_client_id") })
public class FamilyDetails extends AbstractPersistable<Long> {

	@ManyToOne(optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@Column(name = "firstname", length = 50, nullable = false)
	private String firstname;

	@Column(name = "middlename", length = 50)
	private String middlename;

	@Column(name = "lastname", length = 50)
	private String lastname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "relationship_cv_id", nullable = false)
	private CodeValue relationship;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gender_cv_id", nullable = false)
	private CodeValue gender;

	@Column(name = "date_of_birth", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "age", length = 3, nullable = false)
	private Integer age;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "occupation_cv_id", nullable = false)
	private CodeValue occupation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "educational_status_cv_id", nullable = false)
	private CodeValue educationalStatus;

	public static FamilyDetails createFrom(final Client client,
			final String firstname, final String middlename,
			final String lastname, final CodeValue relationship,
			final CodeValue gender, final LocalDate dateOfBirth,
			final Integer age, final CodeValue occupation,
			final CodeValue educationalStatus) {

		return new FamilyDetails(client, firstname, middlename, lastname,
				relationship, gender, dateOfBirth, age, occupation,
				educationalStatus);
	}

	protected FamilyDetails() {
		//
	}

	private FamilyDetails(final Client client, final String firstname,
			final String middlename, final String lastname,
			final CodeValue relationship, final CodeValue gender,
			final LocalDate dateOfBirth, final Integer age,
			final CodeValue occupation, final CodeValue educationalStatus) {

		this.client = client;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.relationship = relationship;
		this.gender = gender;
		if (dateOfBirth != null) {
			this.dateOfBirth = dateOfBirth.toDateTimeAtStartOfDay().toDate();
		}
		this.age = age;
		this.occupation = occupation;
		this.educationalStatus = educationalStatus;
	}

	public void updateClient(Client client) {
        this.client = client;
    }
	
	public Client getClient() {
		return client;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public CodeValue getRelationship() {
		return relationship;
	}

	public CodeValue getGender() {
		return gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public CodeValue getOccupation() {
		return occupation;
	}

	public CodeValue getEducationalStatus() {
		return educationalStatus;
	}

	public void update(final String firstname,
			final String middlename, final String lastname,
			final CodeValue relationship, final CodeValue gender,
			final LocalDate dateOfBirth, final Integer age,
			final CodeValue occupation, final CodeValue educationalStatus) {
		
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.relationship = relationship;
		this.gender = gender;
		if (dateOfBirth != null) {
			this.dateOfBirth = dateOfBirth.toDateTimeAtStartOfDay().toDate();
		}
		this.age = age;
		this.occupation = occupation;
		this.educationalStatus = educationalStatus;
		
	}

}

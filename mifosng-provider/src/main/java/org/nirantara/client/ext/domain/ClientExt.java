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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "n_client_ext", uniqueConstraints = { @UniqueConstraint(columnNames = { "client_id" }, name = "client_id_UNIQUE") })
public class ClientExt extends AbstractPersistable<Long> {

	@OneToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salutation_cv_id", nullable = false)
	private CodeValue salutation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marital_status_cv_id", nullable = false)
	private CodeValue maritalStatus;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profession_cv_id", nullable = false)
	private CodeValue profession;

	@Column(name = "profession_others", length = 100)
	private String professionOthers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "educational_qualification_cv_id", nullable = false)
	private CodeValue educationalQualification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "annual_income_cv_id", nullable = false)
	private CodeValue annualIncome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "landholding_cv_id", nullable = false)
	private CodeValue landholding;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "house_type_cv_id", nullable = false)
	private CodeValue houseType;

	@Column(name = "aadhaar_no", length = 100)
	private String aadhaarNo;

	@Column(name = "pan_no", length = 100)
	private String panNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pan_form_cv_id", nullable = false)
	private CodeValue panForm;

	@Column(name = "nrega_no", length = 100)
	private String nregaNo;

	public static ClientExt createFrom(final Client client,final CodeValue salutation,
			final CodeValue maritalStatus, final CodeValue profession,
			final String professionOthers,
			final CodeValue educationalQualification,
			final CodeValue annualIncome, final CodeValue landholding,
			final CodeValue houseType, final String aadhaarNo,
			final String panNo, final CodeValue panForm, final String nregaNo) {

		return new ClientExt(client, salutation, maritalStatus, profession,
				professionOthers, educationalQualification, annualIncome,
				landholding, houseType, aadhaarNo, panNo, panForm, nregaNo);
	}

	protected ClientExt() {
		//
	}

	private ClientExt(final Client client, final CodeValue salutation, final CodeValue maritalStatus,
			final CodeValue profession, final String professionOthers,
			final CodeValue educationalQualification,
			final CodeValue annualIncome, final CodeValue landholding,
			final CodeValue houseType, final String aadhaarNo,
			final String panNo, final CodeValue panForm, final String nregaNo) {

		this.client = client;
		this.salutation = salutation;
		this.maritalStatus = maritalStatus;
		this.profession = profession;
		this.professionOthers = professionOthers;
		this.educationalQualification = educationalQualification;
		this.annualIncome = annualIncome;
		this.landholding = landholding;
		this.houseType = houseType;
		this.aadhaarNo = aadhaarNo;
		this.panNo = panNo;
		this.panForm = panForm;
		this.nregaNo = nregaNo;

	}
}

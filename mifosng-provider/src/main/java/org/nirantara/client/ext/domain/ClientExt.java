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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "salutation_cv_id", nullable = false)
	private CodeValue salutation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marital_status_cv_id", nullable = false)
	private CodeValue maritalStatus;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profession_cv_id", nullable = false)
	private CodeValue profession;

	@Column(name = "profession_others", length = 100)
	private String professionOthers;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "educational_qualification_cv_id", nullable = false)
	private CodeValue educationalQualification;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "annual_income_cv_id", nullable = false)
	private CodeValue annualIncome;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "landholding_cv_id", nullable = false)
	private CodeValue landholding;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "house_type_cv_id", nullable = false)
	private CodeValue houseType;

	@Column(name = "aadhaar_no", length = 100)
	private String aadhaarNo;

	@Column(name = "pan_no", length = 100)
	private String panNo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pan_form_cv_id", nullable = false)
	private CodeValue panForm;

	@Column(name = "nrega_no", length = 100)
	private String nregaNo;
	
	@Column(name = "sp_firstname", length = 100, nullable = false)
	private String spfirstname;

	@Column(name = "sp_middlename", length = 50)
	private String spmiddlename;

	@Column(name = "sp_lastname", length = 50)
	private String splastname;
	
	@Column(name = "external_Id2", length = 100)
	private String externalId2;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "father_spouse_inda", nullable = false)
	private CodeValue spouseRelationShip;
	

	public static ClientExt createFrom(final Client client,final CodeValue salutation,
			final CodeValue maritalStatus, final CodeValue profession,
			final String professionOthers,
			final CodeValue educationalQualification,
			final CodeValue annualIncome, final CodeValue landholding,
			final CodeValue houseType, final String aadhaarNo,
			final String panNo, final CodeValue panForm, final String nregaNo,final String spfirstname,final String spmiddlename,final String splastname,
			final CodeValue spouseRelationShip,final String externalId2 ) {

		return new ClientExt(client, salutation, maritalStatus, profession,
				professionOthers, educationalQualification, annualIncome,
				landholding, houseType, aadhaarNo, panNo, panForm, nregaNo,spfirstname,spmiddlename,splastname,spouseRelationShip,externalId2);
	}

	protected ClientExt() {
		//
	}

	private ClientExt(final Client client, final CodeValue salutation,
			final CodeValue maritalStatus, final CodeValue profession,
			final String professionOthers,
			final CodeValue educationalQualification,
			final CodeValue annualIncome, final CodeValue landholding,
			final CodeValue houseType, final String aadhaarNo,
			final String panNo, final CodeValue panForm, final String nregaNo,
			final String spfirstname, final String spmiddlename,
			final String splastname,
			final CodeValue spouseRelationShip,
			final String externalId2) {

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
		this.spfirstname = spfirstname;
		this.spmiddlename = spmiddlename;
		this.splastname = splastname;
		this.spouseRelationShip=spouseRelationShip;
		this.externalId2=externalId2;
	}

	public Client getClient() {
		return client;
	}

	public CodeValue getSalutation() {
		return salutation;
	}

	public CodeValue getMaritalStatus() {
		return maritalStatus;
	}

	public CodeValue getProfession() {
		return profession;
	}

	public String getProfessionOthers() {
		return professionOthers;
	}

	public CodeValue getEducationalQualification() {
		return educationalQualification;
	}

	public CodeValue getAnnualIncome() {
		return annualIncome;
	}

	public CodeValue getLandholding() {
		return landholding;
	}

	public CodeValue getHouseType() {
		return houseType;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public CodeValue getPanForm() {
		return panForm;
	}

	public String getNregaNo() {
		return nregaNo;
	}

	public String getSpfirstname() {
		return spfirstname;
	}

	public String getSpmiddlename() {
		return spmiddlename;
	}

	public String getSplastname() {
		return splastname;
	}
	
	public CodeValue getSpouseRelationShip() {
		return spouseRelationShip;
	}

	public void setSpouseRelationShip(CodeValue spouseRelationShip) {
		this.spouseRelationShip = spouseRelationShip;
	}
		
	public String getExternalId2() {
		return externalId2;
	}

	public void setExternalId2(String externalId2) {
		this.externalId2 = externalId2;
	}

	public void update(final CodeValue salutation,
			final CodeValue maritalStatus, final CodeValue profession,
			final String professionOthers,
			final CodeValue educationalQualification,
			final CodeValue annualIncome, final CodeValue landholding,
			final CodeValue houseType, final String aadhaarNo,
			final String panNo, final CodeValue panForm, final String nregaNo,
			final String spfirstname, final String spmiddlename,
			final String splastname,
			final CodeValue spouseRelationShip,
			final String externalId2) {

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
		this.spfirstname = spfirstname;
		this.spmiddlename = spmiddlename;
		this.splastname = splastname;
		this.spouseRelationShip=spouseRelationShip;
		this.externalId2=externalId2;
	}
}

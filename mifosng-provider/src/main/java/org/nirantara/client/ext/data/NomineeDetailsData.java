package org.nirantara.client.ext.data;

import java.util.Date;

import org.nirantara.client.ext.domain.NomineeDetails;

public class NomineeDetailsData {

	private final Long id;
	private final Long salutation;
	private final String salutationLabel;
	private final String name;
	private final Long gender;
	private final String genderLabel;
	private final Integer age;
	private final Long relationship;
	private final String relationshipLabel;	
	private final Date dateOfBirth;
	private final String guardianName;
	private final String address;
	private final Date guardianDateOfBirth;
	private final String guardianRelationship;
	
	public static NomineeDetailsData formNomineeDetailsData(final NomineeDetails nomineeDetail) {
		
		Long id = nomineeDetail.getId();
				
		Long salutation = null;
		String salutationLabel = null;
		if(nomineeDetail.getSalutation() != null){
			salutation = nomineeDetail.getSalutation().getId();
			salutationLabel = nomineeDetail.getSalutation().label();
		}
		String name = nomineeDetail.getName();
		Long gender = null;
		String genderLabel = null;
		if(nomineeDetail.getGender() != null){
			gender = nomineeDetail.getGender().getId();
			genderLabel = nomineeDetail.getGender().label();
		}
		
		Integer age = nomineeDetail.getAge();
		
		Long relationship = null;
		String relationshipLabel = null;
		if(nomineeDetail.getRelationship() != null){
			relationship = nomineeDetail.getRelationship().getId();
			relationshipLabel = nomineeDetail.getRelationship().label();
		}		
		Date dateOfBirth = nomineeDetail.getDateOfBirth();
		String guardianName = nomineeDetail.getGuardianName();
		String address = nomineeDetail.getAddress();
		Date guardianDateOfBirth = nomineeDetail.getGuardianDateOfBirth();
		String guardianRelationship = nomineeDetail.getGuardianRelationship();
		
		return new NomineeDetailsData(id, salutation, salutationLabel, name,
				gender, genderLabel, age, relationship,relationshipLabel,
				dateOfBirth, guardianName, address, guardianDateOfBirth,
				guardianRelationship);
	}
	
	private NomineeDetailsData(final Long id, final Long salutation,
			final String salutationLabel, final String name, final Long gender,
			final String genderLabel, final Integer age,
			final Long relationship, final String relationshipLabel,
			final Date dateOfBirth, final String guardianName,
			final String address, final Date guardianDateOfBirth,
			final String guardianRelationship) {		
		this.id = id;
		this.salutation = salutation;
		this.salutationLabel = salutationLabel;
		this.name = name;
		this.gender = gender;
		this.genderLabel = genderLabel;
		this.age = age;
		this.relationship = relationship;
		this.relationshipLabel = relationshipLabel;	
		this.dateOfBirth = dateOfBirth;		
		this.guardianName = guardianName;
		this.address = address;
		this.guardianDateOfBirth = guardianDateOfBirth;
		this.guardianRelationship = guardianRelationship;
	}


	public Long getId() {
		return id;
	}


	public Long getSalutation() {
		return salutation;
	}


	public String getSalutationLabel() {
		return salutationLabel;
	}


	public String getName() {
		return name;
	}


	public Long getGender() {
		return gender;
	}


	public String getGenderLabel() {
		return genderLabel;
	}


	public Integer getAge() {
		return age;
	}


	public Long getRelationship() {
		return relationship;
	}


	public String getRelationshipLabel() {
		return relationshipLabel;
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
}

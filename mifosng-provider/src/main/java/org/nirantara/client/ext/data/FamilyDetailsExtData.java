package org.nirantara.client.ext.data;

import java.util.Date;

import org.nirantara.client.ext.domain.FamilyDetails;

public class FamilyDetailsExtData {

	private final Long id;
	private final String firstname;
	private final String middlename;
	private final String lastname;
	private final Long relationship;
	private final String relationshipLabel;
	private final Long gender;
	private final String genderLabel;
	private final Date dateOfBirth;
	private final Integer age;
	private final Long occupation;
	private final String occupationLabel;
	private final Long educationalStatus;
	private final String educationalStatusLabel;

	public static FamilyDetailsExtData formFamilyDetailsExtData(final FamilyDetails familyDetails) {
		
		Long id = familyDetails.getId();
		String firstname = familyDetails.getFirstname();
		String middlename =familyDetails.getMiddlename();
		String lastname = familyDetails.getLastname();
		
		Long relationship = null;
		String relationshipLabel = null;
		if(familyDetails.getRelationship() != null){
			relationship = familyDetails.getRelationship().getId();
			relationshipLabel = familyDetails.getRelationship().label();
		}
		
		Long gender = null;
		String genderLabel = null;
		if(familyDetails.getGender() != null){
			gender = familyDetails.getGender().getId();
			genderLabel = familyDetails.getGender().label();
		}
		
		Date dateOfBirth = familyDetails.getDateOfBirth();
		Integer age = familyDetails.getAge();
		
		Long occupation = null;
		String occupationLabel = null;
		if(familyDetails.getOccupation() != null){
			occupation = familyDetails.getOccupation().getId();
			occupationLabel = familyDetails.getOccupation().label();
		}
		
		Long educationalStatus = null;
		String educationalStatusLabel = null;
		if(familyDetails.getEducationalStatus() != null){
			educationalStatus = familyDetails.getEducationalStatus().getId();
			educationalStatusLabel = familyDetails.getEducationalStatus().label();
		}
		
		return new FamilyDetailsExtData(id, firstname, middlename, lastname,
				relationship, relationshipLabel, gender, genderLabel,
				dateOfBirth, age, occupation, occupationLabel,
				educationalStatus, educationalStatusLabel);
	}
	
	private FamilyDetailsExtData(final Long id, final String firstname,
			final String middlename, final String lastname,
			final Long relationship, final String relationshipLabel,
			final Long gender, final String genderLabel,
			final Date dateOfBirth, final Integer age, final Long occupation,
			final String occupationLabel, final Long educationalStatus,
			final String educationalStatusLabel) {
		
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.relationship = relationship;
		this.relationshipLabel = relationshipLabel;
		this.gender = gender;
		this.genderLabel = genderLabel;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.occupation = occupation;
		this.occupationLabel = occupationLabel;
		this.educationalStatus = educationalStatus;
		this.educationalStatusLabel = educationalStatusLabel;
	}

	public Long getId() {
		return id;
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

	public Long getRelationship() {
		return relationship;
	}

	public String getRelationshipLabel() {
		return relationshipLabel;
	}

	public Long getGender() {
		return gender;
	}

	public String getGenderLabel() {
		return genderLabel;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public Long getOccupation() {
		return occupation;
	}

	public String getOccupationLabel() {
		return occupationLabel;
	}

	public Long getEducationalStatus() {
		return educationalStatus;
	}

	public String getEducationalStatusLabel() {
		return educationalStatusLabel;
	}

}

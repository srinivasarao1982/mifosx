package org.nirantara.client.ext.data;

import java.util.Date;

import org.nirantara.client.ext.domain.FamilyDetails;

public class FamilyDetailsExtData {

	private final Long id;
	private final String firstname;
	private final String middlename;
	private final String lastname;
	private final Long relationship;
	private final Long gender;
	private final Date dateOfBirth;
	private final Integer age;
	private final Long occupation;
	private final Long educationalStatus;

	public static FamilyDetailsExtData formFamilyDetailsExtData(final FamilyDetails familyDetails) {
		
		Long id = familyDetails.getId();
		String firstname = familyDetails.getFirstname();
		String middlename =familyDetails.getMiddlename();
		String lastname = familyDetails.getLastname();
		
		Long relationship = null;		
		if(familyDetails.getRelationship() != null){
			relationship = familyDetails.getRelationship().getId();
		}
		
		Long gender = null;
		if(familyDetails.getGender() != null){
			gender = familyDetails.getGender().getId();
		}
		
		Date dateOfBirth = familyDetails.getDateOfBirth();
		Integer age = familyDetails.getAge();
		
		Long occupation = null;
		if(familyDetails.getOccupation() != null){
			occupation = familyDetails.getOccupation().getId();
		}
		
		Long educationalStatus = null;
		if(familyDetails.getEducationalStatus() != null){
			educationalStatus = familyDetails.getEducationalStatus().getId();
		}
		
		return new FamilyDetailsExtData(id, firstname, middlename, lastname,
				relationship, gender, dateOfBirth, age, occupation,
				educationalStatus);
	}
	
	private FamilyDetailsExtData(final Long id,final String firstname, final String middlename,
			final String lastname, final Long relationship,
			final Long gender, final Date dateOfBirth,
			final Integer age, final Long occupation,
			final Long educationalStatus) {
		
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.relationship = relationship;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.occupation = occupation;
		this.educationalStatus = educationalStatus;
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

	public Long getGender() {
		return gender;
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

	public Long getEducationalStatus() {
		return educationalStatus;
	}

}

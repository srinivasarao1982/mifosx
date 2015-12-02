package org.nirantara.client.ext.data;

import java.util.Date;

import org.nirantara.client.ext.domain.Coapplicant;

public class CoapplicantData {

	private final Long id;
	private final String firstName;
	private final String middleName;
	private final String lastName;
	private final Long relationship;
	private final String relationshipLabel;
	private final Date dateOfBirth;
	private final Integer age;
	private final String mothersMaidenName;
	private final String emailId;
	private final String fatherFirstName;
	private final String fatherMiddleName;
	private final String fatherLastName;
	
	public static CoapplicantData formCoapplicantData(final Coapplicant coapplicant) {
		
		Long id = coapplicant.getId();
		String firstName = coapplicant.getFirstName();
		String middleName = coapplicant.getMiddleName();
		String lastName = coapplicant.getLastName();
		Long relationship = null;
		String relationshipLabel = null;
		if(coapplicant.getRelationship() != null){
			relationship = coapplicant.getRelationship().getId();
			relationshipLabel = coapplicant.getRelationship().label();
		}		
		Date dateOfBirth = coapplicant.getDateOfBirth();
		Integer age = coapplicant.getAge();
		String mothersMaidenName = coapplicant.getMothersMaidenName();
		String emailId = coapplicant.getEmailId();
		String fatherFirstName= coapplicant.getFatherFirstName();
		String fatherMiddleName=coapplicant.getFatherMiddleName();
		String fatherLastName=coapplicant.getFatherLastName();
		return new CoapplicantData(id, firstName, middleName, lastName,
				relationship,relationshipLabel, dateOfBirth, age, mothersMaidenName,
				emailId,fatherFirstName,fatherMiddleName,fatherLastName);
	}
	
	
	private CoapplicantData(final Long id,
			final String firstName, final String middleName,
			final String lastName, final Long relationship,final String relationshipLabel,
			final Date dateOfBirth, final Integer age,
			final String mothersMaidenName, final String emailId,final String fatherFirstName,final String fatherMiddleName,final String fatherLastName ) {
		
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.relationship = relationship;
		this.relationshipLabel = relationshipLabel;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.mothersMaidenName = mothersMaidenName;
		this.emailId = emailId;
		this.fatherFirstName=fatherFirstName;
		this.fatherMiddleName=fatherMiddleName;
		this.fatherLastName=fatherLastName;
		
	}


	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
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

	public Integer getAge() {
		return age;
	}

	public String getMothersMaidenName() {
		return mothersMaidenName;
	}

	public String getEmailId() {
		return emailId;
	}


	public String getFatherFirstName() {
		return fatherFirstName;
	}


	public String getFatherMiddleName() {
		return fatherMiddleName;
	}


	public String getFatherLastName() {
		return fatherLastName;
	}
	

}

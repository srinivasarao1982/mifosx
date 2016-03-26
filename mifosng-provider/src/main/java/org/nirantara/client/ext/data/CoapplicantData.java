package org.nirantara.client.ext.data;

import java.util.Date;

import org.nirantara.client.ext.domain.Coapplicant;

public class CoapplicantData {

    private final Long id;
    private final Long salutation;
    private final String salutationLabel;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final Long genderId;
    private final String genderLabel;
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
        Long salutation = null;
        String salutationLabel = null;
        if (coapplicant.getSalutation() != null) {
            salutation = coapplicant.getSalutation().getId();
            salutationLabel = coapplicant.getSalutation().label();
        }
        String firstName = coapplicant.getFirstName();
        String middleName = coapplicant.getMiddleName();
        String lastName = coapplicant.getLastName();
        Long genderId = null;
        String genderLabel = null;
        if (coapplicant.getGender() != null) {
            genderId = coapplicant.getGender().getId();
            genderLabel = coapplicant.getGender().label();
        }
        Long relationship = null;
        String relationshipLabel = null;
        if (coapplicant.getRelationship() != null) {
            relationship = coapplicant.getRelationship().getId();
            relationshipLabel = coapplicant.getRelationship().label();
        }
        Date dateOfBirth = coapplicant.getDateOfBirth();
        Integer age = coapplicant.getAge();
        String mothersMaidenName = coapplicant.getMothersMaidenName();
        String emailId = coapplicant.getEmailId();
        String fatherFirstName = coapplicant.getFatherFirstName();
        String fatherMiddleName = coapplicant.getFatherMiddleName();
        String fatherLastName = coapplicant.getFatherLastName();
        return new CoapplicantData(id, salutation, salutationLabel, firstName, middleName, lastName, genderId, genderLabel, relationship,
                relationshipLabel, dateOfBirth, age, mothersMaidenName, emailId, fatherFirstName, fatherMiddleName, fatherLastName);
    }

    private CoapplicantData(final Long id, final Long salutation, final String salutationLabel, final String firstName,
            final String middleName, final String lastName, final Long genderId, final String genderLabel, final Long relationship,
            final String relationshipLabel, final Date dateOfBirth, final Integer age, final String mothersMaidenName, final String emailId,
            final String fatherFirstName, final String fatherMiddleName, final String fatherLastName) {

        this.id = id;
        this.salutation = salutation;
        this.salutationLabel = salutationLabel;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.genderId = genderId;
        this.genderLabel = genderLabel;
        this.relationship = relationship;
        this.relationshipLabel = relationshipLabel;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.mothersMaidenName = mothersMaidenName;
        this.emailId = emailId;
        this.fatherFirstName = fatherFirstName;
        this.fatherMiddleName = fatherMiddleName;
        this.fatherLastName = fatherLastName;

    }

    public Long getId() {
        return this.id;
    }

    public Long getSalutation() {
        return this.salutation;
    }

    public String getSalutationLabel() {
        return this.salutationLabel;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Long getGenderId() {
        return this.genderId;
    }

    public String getGenderLabel() {
        return this.genderLabel;
    }

    public Long getRelationship() {
        return this.relationship;
    }

    public String getRelationshipLabel() {
        return this.relationshipLabel;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getMothersMaidenName() {
        return this.mothersMaidenName;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getFatherFirstName() {
        return this.fatherFirstName;
    }

    public String getFatherMiddleName() {
        return this.fatherMiddleName;
    }

    public String getFatherLastName() {
        return this.fatherLastName;
    }

}

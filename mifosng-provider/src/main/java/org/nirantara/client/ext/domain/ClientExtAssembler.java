/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.nirantara.client.ext.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.serialization.FromJsonHelper;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientIdentifier;
import org.mifosplatform.portfolio.client.domain.ClientIdentifierRepository;
import org.mifosplatform.portfolio.loanaccount.domain.Loan;
import org.nirantara.client.ext.exception.DupicateDocumentException;
import org.nirantara.client.ext.exception.MandatoryFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ClientExtAssembler {

    private final FromJsonHelper fromApiJsonHelper;
    private final CodeValueRepositoryWrapper codeValueRepository;
    private final ClientExtRepository clientExtRepository;
    private final AddressRepositoryWrapper addressRepository;
    private final FamilyDetailsRepository familyDetailsRepository;
    private final ClientIdentifierRepository clientIdentifierRepository;
    private final OccupationDetailsRepository occupationDetailsRepository;
    private final NomineeDetailsRepository nomineeDetailsRepository;
    private final CoapplicantRepository coapplicantRepository;

    @Autowired
    public ClientExtAssembler(final FromJsonHelper fromApiJsonHelper, final CodeValueRepositoryWrapper codeValueRepository,
            final ClientExtRepository clientExtRepository, final AddressRepositoryWrapper addressRepository,
            final FamilyDetailsRepository familyDetailsRepository, final ClientIdentifierRepository clientIdentifierRepository,
            final OccupationDetailsRepository occupationDetailsRepository, final NomineeDetailsRepository nomineeDetailsRepository,
            final CoapplicantRepository coapplicantRepository) {
        this.fromApiJsonHelper = fromApiJsonHelper;
        this.codeValueRepository = codeValueRepository;
        this.clientExtRepository = clientExtRepository;
        this.addressRepository = addressRepository;
        this.familyDetailsRepository = familyDetailsRepository;
        this.clientIdentifierRepository = clientIdentifierRepository;
        this.occupationDetailsRepository = occupationDetailsRepository;
        this.nomineeDetailsRepository = nomineeDetailsRepository;
        this.coapplicantRepository = coapplicantRepository;
    }

    public ClientExt assembleClientExt(final JsonCommand command, final Client newClient) {

        final JsonObject formDataObject = new JsonParser().parse(command.json()).getAsJsonObject();
        
        if (!formDataObject.has("clientExt")) { return null; }
        
        final JsonElement element = formDataObject.get("clientExt");

        final Long id = this.fromApiJsonHelper.extractLongNamed("id", element);

        final Long salutationId = this.fromApiJsonHelper.extractLongNamed("salutation", element);
        CodeValue salutationCodeValue = null;
        if (salutationId != null) {
            salutationCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(salutationId);
        }
        else{
    	    throw new MandatoryFieldException("title"); 
        }

        final Long maritalStatusId = this.fromApiJsonHelper.extractLongNamed("maritalStatus", element);
        CodeValue maritalStatusCodeValue = null;
        if (maritalStatusId != null) {
            maritalStatusCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(maritalStatusId);
        }
        else{
    	    throw new MandatoryFieldException("maritalStatus"); 
        }
        final Long spouseRelationShipId = this.fromApiJsonHelper.extractLongNamed("spouseRelationShip", element);
        CodeValue spouseRelationShip = null;
        if (spouseRelationShipId != null) {
            spouseRelationShip = this.codeValueRepository.findOneWithNotFoundDetection(spouseRelationShipId);
        }
        else{
    	    throw new MandatoryFieldException("Detail of Father/SpouseMandatory"); 

        }
        final Long professionId = this.fromApiJsonHelper.extractLongNamed("profession", element);
        CodeValue professionCodeValue = null;
        if (professionId != null) {
            professionCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(professionId);
        }

        final String professionOthers = this.fromApiJsonHelper.extractStringNamed("professionOthers", element);
        ;

        final Long educationalQualificationId = this.fromApiJsonHelper.extractLongNamed("educationalQualification", element);
        CodeValue educationalQualificationCodeValue = null;
        if (educationalQualificationId != null) {
            educationalQualificationCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(educationalQualificationId);
        }

        final Long annualIncomeId = this.fromApiJsonHelper.extractLongNamed("annualIncome", element);
        CodeValue annualIncomeCodeValue = null;
        if (annualIncomeId != null) {
            annualIncomeCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(annualIncomeId);
        }

        final Long landholdingId = this.fromApiJsonHelper.extractLongNamed("landholding", element);
        CodeValue landholdingCodeValue = null;
        if (landholdingId != null) {
            landholdingCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(landholdingId);
        }

        final Long houseTypeId = this.fromApiJsonHelper.extractLongNamed("houseType", element);
        CodeValue houseTypeCodeValue = null;
        if (houseTypeId != null) {
            houseTypeCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(houseTypeId);
        }

        final String aadhaarNo = this.fromApiJsonHelper.extractStringNamed("aadhaarNo", element);
        if(aadhaarNo==null){
    	    throw new MandatoryFieldException("aadhaarNo"); 
        }

        final String panNo = this.fromApiJsonHelper.extractStringNamed("panNo", element);

        final Long panFormId = this.fromApiJsonHelper.extractLongNamed("panForm", element);
        CodeValue panFormCodeValue = null;
        if (panFormId != null) {
            panFormCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(panFormId);
        }

        final String nregaNo = this.fromApiJsonHelper.extractStringNamed("nregaNo", element);

        final String spfirstname = this.fromApiJsonHelper.extractStringNamed("spfirstname", element);
        if(spfirstname==null){
    	    throw new MandatoryFieldException("Father First Name"); 
        }
        final String spmiddlename = this.fromApiJsonHelper.extractStringNamed("spmiddlename", element);

        final String splastname = this.fromApiJsonHelper.extractStringNamed("splastname", element);
       
        final String externalId2 = this.fromApiJsonHelper.extractStringNamed("externalId2", element);
        if(externalId2==null){
    	    throw new MandatoryFieldException("externalId2"); 
        }

        if (id != null) {
            final ClientExt updateClientExt = this.clientExtRepository.findOne(id);
            if (updateClientExt != null) {
                updateClientExt.update(salutationCodeValue, maritalStatusCodeValue, professionCodeValue, professionOthers,
                        educationalQualificationCodeValue, annualIncomeCodeValue, landholdingCodeValue, houseTypeCodeValue, aadhaarNo,
                        panNo, panFormCodeValue, nregaNo, spfirstname, spmiddlename, splastname, spouseRelationShip, externalId2);
                return updateClientExt;
            }
            return ClientExt.createFrom(newClient, salutationCodeValue, maritalStatusCodeValue, professionCodeValue, professionOthers,
                    educationalQualificationCodeValue, annualIncomeCodeValue, landholdingCodeValue, houseTypeCodeValue, aadhaarNo, panNo,
                    panFormCodeValue, nregaNo, spfirstname, spmiddlename, splastname, spouseRelationShip, externalId2);
        }
        return ClientExt.createFrom(newClient, salutationCodeValue, maritalStatusCodeValue, professionCodeValue, professionOthers,
                educationalQualificationCodeValue, annualIncomeCodeValue, landholdingCodeValue, houseTypeCodeValue, aadhaarNo, panNo,
                panFormCodeValue, nregaNo, spfirstname, spmiddlename, splastname, spouseRelationShip, externalId2);
    }

    @SuppressWarnings("null")
    public Set<Address> assembleAddress(final JsonArray addressArray, Client newClient) {

        final Set<Address> addressList = new HashSet<>();

        for (int i = 0; i < addressArray.size(); i++) {
            final JsonElement element = addressArray.get(i).getAsJsonObject();
            if (!element.isJsonNull() && !element.toString().equals("{}")) {

                final Long id = this.fromApiJsonHelper.extractLongNamed("id", element);

                final Long addressTypeId = this.fromApiJsonHelper.extractLongNamed("addressType", element);
                final Long stateId = this.fromApiJsonHelper.extractLongNamed("state", element);
                if(stateId==null && addressTypeId==20)
        		{
        	    throw new MandatoryFieldException("state"); 
                 }
                if(addressTypeId==null)
        		{
        	    throw new MandatoryFieldException("addressTypeId"); 
                 }
                CodeValue addressTypeCodeValue = null;
                if (addressTypeId != null && stateId != null) {

                    addressTypeCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(addressTypeId);

                    final String houseNo = this.fromApiJsonHelper.extractStringNamed("houseNo", element);

                    final String streetNo = this.fromApiJsonHelper.extractStringNamed("streetNo", element);

                    final String areaLocality = this.fromApiJsonHelper.extractStringNamed("areaLocality", element);
                      
                    if((areaLocality==null ||  areaLocality.equals("")) && addressTypeId==20)
                    {
                    	throw new MandatoryFieldException("areaLocality"); 
                    }

                    final String landmark = this.fromApiJsonHelper.extractStringNamed("landmark", element);
                    

                    final String villageTown = this.fromApiJsonHelper.extractStringNamed("villageTown", element);
                    if((villageTown==null ||  villageTown.equals("")) &&  addressTypeId==20)
            		{
            	    throw new MandatoryFieldException("villageTown"); 
                     }

                    final String taluka = this.fromApiJsonHelper.extractStringNamed("taluka", element);
                    
                    if((taluka==null ||  taluka.equals("")) &&   addressTypeId==20)
            		{
            	    throw new MandatoryFieldException("taluka"); 
                     }

                    final Long districtId = this.fromApiJsonHelper.extractLongNamed("district", element);
                    if(districtId==null &&  addressTypeId==20)
            		{
            	    throw new MandatoryFieldException("districtId"); 
                     }
                    CodeValue districtCodeValue = null;
                    if (districtId != null) {
                        districtCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(districtId);
                    }

                    CodeValue stateCodeValue = null;
                    if (stateId != null) {
                        stateCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(stateId);
                    }

                    final Integer pinCode = this.fromApiJsonHelper.extractIntegerWithLocaleNamed("pinCode", element);
                    if(pinCode==null &&  addressTypeId==20)
            		{
            	    throw new MandatoryFieldException("pinCode"); 
                     }

                    final Long landlineNo = this.fromApiJsonHelper.extractLongNamed("landlineNo", element);

                    final Long mobileNo = this.fromApiJsonHelper.extractLongNamed("mobileNo", element);
                    
                    if(mobileNo==null &&  addressTypeId==20)
            		{
            	    throw new MandatoryFieldException("mobileNo"); 
                     }

                    Address address = this.addressRepository.findByClientAndAddressType(newClient, addressTypeCodeValue);

                    if (id != null) {
                        address = this.addressRepository.findOneWithNotFoundDetection(id);
                        address.update(addressTypeCodeValue, houseNo, streetNo, areaLocality, landmark, villageTown, taluka,
                                districtCodeValue, stateCodeValue, pinCode, landlineNo, mobileNo);
                    } else if (address != null) {
                        address.update(addressTypeCodeValue, houseNo, streetNo, areaLocality, landmark, villageTown, taluka,
                                districtCodeValue, stateCodeValue, pinCode, landlineNo, mobileNo);
                    } else {
                        address = Address.createFrom(newClient, addressTypeCodeValue, houseNo, streetNo, areaLocality, landmark,
                                villageTown, taluka, districtCodeValue, stateCodeValue, pinCode, landlineNo, mobileNo);
                    }
                    if (address != null) {
                        addressList.add(address);
                    }
                }
            }
        }

        return addressList;
    }

    public List<FamilyDetails> assembleFamilyDetails(final JsonArray familyDetailsArray, final Client newClient) {
        List<FamilyDetails> familyDetailsList = new ArrayList<>();
        for (int i = 0; i < familyDetailsArray.size(); i++) {
            final JsonElement element = familyDetailsArray.get(i).getAsJsonObject();
            if (!element.isJsonNull() && !element.toString().equals("{}")) {

                final Long id = this.fromApiJsonHelper.extractLongNamed("id", element);

                final String firstname = this.fromApiJsonHelper.extractStringNamed("firstname", element);

                if (firstname != null && !firstname.equalsIgnoreCase("null") && firstname.trim().length() > 0) {
                    final String middlename = this.fromApiJsonHelper.extractStringNamed("middlename", element);

                    final String lastname = this.fromApiJsonHelper.extractStringNamed("lastname", element);

                    final Long relationshipId = this.fromApiJsonHelper.extractLongNamed("relationship", element);
                    CodeValue relationshipCodeValue = null;
                    if (relationshipId != null) {
                        relationshipCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(relationshipId);
                    }

                    final Long genderId = this.fromApiJsonHelper.extractLongNamed("gender", element);
                    CodeValue genderCodeValue = null;
                    if (genderId != null) {
                        genderCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(genderId);
                    }

                    final LocalDate dataOfBirth = this.fromApiJsonHelper.extractLocalDateNamed("dateOfBirth", element);

                    final Integer age = this.fromApiJsonHelper.extractIntegerWithLocaleNamed("age", element);

                    final Long occupationId = this.fromApiJsonHelper.extractLongNamed("occupation", element);
                    CodeValue occupationCodeValue = null;
                    if (occupationId != null) {
                        occupationCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(occupationId);
                    }

                    final Long educationalStatusId = this.fromApiJsonHelper.extractLongNamed("educationalStatus", element);
                    CodeValue educationalStatusCodeValue = null;
                    if (educationalStatusId != null) {
                        educationalStatusCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(educationalStatusId);
                    }

                    FamilyDetails familyDetails = null;
                    if (id != null) {
                        familyDetails = this.familyDetailsRepository.findOne(id);
                        if (familyDetails != null) {
                            familyDetails.update(firstname, middlename, lastname, relationshipCodeValue, genderCodeValue, dataOfBirth, age,
                                    occupationCodeValue, educationalStatusCodeValue);

                        } else {
                            familyDetails = FamilyDetails.createFrom(newClient, firstname, middlename, lastname, relationshipCodeValue,
                                    genderCodeValue, dataOfBirth, age, occupationCodeValue, educationalStatusCodeValue);
                        }
                    } else {
                        familyDetails = FamilyDetails.createFrom(newClient, firstname, middlename, lastname, relationshipCodeValue,
                                genderCodeValue, dataOfBirth, age, occupationCodeValue, educationalStatusCodeValue);
                    }

                    if (familyDetails != null) {
                        familyDetailsList.add(familyDetails);
                    }
                }
            }
        }
        return familyDetailsList;
    }

    public List<OccupationDetails> assembleOccupationDetails(final JsonArray occupationDetailsArray, final Client newClient) {
        List<OccupationDetails> occupationDetailsList = new ArrayList<>();
        for (int i = 0; i < occupationDetailsArray.size(); i++) {
            final JsonElement element = occupationDetailsArray.get(i).getAsJsonObject();
            if (!element.isJsonNull() && !element.toString().equals("{}")) {

                final Long id = this.fromApiJsonHelper.extractLongNamed("occupationId", element);

                final Long occupationTypeId = this.fromApiJsonHelper.extractLongNamed("id", element);
                CodeValue occupationTypeCodeValue = null;
                if (occupationTypeId != null) {
                    occupationTypeCodeValue = this.codeValueRepository.findOneWithNotFoundDetection(occupationTypeId);
                    final Locale locale = this.fromApiJsonHelper.extractLocaleParameter(element.getAsJsonObject());

                    final BigDecimal annualRevenue = this.fromApiJsonHelper.extractBigDecimalNamed("revenue", element, locale);

                    final BigDecimal annualExpense = this.fromApiJsonHelper.extractBigDecimalNamed("expense", element, locale);

                    final BigDecimal annualSurplus = this.fromApiJsonHelper.extractBigDecimalNamed("surplus", element, locale);

                    OccupationDetails occupationDetails = null;
                    if (id != null) {
                        occupationDetails = this.occupationDetailsRepository.findOne(id);
                        if (occupationDetails != null) {
                            occupationDetails.update(newClient, occupationTypeCodeValue, annualRevenue, annualExpense, annualSurplus);

                        } else {
                            occupationDetails = OccupationDetails.createFrom(newClient, occupationTypeCodeValue, annualRevenue,
                                    annualExpense, annualSurplus);
                        }
                    } else {
                        occupationDetails = OccupationDetails.createFrom(newClient, occupationTypeCodeValue, annualRevenue, annualExpense,
                                annualSurplus);
                    }

                    if (occupationDetails != null) {
                        occupationDetailsList.add(occupationDetails);
                    }
                }

            }
        }
        return occupationDetailsList;
    }

    @SuppressWarnings("null")
    public List<ClientIdentifier> assembleDoumentIdentifiersDetails(final JsonArray clientIdentifierDataArray, final Client newClient,boolean isUpdate) {
        List<ClientIdentifier> clientIdentifiers = new ArrayList<>();
        for (int i = 0; i < clientIdentifierDataArray.size(); i++) {
            final JsonElement element = clientIdentifierDataArray.get(i).getAsJsonObject();
            if (!element.isJsonNull() && !element.toString().equals("{}")) {

                final Long id = this.fromApiJsonHelper.extractLongNamed("id", element);

                final Long documentTypeId = this.fromApiJsonHelper.extractLongNamed("documentTypeId", element);

                CodeValue documentType = null;
                if (documentTypeId != null) {
                    documentType = this.codeValueRepository.findOneWithNotFoundDetection(documentTypeId);
                }
                final String documentKey = this.fromApiJsonHelper.extractStringNamed("documentKey", element);
                final String description = this.fromApiJsonHelper.extractStringNamed("documentDescription", element);
                List<ClientIdentifier> identifiers=this.clientIdentifierRepository.getclientIdentifier(documentTypeId,documentKey);
                 if(!isUpdate){
                if(identifiers.size()>0){
                	throw new DupicateDocumentException(documentKey);
                }
                 }
                 else{
                     List<ClientIdentifier> identifiersforUpdate=this.clientIdentifierRepository.getclientIdentifierwithclient(documentTypeId,documentKey,newClient.getId());
                     if(identifiersforUpdate.size()==0){
                    	 if(identifiers.size()>0){
                         	throw new DupicateDocumentException(documentKey); 
                    	 }
                     }
                   }
                ClientIdentifier clientIdentifier = null;
                if (id != null) {
                    clientIdentifier = this.clientIdentifierRepository.findOne(id);
                    if (clientIdentifier != null) {
                        clientIdentifier.update(newClient, documentType, documentKey, description);
                    } else {
                        clientIdentifier = new ClientIdentifier(newClient, documentType, documentKey, description);
                    }
                } else {
                    clientIdentifier = new ClientIdentifier(newClient, documentType, documentKey, description);
                }
                if (clientIdentifier != null) {
                    clientIdentifiers.add(clientIdentifier);
                }
            }
            
        }
        return clientIdentifiers;
    }

    public List<NomineeDetails> assembleNomineeDetails(final JsonArray nomineeDetailsArray, final Client newClient) {

        List<NomineeDetails> nomineeDetails = new ArrayList<>();
        for (int i = 0; i < nomineeDetailsArray.size(); i++) {
            final JsonElement element = nomineeDetailsArray.get(i).getAsJsonObject();

            if (!element.isJsonNull() && !element.toString().equals("{}")) {

                final String name = this.fromApiJsonHelper.extractStringNamed("name", element);

                if (name != null && !name.equalsIgnoreCase("null") && name.trim().length() > 0) {
                    final Long id = this.fromApiJsonHelper.extractLongNamed("id", element);

                    final Long salutationId = this.fromApiJsonHelper.extractLongNamed("salutation", element);
                    CodeValue salutation = null;
                    if (salutationId != null) {
                        salutation = this.codeValueRepository.findOneWithNotFoundDetection(salutationId);
                    }

                    final Long genderId = this.fromApiJsonHelper.extractLongNamed("gender", element);
                    CodeValue gender = null;
                    if (genderId != null) {
                        gender = this.codeValueRepository.findOneWithNotFoundDetection(genderId);
                    }

                    final Integer age = this.fromApiJsonHelper.extractIntegerWithLocaleNamed("age", element);

                    final Long relationshipId = this.fromApiJsonHelper.extractLongNamed("relationship", element);
                    CodeValue relationship = null;
                    if (relationshipId != null) {
                        relationship = this.codeValueRepository.findOneWithNotFoundDetection(relationshipId);
                    }

                    final LocalDate dateOfBirth = this.fromApiJsonHelper.extractLocalDateNamed("dateOfBirth", element);

                    final String guardianName = this.fromApiJsonHelper.extractStringNamed("guardianName", element);

                    final String address = this.fromApiJsonHelper.extractStringNamed("address", element);

                    final LocalDate guardianDateOfBirth = this.fromApiJsonHelper.extractLocalDateNamed("dateOfBirth", element);

                    final String guardianRelationship = this.fromApiJsonHelper.extractStringNamed("guardianRelationship", element);

                    NomineeDetails nomineeDetail = null;
                    if (id != null) {
                        nomineeDetail = this.nomineeDetailsRepository.findOne(id);
                        if (nomineeDetail != null) {
                            nomineeDetail.update(newClient, salutation, name, gender, age, relationship, dateOfBirth, guardianName, address,
                                    guardianDateOfBirth, guardianRelationship);

                        } else {
                            nomineeDetail = NomineeDetails.createFrom(newClient, salutation, name, gender, age, relationship, dateOfBirth,
                                    guardianName, address, guardianDateOfBirth, guardianRelationship);
                        }
                    } else {
                        nomineeDetail = NomineeDetails.createFrom(newClient, salutation, name, gender, age, relationship, dateOfBirth,
                                guardianName, address, guardianDateOfBirth, guardianRelationship);
                    }

                    if (nomineeDetail != null) {
                        nomineeDetails.add(nomineeDetail);
                    }
                }

            }
        }
        return nomineeDetails;
    }

    public List<Coapplicant> assembleCoClientDataArray(final JsonArray coClientDataArray, final Client newClient) {
        List<Coapplicant> coapplicant = new ArrayList<>();
        for (int i = 0; i < coClientDataArray.size(); i++) {
            final JsonElement element = coClientDataArray.get(i).getAsJsonObject();

            if (!element.isJsonNull() && !element.toString().equals("{}")) {

                final Long id = this.fromApiJsonHelper.extractLongNamed("id", element);

                final Long salutationId = this.fromApiJsonHelper.extractLongNamed("salutation", element);
                CodeValue salutation = null;
                if (salutationId != null) {
                    salutation = this.codeValueRepository.findOneWithNotFoundDetection(salutationId);
                }
                else{
                	throw new MandatoryFieldException("title"); 
                }
                
                final String firstName = this.fromApiJsonHelper.extractStringNamed("firstName", element);
                if(firstName==null ||firstName=="" ){
                	throw new MandatoryFieldException("firstName");
                }

                final String middleName = this.fromApiJsonHelper.extractStringNamed("middleName", element);

                final String lastName = this.fromApiJsonHelper.extractStringNamed("lastName", element);
               

                final Long genderId = this.fromApiJsonHelper.extractLongNamed("genderId", element);
                CodeValue gender = null;
                if (genderId != null) {
                    gender = this.codeValueRepository.findOneWithNotFoundDetection(genderId);
                }
                else{
                	throw new MandatoryFieldException("gender");
                }

                final Long relationshipId = this.fromApiJsonHelper.extractLongNamed("relationship", element);
                CodeValue relationship = null;
                if (relationshipId != null) {
                    relationship = this.codeValueRepository.findOneWithNotFoundDetection(relationshipId);
                }
                else{
                	throw new MandatoryFieldException("Details of Father/Spouse");
                }

                final LocalDate dateOfBirth = this.fromApiJsonHelper.extractLocalDateNamed("dateOfBirth", element);
                if(dateOfBirth==null){
                	throw new MandatoryFieldException("dateOfBirth");

                }

                final Integer age = this.fromApiJsonHelper.extractIntegerWithLocaleNamed("age", element);

                final String mothersMaidenName = this.fromApiJsonHelper.extractStringNamed("mothersMaidenName", element);

               
                final String emailId = this.fromApiJsonHelper.extractStringNamed("emailId", element);

                final String fatherFirstName = this.fromApiJsonHelper.extractStringNamed("fatherFirstName", element);
                if(fatherFirstName==null){
                	throw new MandatoryFieldException("dateOfBirth");

                }
                final String fatherMiddleName = this.fromApiJsonHelper.extractStringNamed("fatherMiddleName", element);

                final String fatherLastName = this.fromApiJsonHelper.extractStringNamed("fatherLastName", element);

                Coapplicant coapp = null;
                if (id != null) {
                    coapp = this.coapplicantRepository.findOne(id);
                    if (coapp != null) {
                        coapp.update(newClient, salutation, firstName, middleName, lastName, gender, relationship, dateOfBirth, age,
                                mothersMaidenName, emailId, fatherFirstName, fatherMiddleName, fatherLastName);

                    } else {
                        coapp = Coapplicant.createFrom(newClient, salutation, firstName, middleName, lastName, gender, relationship,
                                dateOfBirth, age, mothersMaidenName, emailId, fatherFirstName, fatherMiddleName, fatherLastName);
                    }
                } else {
                    coapp = Coapplicant.createFrom(newClient, salutation, firstName, middleName, lastName, gender, relationship,
                            dateOfBirth, age, mothersMaidenName, emailId, fatherFirstName, fatherMiddleName, fatherLastName);
                }

                if (coapp != null) {
                    coapplicant.add(coapp);
                }
            }
        }
        return coapplicant;
    }

    public LoanExt assembleLoantemparyId(final Loan loan, final String loanApplicationId) {

        return LoanExt.createTemparyId(loan, loanApplicationId);

    }

}

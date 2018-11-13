/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.client.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.mifosplatform.portfolio.client.data.ClientData;

public class ClientApiConstants {

    public static final String CLIENT_RESOURCE_NAME = "client";
    public static final String CLIENT_CLOSURE_REASON = "ClientClosureReason";
    public static final String CLIENT_ACTION_REASON = "ClientActionReason";
    public static final String CLIENT_REJECT_REASON = "ClientRejectReason";
    public static final String CLIENT_WITHDRAW_REASON = "ClientWithdrawReason";
    public static final String SPOUSE_RELATIONSHIP=  "spouseRelationShip";

	
    public static final String GENDER = "Gender";
    public static final String CLIENT_TYPE = "ClientType";
    public static final String CLIENT_CLASSIFICATION = "ClientClassification";
    // general
    public static final String localeParamName = "locale";
    public static final String dateFormatParamName = "dateFormat";

    // request parameters
    public static final String idParamName = "id";
    public static final String groupIdParamName = "groupId";
    public static final String accountNoParamName = "accountNo";
    public static final String externalIdParamName = "externalId";
    public static final String mobileNoParamName = "mobileNo";
    public static final String firstnameParamName = "firstname";
    public static final String middlenameParamName = "middlename";
    public static final String lastnameParamName = "lastname";
    public static final String fullnameParamName = "fullname";
    public static final String officeIdParamName = "officeId";
    public static final String transferOfficeIdParamName = "transferOfficeIdParamName";
    public static final String activeParamName = "active";
    public static final String activationDateParamName = "activationDate";
    public static final String reactivationDateParamName = "reactivationDate";
    public static final String staffIdParamName = "staffId";
    public static final String closureDateParamName = "closureDate";
    public static final String closureReasonIdParamName = "closureReasonId";
    
    public static final String rejectionDateParamName = "rejectionDate";
    public static final String rejectionReasonIdParamName ="rejectionReasonId";
    public static final String withdrawalDateParamName = "withdrawalDate";
    public static final String withdrawalReasonIdParamName ="withdrawalReasonId";
        
    public static final String submittedOnDateParamName = "submittedOnDate";
    public static final String savingsProductIdParamName = "savingsProductId";
    public static final String savingsAccountIdParamName = "savingsAccountId";
    public static final String dateOfBirthParamName = "dateOfBirth";
    public static final String genderIdParamName = "genderId";
    public static final String genderParamName = "gender";
    public static final String clientTypeIdParamName = "clientTypeId";
    public static final String clientTypeParamName = "clientType";
    public static final String clientClassificationIdParamName = "clientClassificationId";
    public static final String clientClassificationParamName = "clientClassification";
    // response parameters
    public static final String statusParamName = "status";
    public static final String hierarchyParamName = "hierarchy";
    public static final String displayNameParamName = "displayName";
    public static final String officeNameParamName = "officeName";
    public static final String staffNameParamName = "staffName";
    public static final String trasnferOfficeNameParamName = "transferOfficeName";
    public static final String transferToOfficeNameParamName = "transferToOfficeName";
    public static final String transferToOfficeIdParamName = "transferToOfficeId";
    public static final String imageKeyParamName = "imageKey";
    public static final String imageIdParamName = "imageId";
    public static final String imagePresentParamName = "imagePresent";
    public static final String timelineParamName = "timeline";

    // associations related part of response
    public static final String groupsParamName = "groups";

    // template related part of response
    public static final String officeOptionsParamName = "officeOptions";
    public static final String staffOptionsParamName = "staffOptions";
    
    //Nirantara
    public static final String clientExtParamName = "clientExt";
    public static final String familyDetailsParamName = "familyDetails";
    public static final String naddressParamName = "naddress";

    public static final String clientIdentifierDataParamName = "clientIdentifierData";
    public static final String occupationDetailsParamName = "cfaOccupations";
    public static final String nomineeDetailsParamName = "nomineeDetails";
    public static final String coClientDataParamName = "coClientData";
    public static final String isReprocessedParamName = "isReprocessed";
    public static final String gurdianTitlesParamName="gurdianTitles";
    public static final String gurdianRelationParamName="gurdianRelations";

    public static final Set<String> CLIENT_UPDATE_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,officeIdParamName,dateFormatParamName, accountNoParamName, externalIdParamName, mobileNoParamName, firstnameParamName, middlenameParamName,lastnameParamName, fullnameParamName, activeParamName, activationDateParamName, staffIdParamName, savingsProductIdParamName,
            dateOfBirthParamName, genderIdParamName, clientTypeIdParamName, clientClassificationIdParamName,submittedOnDateParamName,clientExtParamName,familyDetailsParamName,naddressParamName,clientIdentifierDataParamName, occupationDetailsParamName,nomineeDetailsParamName,coClientDataParamName,isReprocessedParamName));
  

    /**
     * These parameters will match the class level parameters of
     * {@link ClientData}. Where possible, we try to get response parameters to
     * match those of request parameters.
     */
    public static final Set<String> CLIENT_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(idParamName, accountNoParamName,
            externalIdParamName, statusParamName, activeParamName, activationDateParamName, firstnameParamName, middlenameParamName,
            lastnameParamName, fullnameParamName, displayNameParamName, mobileNoParamName, officeIdParamName, officeNameParamName,
            transferToOfficeIdParamName, transferToOfficeNameParamName, hierarchyParamName, imageIdParamName, imagePresentParamName,
            staffIdParamName, staffNameParamName, timelineParamName, groupsParamName, officeOptionsParamName, staffOptionsParamName,
            dateOfBirthParamName, genderParamName,clientTypeParamName,clientClassificationParamName,isReprocessedParamName,gurdianTitlesParamName,gurdianRelationParamName));

    public static final Set<String> ACTIVATION_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,
            dateFormatParamName, activationDateParamName));
    public static final Set<String> REACTIVATION_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,
            dateFormatParamName, reactivationDateParamName));

    public static final Set<String> CLIENT_CLOSE_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,
            dateFormatParamName, closureDateParamName, closureReasonIdParamName));
	
    public static final Set<String> CLIENT_REJECT_DATA_PARAMETERS =	new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName,rejectionDateParamName,rejectionReasonIdParamName));

    public static final Set<String> CLIENT_WITHDRAW_DATA_PARAMETERS =	new HashSet<>(Arrays.asList(localeParamName,dateFormatParamName,withdrawalDateParamName,withdrawalReasonIdParamName));

    
    // Nirantara specific
    
    public static final String CLIENT_SALUTATION = "salutation";
    public static final String MARITAL_STATUS = "maritalStatus";
    public static final String PROFESSION = "profession";
    public static final String EDUCATION_QUALIFICATION = "educationQualification";
    public static final String ANNUAL_INCOME = "annualIncome";
    public static final String LAND_HOLDING = "landHolding";
    public static final String HOUSE_TYPE = "houseType";
    public static final String STATE = "state";
    public static final String DISTRICT = "district";
    public static final String IDENTITY_PROOF = "identityProof";
    public static final String ADDRESS_PROOF = "addressProof";
    public static final String FAMILY_RELATIONSHIP = "familyrelationShip";
    public static final String FAMILY_OCCUPATION = "familyOccupation";
    public static final String YES_NO = "YesNo";
    public static final String FIN_OCCUPATION = "cfaOccupation";
    public static final String EXTERNALLOAN_STATUS = "externalLoanstatus";
    public static final String CLIENT_ADDRESS_TYPE = "addressType";
    public static final String PRESETLOANSOURCETYPES = "presentLoanSourceTypes";
    public static final String PRESETLOANPURPOSETYPES = "presentLoanPurposeTypes";
    public static final String GURDIANTITLE = "gurdianTitles";
    public static final String GURDIANRELATION = "gurdianRelations";


    
    public static final Set<String> CLIENT_CREATE_REQUEST_DATA_PARAMETERS = new HashSet<>(Arrays.asList(localeParamName,
            dateFormatParamName, groupIdParamName, accountNoParamName, externalIdParamName, mobileNoParamName, firstnameParamName,
            middlenameParamName, lastnameParamName, fullnameParamName, officeIdParamName, activeParamName, activationDateParamName,
            staffIdParamName, submittedOnDateParamName, savingsProductIdParamName, dateOfBirthParamName, genderIdParamName,
            clientTypeIdParamName, clientClassificationIdParamName, clientClassificationParamName,CLIENT_TYPE,clientExtParamName,familyDetailsParamName,naddressParamName,clientIdentifierDataParamName, occupationDetailsParamName,nomineeDetailsParamName));
    
    
}
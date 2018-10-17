/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.client.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.api.ApiParameterHelper;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.Page;
import org.mifosplatform.infrastructure.core.service.PaginationHelper;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.core.service.SearchParameters;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.office.data.OfficeData;
import org.mifosplatform.organisation.office.service.OfficeReadPlatformService;
import org.mifosplatform.organisation.staff.data.StaffData;
import org.mifosplatform.organisation.staff.service.StaffReadPlatformService;
import org.mifosplatform.portfolio.client.api.ClientApiConstants;
import org.mifosplatform.portfolio.client.data.AgriOccupationDetails;
import org.mifosplatform.portfolio.client.data.ClientAdditionalDetails;
import org.mifosplatform.portfolio.client.data.ClientAddress;
import org.mifosplatform.portfolio.client.data.ClientCFADetails;
import org.mifosplatform.portfolio.client.data.ClientData;
import org.mifosplatform.portfolio.client.data.ClientDetailedData;
import org.mifosplatform.portfolio.client.data.ClientFamilyDetails;
import org.mifosplatform.portfolio.client.data.ClientIdentifierData;
import org.mifosplatform.portfolio.client.data.ClientKYCData;
import org.mifosplatform.portfolio.client.data.ClientTimelineData;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientEnumerations;
import org.mifosplatform.portfolio.client.domain.ClientIdentifier;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.domain.ClientStatus;
import org.mifosplatform.portfolio.client.exception.ClientNotFoundException;
import org.mifosplatform.portfolio.group.data.GroupGeneralData;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.savings.data.SavingsProductData;
import org.mifosplatform.portfolio.savings.service.SavingsProductReadPlatformService;
import org.mifosplatform.useradministration.domain.AppUser;
import org.nirantara.client.ext.data.AddressExtData;
import org.nirantara.client.ext.data.ClientDataExt;
import org.nirantara.client.ext.data.CoapplicantDetailsData;
import org.nirantara.client.ext.data.FamilyDetailsExtData;
import org.nirantara.client.ext.data.NomineeDetailsData;
import org.nirantara.client.ext.data.OccupationDetailsData;
import org.nirantara.client.ext.domain.Address;
import org.nirantara.client.ext.domain.FamilyDetails;
import org.nirantara.client.ext.domain.NomineeDetails;
import org.nirantara.client.ext.domain.OccupationDetails;
import org.nirantara.client.ext.service.CoapplicantReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ClientReadPlatformServiceImpl implements ClientReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;
    private final OfficeReadPlatformService officeReadPlatformService;
    private final StaffReadPlatformService staffReadPlatformService;
    private final CodeValueReadPlatformService codeValueReadPlatformService;
    private final SavingsProductReadPlatformService savingsProductReadPlatformService;
    // data mappers
    private final PaginationHelper<ClientData> paginationHelper = new PaginationHelper<>();
    private final ClientMapper clientMapper = new ClientMapper();
    private final ClientLookupMapper lookupMapper = new ClientLookupMapper();
    private final ClientMembersOfGroupMapper membersOfGroupMapper = new ClientMembersOfGroupMapper();
    private final ParentGroupsMapper clientGroupsMapper = new ParentGroupsMapper();
    private final ClientRepositoryWrapper clientRepository;
    private final CoapplicantReadPlatformService coapplicantReadPlatformService;

    @Autowired
    public ClientReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource,
            final OfficeReadPlatformService officeReadPlatformService, final StaffReadPlatformService staffReadPlatformService,
            final CodeValueReadPlatformService codeValueReadPlatformService,
            final SavingsProductReadPlatformService savingsProductReadPlatformService, final ClientRepositoryWrapper clientRepository,
            final CoapplicantReadPlatformService coapplicantReadPlatformService) {
        this.context = context;
        this.officeReadPlatformService = officeReadPlatformService;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.staffReadPlatformService = staffReadPlatformService;
        this.codeValueReadPlatformService = codeValueReadPlatformService;
        this.savingsProductReadPlatformService = savingsProductReadPlatformService;
        this.clientRepository = clientRepository;
        this.coapplicantReadPlatformService = coapplicantReadPlatformService;
    }

    @Override
    public ClientDetailedData retrieveClientDetailedTemplate(final Long officeId, final boolean staffInSelectedOfficeOnly,
            final Long clientId,final boolean loanOfficersOnly) {
        this.context.authenticatedUser();
        
        
        ClientData clientBasicDetails = retrieveTemplate(officeId, staffInSelectedOfficeOnly,loanOfficersOnly);

        final ClientAdditionalDetails additionalDetails = null;
        final ClientAddress address = null;
        final ClientFamilyDetails familyDetails = null;
        final ClientCFADetails cfaDetails = null;
        final AgriOccupationDetails agriOccupation = null;
        final ClientIdentifierData identifier = null;
        final ClientKYCData kycDetails = null;

        Collection<CodeValueData> spouseRelationShip = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.SPOUSE_RELATIONSHIP));

        Collection<CodeValueData> salutation = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.CLIENT_SALUTATION));

        Collection<CodeValueData> maritalStatus = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.MARITAL_STATUS));

        Collection<CodeValueData> profession = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.PROFESSION));

        Collection<CodeValueData> educationQualification = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.EDUCATION_QUALIFICATION));

        Collection<CodeValueData> annualIncome = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.ANNUAL_INCOME));

        Collection<CodeValueData> landHolding = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.LAND_HOLDING));

        Collection<CodeValueData> houseType = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.HOUSE_TYPE));

        Collection<CodeValueData> state = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.STATE));

        Collection<CodeValueData> district = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.DISTRICT));

        Collection<CodeValueData> identityProof = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.IDENTITY_PROOF));

        Collection<CodeValueData> addressProof = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.ADDRESS_PROOF));

        Collection<CodeValueData> familyrelationShip = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.FAMILY_RELATIONSHIP));

        Collection<CodeValueData> familyOccupation = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.FAMILY_OCCUPATION));

        Collection<CodeValueData> yesOrNo = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.YES_NO));

        Collection<CodeValueData> cfaOccupation = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.FIN_OCCUPATION));

        Collection<CodeValueData> externalLoanstatus = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.EXTERNALLOAN_STATUS));

        Collection<CodeValueData> addressTypes = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.CLIENT_ADDRESS_TYPE));

        Collection<CodeValueData> presentLoanSourceTypes = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.PRESETLOANSOURCETYPES));

        Collection<CodeValueData> presentLoanPurposeTypes = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.PRESETLOANPURPOSETYPES));
        
        Collection<CodeValueData> gurdianTitles = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.GURDIANTITLE));

        Collection<CodeValueData> gurdianRelations = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.GURDIANRELATION));

        ClientDataExt clientDataExt = null;
        List<AddressExtData> addressExtData = new ArrayList<>();
        List<FamilyDetailsExtData> familyDetailsExtData = new ArrayList<>();
        List<ClientIdentifierData> clientIdentifierData = new ArrayList<>();
        List<OccupationDetailsData> occupationDetailsDatas = new ArrayList<>();
        List<NomineeDetailsData> nomineeDetailsData = new ArrayList<>();
        CoapplicantDetailsData coapplicantDetailsData = null;
        if (clientId != null) {
            final Client client = this.clientRepository.findOneWithNotFoundDetection(clientId);
            if (client != null) {
                if (client.clientExt() != null) {
                    clientDataExt = ClientDataExt.formClientDataExt(client.clientExt());
                }
                if (client.addressExt() != null) {
                    for (Address addressExt : client.addressExt()) {
                        addressExtData.add(AddressExtData.formAddressExtData(addressExt));
                    }
                }
                if (client.familyDetails() != null) {
                    for (FamilyDetails familyDetailsExt : client.familyDetails()) {
                        familyDetailsExtData.add(FamilyDetailsExtData.formFamilyDetailsExtData(familyDetailsExt));
                    }
                }
                if (client.clientIdentifiers() != null) {
                    for (ClientIdentifier clientIdentifier : client.clientIdentifiers()) {
                        clientIdentifierData.add(ClientIdentifierData.fromData(clientIdentifier));
                    }
                }
                if (client.occupationDetails() != null) {
                    for (OccupationDetails occupationDeatails : client.occupationDetails()) {
                        occupationDetailsDatas.add(OccupationDetailsData.formOccupationDetailsData(occupationDeatails));
                    }
                }
                if (client.nomineeDetails() != null) {
                    for (NomineeDetails nomineeDetail : client.nomineeDetails()) {
                        nomineeDetailsData.add(NomineeDetailsData.formNomineeDetailsData(nomineeDetail));
                    }
                }

                if (client.coapplicant() != null) {
                    coapplicantDetailsData = this.coapplicantReadPlatformService.retrieveCoapplicantDetailsDataTemplate(client);
                }
            }
        }

        return new ClientDetailedData(clientBasicDetails, additionalDetails, address, familyDetails, cfaDetails, agriOccupation, identifier,
                kycDetails, salutation, maritalStatus, profession, educationQualification, annualIncome, landHolding, houseType, state,
                district, identityProof, addressProof, familyrelationShip, familyOccupation, yesOrNo, cfaOccupation, externalLoanstatus,
                addressTypes, presentLoanSourceTypes, presentLoanPurposeTypes, clientDataExt, addressExtData, familyDetailsExtData,
                clientIdentifierData, occupationDetailsDatas, nomineeDetailsData, coapplicantDetailsData, spouseRelationShip,gurdianRelations,gurdianTitles);

    }

    @Override
    public ClientData retrieveTemplate(final Long officeId, final boolean staffInSelectedOfficeOnly,final boolean loanOfficersOnly) {
        this.context.authenticatedUser();

        final Long defaultOfficeId = defaultToUsersOfficeIfNull(officeId);

        final Collection<OfficeData> offices = this.officeReadPlatformService.retrieveAllOfficesForDropdown();

        final Collection<SavingsProductData> savingsProductDatas = this.savingsProductReadPlatformService.retrieveAllForLookupByType(null);

        Collection<StaffData> staffOptions = null;
        if(loanOfficersOnly){
        	staffOptions = this.staffReadPlatformService.retrieveAllStaffInOfficeAndItsParentOfficeHierarchy(defaultOfficeId,
                    loanOfficersOnly);
        }
        else
        {
        if (staffInSelectedOfficeOnly) {
            staffOptions = this.staffReadPlatformService.retrieveAllStaffForDropdown(defaultOfficeId);
        } else {
            staffOptions = this.staffReadPlatformService.retrieveAllStaffInOfficeAndItsParentOfficeHierarchy(defaultOfficeId,
                    loanOfficersOnly);
        }}
        if (CollectionUtils.isEmpty(staffOptions)) {
            staffOptions = null;
        }
        final List<CodeValueData> genderOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.GENDER));

        final List<CodeValueData> clientTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.CLIENT_TYPE));

        final List<CodeValueData> clientClassificationOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(ClientApiConstants.CLIENT_CLASSIFICATION));

        ClientData clientData = ClientData.template(defaultOfficeId, new LocalDate(), offices, staffOptions, null, genderOptions,
                savingsProductDatas, clientTypeOptions, clientClassificationOptions);

        return clientData;
    }

    @Override
    public Page<ClientData> retrieveAll(final SearchParameters searchParameters) {

        final String userOfficeHierarchy = this.context.officeHierarchy();
        final String underHierarchySearchString = userOfficeHierarchy + "%";

        // if (searchParameters.isScopedByOfficeHierarchy()) {
        // this.context.validateAccessRights(searchParameters.getHierarchy());
        // underHierarchySearchString = searchParameters.getHierarchy() + "%";
        // }
        String isgroupSearch = searchParameters.getGroupSearch(); 
        final StringBuilder sqlBuilder = new StringBuilder(200);
        sqlBuilder.append("select SQL_CALC_FOUND_ROWS ");
        if(isgroupSearch!=null){
        	if(isgroupSearch.equalsIgnoreCase("true")){
        	sqlBuilder.append(" mgc.id as groupId,mgc.display_name as groupName, " );
            sqlBuilder.append(" mg.id as centerId ,mg.display_name as centerName,  ");	
        }}
        else{
        	sqlBuilder.append(" null as groupId,null as groupName, " );
            sqlBuilder.append(" null as centerId ,null as centerName,  ");
        }
        sqlBuilder.append(this.clientMapper.schema());
        if(isgroupSearch!=null){
            if(isgroupSearch.equalsIgnoreCase("true")){
            	sqlBuilder.append(" left outer join m_group_client mgl on mgl.client_id = c.id  ");            
                sqlBuilder.append("left outer join  m_group mgc on  mgc.id   = case WHEN mgc.level_id=2 then mgl.group_id END " );
                sqlBuilder.append("left outer join  m_group mg  on  mg.id  = CASE WHEN mg.level_id = 1 then mgc.parent_id  END ");

            }}
        sqlBuilder.append(" where (o.hierarchy like ? or transferToOffice.hierarchy like ?) ");

        final String extraCriteria = buildSqlStringFromClientCriteria(searchParameters);

        if (StringUtils.isNotBlank(extraCriteria)) {
            sqlBuilder.append(" and (").append(extraCriteria).append(")");
        }

        if (searchParameters.isOrderByRequested()) {
            sqlBuilder.append(" order by ").append(searchParameters.getOrderBy());

            if (searchParameters.isSortOrderProvided()) {
                sqlBuilder.append(' ').append(searchParameters.getSortOrder());
            }
        }

        if (searchParameters.isLimited()) {
            sqlBuilder.append(" limit ").append(searchParameters.getLimit());
            if (searchParameters.isOffset()) {
                sqlBuilder.append(" offset ").append(searchParameters.getOffset());
            }
        }

        final String sqlCountRows = "SELECT FOUND_ROWS()";
        return this.paginationHelper.fetchPage(this.jdbcTemplate, sqlCountRows, sqlBuilder.toString(),
                new Object[] { underHierarchySearchString, underHierarchySearchString }, this.clientMapper);
    }

    private String buildSqlStringFromClientCriteria(final SearchParameters searchParameters) {

        String sqlSearch = searchParameters.getSqlSearch();
        final Long officeId = searchParameters.getOfficeId();
        final String externalId = searchParameters.getExternalId();
        final String displayName = searchParameters.getName();
        final String firstname = searchParameters.getFirstname();
        final String lastname = searchParameters.getLastname();

        String extraCriteria = "";
        if (sqlSearch != null) {
            sqlSearch = sqlSearch.replaceAll(" display_name ", " c.display_name ");
            sqlSearch = sqlSearch.replaceAll("display_name ", "c.display_name ");
            extraCriteria = " and (" + sqlSearch + ")";
        }

        if (officeId != null) {
            extraCriteria += " and c.office_id = " + officeId;
        }

        if (externalId != null) {
            extraCriteria += " and c.external_id like " + ApiParameterHelper.sqlEncodeString(externalId);
        }

        if (displayName != null) {
            extraCriteria += " and concat(ifnull(c.firstname, ''), if(c.firstname > '',' ', '') , ifnull(c.lastname, '')) like "
                    + ApiParameterHelper.sqlEncodeString(displayName);
        }

        if (firstname != null) {
            extraCriteria += " and c.firstname like " + ApiParameterHelper.sqlEncodeString(firstname);
        }

        if (lastname != null) {
            extraCriteria += " and c.lastname like " + ApiParameterHelper.sqlEncodeString(lastname);
        }

        if (searchParameters.isScopedByOfficeHierarchy()) {
            extraCriteria += " and o.hierarchy like " + ApiParameterHelper.sqlEncodeString(searchParameters.getHierarchy() + "%");
        }

        if (StringUtils.isNotBlank(extraCriteria)) {
            extraCriteria = extraCriteria.substring(4);
        }

        return extraCriteria;
    }

    @Override
    public ClientData retrieveOne(final Long clientId) {
        try {
            final String hierarchy = this.context.officeHierarchy();
            final String hierarchySearchString = hierarchy + "%";

            final String sql = "select null as centerId,null as groupId,null as centerName,null as groupName ," + this.clientMapper.schema()
                    + " where ( o.hierarchy like ? or transferToOffice.hierarchy like ?) and c.id = ?";
            final ClientData clientData = this.jdbcTemplate.queryForObject(sql, this.clientMapper,
                    new Object[] { hierarchySearchString, hierarchySearchString, clientId });

            final String clientGroupsSql = "select " + this.clientGroupsMapper.parentGroupsSchema();

            final Collection<GroupGeneralData> parentGroups = this.jdbcTemplate.query(clientGroupsSql, this.clientGroupsMapper,
                    new Object[] { clientId });

            return ClientData.setParentGroups(clientData, parentGroups);

        } catch (final EmptyResultDataAccessException e) {
            throw new ClientNotFoundException(clientId);
        }
    }

    @Override
    public Collection<ClientData> retrieveAllForLookup(final String extraCriteria) {

        String sql = "select " + this.lookupMapper.schema();

        if (StringUtils.isNotBlank(extraCriteria)) {
            sql += " and (" + extraCriteria + ")";
        }

        return this.jdbcTemplate.query(sql, this.lookupMapper, new Object[] {});
    }

    @Override
    public Collection<ClientData> retrieveAllForLookupByOfficeId(final Long officeId) {

        final String sql = "select " + this.lookupMapper.schema() + " where c.office_id = ? and c.status_enum != ?";

        return this.jdbcTemplate.query(sql, this.lookupMapper, new Object[] { officeId, ClientStatus.CLOSED.getValue() });
    }

    @Override
    public Collection<ClientData> retrieveClientMembersOfGroup(final Long groupId) {

        final AppUser currentUser = this.context.authenticatedUser();
        final String hierarchy = currentUser.getOffice().getHierarchy();
        final String hierarchySearchString = hierarchy + "%";

        final String sql = "select " + this.membersOfGroupMapper.schema() + " where o.hierarchy like ? and pgc.group_id = ?";

        return this.jdbcTemplate.query(sql, this.membersOfGroupMapper, new Object[] { hierarchySearchString, groupId });
    }

    @Override
    public Collection<ClientData> retrieveActiveClientMembersOfGroup(final Long groupId) {

        final AppUser currentUser = this.context.authenticatedUser();
        final String hierarchy = currentUser.getOffice().getHierarchy();
        final String hierarchySearchString = hierarchy + "%";

        final String sql = "select " + this.membersOfGroupMapper.schema()
                + " where o.hierarchy like ? and pgc.group_id = ? and c.status_enum = ? ";

        return this.jdbcTemplate.query(sql, this.membersOfGroupMapper,
                new Object[] { hierarchySearchString, groupId, ClientStatus.ACTIVE.getValue() });
    }

    private static final class ClientMembersOfGroupMapper implements RowMapper<ClientData> {

        private final String schema;

        public ClientMembersOfGroupMapper() {
            final StringBuilder sqlBuilder = new StringBuilder(200);

            sqlBuilder.append(
                    "c.id as id, c.account_no as accountNo, c.external_id as externalId, c.status_enum as statusEnum,c.sub_status as subStatus, ");
            sqlBuilder.append(
                    "cvSubStatus.code_value as subStatusValue,cvSubStatus.code_description as subStatusDesc,c.office_id as officeId, o.name as officeName, ");
            sqlBuilder.append("c.transfer_to_office_id as transferToOfficeId, transferToOffice.name as transferToOfficeName, ");
            sqlBuilder.append("c.firstname as firstname, c.middlename as middlename, c.lastname as lastname, ");
            sqlBuilder.append("c.fullname as fullname, c.display_name as displayName, ");
            sqlBuilder.append("c.mobile_no as mobileNo, ");
            sqlBuilder.append("c.date_of_birth as dateOfBirth, ");
            sqlBuilder.append("c.gender_cv_id as genderId, ");
            sqlBuilder.append("cv.code_value as genderValue, ");
            sqlBuilder.append("c.client_type_cv_id as clienttypeId, ");
            sqlBuilder.append("cvclienttype.code_value as clienttypeValue, ");
            sqlBuilder.append("c.client_classification_cv_id as classificationId, ");
            sqlBuilder.append("cvclassification.code_value as classificationValue, ");
            sqlBuilder.append("c.activation_date as activationDate, c.image_id as imageId, ");
            sqlBuilder.append("c.staff_id as staffId, s.display_name as staffName,");
            sqlBuilder.append("c.default_savings_product as savingsProductId, sp.name as savingsProductName, ");
            sqlBuilder.append("c.default_savings_account as savingsAccountId, ");

            sqlBuilder.append("c.submittedon_date as submittedOnDate, ");
            sqlBuilder.append("sbu.username as submittedByUsername, ");
            sqlBuilder.append("sbu.firstname as submittedByFirstname, ");
            sqlBuilder.append("sbu.lastname as submittedByLastname, ");

            sqlBuilder.append("c.closedon_date as closedOnDate, ");
            sqlBuilder.append("clu.username as closedByUsername, ");
            sqlBuilder.append("clu.firstname as closedByFirstname, ");
            sqlBuilder.append("clu.lastname as closedByLastname, ");

            sqlBuilder.append("acu.username as activatedByUsername, ");
            sqlBuilder.append("acu.firstname as activatedByFirstname, ");
            sqlBuilder.append("acu.lastname as activatedByLastname ");

            sqlBuilder.append("from m_client c ");
            sqlBuilder.append("join m_office o on o.id = c.office_id ");
            sqlBuilder.append("join m_group_client pgc on pgc.client_id = c.id ");
            sqlBuilder.append("left join m_staff s on s.id = c.staff_id ");
            sqlBuilder.append("left join m_savings_product sp on sp.id = c.default_savings_product ");
            sqlBuilder.append("left join m_office transferToOffice on transferToOffice.id = c.transfer_to_office_id ");

            sqlBuilder.append("left join m_appuser sbu on sbu.id = c.submittedon_userid ");
            sqlBuilder.append("left join m_appuser acu on acu.id = c.activatedon_userid ");
            sqlBuilder.append("left join m_appuser clu on clu.id = c.closedon_userid ");
            sqlBuilder.append("left join m_code_value cv on cv.id = c.gender_cv_id ");
            sqlBuilder.append("left join m_code_value cvclienttype on cvclienttype.id = c.client_type_cv_id ");
            sqlBuilder.append("left join m_code_value cvclassification on cvclassification.id = c.client_classification_cv_id ");
            sqlBuilder.append("left join m_code_value cvSubStatus on cvSubStatus.id = c.sub_status ");

            this.schema = sqlBuilder.toString();
        }

        public String schema() {
            return this.schema;
        }

        @Override
        public ClientData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final String accountNo = rs.getString("accountNo");

            final Integer statusEnum = JdbcSupport.getInteger(rs, "statusEnum");
            final EnumOptionData status = ClientEnumerations.status(statusEnum);

            final Long subStatusId = JdbcSupport.getLong(rs, "subStatus");
            final String subStatusValue = rs.getString("subStatusValue");
            final String subStatusDesc = rs.getString("subStatusDesc");
            final CodeValueData subStatus = CodeValueData.instance(subStatusId, subStatusValue, subStatusDesc);

            final Long officeId = JdbcSupport.getLong(rs, "officeId");
            final String officeName = rs.getString("officeName");

            final Long transferToOfficeId = JdbcSupport.getLong(rs, "transferToOfficeId");
            final String transferToOfficeName = rs.getString("transferToOfficeName");

            final Long id = JdbcSupport.getLong(rs, "id");
            final String firstname = rs.getString("firstname");
            final String middlename = rs.getString("middlename");
            final String lastname = rs.getString("lastname");
            final String fullname = rs.getString("fullname");
            final String displayName = rs.getString("displayName");
            final String externalId = rs.getString("externalId");
            final String mobileNo = rs.getString("mobileNo");
            final LocalDate dateOfBirth = JdbcSupport.getLocalDate(rs, "dateOfBirth");
            final Long genderId = JdbcSupport.getLong(rs, "genderId");
            final String genderValue = rs.getString("genderValue");
            final CodeValueData gender = CodeValueData.instance(genderId, genderValue);

            final Long clienttypeId = JdbcSupport.getLong(rs, "clienttypeId");
            final String clienttypeValue = rs.getString("clienttypeValue");
            final CodeValueData clienttype = CodeValueData.instance(clienttypeId, clienttypeValue);

            final Long classificationId = JdbcSupport.getLong(rs, "classificationId");
            final String classificationValue = rs.getString("classificationValue");
            final CodeValueData classification = CodeValueData.instance(classificationId, classificationValue);

            final LocalDate activationDate = JdbcSupport.getLocalDate(rs, "activationDate");
            final Long imageId = JdbcSupport.getLong(rs, "imageId");
            final Long staffId = JdbcSupport.getLong(rs, "staffId");
            final String staffName = rs.getString("staffName");

            final Long savingsProductId = JdbcSupport.getLong(rs, "savingsProductId");
            final String savingsProductName = rs.getString("savingsProductName");

            final Long savingsAccountId = JdbcSupport.getLong(rs, "savingsAccountId");

            final LocalDate closedOnDate = JdbcSupport.getLocalDate(rs, "closedOnDate");
            final String closedByUsername = rs.getString("closedByUsername");
            final String closedByFirstname = rs.getString("closedByFirstname");
            final String closedByLastname = rs.getString("closedByLastname");

            final LocalDate submittedOnDate = JdbcSupport.getLocalDate(rs, "submittedOnDate");
            final String submittedByUsername = rs.getString("submittedByUsername");
            final String submittedByFirstname = rs.getString("submittedByFirstname");
            final String submittedByLastname = rs.getString("submittedByLastname");

            final String activatedByUsername = rs.getString("activatedByUsername");
            final String activatedByFirstname = rs.getString("activatedByFirstname");
            final String activatedByLastname = rs.getString("activatedByLastname");

            final ClientTimelineData timeline = new ClientTimelineData(submittedOnDate, submittedByUsername, submittedByFirstname,
                    submittedByLastname, activationDate, activatedByUsername, activatedByFirstname, activatedByLastname, closedOnDate,
                    closedByUsername, closedByFirstname, closedByLastname);

            return ClientData.instance(accountNo, status, subStatus, officeId, officeName, transferToOfficeId, transferToOfficeName, id,
                    firstname, middlename, lastname, fullname, displayName, externalId, mobileNo, dateOfBirth, gender, activationDate,
                    imageId, staffId, staffName, timeline, savingsProductId, savingsProductName, savingsAccountId, clienttype,
                    classification,null,null,null,null,null);

        }
    }

    @Override
    public Collection<ClientData> retrieveActiveClientMembersOfCenter(final Long centerId) {

        final AppUser currentUser = this.context.authenticatedUser();
        final String hierarchy = currentUser.getOffice().getHierarchy();
        final String hierarchySearchString = hierarchy + "%";

        final String sql = "select " + this.membersOfGroupMapper.schema()
                + " left join m_group g on pgc.group_id=g.id where o.hierarchy like ? and g.parent_id = ? and c.status_enum = ? group by c.id";

        return this.jdbcTemplate.query(sql, this.membersOfGroupMapper,
                new Object[] { hierarchySearchString, centerId, ClientStatus.ACTIVE.getValue() });
    }

    private static final class ClientMapper implements RowMapper<ClientData> {

        private final String schema;

        public ClientMapper() {
            final StringBuilder builder = new StringBuilder(400);

            builder.append(
                    "c.id as id, c.account_no as accountNo, c.external_id as externalId, c.status_enum as statusEnum,c.sub_status as subStatus, ");
            builder.append(
                    "cvSubStatus.code_value as subStatusValue,cvSubStatus.code_description as subStatusDesc,c.office_id as officeId, o.name as officeName, ");
            builder.append("c.transfer_to_office_id as transferToOfficeId, transferToOffice.name as transferToOfficeName, ");
            builder.append("c.firstname as firstname, c.middlename as middlename, c.lastname as lastname, ");
            builder.append("c.fullname as fullname, c.display_name as displayName, ");
            builder.append("c.mobile_no as mobileNo, ");
            builder.append("c.date_of_birth as dateOfBirth, ");
            builder.append("c.gender_cv_id as genderId, ");
            builder.append("cv.code_value as genderValue, ");
            builder.append("c.client_type_cv_id as clienttypeId, ");
            builder.append("cvclienttype.code_value as clienttypeValue, ");
            builder.append("c.client_classification_cv_id as classificationId, ");
            builder.append("cvclassification.code_value as classificationValue, ");

            builder.append("c.submittedon_date as submittedOnDate, ");
            builder.append("sbu.username as submittedByUsername, ");
            builder.append("sbu.firstname as submittedByFirstname, ");
            builder.append("sbu.lastname as submittedByLastname, ");

            builder.append("c.closedon_date as closedOnDate, ");
            builder.append("clu.username as closedByUsername, ");
            builder.append("clu.firstname as closedByFirstname, ");
            builder.append("clu.lastname as closedByLastname, ");

            // builder.append("c.submittedon as submittedOnDate, ");
            builder.append("acu.username as activatedByUsername, ");
            builder.append("acu.firstname as activatedByFirstname, ");
            builder.append("acu.lastname as activatedByLastname, ");

            builder.append("c.activation_date as activationDate, c.image_id as imageId, ");
            builder.append("c.staff_id as staffId, s.display_name as staffName, ");
            builder.append("c.default_savings_product as savingsProductId, sp.name as savingsProductName, ");
            builder.append("c.default_savings_account as savingsAccountId, ");
            builder.append("c.is_reprocessed as isReprocessed ");
            builder.append("from m_client c ");
            builder.append("join m_office o on o.id = c.office_id ");
            builder.append("left join m_staff s on s.id = c.staff_id ");
            builder.append("left join m_savings_product sp on sp.id = c.default_savings_product ");
            builder.append("left join m_office transferToOffice on transferToOffice.id = c.transfer_to_office_id ");
            builder.append("left join m_appuser sbu on sbu.id = c.submittedon_userid ");
            builder.append("left join m_appuser acu on acu.id = c.activatedon_userid ");
            builder.append("left join m_appuser clu on clu.id = c.closedon_userid ");
            builder.append("left join m_code_value cv on cv.id = c.gender_cv_id ");
            builder.append("left join m_code_value cvclienttype on cvclienttype.id = c.client_type_cv_id ");
            builder.append("left join m_code_value cvclassification on cvclassification.id = c.client_classification_cv_id ");
            builder.append("left join m_code_value cvSubStatus on cvSubStatus.id = c.sub_status ");

            this.schema = builder.toString();
        }

        public String schema() {
            return this.schema;
        }

        @Override
        public ClientData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final String accountNo = rs.getString("accountNo");

            final Integer statusEnum = JdbcSupport.getInteger(rs, "statusEnum");
            final EnumOptionData status = ClientEnumerations.status(statusEnum);

            final Long subStatusId = JdbcSupport.getLong(rs, "subStatus");
            final String subStatusValue = rs.getString("subStatusValue");
            final String subStatusDesc = rs.getString("subStatusDesc");
            final CodeValueData subStatus = CodeValueData.instance(subStatusId, subStatusValue, subStatusDesc);

            final Long officeId = JdbcSupport.getLong(rs, "officeId");
            final String officeName = rs.getString("officeName");

            final Long transferToOfficeId = JdbcSupport.getLong(rs, "transferToOfficeId");
            final String transferToOfficeName = rs.getString("transferToOfficeName");

            final Long id = JdbcSupport.getLong(rs, "id");
            final String firstname = rs.getString("firstname");
            final String middlename = rs.getString("middlename");
            final String lastname = rs.getString("lastname");
            final String fullname = rs.getString("fullname");
            final String displayName = rs.getString("displayName");
            final String externalId = rs.getString("externalId");
            final String mobileNo = rs.getString("mobileNo");
            final LocalDate dateOfBirth = JdbcSupport.getLocalDate(rs, "dateOfBirth");
            final Long genderId = JdbcSupport.getLong(rs, "genderId");
            final String genderValue = rs.getString("genderValue");
            final CodeValueData gender = CodeValueData.instance(genderId, genderValue);

            final Long clienttypeId = JdbcSupport.getLong(rs, "clienttypeId");
            final String clienttypeValue = rs.getString("clienttypeValue");
            final CodeValueData clienttype = CodeValueData.instance(clienttypeId, clienttypeValue);

            final Long classificationId = JdbcSupport.getLong(rs, "classificationId");
            final String classificationValue = rs.getString("classificationValue");
            final CodeValueData classification = CodeValueData.instance(classificationId, classificationValue);

            final LocalDate activationDate = JdbcSupport.getLocalDate(rs, "activationDate");
            final Long imageId = JdbcSupport.getLong(rs, "imageId");
            final Long staffId = JdbcSupport.getLong(rs, "staffId");
            final String staffName = rs.getString("staffName");

            final Long savingsProductId = JdbcSupport.getLong(rs, "savingsProductId");
            final String savingsProductName = rs.getString("savingsProductName");
            final Long savingsAccountId = JdbcSupport.getLong(rs, "savingsAccountId");

            final LocalDate closedOnDate = JdbcSupport.getLocalDate(rs, "closedOnDate");
            final String closedByUsername = rs.getString("closedByUsername");
            final String closedByFirstname = rs.getString("closedByFirstname");
            final String closedByLastname = rs.getString("closedByLastname");
            final String groupName = rs.getString("groupName");
            final String centerName =rs.getString("centerName");
            final Long groupId =JdbcSupport.getLong(rs, "groupId"); 
            final Long centerId =JdbcSupport.getLong(rs, "centerId");


            final LocalDate submittedOnDate = JdbcSupport.getLocalDate(rs, "submittedOnDate");
            final String submittedByUsername = rs.getString("submittedByUsername");
            final String submittedByFirstname = rs.getString("submittedByFirstname");
            final String submittedByLastname = rs.getString("submittedByLastname");

            final String activatedByUsername = rs.getString("activatedByUsername");
            final String activatedByFirstname = rs.getString("activatedByFirstname");
            final String activatedByLastname = rs.getString("activatedByLastname");
            final Boolean isReprocessed = rs.getBoolean("isReprocessed");

            final ClientTimelineData timeline = new ClientTimelineData(submittedOnDate, submittedByUsername, submittedByFirstname,
                    submittedByLastname, activationDate, activatedByUsername, activatedByFirstname, activatedByLastname, closedOnDate,
                    closedByUsername, closedByFirstname, closedByLastname);

            return ClientData.instance(accountNo, status, subStatus, officeId, officeName, transferToOfficeId, transferToOfficeName, id,
                    firstname, middlename, lastname, fullname, displayName, externalId, mobileNo, dateOfBirth, gender, activationDate,
                    imageId, staffId, staffName, timeline, savingsProductId, savingsProductName, savingsAccountId, clienttype,
                    classification,groupName,centerName,groupId,centerId,isReprocessed);

        }
    }

    private static final class ParentGroupsMapper implements RowMapper<GroupGeneralData> {

        public String parentGroupsSchema() {
            return "gp.id As groupId , gp.display_name As groupName from m_client cl JOIN m_group_client gc ON cl.id = gc.client_id "
                    + "JOIN m_group gp ON gp.id = gc.group_id WHERE cl.id  = ?";
        }

        @Override
        public GroupGeneralData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long groupId = JdbcSupport.getLong(rs, "groupId");
            final String groupName = rs.getString("groupName");

            return GroupGeneralData.lookup(groupId, groupName);
        }
    }

    private static final class ClientLookupMapper implements RowMapper<ClientData> {

        private final String schema;

        public ClientLookupMapper() {
            final StringBuilder builder = new StringBuilder(200);

            builder.append("c.id as id, c.display_name as displayName, ");
            builder.append("c.office_id as officeId, o.name as officeName ");
            builder.append("from m_client c ");
            builder.append("join m_office o on o.id = c.office_id ");

            this.schema = builder.toString();
        }

        public String schema() {
            return this.schema;
        }

        @Override
        public ClientData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long id = rs.getLong("id");
            final String displayName = rs.getString("displayName");
            final Long officeId = rs.getLong("officeId");
            final String officeName = rs.getString("officeName");

            return ClientData.lookup(id, displayName, officeId, officeName);
        }
    }

    @Override
    public ClientData retrieveClientByIdentifier(final Long identifierTypeId, final String identifierKey) {
        try {
            final ClientIdentifierMapper mapper = new ClientIdentifierMapper();

            final String sql = "select " + mapper.clientLookupByIdentifierSchema();

            return this.jdbcTemplate.queryForObject(sql, mapper, new Object[] { identifierTypeId, identifierKey });
        } catch (final EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final class ClientIdentifierMapper implements RowMapper<ClientData> {

        public String clientLookupByIdentifierSchema() {
            return "c.id as id, c.account_no as accountNo, c.firstname as firstname, c.middlename as middlename, c.lastname as lastname, "
                    + "c.fullname as fullname, c.display_name as displayName," + "c.office_id as officeId, o.name as officeName "
                    + " from m_client c, m_office o, m_client_identifier ci " + "where o.id = c.office_id and c.id=ci.client_id "
                    + "and ci.document_type_id= ? and ci.document_key like ?";
        }

        @Override
        public ClientData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long id = rs.getLong("id");
            final String accountNo = rs.getString("accountNo");

            final String firstname = rs.getString("firstname");
            final String middlename = rs.getString("middlename");
            final String lastname = rs.getString("lastname");
            final String fullname = rs.getString("fullname");
            final String displayName = rs.getString("displayName");

            final Long officeId = rs.getLong("officeId");
            final String officeName = rs.getString("officeName");

            return ClientData.clientIdentifier(id, accountNo, firstname, middlename, lastname, fullname, displayName, officeId, officeName);
        }
    }

    private Long defaultToUsersOfficeIfNull(final Long officeId) {
        Long defaultOfficeId = officeId;
        if (defaultOfficeId == null) {
            defaultOfficeId = this.context.authenticatedUser().getOffice().getId();
        }
        return defaultOfficeId;
    }

    @Override
    public ClientData retrieveAllNarrations(final String clientNarrations) {
        final List<CodeValueData> narrations = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(clientNarrations));
        final Collection<CodeValueData> clientTypeOptions = null;
        final Collection<CodeValueData> clientClassificationOptions = null;
        return ClientData.template(null, null, null, null, narrations, null, null, clientTypeOptions, clientClassificationOptions);
    }
}
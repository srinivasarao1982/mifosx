package org.mifosplatform.portfolio.rblvalidation.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanNotFoundException;
import org.mifosplatform.portfolio.rblvalidation.data.RblCenterValidateData;
import org.mifosplatform.portfolio.rblvalidation.data.RblGroupValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblLoanValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblSavingValidationData;
import org.mifosplatform.portfolio.rblvalidation.data.RblclientDatValidation;
import org.mifosplatform.portfolio.rblvalidation.exception.GenerateFileException;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class RblDataReadplatformServiceImpl  implements RblDataReadplatformService{
	
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;

	@Autowired
	public RblDataReadplatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}
	
	
	@Override
    public List<RblCenterValidateData> readRblCenterData(final String centerId,final String groupId) {
        try {
        	String[]str=groupId.split(",");
        	Long groupIds=Long.parseLong(str[1]);
            final AppUser currentUser = this.context.authenticatedUser();            
            final RblCenterDatMapper rm = new RblCenterDatMapper();
            String Sql ="select"+rm.schema() + " where mg.id in ("+ centerId +")";
            List<RblCenterValidateData>RblCenterValidateDatas=new ArrayList<RblCenterValidateData>();
            RblCenterValidateDatas = this.jdbcTemplate.query(Sql, rm, new Object[] { groupIds,groupIds,groupIds,groupIds});
            return RblCenterValidateDatas;
        } catch (final EmptyResultDataAccessException e) {
            throw new GenerateFileException(centerId,"center");
        }

    }

    private static final class RblCenterDatMapper implements RowMapper<RblCenterValidateData> {

        public RblCenterDatMapper() {}
      
        public String schema() {
            return   "  mg.external_id as externalId,mg.display_name as centerName,mg.activation_date,s.display_name as serviceAgent, "
                     +"mrblc.max_individual as maxIndividual,mrblc.meting_time as meetingTime,concat(ifnull(mrblc.house_no,''),ifnull(mrblc.street_no ,'')) as addressline1 ,"
                     +" concat (ifnull(mrblc.area_loc,''),ifnull(mrblc.landmark,'')) as addressline2,ifnull(mrblc.village,'') as addressLine3,rblb.`city code` as cityCode, "
                     +"state.code_score as state,mrblc.pin as pincode,rblb.`operating region` as OperatingRegion,rblb.`Branch Code` as branchCode, "
                     +"mrblc.description as description,mg.activation_date as formationDate, "
                     +"(select clients.display_name  from m_client clients where  clients.id in (select  mgr.client_id from  m_group_roles mgr where mgr.group_id = ? and mgr.role_cv_id= 97)) as primaryContact, "
                     +"(select clients.mobile_no  from m_client clients where  clients.id in (select  mgr.client_id from  m_group_roles mgr where mgr.group_id = ? and mgr.role_cv_id= 97)) as primaryPhoneNo, "
                     +"(select clients.display_name  from m_client clients where  clients.id in (select  mgr.client_id from  m_group_roles mgr where mgr.group_id =? and mgr.role_cv_id= 98)) as secondaryContactContact, "
                     +"(select clients.mobile_no  from m_client clients where  clients.id in (select  mgr.client_id from  m_group_roles mgr where mgr.group_id = ? and mgr.role_cv_id= 98)) as secondaryPhonePhoneNo "
                     +"from m_group mg "       
                     +" left join m_rblcenter mrblc on mrblc.center_id =mg.id "
                     +" left join m_staff s on s.id=mg.staff_id "
                     +" left join m_code_value meetingTime on meetingTime.id =mrblc.meting_time "
                     +" left join m_code_value district on  district.id=mrblc.district "
                     +" left join m_code_value state on state.id =mrblc.state "
                     +" left join `rbl branch name` rblb on rblb.office_id =mg.office_id ";
                     
                         }
        
        @Override
        public RblCenterValidateData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final String externalId = rs.getString("externalId");
            final String centerName = rs.getString("centerName");
            final String formationDate = rs.getString("formationDate");
            final String serviceAgent=rs.getString("serviceAgent");
            final Integer maximumIndividual =JdbcSupport.getInteger(rs, "maxIndividual");
            final String meetingTime =rs.getString("meetingTime");
            final String addressLine1=rs.getString("addressline1");
            final String addressLine2=rs.getString("addressline2");
            final String addressLine3=rs.getString("addressLine3");
            final String cityCode =rs.getString("cityCode");
            final String stateCode =rs.getString("state");
            final Integer pincode=JdbcSupport.getInteger(rs, "pincode");
            final String operatingRegionCode=rs.getString("OperatingRegion");
            final String branchCode =rs.getString("branchCode");
            final String description =rs.getString("description");
            final String primaryContact=rs.getString("primaryContact");
            final String primaryPhoneNumber=rs.getString("primaryPhoneNo");
            final String secondaryContact =rs.getString("secondaryContactContact");
            final String secondaryPhoneNumber=rs.getString("secondaryPhonePhoneNo");
      
            return new RblCenterValidateData(externalId,centerName,formationDate,serviceAgent,maximumIndividual,meetingTime,addressLine1,
            		addressLine2,addressLine3,cityCode,stateCode,pincode,operatingRegionCode,branchCode,description,primaryContact,
            		primaryPhoneNumber,secondaryContact,secondaryPhoneNumber);
        }

    }

    @Override
    public List<RblclientDatValidation> readRblClientData(final String clientId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();            
            final RblClientDatMapper rm = new RblClientDatMapper();
            String Sql ="select"+rm.schema() +" where mc.id in ("+clientId+ ")";
            List<RblclientDatValidation>RblclientDatValidations=new ArrayList<RblclientDatValidation>();
            RblclientDatValidations = this.jdbcTemplate.query(Sql, rm, new Object[] { });
            return RblclientDatValidations;
        } catch (final EmptyResultDataAccessException e) {
            throw new GenerateFileException(clientId,"client");
        }

    }

    private static final class RblClientDatMapper implements RowMapper<RblclientDatValidation> {

        public RblClientDatMapper() {}

                public String schema() {
            return   "   mc.external_id as externalId,center.external_id as externalCenterId, title.code_score as title,mc.display_name as customerName, "
                   + " concat(ifnull(na.house_no,''),ifnull(na.street_no,'')) as addressline1,concat(ifnull(na.area_locality,''),ifnull(na.landmark,'')) as addressline2, "
                    +"concat(ifnull(na.village_town,''),ifnull(na.taluka,'')) as addressline3,rblbranch.`city code` as cityCode,state.code_score as stateCode,na.pin_code as pincode, "
                   +"mc.date_of_birth as dateofBirth ,mc.mobile_no as mobileNumber,caste.code_score as caste,gender.code_score as gender, "
                   +"maritals.code_score as maritalStatus,relegion.code_score as relegion,'IN' as nataonality,profession.code_score as occupation, "
                   +"profession.code_value as category,edu.code_score as educationqualification,mrbl.pension_card as pensionCard, "
                  +"mrbl.mother_tounge as motherTounge,rblbranch.`Branch Code` as branchCode,rblbranch.`operating region` as operatingRegionCode, "
                  +"nct.aadhaar_no as aadharNo ,mrbl.health as health ,mrbl.`language` as language,mrbl.card_issue_fl as cardIssueFl, "
                  +"rblbranch.`bc branch code` as bcBranchCode,rblbranch.collector as collector,rblbranch.approver as Approver, "
                 +"nco.first_name as nomineeName,nomineeRelation.code_score as nomineeRelation,mrbl.cb_check as cbCheck, "
                 +"bankdtails.bank_name as bankName,bankdtails.account_no as AccountNumber,bankdtails.branch_name as bankbranchName,mrbl.spouse_name as spouseName , mrbl.spouse_DatofBirth as spouseDateOfBirth, Round(datediff(curdate(),mc.date_of_birth)/365) as ClientAge , "
                 +"mrbl.renewal_fl as renewalFl,nct.pan_no  as panNo,nct.external_Id2 as barcodeNumber,mrbl.adharSeeding_constant as adharSeedingConstant, "
                 +"(select mci.document_key from m_client_identifier mci where  mci.client_id =3 and mci.document_type_id =43) as rationcard, "
                 +"(select mci.document_key from m_client_identifier mci where  mci.client_id =4 and mci.document_type_id =41) as voterId "
                 +"from m_client mc "
                 +"left join m_group_client mgc on mgc.client_id =mc.id "
                 +"left join m_group mg on mgc.group_id =mg.id "
                 +"left join m_group center on center.id =mg.parent_id and center.level_id =1 "
                 +"left join n_client_ext nct on mc.id=nct.client_id "
                 +"left join m_rblcustomer mrbl on mrbl.client_id=mc.id "
                 +"left join m_code_value title on nct.salutation_cv_id =title.id "
                 +"left join n_address na on na.client_id =mc.id and na.address_type_cv_id=20 "
                 +"left join m_code_value district on district.id=na.district_cv_id "
                 +"left join m_code_value state on state.id=na.state_cv_id "
                 +"left join m_code_value caste on caste.id =mc.client_type_cv_id "
                 +"left join m_code_value gender on gender.id=mc.gender_cv_id "
                 +"left join m_code_value maritals on maritals.id=nct.marital_status_cv_id "
                +"left join m_code_value relegion  on relegion.id =mc.client_classification_cv_id "
                 +"left join m_code_value profession on profession.id =nct.profession_cv_id "
                +"left join m_code_value edu on edu.id=nct.educational_qualification_cv_id "
                +"left join `RBL Branch Name` rblbranch on rblbranch.office_id =mc.office_id "
                 +"left join n_coapplicant nco on nco.client_id =mc.id "   
                +"left join m_code_value nomineeRelation on nomineeRelation.id=nco.sp_relationship_cv_id "
               +"left join m_bankdetails bankdtails on bankdtails.client_id = mc.id ";

        }        
      	
        @Override
        public RblclientDatValidation mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final String externalId = rs.getString("externalId");
            final String externalCenterId=rs.getString("externalCenterId");
            final String title=rs.getString("title");
            final String customerName =rs.getString("customerName");
            final String addressLine1=rs.getString("addressline1");
            final String addressLine2=rs.getString("addressline2");
            final String addressLine3=rs.getString("addressLine3");
            final String cityCode =rs.getString("cityCode");
            final String stateCode =rs.getString("stateCode");
            final Integer pincode=JdbcSupport.getInteger(rs, "pincode");
            final Date dateofBirt =rs.getDate("dateofBirth");
            final String mobileNumber=rs.getString("mobileNumber");
            final String caste =rs.getString("caste");
            final String gender=rs.getString("gender");
            final String maritalStatus=rs.getString("maritalStatus");
            final String nationality=rs.getString("nataonality");
            final String relegion =rs.getString("relegion");
            final String motherTounge=rs.getString("motherTounge");
            final String branchCode =rs.getString("branchCode");
            final String operatingRegionCode=rs.getString("operatingRegionCode");
            final String aadharNo =rs.getString("aadharNo");
            final String pensionCard =rs.getString("pensionCard");
            final String rationcard=rs.getString("rationcard");
            final String voterId =rs.getString("voterId");
            final String health=rs.getString("health");
            final String occpation=rs.getString("occupation");
            final String educationqualification=rs.getString("educationqualification");
            final String category=rs.getString("category");
            final String language =rs.getString("language");
            final Integer cardIssueFl =JdbcSupport.getInteger(rs, "cardIssueFl");
            final String bcBranchCode =rs.getString("bcBranchCode");
            final String collector =rs.getString("collector");
            final String approver =rs.getString("approver");
            final String spouseName=rs.getString("spouseName");
            final Date spouseDateOfBirth =rs.getDate("spouseDateOfBirth");
            final String  nomineeName =rs.getString("nomineeName");
            final String nomineeRelation =rs.getString("nomineeRelation");
            final Integer cbCheck  =JdbcSupport.getInteger(rs, "cbCheck"); 
            final String bankName=rs.getString("bankName");
            final String AccountNumber =rs.getString("AccountNumber");
            final String bankbranchName=rs.getString("bankbranchName");
            final Integer renewalFl=JdbcSupport.getInteger(rs, "renewalFl");
            final  String panno =rs.getString("panNo");
        	final String barcodeNumber =rs.getString("barcodeNumber");
        	final String adharSeedingConstant =rs.getString( "adharSeedingConstant");
        	final Integer clientAge =JdbcSupport.getInteger(rs, "ClientAge");
           
            return new RblclientDatValidation(externalId,externalCenterId,title,customerName,addressLine1,addressLine2,addressLine3,
            		cityCode,stateCode,pincode,dateofBirt,mobileNumber,caste,gender,maritalStatus,nationality,relegion,motherTounge,
            		branchCode,operatingRegionCode,aadharNo,pensionCard,rationcard,voterId,health,occpation,educationqualification,category,
            		language,cardIssueFl,bcBranchCode,collector,approver,spouseName,spouseDateOfBirth,nomineeName,nomineeRelation,
            		cbCheck,bankName,AccountNumber,bankbranchName,renewalFl,panno,barcodeNumber,adharSeedingConstant,clientAge);
        }

    }

    
    @Override
    public List<RblGroupValidationData> readRblGroupData(final String groupId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();            
            final RblGroupDatMapper rm = new RblGroupDatMapper();
            String Sql ="select"+rm.schema()+" where mg.id in ("+groupId +")";
            List<RblGroupValidationData>RblGroupValidationDatas=new ArrayList<RblGroupValidationData>();
            RblGroupValidationDatas = this.jdbcTemplate.query(Sql, rm, new Object[] {});
            return RblGroupValidationDatas;
        } catch (final EmptyResultDataAccessException e) {
            throw new GenerateFileException(groupId,"groupId");
        }

    }

    private static final class RblGroupDatMapper implements RowMapper<RblGroupValidationData> {

        public RblGroupDatMapper() {}

        public String schema() {
            return   "  mg.external_id as externalId,center.external_id as externalCenterId,center.display_name as centerName,(select value from c_configuration where name='max-center')  as maximuncenter, "
                     +" 'JLG' as groupType,(select value from c_configuration where name='min-clients-in-group') as minumNumber,(select value from c_configuration where name='max-clients-in-group') as maximumNumber ,mg.activation_date as formationDate,"
                      +"mrblg.meting_time as meetinTime,'Weekely' as meetingfrequency,mrblg.distance_from_branch as distancefromBranch,"
                      +"rblbranch.`Branch Code` as branchCode,mg.display_name as groupName, "
                      +"rblbranch.`operating region` as operatingRegionCode "
                      +"from m_group mg "
                      +"left join m_group center on center.id =mg.parent_id and center.level_id =1 "
                      +"join m_rblcenter mrblg on mg.parent_id =mrblg.center_id  "
                      +"left join `RBL Branch Name` rblbranch  on rblbranch.office_Id =mg.office_id ";
                      }        
      	
        @Override
        public RblGroupValidationData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
          
         
        	final String externalId = rs.getString("externalId");
            final String centerExternalId=rs.getString("externalCenterId");
            final String centerName =rs.getString("centerName");
            final String maximuncenter = rs.getString("maximuncenter");
            final String groupName =rs.getString("groupName");
        	final String groupType=rs.getString("groupType");
        	final Integer minumNumber=JdbcSupport.getInteger(rs,"minumNumber");
        	final Integer maximumNumber=JdbcSupport.getInteger(rs,"maximumNumber");
        	final Date formationDate=rs.getDate("formationDate");
        	final String meetinTime=rs.getString("meetinTime");
        	final String meetingfrequency=rs.getString("meetingfrequency");
        	final String distancefromBranch=rs.getString("distancefromBranch");
        	final String branchCode=rs.getString("branchCode");
        	final String operatingRegionCode=rs.getString("operatingRegionCode");
        	
            
            return new RblGroupValidationData(externalId,centerExternalId,centerName,maximuncenter,groupName,groupType,
            		minumNumber,maximumNumber,formationDate,meetinTime,meetingfrequency,distancefromBranch,branchCode,operatingRegionCode);
        }

    }

    
    @Override
    public List<RblLoanValidationData> readRblLoanData(final String clientId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();            
            final RblLoanDatMapper rm = new RblLoanDatMapper();
            String Sql ="select"+rm.schema() +"where mc.id in ("+clientId +") and (ml.loan_status_id=100 or ml.loan_status_id=200)";
            List<RblLoanValidationData>RblLoanValidationDatas=new ArrayList<RblLoanValidationData>();
            RblLoanValidationDatas = this.jdbcTemplate.query(Sql, rm, new Object[] { });
            return RblLoanValidationDatas;
        } catch (final EmptyResultDataAccessException e) {
            throw new GenerateFileException(clientId,"loanId");
        }

    }

    private static final class RblLoanDatMapper implements RowMapper<RblLoanValidationData> {

        public RblLoanDatMapper() {}
        
        public String schema() {
            return   "  ml.external_id as externalId,mc.external_id as customerExternalId ,center.external_id as externalCenterId,"
                   + "mg.external_id as groupExternalId, mp.name as loanProductCode,purpose.code_score as loanPurpose,rbl.psl_code as pslcode,"
                    +"ml.principal_amount as loanAmount,'2' as disbursementMode,mp.number_of_repayments as noOfInstallment,"
                    +"ifnull(ml.loan_counter,1) as loanCycle,nlt.loanApplication_Id as barcodeNo,ml.expected_firstrepaymenton_date as loanStratDate,"
                    +"ml.expected_firstrepaymenton_date as repaymentStartDate,ml.expected_disbursedon_date as ExceptedDisbursementDate,"
                    +"rbb.`bc branch code` as bcBranchCode,rbb.collector as colector ,rbb.approver as approver,rbl.to_Up_flag as TopUpLoanFlag,"
                    +"rbl.hosiptal_cash as hosiptalCash,rbl.prepaid_charge as prepaidCharge,"
                    +"nc.first_name as nomineeName, concat(ifnull(na.house_no,''),ifnull(na.street_no,' ')) as nomineeAddressline1,"
                    +"concat(ifnull(na.house_no,''),ifnull(na.street_no,' ')) as gurdianAddressline1,concat(ifnull(na.area_locality,''),ifnull(na.landmark,' ')) as nomineeAddressline2,"
                    +"concat(ifnull(na.area_locality,''),ifnull(na.landmark,' ')) as gurdianAddressline2,ifnull(na.taluka,'') as nomineeAddressline3,"
                    +"ifnull(na.taluka,'') as gurdianAddressline3,rbb.`city code` as nomineecity,rbb.`city code` as gurdianCity,"
                    +"gurdianTitle.code_score as gurdianTitle,mrbl.gurdian_name as gurdianName,mrbl.gurdian_DatofBirth as gurdianDateofBirth,"
                    +"gurdiangender.code_score as gurdianGender,gurdianrelation.code_score as gurdianRelation,Round(datediff(curdate(),nc.date_of_birth)/365) as nomineeAge,maritalStaus.code_Value as MaritalSta,Round(datediff(curdate(),mrbl.spouse_DatofBirth)/365) as SpouseAge, "
                    +"nomineestate.code_score as nomineestate,nomineestate.code_score as gurdianState,'02' as nomineeMinor,relation.code_score as nomineeRlation,nc.date_of_birth as nomineeDateOfBirth,gender.code_score as nomineeGender, "
                    +"if(mp.repayment_period_frequency_enum=1,'02',if(mp.repayment_period_frequency_enum=0,'01',if(mp.repayment_period_frequency_enum=2,'04',if(mp.repayment_period_frequency_enum=3,'07',0)))) as repaymentfrequency "
                    +" from m_client mc "
                    + "left join n_coapplicant nc on nc.client_id =mc.id "
                    + "left join n_client_ext nct on mc.id=nct.client_id "
                    + "left join n_address na on na.client_id =mc.id and na.address_type_cv_id= 26 "
                    +"left join m_rblcustomer mrbl on mrbl.client_id =mc.id " 
                    +"left join m_group_client mgc on mgc.client_id =mc.id "
                    +"left join m_group mg on mgc.group_id =mg.id "
                    +"left join m_group center on center.id =mg.parent_id and center.level_id =1 "
                    +"left join m_loan ml on ml.client_id =mc.id "
                    +"left join m_product_loan mp on mp.id =ml.product_id "
                    +"left join m_code_value purpose on purpose.id =ml.loanpurpose_cv_id "
                    +"left join m_rblloan rbl on rbl.loan_id =ml.id "
                    +"left join n_loan_ext nlt on nlt.loan_id =ml.id "
                    +"left join m_code_value title on title.id =nc.salutation_cv_id "
                    +"left join m_code_value relation on relation.id =nc.sp_relationship_cv_id "
                    +"left join m_code_value gender on gender.id =nc.gender_cv_id "
                    +"left join m_code_value nomineecity on nomineecity.id =na.district_cv_id "
                    +"left join m_code_value nomineestate on nomineestate.id =na.state_cv_id "
                    +"left join m_code_value gurdianTitle on gurdianTitle.id =mrbl.title "
                    +"left join m_code_value gurdiangender on gurdiangender.id =mrbl.gurdian_gender "
                    +"left join m_code_value gurdianrelation on gurdianrelation.id =mrbl.relation_cv_id "
                    +"left join m_code_value maritalStaus on maritalStaus.id=nct.marital_status_cv_id "
                    +"left join `RBL Branch Name` rbb on rbb.office_id =mc.office_id ";
        }        
      	
        @Override
        public RblLoanValidationData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
          
        
        	final String externalId = rs.getString("externalId");
        	final String customerExternalId =rs.getString("customerExternalId");
            final String centerExtrenalId=rs.getString("externalCenterId");
            final String groupExternalId =rs.getString("groupExternalId");
             String loanProductCode =rs.getString("loanProductCode");
             String []str =loanProductCode.split("-");
             loanProductCode=str[2];
            final Integer loanPurpose =JdbcSupport.getInteger(rs, "loanPurpose"); 
            final Integer pslcode =JdbcSupport.getInteger(rs, "pslcode"); 
            final Long loanAmount =JdbcSupport.getLong(rs, "loanAmount"); 
            final String disbursementMode =rs.getString("disbursementMode");
            final Integer noofInstallments =JdbcSupport.getInteger(rs, "noOfInstallment");
            final String repaymentfrequency =rs.getString("repaymentfrequency");
            final Integer loanCycle =JdbcSupport.getInteger(rs, "loanCycle");            
            final String barcodeNo =rs.getString("barcodeNo");
            final Date  loanStratDate =rs.getDate("loanStratDate");
            final Date repaymentStartDate =rs.getDate("repaymentStartDate");
            final String bcBranchCode =rs.getString("bcBranchCode");
            final String colector =rs.getString("colector");
            final String approver =rs.getString("approver");
            final Date ExceptedDisbursementDate =rs.getDate("ExceptedDisbursementDate");
            final Integer TopUpLoanFlag =JdbcSupport.getInteger(rs, "TopUpLoanFlag");
            final String hosiptalCash =rs.getString("hosiptalCash");
            final String prepaidCharge =rs.getString("prepaidCharge");
            
            final String nomineeName =rs.getString("nomineeName");
            final String nomineeAddressline1=rs.getString("nomineeAddressline1");
            final String nomineeAddressline2 =rs.getString("nomineeAddressline2");
            final String nomineeAddressline3 =rs.getString("nomineeAddressline3");
            final String nomineeRlation =rs.getString("nomineeRlation");
            final Date nomineeDateOfBirth =rs.getDate("nomineeDateOfBirth") ;
            final String nomineeAge=rs.getString("nomineeAge");
            final String nomineeGender =rs.getString("nomineeGender");
            final String nomineestate =rs.getString("nomineestate");
            final String nomineecity =rs.getString("nomineecity");
            final String nomineeMinor =rs.getString("nomineeMinor");
            final String gurdianTitle =rs.getString("gurdianTitle");
            final String gurdianName =rs.getString("gurdianName");
            final Date gurdianDateofBirth =rs.getDate("gurdianDateofBirth");
            final String gurdianGender =rs.getString("gurdianGender");
            final String gurdianAddressline1=rs.getString("gurdianAddressline1");
            //final String gurdianAddressline2 =rs.getString("gurdianAddressline2");
           // final String gurdianAddressline3 =rs.getString("gurdianAddressline3");
          //  final String gurdianState =rs.getString("gurdianState");
          //  final String gurdianCity =rs.getString("gurdianCity");
            final String gurdianRelation =rs.getString("gurdianRelation");
            final String maritalStaus =rs.getString("MaritalSta");
            final Integer SpouseAge=JdbcSupport.getInteger(rs, "SpouseAge") ;//,Round(datediff(curdate(),mrbl.spouse_DatofBirth)/365) as SpouseAge

            
            return new RblLoanValidationData(externalId,customerExternalId,centerExtrenalId,groupExternalId,loanProductCode,loanPurpose,
            		pslcode,loanAmount,disbursementMode,noofInstallments,repaymentfrequency,loanCycle,barcodeNo,loanStratDate,
            		repaymentStartDate,bcBranchCode,colector,approver,ExceptedDisbursementDate,TopUpLoanFlag,hosiptalCash,prepaidCharge,
            		nomineeName,nomineeAddressline1,nomineeAddressline2,nomineeAddressline3,nomineeRlation,nomineeDateOfBirth,nomineeAge
            		,nomineeGender,nomineestate,nomineecity,nomineeMinor,gurdianTitle,gurdianName,gurdianDateofBirth,gurdianGender,gurdianAddressline1,
            		gurdianRelation,maritalStaus,SpouseAge);
        }

    }


    @Override
    public List<RblSavingValidationData> readRblSavingData(final String clientId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();            
            final RblSavingDatMapper rm = new RblSavingDatMapper();
            String Sql ="select"+rm.schema() + " where mc.id in ("+clientId+") and (msa.status_enum=100 or msa.status_enum=200) ";
            List<RblSavingValidationData>RblSavingValidationDatas=new ArrayList<RblSavingValidationData>();
            RblSavingValidationDatas = this.jdbcTemplate.query(Sql, rm, new Object[] {});
            return RblSavingValidationDatas;
        } catch (final EmptyResultDataAccessException e) {
            throw new GenerateFileException(clientId,"savings");
        }

    }

    private static final class RblSavingDatMapper implements RowMapper<RblSavingValidationData> {

        public RblSavingDatMapper() {}

         public String schema() {
            return   "   msa.external_id as externalId,center.external_id as externalCenterId,mc.external_id as customerExternalId,"
                     + "  '1' as accountType,msp.name as savingproductCode,msp.name as productName,msa.submittedon_date as accountOpeningDate,"
                      +"title.code_score as nomineeTitle,nc.first_name as nomineeName,relation.code_score as nomineeRlation,nc.date_of_birth as nomineeDateOfBirth,"
                     +"gender.code_score as nomineeGender, concat(ifnull(na.house_no,''),ifnull(na.street_no,' ')) as nomineeAddressline1,"
                     +"concat(ifnull(na.house_no,''),ifnull(na.street_no,' ')) as gurdianAddressline1,concat(ifnull(na.area_locality,''),ifnull(na.landmark,' ')) as nomineeAddressline2,"
                     +"concat(ifnull(na.area_locality,''),ifnull(na.landmark,' ')) as gurdianAddressline2,ifnull(na.taluka,'') as nomineeAddressline3,"
                     +"ifnull(na.taluka,'') as gurdianAddressline3,rblbranch.`city code` as nomineecity,rblbranch.`city code` as gurdianCity,"
                     +"nomineestate.code_score as nomineestate,nomineestate.code_score as gurdianState,'02' as nomineeMinor,"
                     +"gurdianTitle.code_score as gurdianTitle,mrbl.gurdian_name as gurdianName,mrbl.gurdian_DatofBirth as gurdianDateofBirth,"
                     +"gurdiangender.code_score as gurdianGender,gurdianrelation.code_score as gurdianRelation,mrbl.gurdian_mobileNo as gurdianPhoneNo,"
                     +"na.pin_code as nomineePincode,na.pin_code as gurdianPincode,rblbranch.collector as collector,rblbranch.approver as approver "
                     +" from m_client mc "
                     +"left join m_group_client mgc on mgc.client_id =mc.id "
                     +"left join m_group mg on mgc.group_id =mg.id "
                     +"left join m_group center on center.id =mg.parent_id and center.level_id =1 "
                     +"left join m_savings_account msa on msa.client_id =mc.id "
                     +"left join m_savings_product msp on msp.id =msa.product_id "
                     +"left join n_address na on na.client_id =mc.id and na.address_type_cv_id= 26 "
                     +"left join n_coapplicant nc on nc.client_id =mc.id "
                     +"left join m_rblcustomer mrbl on mrbl.client_id =mc.id "
                     +"left join m_code_value title on title.id =nc.salutation_cv_id "
                     +"left join m_code_value relation on relation.id =nc.sp_relationship_cv_id "
                     +"left join m_code_value gender on gender.id =nc.gender_cv_id "
                     +"left join m_code_value nomineecity on nomineecity.id =na.district_cv_id "
                     +"left join m_code_value nomineestate on nomineestate.id =na.state_cv_id "
                     +"left join m_code_value gurdianTitle on gurdianTitle.id =mrbl.title "
                     +"left join m_code_value gurdiangender on gurdiangender.id =mrbl.gurdian_gender "
                     +"left join m_code_value gurdianrelation on gurdianrelation.id =mrbl.relation_cv_id "
                    +"left join `RBL Branch Name` rblbranch on rblbranch.office_id =mc.office_id ";
        }        
      	
        @Override
        public RblSavingValidationData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
                  	
        	final String externalId = rs.getString("externalId");
        	final String customerExternalId =rs.getString("customerExternalId");
            final String centerExtrenalId=rs.getString("externalCenterId");
            final String accountType =rs.getString("accountType");
             String savingproductCode =rs.getString("savingproductCode");
             if(savingproductCode!=null){
             String [] productCodeArray =savingproductCode.split("-");
             savingproductCode=productCodeArray[1];
             }
            final String productName =rs.getString("productName");
            final Date accountOpeningDate=rs.getDate("accountOpeningDate");
            final String nomineeTitle =rs.getString("nomineeTitle");
            final String nomineeName =rs.getString("nomineeName");
            final String nomineeRlation =rs.getString("nomineeRlation");
            final Date nomineeDateOfBirth =rs.getDate("nomineeDateOfBirth") ;
            final String nomineeGender =rs.getString("nomineeGender");
            final String nomineeAddressline1=rs.getString("nomineeAddressline1");
            final String nomineeAddressline2 =rs.getString("nomineeAddressline2");
            final String nomineeAddressline3 =rs.getString("nomineeAddressline3");
            final String nomineecity =rs.getString("nomineecity");
            final String nomineestate =rs.getString("nomineestate");
            final String nomineeMinor =rs.getString("nomineeMinor");
            final String gurdianTitle =rs.getString("gurdianTitle");
            final String gurdianName =rs.getString("gurdianName");
            final Date gurdianDateofBirth =rs.getDate("gurdianDateofBirth");
            final String gurdianGender =rs.getString("gurdianGender");
            final String gurdianAddressline1=rs.getString("gurdianAddressline1");
            final String gurdianAddressline2 =rs.getString("gurdianAddressline2");
            final String gurdianAddressline3 =rs.getString("gurdianAddressline3");
            final String gurdianState =rs.getString("gurdianState");
            final String gurdianCity =rs.getString("gurdianCity");
            final String gurdianRelation =rs.getString("gurdianRelation");
            final String gurdianPhoneNo =rs.getString("gurdianPhoneNo");
            final String gurdianPincode =rs.getString("gurdianPincode");
            final String collector =rs.getString("collector");
            final String approver =rs.getString("approver");
        	final String nomineePincode = rs.getString("nomineePincode");

                       
            
            return new RblSavingValidationData(externalId,customerExternalId,centerExtrenalId,accountType,savingproductCode,productName,
            		accountOpeningDate,nomineeTitle,nomineeName,nomineeRlation,nomineeDateOfBirth,nomineeGender,nomineeAddressline1,nomineeAddressline2,
            		nomineeAddressline3,nomineecity,nomineestate,nomineeMinor,gurdianTitle,gurdianName,gurdianDateofBirth,gurdianGender,
            		gurdianAddressline1,gurdianAddressline2,gurdianAddressline3,gurdianState,gurdianCity,gurdianRelation,
            		gurdianPhoneNo,gurdianPincode,collector,approver,nomineePincode);
        }

    }



}

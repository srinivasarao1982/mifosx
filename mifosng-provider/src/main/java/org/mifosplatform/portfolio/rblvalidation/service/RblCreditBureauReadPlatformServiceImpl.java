package org.mifosplatform.portfolio.rblvalidation.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanNotFoundException;
import org.mifosplatform.portfolio.rblvalidation.data.RblAddressData;
import org.mifosplatform.portfolio.rblvalidation.data.RblClientsData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCrdeitResponseData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditBureauData;
import org.mifosplatform.portfolio.rblvalidation.data.RblEquifaxData;
import org.mifosplatform.portfolio.rblvalidation.data.RblGurdianData;
import org.mifosplatform.portfolio.rblvalidation.data.RblHeaderData;
import org.mifosplatform.portfolio.rblvalidation.data.RblNomineeData;
import org.mifosplatform.portfolio.rblvalidation.data.RblOperatingRegion;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class RblCreditBureauReadPlatformServiceImpl  implements RblCreditBurequReadPlatfoemServie {
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;

	@Autowired
	public RblCreditBureauReadPlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}
  
	@Override
    public List<RblCreditBureauData> getbreauRequstData(final Long clientId,final Long centerId,String fromDate,String toDate) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final RblCreditBureauDataMapper rm = new RblCreditBureauDataMapper();
            String Sql ="select"+rm.schema() +"where mcr.center_id=?";
            if(clientId!=null){
            	 Sql =Sql+ " and mcr.client_id=?";
            }
            if(fromDate!=null && toDate!=null){
            	Sql =Sql+ " and mcr.createdDate between ? and ?";
            }
            List<RblCreditBureauData>rblCreditBureauData=new ArrayList<RblCreditBureauData>();
            rblCreditBureauData = this.jdbcTemplate.query(Sql, rm, new Object[] { centerId,clientId,fromDate,toDate});
            return rblCreditBureauData;
            //return rblCreditBureauData.get(0);
        } catch (final EmptyResultDataAccessException e) {
            throw new PartialLoanNotFoundException(clientId);
        }

    }
	
 private static final class RblCreditBureauDataMapper implements RowMapper<RblCreditBureauData> {
        public RblCreditBureauDataMapper() {}

        public String schema() {
            return " select mcr.center_id as centerId,mcr.client_id as clientId,mcr.barcode_no as barcdode,mcr.external_id as externalId,"
                   +"mcr.is_Renewal_Loan as renewalfl,mcr.loanAmount as loanAmount,mcr.customer_Name as customerName,mcr.title as title,mcr.name as name,"
                   +"mcr.relation as rlation,mcr.line1 as line1,mcr.line2 as line2,mcr.line3 as line3,mcr.city_code as cityCode,"
                   +"mcr.city_Name as cityName,mcr.state_code as stateCode,mcr.pin as pincode,mcr.operating_RegionCode as operatingRegion,"
                   +"mcr.operating_RegionName as operatingRegionName,mcr.dateOfBirth as dateOfBirth,mcr.branch_code as branchCode,"
                   +"mcr.branch_name as branchName"
                   +"from m_creditbureau_request mcr";

        }
        @Override
        public RblCreditBureauData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

                    
            final String barcodeNo =rs.getString("barcdode");
        	final   String externalId =rs.getString("externalId");
        	final   Long loanAmount=JdbcSupport.getLong(rs, "loanAmount");
        	final   String isRenewalLoan=rs.getString("renewalfl");
        	final   String customerName=rs.getString("customerName");
        	final   String title=rs.getString("title");
        	final   String name=rs.getString("name");
        	final   String relation=rs.getString("rlation");
        	final   String line1=rs.getString("line1");
        	final   String line2=rs.getString("line2");
        	final   String line3=rs.getString("line3");
        	final   String cityCode=rs.getString("cityCode");
        	final   String cityName=rs.getString("cityName");
        	final   String stateCode=rs.getString("stateCode");
        	final   String pin=rs.getString("pincode");
        	final   String operatingRegionCode=rs.getString("operatingRegion");
        	final   String operatingRegionName=rs.getString("operatingRegionName");
        	final   DateTime dateOfBirth=new DateTime(rs.getDate("dateOfBirth"));
        	final   String branchCode=rs.getString("branchCode");
        	final   String branchName=rs.getString("branchName");

            return new RblCreditBureauData(barcodeNo,externalId,loanAmount,isRenewalLoan,customerName,title,name,relation,line1,
            		line2,line3,cityCode,cityName,stateCode,pin,operatingRegionCode,operatingRegionName,dateOfBirth,branchCode,branchName);
        }
            		

    }
 
 
 public List<RblCreditBureauData> getbreauResponseData(final Long clientId,final Long centerId,String fromDate,String toDate) {
     try {
         final AppUser currentUser = this.context.authenticatedUser();
         
         final RblCreditBureauResponseDataMapper rm = new RblCreditBureauResponseDataMapper();
         String Sql ="select"+rm.schema() +"where mcr.center_id=?";
         if(clientId!=null){
         	 Sql =Sql+ " and mcr.client_id=?";
         }
         if(fromDate!=null && toDate!=null){
         	Sql =Sql+ " and mcr.createdDate between ? and ?";
         }
         List<RblCreditBureauData>rblCreditBureauData=new ArrayList<RblCreditBureauData>();
         rblCreditBureauData = this.jdbcTemplate.query(Sql, rm, new Object[] { centerId,clientId,fromDate,toDate});
         //return rblCreditBureauData.get(0);
         return rblCreditBureauData;
     } catch (final EmptyResultDataAccessException e) {
         throw new PartialLoanNotFoundException(clientId);
     }

 }
	
private static final class RblCreditBureauResponseDataMapper implements RowMapper<RblCreditBureauData> {
     public RblCreditBureauResponseDataMapper() {}

     public String schema() {
         return " select mcr.center_id as centerId,mcr.client_id as clientId,mcr.barcode_no as barcdode,mcr.external_id as externalId,"
                +"mcr.is_Renewal_Loan as renewalfl,mcr.loanAmount as loanAmount,mcr.customer_Name as customerName,mcr.title as title,mcr.name as name,"
                +"mcr.relation as rlation,mcr.line1 as line1,mcr.line2 as line2,mcr.line3 as line3,mcr.city_code as cityCode,"
                +"mcr.city_Name as cityName,mcr.state_code as stateCode,mcr.pin as pincode,mcr.operating_RegionCode as operatingRegion,"
                +"mcr.operating_RegionName as operatingRegionName,mcr.dateOfBirth as dateOfBirth,mcr.branch_code as branchCode,"
                +"mcr.branch_name as branchName"
                +"from m_creditbureau_response mcr";

     }
     @Override
     public RblCreditBureauData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

                 
         final String barcodeNo =rs.getString("barcdode");
     	final   String externalId =rs.getString("externalId");
     	final   Long loanAmount=JdbcSupport.getLong(rs, "loanAmount");
     	final   String isRenewalLoan=rs.getString("renewalfl");
     	final   String customerName=rs.getString("customerName");
     	final   String title=rs.getString("title");
     	final   String name=rs.getString("name");
     	final   String relation=rs.getString("rlation");
     	final   String line1=rs.getString("line1");
     	final   String line2=rs.getString("line2");
     	final   String line3=rs.getString("line3");
     	final   String cityCode=rs.getString("cityCode");
     	final   String cityName=rs.getString("cityName");
     	final   String stateCode=rs.getString("stateCode");
     	final   String pin=rs.getString("pincode");
     	final   String operatingRegionCode=rs.getString("operatingRegion");
     	final   String operatingRegionName=rs.getString("operatingRegionName");
     	final   DateTime dateOfBirth=new DateTime(rs.getDate("dateOfBirth"));
     	final   String branchCode=rs.getString("branchCode");
     	final   String branchName=rs.getString("branchName");

         return new RblCreditBureauData(barcodeNo,externalId,loanAmount,isRenewalLoan,customerName,title,name,relation,line1,
         		line2,line3,cityCode,cityName,stateCode,pin,operatingRegionCode,operatingRegionName,dateOfBirth,branchCode,branchName);
     }
         		

 }

public List<RblCrdeitResponseData> getbreauErrorData(final Long clientId,final Long centerId,String fromDate,String toDate) {
    try {
        final AppUser currentUser = this.context.authenticatedUser();
        
        final RblCrdeitErrorDataMapper rm = new RblCrdeitErrorDataMapper();
        String Sql ="select"+rm.schema() +"where mcr.center_id=?";
        if(clientId!=null){
        	 Sql =Sql+ " and mcr.client_id=?";
        }
        if(fromDate!=null && toDate!=null){
        	Sql =Sql+ " and mcr.createdDate between ? and ?";
        }
        List<RblCrdeitResponseData>rblCrdeitResponseData=new ArrayList<RblCrdeitResponseData>();
        rblCrdeitResponseData = this.jdbcTemplate.query(Sql, rm, new Object[] { centerId,clientId,fromDate,toDate});
       // return rblCrdeitResponseData.get(0);
        return rblCrdeitResponseData;
    } catch (final EmptyResultDataAccessException e) {
        throw new PartialLoanNotFoundException(clientId);
    }

}
	
private static final class RblCrdeitErrorDataMapper implements RowMapper<RblCrdeitResponseData> {
    public RblCrdeitErrorDataMapper() {}

    public String schema() {
        return " select mce.client_id as clientId,mce.request_uid as requestId,mce.service_name as serviceName,mce.channel_id as channelId,"
               +"mce.cor_Id as corId,mce.credit_Approved as creditApproved,mce.reason as region,mce.eligible_Amount as eligibalAmount "
               +"from m_creditbureau_response mce";


    }
    @Override
    public RblCrdeitResponseData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

    	final Long clientId=JdbcSupport.getLong(rs, "clientId");
        
    	final   String requestId =rs.getString("requestId");
    	final   String serviceName=rs.getString("serviceName");
    	final   String channelId=rs.getString("channelId");
    	final   String corId=rs.getString("corId");
    	final   String creditApproved=rs.getString("creditApproved");
    	final   String region=rs.getString("region");
    	final Long eligibalAmount =JdbcSupport.getLong(rs, "eligibalAmount");    	
        return new RblCrdeitResponseData(clientId,requestId,serviceName,channelId,corId,creditApproved,region,
        		eligibalAmount,null);
    }
        		

}

		
   public RblClientsData generateCustomerData(final Long clientId) {
	        try {
	            final AppUser currentUser = this.context.authenticatedUser();
	            
	            final RblClientsDataDataMapper rm = new RblClientsDataDataMapper();
	            String Sql ="select"+rm.schema();
	            List<RblClientsData>rblCreditBureauData=new ArrayList<RblClientsData>();
	            rblCreditBureauData = this.jdbcTemplate.query(Sql, rm, new Object[] { clientId});
	            return rblCreditBureauData.get(0);
	        } catch (final EmptyResultDataAccessException e) {
	            throw new PartialLoanNotFoundException(clientId);
	        }

	    }
		//Partial Loan Name we Have To change
		//Mrbl Table we have to change
		    private static final class RblClientsDataDataMapper implements RowMapper<RblClientsData> {

	        public RblClientsDataDataMapper() {}

	        public String schema() {
	            return "select mc.external_id as externalId,nct.external_Id2 as barccodeNo,mp.loan_amount as partialLoanAmount,mc.display_name as customerName,"
	                    +"mp.loan_amount as loanAmount,if(mrbl.renewal_fl=0,'Y','N') as renewalFl,title.code_score as title,nct.pan_no as pan,mc.mobile_no as mobileNo,"
	                    +"mc.date_of_birth as dateofBirth, if(gender.code_value like 'Mal%',01,02) gender,rbl.`Branch Code` as branchCode,rblbranchName.code_value as branchName ,"
	                    +"rbl.operatingregion_code as operatingRegion,rbl.operatingRegion_Name as OperatingRegionName,"
		                +"(select document_key from m_client_identifier where client_id=? and document_type_id=40)  adhar,"
		                +"(select document_key from m_client_identifier where client_id=? 84675 and document_type_id=41) voterId,"
		                +"(select document_key from m_client_identifier where client_id=?84675 and document_type_id=43)   ration " 
	                    +"from m_client mc "
	                    +"left join m_partial_loan1 mp on mc.id=mp.client_id"
	                    +"left join n_client_ext nct on mc.id=nct.client_id"
	                    +"left join m_rblcustomer mrbl  on mc.id=mrbl.client_id"
	                    +"left join m_client_identifier mci on mci.client_id=mc.id"
	                    +"left join m_code_value gender on mc.gender_cv_id=gender.id"
	                    +"left join m_code_value title on nc.salutation_cv_id=title.id"
	                    +"left join `RBL Branch Name` rbl on  rbl.office_id=mc.id"
	                    +"left join m_code_value rblbranchName on rblbranchName.id=rbl.`RBL_Branch_cd_RBL Branch`"
	                    +"where mc.id=? ";
	        }
	        @Override
	        public RblClientsData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

	                    
	            final String barcodeNo =rs.getString("barccodeNo");
	        	final   String externalId =rs.getString("externalId");
	        	final   Long loanAmount=JdbcSupport.getLong(rs, "partialLoanAmount");
	        	final   String isRenewalLoan=rs.getString("renewalFl");
	        	final   String customerName=rs.getString("customerName");
	        	final   Long title=JdbcSupport.getLong(rs, "title");	        	
	            final  String pan=rs.getString("pan");
	            final String mobileNo=rs.getString("mobileNo");
	            final Date  dateOfBirth=rs.getDate("dateofBirth");
	            final String branchCode=rs.getString("branchCode");
	            final String branchName=rs.getString("branchName");
	            final String rationCardNo=rs.getString("ration");
	            final String voterId=rs.getString("voterId");   
	            final String aadharNo=rs.getString("adhar");
	            final Long gender=JdbcSupport.getLong(rs, "gender");
	            final String operatingRegion=rs.getString("operatingRegion");
	            final String operatingRegionName=rs.getString("OperatingRegionName");	         
	            RblOperatingRegion RblOperatingRegion =new RblOperatingRegion(operatingRegion,operatingRegionName);
	            return new RblClientsData(barcodeNo,externalId,loanAmount,isRenewalLoan,customerName,pan,mobileNo,dateOfBirth,
	            		branchCode,branchName,rationCardNo,voterId,aadharNo,gender,null,null,RblOperatingRegion);
	            
	           
	        }

	    }
   

		    public RblAddressData getAddressData(final Long clientId,boolean iscoClient) {
		        try {
		            final AppUser currentUser = this.context.authenticatedUser();
		            
		            final RblAddressDataMapper rm = new RblAddressDataMapper();
		            String Sql ="select"+rm.schema();
		             if(iscoClient){
		            	 Sql=Sql   +"and na.address_type_cv_id=20 and client_id=?"; 
		             }else{
		            	 Sql=Sql   +"and na.address_type_cv_id=26 and client_id=?";  
		             }

		            List<RblAddressData>rblAddressData=new ArrayList<RblAddressData>();
		            rblAddressData = this.jdbcTemplate.query(Sql, rm, new Object[] { clientId});
		            return rblAddressData.get(0);
		        } catch (final EmptyResultDataAccessException e) {
		            throw new PartialLoanNotFoundException(clientId);
		        }

		    }
	  private static final class RblAddressDataMapper implements RowMapper<RblAddressData> {

		        public RblAddressDataMapper() {}

		        public String schema() {
		            return " select CONCAT(na.house_no,na.street_no) as line1,(na.area_locality,na.taluka) as line2,na.village_town as line3, "
	                      +" mcv.code_description as cityCode,mcv.code_value as cityName,state.code_description as stateCode,"
	                      +"state.code_value as stateName,na.pin_code as pincode"
	                      +"from m_client mc "
	                      +"left join n_address na on na.client_id=mc.id "
	                      +"left join m_code_value mcv on mcv.id=na.district_cv_id"
	                      +"left join m_code_value state on state.id=na.state_cv_id";
		        }
		        @Override
		        public RblAddressData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
     
		            final String line1 =rs.getString("line1");
		        	final String line2 =rs.getString("line2");
		        	final String line3=rs.getString("line3");
		        	final String cityCode=rs.getString("cityCode");
		        	final String cityName=rs.getString("cityName");
		        	final String stateCode=rs.getString("stateCode");
		        	final String stateName=rs.getString("stateName");
		        	final String pincode=rs.getString("pincode");		        	
		        	
		            return new RblAddressData(line1,line2,line3,cityCode,cityName,stateCode,stateName,pincode);
		        }

		    }


	    public RblNomineeData getNomineeData(final Long clientId) {
	        try {
	            final AppUser currentUser = this.context.authenticatedUser();
	            
	            final RblNomineeDataMapper rm = new RblNomineeDataMapper();
	            String Sql ="select"+rm.schema();	             

	            List<RblNomineeData>rblNomineeDatas=new ArrayList<RblNomineeData>();
	            RblAddressData rblAddressData =getAddressData( clientId,true);
	            rblNomineeDatas = this.jdbcTemplate.query(Sql, rm, new Object[] { clientId});
	            RblNomineeData rblNomineeData =rblNomineeDatas.get(0);
	            RblGurdianData rblGurdianData =rblNomineeData.getGuardian();
	            rblGurdianData.setAddress(rblAddressData);
	            rblNomineeData.setAddress(rblAddressData);	            
	            return rblNomineeData;
	        } catch (final EmptyResultDataAccessException e) {
	            throw new PartialLoanNotFoundException(clientId);
	        }

	    }
          private static final class RblNomineeDataMapper implements RowMapper<RblNomineeData> {

	        public RblNomineeDataMapper() {}

	        public String schema() {
	            return "select mcv.code_score as title, concat(nc.first_name,ifnull(nc.middle_name,''),ifnull(nc.last_name,'')) as nomineeName,"
	                   +"relation.code_score as relation,nc.date_of_birth as dateofBirth,gender.code_score as gender,null as pan,"
	                   +"null as minor,null as nomineeId,null as nomineeAddressID ,gurdianTitle.code_value as  gurdiantitle,"
	                   +"gurdainrelation.code_value as gurdianrelation,gurdianGender.code_value as gurdiangender,mrbl.gurdian_name as gurdianName,"
	                   + "mrbl.gurdian_DatofBirth as gurdianDob"
	                   +"from m_client mc "
	                   +"left join n_coapplicant nc on mc.id=nc.client_id"
	                   +"left join m_code_value mcv on mcv.id=nc.salutation_cv_id"
	                   +"left join m_code_value relation on relation.id =nc.sp_relationship_cv_id"
	                   +"left join m_code_value gender on gender.id=nc.gender_cv_id "
	                   +"left join m_rblcustomer mrbl on mrbl.client_id=mc.id"
	                   +"left join m_code_value gurdianTitle on gurdianTitle.id=mrbl.title "
	                   +"left join m_code_value gurdainrelation on gurdainrelation.id=mrbl.relation_cv_id"
	                   + "left join m_code_value gurdianGender on gurdianGender.id=mrbl.gurdian_gender"
	                   +"where mc.id=?";
	        }
	        @Override
	        public RblNomineeData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

	        	final Long Nomineetitle =JdbcSupport.getLong(rs, "title");
	        	final  String name=rs.getString("nomineeName");
	            final  String relation=rs.getString("relation");
	            final  Date dateOfBirth=rs.getDate("dateofBirth");
	            final  Long gender=JdbcSupport.getLong(rs, "gender");
	            final  String pan=rs.getString("pan");
	            final  String minor=rs.getString("minor");
	            final  String nomineeID=rs.getString("nomineeId");
	            final  String nomineeAddressID=rs.getString("nomineeAddressID");
	            final String title=rs.getString("gurdiantitle");
	            final String gurdianname=rs.getString("gurdianName");
	            final String gurdainrelation=rs.getString("gurdianrelation");
	            final String gurdiandob =rs.getString("gurdianDob");
	            final String gurdiangender=rs.getString("gurdiangender");
	             RblAddressData address=null;
	            RblGurdianData rblGurdianData =RblGurdianData.create(title, gurdianname, gurdainrelation, gurdiandob, gurdiangender, null);
	            		        	
	        	return new RblNomineeData(Nomineetitle,name,relation,dateOfBirth,gender,pan,minor,nomineeID,nomineeAddressID,address,rblGurdianData);
		    }
	  


}

		@Override
		public RblEquifaxData generateDataforCreditBureau(Long clientId) {
			 try {
		            final AppUser currentUser = this.context.authenticatedUser();
		            RblHeaderData rblHeaderData =new RblHeaderData();
		          
		            rblHeaderData.setRequestUUID("Req_LodgeColl_"+new DateTime().toString("hh:mm:ss"));
		            rblHeaderData.setChannelId("LOS");
		            rblHeaderData.setServiceName("Equifax");
		            rblHeaderData.setMessageDateTime(new DateTime().toString());
		            rblHeaderData.setCorpId("Los1234");
		            RblAddressData rblAddressData =getAddressData(clientId,false);
		            RblNomineeData rblNomineeData =getNomineeData(clientId);
		            RblClientsData rblClientsData =generateCustomerData(clientId);
		            rblClientsData.setAddress(rblAddressData);
		            rblClientsData.setNominee(rblNomineeData);
		            RblEquifaxData rblEquifaxData =new RblEquifaxData(rblHeaderData,rblClientsData);
		            return rblEquifaxData;		            
		           } catch (final EmptyResultDataAccessException e) {
		            throw new PartialLoanNotFoundException(clientId);
		        }

		    }
		}
			
		

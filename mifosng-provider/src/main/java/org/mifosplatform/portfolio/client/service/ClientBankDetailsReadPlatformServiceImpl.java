package org.mifosplatform.portfolio.client.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.api.ClientsBankDetailsApiConstants;
import org.mifosplatform.portfolio.client.data.ClientBankDetailsData;
import org.mifosplatform.portfolio.client.data.ClientIdentifierData;
import org.mifosplatform.portfolio.client.domain.ClientStatus;
import org.mifosplatform.portfolio.client.exception.ClientIdentifierNotFoundException;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class ClientBankDetailsReadPlatformServiceImpl implements ClientBankDetailsReadPlatformService {
	
	 private final JdbcTemplate jdbcTemplate;
	    private final PlatformSecurityContext context;
	    private final CodeValueReadPlatformService codeValueReadPlatformService;

	    @Autowired
	    public ClientBankDetailsReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource,
	    		final CodeValueReadPlatformService codeValueReadPlatformService) {
	        this.context = context;
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	        this.codeValueReadPlatformService = codeValueReadPlatformService;
	    }

	    
	    @Override
	    public ClientBankDetailsData retrieveClientBankDetails(final Long bankdetailId) {
	    	ClientBankDetailsData clientBankDetailsData = null;
	        try {
	            this.context.authenticatedUser();
	            final ClientsBankDetailsMapper rm = new ClientsBankDetailsMapper();
	           	String sql = "select " + rm.schema();
   	            sql += " where mb.id=? ";
	            clientBankDetailsData = this.jdbcTemplate.queryForObject(sql, rm, new Object[] { bankdetailId });
	        } catch (final EmptyResultDataAccessException e) {
	           // throw new ClientIdentifierNotFoundException(clientId);
	        }
           return clientBankDetailsData;
	    }
	    
	    private static final class ClientsBankDetailsMapper implements RowMapper<ClientBankDetailsData> {

	        public ClientsBankDetailsMapper() {}

	        public String schema() {
	            return "mb.id as id,mb.client_id as clientId,mb.beneficiary_name as benificaryName,"
	                    + " mb.account_no as accountNumber,mb.lasttransaction_amount as lastTransactionAmount, "
	            		+ " mb.bank_name as bankName,mb.micr_code as micrCode, " 
	                    + " mb.ifsc_code as ifscCode,mb.branch_name as branchName ,mb.branch_address as bankAddress,"
	                    + " mb.lasttransaction_date as lastTransactionDate,usr.username as createdBy,"
	                    + " usr1.username as lastModifyBy,mb.created_date as createddate,mb.lastmodified_date as modifiedDate,  "
	                    + " mb.is_primary_account as isPrimacyAccount, "
	                    + " mcv.id as accountTypeId, mcv.code_value as accountTypeName "
	                    + " from m_bankdetails mb join m_appuser usr on mb.createdby_id=usr.id "
	                    + " join m_appuser usr1 on mb.lastmodifiedby_id=usr1.id "
	                    + " left join m_code_value mcv on mcv.id = mb.account_type_cv_id ";
	        }

	        @Override
	        public ClientBankDetailsData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

	            final Long id = JdbcSupport.getLong(rs, "id");
	            final Long clientId = JdbcSupport.getLong(rs, "clientId");
	            final String bankName =rs.getString("branchName");
	            final String bankAddress=rs.getString("bankAddress");
	            final String ifscCode=rs.getString("ifscCode");
	            final String bankName1=rs.getString("bankName");
	            final String micrCode=rs.getString("micrCode");
	            final String benificaryName=rs.getString("benificaryName");
	            final String accountNumber=rs.getString("accountNumber");
	            final String createdby=rs.getString("createdBy");
	            final String lastmodifyBy=rs.getString("lastModifyBy");
	            final LocalDate createdDate = JdbcSupport.getLocalDate(rs, "createddate");
	            final LocalDate modifiedDate = JdbcSupport.getLocalDate(rs, "modifiedDate");
	            final LocalDate lastTransactionDate = JdbcSupport.getLocalDate(rs, "lastTransactionDate");
	            final boolean isPrimaryAccount = rs.getBoolean("isPrimacyAccount");
	            final Long accountTypeCodeValueId = JdbcSupport.getLong(rs, "accountTypeId");
	            final String accountTypeCodeValueName = rs.getString("accountTypeName");
	            final CodeValueData accountType = CodeValueData.instance(accountTypeCodeValueId, accountTypeCodeValueName);

	            final BigDecimal lastTransactionAmount =JdbcSupport.getBigDecimalDefaultToZeroIfNull(rs, "lastTransactionAmount");
	            return ClientBankDetailsData.bankdetailsData(id, clientId, benificaryName, ifscCode, bankName, bankAddress, lastTransactionDate, lastTransactionAmount, accountNumber, bankName1,micrCode,createdby, createdDate, 
	            		lastmodifyBy, modifiedDate, isPrimaryAccount,null,accountType);
	      
	        }

	    }

		@Override
		public List<ClientBankDetailsData> retriveAllBankDetailsByClientId(Long clientId) {
			 this.context.authenticatedUser();
			 final ClientsBankDetailsMapper rm = new ClientsBankDetailsMapper();
			 List<ClientBankDetailsData> listOfBankDetails;
			try{
	             String sql = "select " + rm.schema() + " where mb.client_id=? ";
	             listOfBankDetails = this.jdbcTemplate.query(sql, rm,
	                     new Object[] {clientId});
			}catch(final EmptyResultDataAccessException e){
				throw new ClientIdentifierNotFoundException(clientId);
			}
			return listOfBankDetails;
		}


		@Override
		public ClientBankDetailsData retriveClientBankDetailsTemplate(String accountType) {
			final List<CodeValueData> accountTypes = new ArrayList<>(this.codeValueReadPlatformService.retrieveCodeValuesByCode(accountType));
		    return ClientBankDetailsData.templateData(accountTypes);
			
		}
          

}

package org.mifosplatform.portfolio.client.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.data.ClientBankDetailsData;
import org.mifosplatform.portfolio.client.data.ClientIdentifierData;
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

	    @Autowired
	    public ClientBankDetailsReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
	        this.context = context;
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }

	    
	    @Override
	    public ClientBankDetailsData retrieveClientBankDetails(final Long clientId, final Long bankdetailsId) {
	        try {
	            final AppUser currentUser = this.context.authenticatedUser();
	            
	            final ClientsBankDetailsMapper rm = new ClientsBankDetailsMapper();
	             ClientBankDetailsData clientBankDetailsData;

	            String sql = "select " + rm.schema();
               
	            if(clientId!=null){
	            sql += " where mb.client_id=? ";
	             clientBankDetailsData = this.jdbcTemplate.queryForObject(sql, rm, new Object[] { clientId });
	            }
	            else{
	            sql += " where mb.id=? ";

	             clientBankDetailsData = this.jdbcTemplate.queryForObject(sql, rm, new Object[] { bankdetailsId });
	            		
	            }

	            return clientBankDetailsData;
	        } catch (final EmptyResultDataAccessException e) {
	            throw new ClientIdentifierNotFoundException(clientId);
	        }

	    }
	    
	    private static final class ClientsBankDetailsMapper implements RowMapper<ClientBankDetailsData> {

	        public ClientsBankDetailsMapper() {}

	        public String schema() {
	            return "mb.id as id,mb.client_id as clientId,mb.beneficiary_name as benificaryName,"
	                    + " mb.account_no as accountNumber,mb.lasttransaction_amount as lastTransactionAmount, "
	                    + " mb.ifsc_code as ifscCode,mb.branch_name as bankName ,mb.branch_address as bankAddress,"
	                    + " mb.lasttransaction_date as lastTransactionDate,usr.username as createdBy,"
	                    + " usr1.username as lastModifyBy,mb.created_date as createddate,mb.lastmodified_date as modifiedDate  "
	                    + " from m_bankdetails mb join m_appuser usr on mb.createdby_id=usr.id "
	                    + " join m_appuser usr1 on mb.lastmodifiedby_id=usr1.id ";
	        }

	        @Override
	        public ClientBankDetailsData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

	            final Long id = JdbcSupport.getLong(rs, "id");
	            final Long clientId = JdbcSupport.getLong(rs, "clientId");
	            final String bankName =rs.getString("bankName");
	            final String bankAddress=rs.getString("bankAddress");
	            final String ifscCode=rs.getString("ifscCode");
	            final String benificaryName=rs.getString("benificaryName");
	            final String accountNumber=rs.getString("accountNumber");
	            final String createdby=rs.getString("createdBy");
	            final String lastmodifyBy=rs.getString("lastModifyBy");
	            final LocalDate createdDate = JdbcSupport.getLocalDate(rs, "createddate");
	            final LocalDate modifiedDate = JdbcSupport.getLocalDate(rs, "modifiedDate");
	            final LocalDate lastTransactionDate = JdbcSupport.getLocalDate(rs, "lastTransactionDate");

	            final BigDecimal lastTransactionAmount =JdbcSupport.getBigDecimalDefaultToZeroIfNull(rs, "lastTransactionAmount");
	            return ClientBankDetailsData.bankdetailsData(id, clientId, benificaryName, ifscCode, bankName, bankAddress, lastTransactionDate, lastTransactionAmount, accountNumber, createdby, createdDate, lastmodifyBy, modifiedDate);
	      
	        }

	    }
    

}

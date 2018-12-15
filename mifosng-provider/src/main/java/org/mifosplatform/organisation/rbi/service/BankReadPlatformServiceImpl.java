package org.mifosplatform.organisation.rbi.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.organisation.rbi.data.BankData;
import org.mifosplatform.organisation.rbi.exception.BankNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class BankReadPlatformServiceImpl implements BankReadPlatformService{
	private final PlatformSecurityContext context;
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
	public BankReadPlatformServiceImpl(PlatformSecurityContext context, 
			final RoutingDataSource dataSource) {
		super();
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<String> getListOfIfscCode() {
		this.context.authenticatedUser();
		final String query = "select distinct mb.ifsc from m_bank mb";
		return this.jdbcTemplate.queryForList(query, String.class);
	}

	@Override
	public BankData getBankDetailsByIfscCode(String ifscCode) {
		this.context.authenticatedUser();
		try{
			final BankMapper rm = new BankMapper();
			final String sql = " select " + rm.schema() + " where mb.ifsc = ? ";
			return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { ifscCode });
		}catch(final EmptyResultDataAccessException e){
			throw new BankNotFoundException(ifscCode);
		}
	}
	
	private static final class BankMapper implements RowMapper<BankData> {
        
		private final String schema;
		
		public BankMapper(){
			
			final StringBuilder sqlBuilder = new StringBuilder(200);
			sqlBuilder.append(" mb.id as id, mb.bank as bank, mb.ifsc as ifsc, ");
			sqlBuilder.append(" mb.micr_code as micrcode, mb.branch as branch, ");
			sqlBuilder.append(" mb.address as address, mb.contact as contact, ");
			sqlBuilder.append(" mb.city as city, mb.district as district, mb.state as state ");
			sqlBuilder.append(" from m_bank mb ");
			this.schema = sqlBuilder.toString();
		}
		 public String schema() {
	            return this.schema;
	     }
		 
		@Override
		public BankData mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			final Long id = rs.getLong("id");
            final String bank = rs.getString("bank");
            final String ifsc = rs.getString("ifsc");
            final String micrcode = rs.getString("micrcode");
            final String branch = rs.getString("branch");
            final String address = rs.getString("address");
            final String contact = rs.getString("contact");
            final String city = rs.getString("city");
            final String district = rs.getString("district");
            final String state = rs.getString("state");
            
            return new BankData(id,bank,ifsc,micrcode,branch,address,contact,city,district,state);
		}
	}
}

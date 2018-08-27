package org.mifosplatform.portfolio.rbl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.joda.time.DateTime;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanNotFoundException;
import org.mifosplatform.portfolio.rbl.data.RblLoanData;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class RblLoanReadPlatformServiceImpl implements  RblLoanReadPlatformService{
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;

	@Autowired
	public RblLoanReadPlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}
	
	Date dt =new Date();
	DateTime dt1  =new DateTime(dt);
	

	@Override
	public RblLoanData retriveRblLoan(Long loanId) {
		try{
	    final AppUser currentUser = this.context.authenticatedUser();
        
         RblLoanDataMapper rm = new RblLoanDataMapper();
        String Sql =rm.schema() + "where rbill.loan_id=?";
        RblLoanData rblLoanData =null;
        rblLoanData = this.jdbcTemplate.queryForObject(Sql, rm, new Object[] { loanId});
        return rblLoanData;
    } catch (final EmptyResultDataAccessException e) {
        throw new PartialLoanNotFoundException(loanId);
    }
	}
	
	private static final class RblLoanDataMapper implements RowMapper<RblLoanData> {

        public RblLoanDataMapper() {}

        public String schema() {
            return    
            		 "select rbll.loan_id as loanId,rbll.pslcode as plscode,rbll.to_Up_flag as touupFlag,rbllhosiptalcash as hospitalcash,"
            		 +"rbll.prepaid_charge as prepaidcharge from m_rblloan rbll";
            		 }

        @Override
        public RblLoanData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

;

            final Integer pslcode=JdbcSupport.getInteger(rs,"pslcode");;
        	final Integer touupFlag=JdbcSupport.getInteger(rs,"touupFlag");;
        	final String hosiptalcash=rs.getString("hospitalcash");
        	final String prepaidcharge=rs.getString("prepaidcharge");        	


        	RblLoanData rblLoanData=RblLoanData.create(pslcode, touupFlag, hosiptalcash, prepaidcharge);
        	return rblLoanData;
        }
	}



	
}
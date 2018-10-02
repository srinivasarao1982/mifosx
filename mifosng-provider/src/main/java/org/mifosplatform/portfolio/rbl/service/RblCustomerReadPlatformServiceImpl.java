package org.mifosplatform.portfolio.rbl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanNotFoundException;
import org.mifosplatform.portfolio.rbl.data.RblCustomerData;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class RblCustomerReadPlatformServiceImpl implements RblCustomerReadPlatformService {
	
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;

	@Autowired
	public RblCustomerReadPlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}

	
	@Override
    public RblCustomerData retriveRblCustomerr(final Long customerId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final RblCustomerDataMapper rm = new RblCustomerDataMapper();
            String Sql =rm.schema() + "  from m_rblcustomer mrblc  where mrblc.client_id=?";
            RblCustomerData rblCustomerData =null;
            rblCustomerData = this.jdbcTemplate.queryForObject(Sql, rm, new Object[] { customerId});
            return rblCustomerData;
        } catch (final EmptyResultDataAccessException e) {
            throw new PartialLoanNotFoundException(customerId);
        }

    }

    private static final class RblCustomerDataMapper implements RowMapper<RblCustomerData> {

        public RblCustomerDataMapper() {}

        public String schema() {
            return  "select    mrblc.client_id as clientId,mrblc.pension_card as pensioncard,mrblc.adharSeeding_constant as adharseeingconstant"
	                +",mrblc.health as health,mrblc.language as language,mrblc.card_issue_fl as cardissueFl,mrblc.cb_check as cbcheck"
	                +",mrblc.renewal_fl as renwalfl,mrblc.mother_tounge as montherTounge,mrblc.gurdian_name gurdianName,mrblc.gurdian_gender as gurdiangender,"
	                +" mrblc.gurdian_DatofBirth as gurdiandateofbirth,mrblc.spouse_name as spouseName,mrblc.spouse_DatofBirth as spouseDateOfbirth,"
	                +" mrblc.relation_cv_id as relation,mrblc.gurdian_mobileNo as gurdianMobileNo,mrblc.title as gurdianTitle ";
	              
        }

        @Override
        public RblCustomerData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

;

            final Long clientId = JdbcSupport.getLong(rs, "clientId");
            final String pensionCard = rs.getString( "pensioncard");
            final String adharsdingconstant = rs.getString( "adharseeingconstant");
            final String health=rs.getString("health");;
        	final String language=rs.getString("language");;
        	final Integer cardIssueFlag=JdbcSupport.getInteger(rs,"cardissueFl");;
        	final Integer cbCheck=JdbcSupport.getInteger(rs,"cbcheck");;
        	final Integer renewalFlag=JdbcSupport.getInteger(rs,"renwalfl");;
        	final String mothertounge=rs.getString("montherTounge");;
        	final LocalDate guedianDateOfBirth=JdbcSupport.getLocalDate(rs, "gurdiandateofbirth");
        	final String gurdianName=rs.getString("gurdianName");;
        	final LocalDate spouseDateOfBirth=JdbcSupport.getLocalDate(rs, "spouseDateOfbirth");
        	final String spouseName=rs.getString("spouseName");
            final Long gurdiangender = JdbcSupport.getLong(rs, "gurdiangender");
            final Long relation=JdbcSupport.getLong(rs, "relation");
            final String gurdianMobileNo=rs.getString("gurdianMobileNo");
            final Long gurdianTitle =JdbcSupport.getLong(rs,"gurdianTitle"); 


        	RblCustomerData rblCustomerData=RblCustomerData.create(clientId, pensionCard, adharsdingconstant, health, language, cardIssueFlag, cbCheck, renewalFlag, mothertounge, gurdianName, guedianDateOfBirth, spouseName, spouseDateOfBirth,gurdiangender,
        			relation,gurdianTitle,gurdianMobileNo);
          return rblCustomerData;
        }

    }


}

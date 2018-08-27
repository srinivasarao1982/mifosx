package org.mifosplatform.portfolio.rbl.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanNotFoundException;
import org.mifosplatform.portfolio.rbl.data.RblGroupData;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class RblGroupReadPlatformServiceImpl  implements RblGroupReadPlatformService {
	
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;

	@Autowired
	public RblGroupReadPlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}

	
	
	@Override
    public RblGroupData retriveRblGroup(final Long groupId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final RblGroupDataMapper rm = new RblGroupDataMapper();
            String Sql =rm.schema() + "where mrbg.group_id=?";
            RblGroupData rblGroupData =null;
            rblGroupData = this.jdbcTemplate.queryForObject(Sql, rm, new Object[] { groupId});
            return rblGroupData;
        } catch (final EmptyResultDataAccessException e) {
            throw new PartialLoanNotFoundException(groupId);
        }

    }

    private static final class RblGroupDataMapper implements RowMapper<RblGroupData> {

        public RblGroupDataMapper() {}

        public String schema() {
            return   "select mrbg.maximum_center as maximumcenter,mrbg.group_type as groupType,mrbg.min_number as minNumber,"
            		+ "mrbg.max_numbeer as maxNumber,mrbg.distance_from_center as distancefromCenter,mrbg.meeting_time as meetingTime"	 
            		 +"from m_rblgroup mrbg ";
            		}

        @Override
        public RblGroupData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

;

            final Long maximumcenter = JdbcSupport.getLong(rs, "maximumcenter");
            final Integer groupType=JdbcSupport.getInteger(rs,"groupType");;
        	final Integer minNumber=JdbcSupport.getInteger(rs,"minNumber");;
        	final Integer maxNumber=JdbcSupport.getInteger(rs,"maxNumber");;
        	final Integer distancefromCnter=JdbcSupport.getInteger(rs,"distancefromCenter");;
        	final String meetingTime=rs.getString("meetingTime");;

        	RblGroupData rblGroupData=RblGroupData.create(maximumcenter, groupType, minNumber, maxNumber, distancefromCnter, meetingTime);
        			return rblGroupData;
        }

    }



}

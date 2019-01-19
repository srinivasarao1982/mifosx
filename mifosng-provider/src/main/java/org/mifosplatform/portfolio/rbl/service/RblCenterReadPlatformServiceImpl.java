package org.mifosplatform.portfolio.rbl.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanNotFoundException;
import org.mifosplatform.portfolio.rbl.data.RblCenterData;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class RblCenterReadPlatformServiceImpl  implements RblCenterReadPlatformService{
	
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;

	@Autowired
	public RblCenterReadPlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}
	@Override
    public RblCenterData retriveRblCenter(final Long centerId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final RblCenterDataMapper rm = new RblCenterDataMapper();
            String Sql =rm.schema() + "where mrbc.center_id=?";
            RblCenterData rblCenterData =null;
            rblCenterData = this.jdbcTemplate.queryForObject(Sql, rm, new Object[] { centerId});
            return rblCenterData;
        } catch (final EmptyResultDataAccessException e) {
            throw new PartialLoanNotFoundException(centerId);
        }

    }

    private static final class RblCenterDataMapper implements RowMapper<RblCenterData> {

        public RblCenterDataMapper() {}

        public String schema() {
            return  "select mrbc.max_individual as maxindividual,mrbc.distance_from_branch as distancefrombranch, mrbc.meting_time as meetingtime, mrbc.house_no as houseNo,"
            		+" mrbc.street_no as streetNo,mrbc.area_loc as areaLoc,mrbc.landmark as landmark,mrbc.center_id as centerId, "
            		+" mrbc.village as village,mrbc.taluk as taluk,mrbc.district as district,mrbc.state as state,mcv.code_value as districtName,mcv1.code_value as stateName,mrbc.description as description,mrbc.pin as pin "
            		+"  from m_rblcenter mrbc join m_code_value mcv on mcv.id=mrbc.district"
            		+" join m_code_value mcv1 on mcv1.id=mrbc.state  ";
        }

        @Override
        public RblCenterData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long maxIndividual = JdbcSupport.getLong(rs, "maxindividual");
            final Long centerId = JdbcSupport.getLong(rs, "centerId");
            final String meetingtime=rs.getString("meetingtime");;
        	final String houseNumbr=rs.getString("houseNo");;
        	final String StreetNumber=rs.getString("streetNo");;
        	final String areaLocality=rs.getString("areaLoc");;
        	final String landmark=rs.getString("landmark");;
        	final String village=rs.getString("village");;
        	final String taluk=rs.getString("taluk");;
        	final String district=rs.getString("districtName");;
        	final String state=rs.getString("stateName");;
        	final Long statId=JdbcSupport.getLong(rs, "state");;
        	final Long districtId=JdbcSupport.getLong(rs, "district");;
        	final Long pincode=JdbcSupport.getLong(rs, "pin");;
        	final String description=rs.getString("description");
        	final Integer distancefrombranch=JdbcSupport.getInteger(rs, "distancefrombranch");

            RblCenterData rblCenterData=RblCenterData.createrblcenter(maxIndividual, centerId, meetingtime, houseNumbr, StreetNumber, areaLocality, landmark, village, taluk, district, state, statId, districtId, district, pincode, description,distancefrombranch);
          return rblCenterData;
        }

    }


}

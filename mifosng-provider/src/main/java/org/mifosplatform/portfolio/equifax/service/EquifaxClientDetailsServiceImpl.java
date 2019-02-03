package org.mifosplatform.portfolio.equifax.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.equifax.data.EquifaxErrorData;
import org.mifosplatform.portfolio.equifax.data.EquifaxRequestData;
import org.mifosplatform.portfolio.equifax.data.EquifaxResponseData;
import org.mifosplatform.portfolio.equifax.data.RequestBody;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class EquifaxClientDetailsServiceImpl implements EquifaxClientDetailsService {
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;

	@Autowired
	public EquifaxClientDetailsServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public RequestBody getRequestBody(Long clientId) {
		final AppUser currentUser = this.context.authenticatedUser();

		final RequestBodyDataMapper rm = new RequestBodyDataMapper();
		String Sql = rm.schema() + "and  mc.id=" + clientId;

		RequestBody requestBody = this.jdbcTemplate.queryForObject(Sql, rm, new Object[] {});
		return requestBody;
	}

	private static final class RequestBodyDataMapper implements RowMapper<RequestBody> {
		public RequestBodyDataMapper() {
		}

		public String schema() {
			return " select  mc.firstname as firstName,mc.lastname as lastName,ml.principal_amount_proposed as transactionAmount, "
					+ " concat(ifnull(na.house_no,''),ifnull(na.street_no,''),ifnull(na.area_locality,'')) as addressline1, "
					+ " state.code_score as state,na.pin_code as postalCode,mc.date_of_birth as Dob,if(mc.gender_cv_id=80,'1','2') as gender, "
					+ " next.pan_no as panNo,na.mobile_no as mobileNumber " + " from m_client mc "
					+ " left join m_loan ml on mc.id=ml.client_id " + " left join n_address na on na.client_id=mc.id "
					+ " left join m_code_value state on state.id=na.state_cv_id "
					+ " left join n_client_ext next on next.client_id =mc.id " + " where   na.address_type_cv_id=20 ";

		}

		@Override
		public RequestBody mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			final String firstName = rs.getString("firstName");
			final String lastName = rs.getString("lastName");
			final String principalAmount = rs.getString("transactionAmount");
			final String addressLine1 = rs.getString("addressline1");
			// final String customerName=rs.getString("customerName");
			final String state = rs.getString("state");
			final String postalCode = rs.getString("postalCode");
			final String DateOfBirt = rs.getString("Dob");
			final String gender = rs.getString("gender");
			final String panNo = rs.getString("panNo");
			final String mobileNo = rs.getString("mobileNumber");
			String inquiryPurpose = "1G";
			String homePhone = null;

			return new RequestBody(inquiryPurpose, principalAmount, firstName, lastName, addressLine1, state,
					postalCode, DateOfBirt, gender, panNo, mobileNo, null);
		}
	}

	@Override
	public List<EquifaxErrorData> getEquifaxErrorData(Long centerId, String fromDate, String toDate) {
		final AppUser currentUser = this.context.authenticatedUser();

		final EquifaxErrorBodyDataMapper rm = new EquifaxErrorBodyDataMapper();
		String Sql = rm.schema() + "where  mec.centerId =" + centerId + " and mec.created_date >='" + fromDate + "'"
				+ " and mec.created_date  <='" + toDate + "'";

		List<EquifaxErrorData> equifaxErrorData = this.jdbcTemplate.query(Sql, rm, new Object[] {});
		return equifaxErrorData;
	}

	private static final class EquifaxErrorBodyDataMapper implements RowMapper<EquifaxErrorData> {
		public EquifaxErrorBodyDataMapper() {
		}

		public String schema() {
			return " select  mec.center_id as centerId,mec.client_id as clientId,mec.error as error "
					+ " from m_equifax_error mec ";

		}

		@Override
		public EquifaxErrorData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			final Long centerId = JdbcSupport.getLong(rs, "centerId");
			final Long clientId = JdbcSupport.getLong(rs, "clientId");
			final String error = rs.getString("error");
			return new EquifaxErrorData(centerId, clientId, error);
		}
	}

	@Override
	public List<EquifaxRequestData> getRequestData(Long centerId, String fromDate, String toDate) {
		final AppUser currentUser = this.context.authenticatedUser();

		final EquifaxRequestDataMapper rm = new EquifaxRequestDataMapper();
		String Sql = rm.schema() + "where  mre.centerId =" + centerId + " and mre.created_date >='" + fromDate + "'"
				+ " and mre.created_date  <='" + toDate + "'";

		List<EquifaxRequestData> equifaxRequestData = this.jdbcTemplate.query(Sql, rm, new Object[] {});
		return equifaxRequestData;
	}

	private static final class EquifaxRequestDataMapper implements RowMapper<EquifaxRequestData> {
		public EquifaxRequestDataMapper() {
		}

		public String schema() {
			return " select  mec.center_id as centerId,mec.client_id as clientId,mec.inquiry_purpose as enquirypurpose, mec.first_name as firstName,mec.mobile_phone as mobileNo"
					+ " from m_equifax_request re ";

		}

		@Override
		public EquifaxRequestData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			final Long centerId = JdbcSupport.getLong(rs, "centerId");
			final Long clientId = JdbcSupport.getLong(rs, "clientId");
			final String enquirypurpose = rs.getString("enquirypurpose");
			final String firstName = rs.getString("firstName");
			final String mobileNumber = rs.getString("mobileNo");
			return new EquifaxRequestData(centerId, clientId, enquirypurpose, firstName, mobileNumber);
		}
	}

	@Override
	public List<EquifaxResponseData> getEquifaxResponseData(Long centerId, String fromData, String toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}

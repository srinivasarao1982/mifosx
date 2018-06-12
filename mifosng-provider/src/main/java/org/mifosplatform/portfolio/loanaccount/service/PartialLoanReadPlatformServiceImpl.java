package org.mifosplatform.portfolio.loanaccount.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.loanaccount.data.PartialLoanData;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanNotFoundException;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class PartialLoanReadPlatformServiceImpl implements PartialLoanReadPlatformService {
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;

	@Autowired
	public PartialLoanReadPlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}

	@Override
	public PartialLoanData retrieveTemplate() {
		this.context.authenticatedUser();
		Collection<CodeValueData> status = new ArrayList<>(this.codeValueReadPlatformService
				.retrieveCodeValuesByCode(PartialLoanApiConstant.STATUSCODEVALUEPARAM_NAME));
		PartialLoanData partialLoanData = PartialLoanData.template(status);

		return partialLoanData;

	}
	
	@Override
    public List<PartialLoanData> retrievepartialLoanDetails(final Long parentId) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final PartialLoanMapper rm = new PartialLoanMapper();
            String Sql ="select"+rm.schema();
            List<PartialLoanData>partialLoanDatas=new ArrayList<PartialLoanData>();
            partialLoanDatas = this.jdbcTemplate.query(Sql, rm, new Object[] { parentId});
            return partialLoanDatas;
        } catch (final EmptyResultDataAccessException e) {
            throw new PartialLoanNotFoundException(parentId);
        }

    }

    private static final class PartialLoanMapper implements RowMapper<PartialLoanData> {

        public PartialLoanMapper() {}

        public String schema() {
            return   "  mpl.client_id as clientId,c.display_name as clientName, mpl.rpdo_no as rpdoNumber,"
                    + " mpl.loan_amount as loanAmount,mpl.submitted_date as submittedDate, mcv.code_value as statues, "
                    + " mpl.remark as remark, if(mpl.is_active=0,'false','true') as isActive"
                    + " from m_partial_loan mpl "
                    + " left join m_client c on  mpl.client_id=c.id "
                    + " left join m_code_value mcv on mcv.id =mpl.status "
                    + " where mpl.group_id in (select  id from m_group mg  where mg.parent_id= ?)";
        }

        @Override
        public PartialLoanData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long clientId = JdbcSupport.getLong(rs, "clientId");
            final String clientName = rs.getString("clientName");
            final String rpdoNumber = rs.getString("rpdoNumber");
            final BigDecimal loanAmount = JdbcSupport.getBigDecimalDefaultToNullIfZero(rs, "loanAmount");
            final LocalDate submittedDate =JdbcSupport.getLocalDate(rs, "submittedDate");
            final String status=rs.getString("statues");
            final String remark =rs.getString("remark");
            final String isActive=rs.getString("isActive");

            return PartialLoanData.getPartialLoanData(clientId,clientName,rpdoNumber,loanAmount,submittedDate,status,remark,isActive,null);
        }

    }
    
    @Override
    public List<Long> retriveAcceptedMember(final Long parentId,final Long isActive) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final AcceptedClientMapper rm = new AcceptedClientMapper();
            String Sql ="select"+rm.schema();
            if(isActive==0){
            	Sql=Sql+ " and mcv.code_value='Accepted'";
            }
            List<Long>acceptedclientsIds=new ArrayList<Long>();
            acceptedclientsIds = this.jdbcTemplate.query(Sql, rm, new Object[] { parentId,isActive});
            return acceptedclientsIds;
        } catch (final EmptyResultDataAccessException e) {
            throw new PartialLoanNotFoundException(parentId);
        }

    }

    private static final class AcceptedClientMapper implements RowMapper<Long> {

        public AcceptedClientMapper() {}

        public String schema() {
            return   "  mpl.client_Id as clientId from m_partial_loan mpl "
                    + " left join m_code_value mcv on mcv.id=mpl.status  "
                    + " where mpl.group_id in (select  id from m_group mg  where mg.parent_id=? ) and mpl.is_active =?";
                  }

        @Override
        public Long mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long clientId = JdbcSupport.getLong(rs, "clientId"); 
            return clientId;
            //return PartialLoanData.getAcceptedclientsId(clientId);
        }

    }



}
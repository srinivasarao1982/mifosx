package org.mifosplatform.portfolio.rblvalidation.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.loanaccount.exception.PartialLoanNotFoundException;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditBureauData;
import org.mifosplatform.portfolio.rblvalidation.data.RblLosFileData;
import org.mifosplatform.portfolio.rblvalidation.exception.NoFileFoundException;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class LosfileReadPlatformServiceImpl implements LosFileReadPlatformService {
	
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;

	@Autowired
	public LosfileReadPlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
	}
  
	@Override
    public List<RblLosFileData> readLosFile(final String fromDate,String toDate,final String fileType) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();
            
            final RblLosFileDataMapper rm = new RblLosFileDataMapper();
            String Sql =rm.schema() ;
            if(fileType.equalsIgnoreCase("received")){
            	 Sql =Sql+ "from m_rblreceivefile where mrblf.created_date between '"+fromDate+"'"+"and'"+toDate+"'" ;
            }
            else{
           	 Sql =Sql+ "from m_rblsendfile where mrblf.created_date between '"+fromDate+"'"+"and'"+toDate+"'" ;
            }
            List<RblLosFileData>rblLosFileDatas=new ArrayList<RblLosFileData>();
            rblLosFileDatas = this.jdbcTemplate.query(Sql, rm, new Object[] { fromDate,toDate});
            return rblLosFileDatas;
        } catch (final EmptyResultDataAccessException e) {
            throw new NoFileFoundException(fromDate,toDate);
        }

    }
	
 private static final class RblLosFileDataMapper implements RowMapper<RblLosFileData> {
        public RblLosFileDataMapper() {}

        public String schema() {
            return "select mrblf.file_type as fileType,mrblf.file_Name as fileName ,mrblf.file_path as filePath ,mrblf.created_date as Date ";


        }
        @Override
        public RblLosFileData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

                    
            final String fileType =rs.getString("fileType");
        	final   String fileName =rs.getString("fileName");
        	final   String  filePath=rs.getString("filePath");
        	final LocalDate fileDate =JdbcSupport.getLocalDate(rs, "Date") ;        	  	
            return new RblLosFileData(fileDate,fileName,filePath,fileType);
            		

    }

 
}
}
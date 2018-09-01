package org.mifosplatform.portfolio.rblvalidation.service;

import java.util.Date;

import org.json.JSONObject;
import org.json.XML;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.domain.ClientStatus;
import org.mifosplatform.portfolio.rblvalidation.data.RblAddressData;
import org.mifosplatform.portfolio.rblvalidation.data.RblClientsData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditBureauResponseData;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditReportBody;
import org.mifosplatform.portfolio.rblvalidation.data.RblCreditReportReply;
import org.mifosplatform.portfolio.rblvalidation.data.RblEquifaxData;
import org.mifosplatform.portfolio.rblvalidation.data.RblNomineeData;
import org.mifosplatform.portfolio.rblvalidation.data.RblOperatingRegion;
import org.mifosplatform.portfolio.rblvalidation.domain.CredeitBureauResponseRepositoryWrapper;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureaRequest;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureaoResponse;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureauRequestRepositoryWrapper;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureauValidationError;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureauValidationErrorRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.group.domain.Group;

@Service
public class RblEquifaxWritePlatformServiceImpl implements RblEquifaxWritePlatformService {
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;
	private final CodeValueReadPlatformService codeValueReadPlatformService;
	private final RblCreditBurequReadPlatfoemServie rblCreditBurequReadPlatfoemServie;
    private final ClientRepositoryWrapper clientRepository;
    private final CreditBureauValidationErrorRepositoryWrapper creditBureauValidationErrorRepositoryWrapper;
    private final CreditBureauRequestRepositoryWrapper creditBureauRequestRepositoryWrapper;
    private final CredeitBureauResponseRepositoryWrapper credeitBureauResponseRepositoryWrapper;

	@Autowired
	public RblEquifaxWritePlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource,
			final RblCreditBurequReadPlatfoemServie rblCreditBurequReadPlatfoemServie,
			final ClientRepositoryWrapper clientRepository,final CreditBureauValidationErrorRepositoryWrapper creditBureauValidationErrorRepositoryWrapper,
			final CreditBureauRequestRepositoryWrapper creditBureauRequestRepositoryWrapper,
			final CredeitBureauResponseRepositoryWrapper credeitBureauResponseRepositoryWrapper) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
		this.rblCreditBurequReadPlatfoemServie=rblCreditBurequReadPlatfoemServie;
		this.clientRepository=clientRepository;
		this.creditBureauValidationErrorRepositoryWrapper=creditBureauValidationErrorRepositoryWrapper;
		this.creditBureauRequestRepositoryWrapper=creditBureauRequestRepositoryWrapper;
		this.credeitBureauResponseRepositoryWrapper =credeitBureauResponseRepositoryWrapper;
	}

	@Override
	public boolean rblequifaxIntregation(Long clientId) {
		
		RblEquifaxData rblEquifaxData =this.rblCreditBurequReadPlatfoemServie.generateDataforCreditBureau(clientId);
	    
		RblClientsData rblClientsData =rblEquifaxData.getGetConsumerCreditReportBody();
		RblNomineeData rblNomineeData =rblClientsData.getNominee();
		RblAddressData rblAddressData =rblClientsData.getAddress();
		RblAddressData RblNomineeAddressData = rblClientsData.getAddress();
		RblOperatingRegion rblOperatingRegion = rblClientsData.getOperatingRegion();
		final Client client =this.clientRepository.findOneWithNotFoundDetection(clientId);
		Long centerId=null;
		for(Group groups :client.getGroups()){
			centerId=groups.getParent().getId();
		}
		boolean isError =false;
		String Error =clientId +"-";
		if(rblClientsData.getBarcodeNo()==null){
			Error=Error+"BarCode Number Cannto Bel Null";
			isError=true;
		}
		if(rblClientsData.getExternalId()==null){Error=Error+"External Id Cannto Be Null\n";isError=true;}
		if(rblClientsData.getLoanAmount()==null){Error=Error+"Loan Amount Cannto Be Null\n";isError=true;}
		if(rblClientsData.getIsRenewalLoan()==null){Error=Error+"Renewal Flag Id Cannto Be Null\n";isError=true;}
		if(rblClientsData.getCustomerName()==null){Error=Error+"Customer Name Cannto Bel Null\n";isError=true;}
		if(rblClientsData.getBranchCode()==null){Error=Error+"Branch Code Cannto Bel Null\n";isError=true;}
		if(rblClientsData.getBranchName()==null){Error=Error+"Branch Name Cannto Bel Null\n";isError=true;}
		if(rblClientsData.getDateOfBirth()==null){Error=Error+"Date of Birth Cannto Bel Null\n";isError=true;}
		if(rblClientsData.getGender()==null){Error=Error+"Gender Cannto Bel Null\n";isError=true;}
		if(rblNomineeData.getTitle()==null){Error=Error+"Nominee Title Cannto Be Null\n";isError=true;}
		if(rblAddressData.getLine1()==null){Error=Error+"Address Line 1 Cannot Be Null\n";isError=true;}
		if(rblAddressData.getLine2()==null){Error=Error+"Address Line 2 Cannto Be Null\n";isError=true;}
		if(rblAddressData.getLine3()==null){Error=Error+"Address Line 3 Cannto Be Null\n";isError=true;}
		if(rblAddressData.getCityCode()==null){Error=Error+"City Code Cannot Be Null\n";isError=true;}
		if(rblAddressData.getCityName()==null){Error=Error+"City Namw Cannot Be Null\n";isError=true;}
		if(rblAddressData.getStateCode()==null){Error=Error+"State Code Cannto Be Null\n";isError=true;}
		if(rblAddressData.getStateName()==null){Error=Error+"State Name Cannto Be Null\n";isError=true;}
		if(rblAddressData.getPin()==null){Error=Error+"Pin Cannto Be Null\n";isError=true;}
		if(rblOperatingRegion.getOperatingRegionCode()==null){Error=Error+"Operating Region Code Cannto Be Null\n";isError=true;}
		if(rblOperatingRegion.getOperatingRegionName()==null){Error=Error+"Operating Region Name Be Null\n";isError=true;}
 
		 if(isError){
			 CreditBureauValidationError creditBureauValidationError =CreditBureauValidationError.create(centerId, rblClientsData.getBarcodeNo(), rblClientsData.getExternalId()
					 , rblClientsData.getIsRenewalLoan(), rblClientsData.getCustomerName(), rblClientsData.getLoanAmount(), rblNomineeData.getTitle(), 
					 rblNomineeData.getName(), rblNomineeData.getRelation(), rblAddressData.getLine1(), rblAddressData.getLine2(), rblAddressData.getLine3(),
					 rblAddressData.getCityCode(), rblAddressData.getCityName(), rblAddressData.getStateCode(), rblAddressData.getPin(), rblOperatingRegion.getOperatingRegionCode(), rblOperatingRegion.getOperatingRegionName(), 
					 rblClientsData.getDateOfBirth(), rblClientsData.getBranchCode(), rblClientsData.getBranchName(), Error, clientId);
					this.creditBureauValidationErrorRepositoryWrapper.save(creditBureauValidationError);    		    
					return false;    
		 }
		 else{
			 
			 try{
			 
			// JSONObject json = new JSONObject(str);
			 String xml = XML.toString(rblEquifaxData);
			 
			 
			 
			 CreditBureaRequest creditBureaRequest =CreditBureaRequest.create(centerId, rblClientsData.getBarcodeNo(), rblClientsData.getExternalId()
					 , rblClientsData.getIsRenewalLoan(), rblClientsData.getCustomerName(), rblClientsData.getLoanAmount(), rblNomineeData.getTitle(), 
					 rblNomineeData.getName(), rblNomineeData.getRelation(), rblAddressData.getLine1(), rblAddressData.getLine2(), rblAddressData.getLine3(),
					 rblAddressData.getCityCode(), rblAddressData.getCityName(), rblAddressData.getStateCode(), rblAddressData.getPin(), rblOperatingRegion.getOperatingRegionCode(), rblOperatingRegion.getOperatingRegionName(), 
					 rblClientsData.getDateOfBirth(), rblClientsData.getBranchCode(), rblClientsData.getBranchName(), Error, clientId);
					this.creditBureauRequestRepositoryWrapper.save(creditBureaRequest);  
					
					//Response Code

			 String responsexml="";
			//Conver Response to Json
			 JSONObject RblCreditBureauResponseData =XML.toJSONObject(responsexml);
			 RblCreditReportBody rblCreditReportBody =(RblCreditReportBody) RblCreditBureauResponseData.get("getConsumerCreditReportBody");
			 RblCreditReportReply rblCreditReportReply =rblCreditReportBody.getGetConsumerCreditReportReply();
			 if(rblCreditReportReply.getCreditApproved().equalsIgnoreCase("false")){			 
				 final Client clientforUpdate =this.clientRepository.findOneWithNotFoundDetection(clientId);
				 ClientStatus status = ClientStatus.REJECTED;
				 clientforUpdate.setStatus(status.getValue());
				 this.clientRepository.saveAndFlush(clientforUpdate);
			 }
			 JSONObject rsponse =(JSONObject) RblCreditBureauResponseData.get("Header");
			 CreditBureaoResponse creditBureaoResponse=
					 CreditBureaoResponse.create(centerId, rsponse.getString("RequestUUID"), rsponse.getString("ServiceName"), rsponse.getString("ChannelId"), rsponse.getString("CorpId"),
							 rblCreditReportReply.getCreditApproved(), rblCreditReportReply.getCreditDecisionReasons().getReason(), rblCreditReportReply.getEligibleLoanAmount(), null, clientId);
			 
			 this.credeitBureauResponseRepositoryWrapper.save(creditBureaoResponse);
		 }
	
		 catch(Exception e){
			 
		 }
		 }
		
		return true;
	
	}
	
}

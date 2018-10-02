package org.mifosplatform.portfolio.rblvalidation.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.joda.time.DateTime;
import org.json.JSONObject;
import org.json.XML;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.domain.ClientRepositoryWrapper;
import org.mifosplatform.portfolio.client.domain.ClientStatus;
import org.mifosplatform.portfolio.rblvalidation.data.RblAddressData;
import org.mifosplatform.portfolio.rblvalidation.data.RblClientsData;
import org.mifosplatform.portfolio.rblvalidation.data.RblEquifaxData;
import org.mifosplatform.portfolio.rblvalidation.data.RblNomineeData;
import org.mifosplatform.portfolio.rblvalidation.data.RblOperatingRegion;
import org.mifosplatform.portfolio.rblvalidation.domain.CredeitBureauResponseRepositoryWrapper;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureaRequest;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureaoResponse;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureauRequestRepositoryWrapper;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureauValidationError;
import org.mifosplatform.portfolio.rblvalidation.domain.CreditBureauValidationErrorRepositoryWrapper;
import org.mifosplatform.portfolio.rblvalidation.domain.ValidateRblFileRepository;
import org.mifosplatform.portfolio.rblvalidation.domain.ValidatefileRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoan;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoanRepositoryWrapper;
import org.mifosplatform.portfolio.loanaccount.service.PartialLoanWriteplatformService;

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
    private final ValidateRblFileRepository validateRblFileRepository;
    private final PartialLoanRepositoryWrapper partialLoanRepositoryWrapper;
    private final CodeValueRepositoryWrapper codeValueRepositoryWrapper;

	@Autowired
	public RblEquifaxWritePlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource,
			final RblCreditBurequReadPlatfoemServie rblCreditBurequReadPlatfoemServie,
			final ClientRepositoryWrapper clientRepository,final CreditBureauValidationErrorRepositoryWrapper creditBureauValidationErrorRepositoryWrapper,
			final CreditBureauRequestRepositoryWrapper creditBureauRequestRepositoryWrapper,
			final CredeitBureauResponseRepositoryWrapper credeitBureauResponseRepositoryWrapper,
			final ValidateRblFileRepository validateRblFileRepository,
			final PartialLoanRepositoryWrapper partialLoanRepositoryWrapper,
			final CodeValueRepositoryWrapper codeValueRepositoryWrapper) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.codeValueReadPlatformService = codeValueReadPlatformService;
		this.rblCreditBurequReadPlatfoemServie=rblCreditBurequReadPlatfoemServie;
		this.clientRepository=clientRepository;
		this.creditBureauValidationErrorRepositoryWrapper=creditBureauValidationErrorRepositoryWrapper;
		this.creditBureauRequestRepositoryWrapper=creditBureauRequestRepositoryWrapper;
		this.credeitBureauResponseRepositoryWrapper =credeitBureauResponseRepositoryWrapper;
		this.validateRblFileRepository=validateRblFileRepository;
		this.partialLoanRepositoryWrapper=partialLoanRepositoryWrapper;
		this.codeValueRepositoryWrapper=codeValueRepositoryWrapper;
	}

	@Override
	public boolean rblequifaxIntregation(String clientIds,boolean isValidate) {
		FileWriter fr =null;
		Long centersId=null;
		String fileLocation=null;
		String centerNames =null;
		String fileNames=null;
		
		String [] stringclientsId =clientIds.split(",");
    	for(int i=1 ;i<stringclientsId.length;i++){
    	   Long clientId =Long.parseLong(stringclientsId[i]);
    	       
		
		RblEquifaxData rblEquifaxData =this.rblCreditBurequReadPlatfoemServie.generateDataforCreditBureau(clientId);
		
		/*	JSONObject json = new JSONObject(rblEquifaxData);
		String xml = XML.toString(json);
		 System.out.println("xml formate is"+xml);
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
		 CreditBureaRequest creditBureaRequest =CreditBureaRequest.create(centerId, rblClientsData.getBarcodeNo(), rblClientsData.getExternalId()
				 , rblClientsData.getIsRenewalLoan(), rblClientsData.getCustomerName(), rblClientsData.getLoanAmount(), rblNomineeData.getTitle(), 
				 rblNomineeData.getName(), rblNomineeData.getRelation(), rblAddressData.getLine1(), rblAddressData.getLine2(), rblAddressData.getLine3(),
				 rblAddressData.getCityCode(), rblAddressData.getCityName(), rblAddressData.getStateCode(), rblAddressData.getPin(), rblOperatingRegion.getOperatingRegionCode(), rblOperatingRegion.getOperatingRegionName(), 
				 rblClientsData.getDateOfBirth(), rblClientsData.getBranchCode(), rblClientsData.getBranchName(), Error, clientId);
				this.creditBureauRequestRepositoryWrapper.save(creditBureaRequest);  

		 
		 String responsexml ="<getConsumerCreditReport><Header><RequestUUID>Req_LodgeColl_00uii891</RequestUUID"
				 +"><ServiceName>Equifax</ServiceName><MessageDateTime>2018-03-"
				 +"20T15:10:57.18</MessageDateTime><ChannelId>LOS</ChannelId><CorpId>Los_1234</CorpId"
				 +"></Header><getConsumerCreditReportBody><getConsumerCreditReportReply><creditApproved"
				 +">false</creditApproved><creditDecisionReasons><reason>resonnasdasdasdasda</reason></cr"
				 +"editDecisionReasons><eligibleLoanAmount>40130</eligibleLoanAmount></getConsumerCreditR"
				 +"eportReply></getConsumerCreditReportBody> </getConsumerCreditReport>";
		//Conver Response to Json
		 JSONObject RblCreditBureauResponseData =XML.toJSONObject(responsexml);
		 JSONObject rblCreditReportBody = RblCreditBureauResponseData.getJSONObject("getConsumerCreditReport").getJSONObject("getConsumerCreditReportBody");
		 JSONObject rblCreditReportReply =rblCreditReportBody.getJSONObject("getConsumerCreditReportReply");
		 JSONObject rblcreditReason =rblCreditReportReply.getJSONObject("creditDecisionReasons");
		 if(rblCreditReportReply.getString("creditApproved").equalsIgnoreCase("false")){			 
			 final Client clientforUpdate =this.clientRepository.findOneWithNotFoundDetection(clientId);
			 ClientStatus status = ClientStatus.REJECTED;
			 clientforUpdate.setStatus(status.getValue());
			 this.clientRepository.saveAndFlush(clientforUpdate);
		 }
		 JSONObject rsponse =(JSONObject) RblCreditBureauResponseData.getJSONObject("getConsumerCreditReport").getJSONObject("Header");
		 CreditBureaoResponse creditBureaoResponse=
				 CreditBureaoResponse.create(centerId, rsponse.getString("RequestUUID"), rsponse.getString("ServiceName"), rsponse.getString("ChannelId"), rsponse.getString("CorpId"),
						 rblCreditReportReply.getString("creditApproved"), rblcreditReason.getString("reason"), rblCreditReportReply.getString("eligibleLoanAmount"), null, clientId);
		 
		 this.credeitBureauResponseRepositoryWrapper.save(creditBureaoResponse);

*/
		 try{

		RblClientsData rblClientsData =rblEquifaxData.getGetConsumerCreditReportBody();
		RblNomineeData rblNomineeData =rblClientsData.getNominee();
		RblAddressData rblAddressData =rblClientsData.getAddress();
		RblAddressData RblNomineeAddressData = rblClientsData.getAddress();
		RblOperatingRegion rblOperatingRegion = rblClientsData.getOperatingRegion();
		final Client client =this.clientRepository.findOneWithNotFoundDetection(clientId);
	    String centerName="";
		Long centerId=null;
		for(Group groups :client.getGroups()){
			centerId=groups.getParent().getId();
			centersId =centerId;
			centerName =groups.getParent().getName();
			centerNames =centerName;
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
		int x=1;
		if(isValidate){
			 if(x==1){
		    String RBL_BASE_DIR = System.getProperty("user.home") + File.separator + ".mifosx"+File.separator+"RblValidationFile";

			 File rblcentervalidatefile =new File (RBL_BASE_DIR,centerName+"_validatefile"+new DateTime().toString("ddmmyyyhhmmss")+".txt");
			 fileLocation =	RBL_BASE_DIR;
			 fileNames =centerName+"_validatefile"+new DateTime().toString("ddmmyyyhhmmss")+".txt";
				fr =new FileWriter(rblcentervalidatefile);	
			 }
		fr.write("===========Start Writing for Client External Id============"+  rblClientsData.getExternalId()  +"\n");
		fr.write(Error);
		fr.write("\n");
		}
		else{
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
			 
			
			 
					JSONObject json = new JSONObject(rblEquifaxData);
					String xml = XML.toString(json,"getConsumerCreditReport");
					 System.out.println("xml formate is"+xml);
			 
			 // url to be called
			 
			 CreditBureaRequest creditBureaRequest =CreditBureaRequest.create(centerId, rblClientsData.getBarcodeNo(), rblClientsData.getExternalId()
					 , rblClientsData.getIsRenewalLoan(), rblClientsData.getCustomerName(), rblClientsData.getLoanAmount(), rblNomineeData.getTitle(), 
					 rblNomineeData.getName(), rblNomineeData.getRelation(), rblAddressData.getLine1(), rblAddressData.getLine2(), rblAddressData.getLine3(),
					 rblAddressData.getCityCode(), rblAddressData.getCityName(), rblAddressData.getStateCode(), rblAddressData.getPin(), rblOperatingRegion.getOperatingRegionCode(), rblOperatingRegion.getOperatingRegionName(), 
					 rblClientsData.getDateOfBirth(), rblClientsData.getBranchCode(), rblClientsData.getBranchName(), Error, clientId);
					this.creditBureauRequestRepositoryWrapper.save(creditBureaRequest);  
					
					//Response Code

			//Conver Response to Json
			 String responsexml ="<getConsumerCreditReport><Header><RequestUUID>Req_LodgeColl_00uii891</RequestUUID"
					 +"><ServiceName>Equifax</ServiceName><MessageDateTime>2018-03-"
					 +"20T15:10:57.18</MessageDateTime><ChannelId>LOS</ChannelId><CorpId>Los_1234</CorpId"
					 +"></Header><getConsumerCreditReportBody><getConsumerCreditReportReply><creditApproved"
					 +">false</creditApproved><creditDecisionReasons><reason>resonnasdasdasdasda</reason></cr"
					 +"editDecisionReasons><eligibleLoanAmount>40130</eligibleLoanAmount></getConsumerCreditR"
					 +"eportReply></getConsumerCreditReportBody> </getConsumerCreditReport>";
			//Conver Response to Json
			 JSONObject RblCreditBureauResponseData =XML.toJSONObject(responsexml);
			 JSONObject rblCreditReportBody = RblCreditBureauResponseData.getJSONObject("getConsumerCreditReport").getJSONObject("getConsumerCreditReportBody");
			 JSONObject rblCreditReportReply =rblCreditReportBody.getJSONObject("getConsumerCreditReportReply");
			 JSONObject rblcreditReason =rblCreditReportReply.getJSONObject("creditDecisionReasons");
			 if(rblCreditReportReply.getString("creditApproved").equalsIgnoreCase("false")){			 
				 final Client clientforUpdate =this.clientRepository.findOneWithNotFoundDetection(clientId);
				 ClientStatus status = ClientStatus.REJECTED;
				 clientforUpdate.setStatus(status.getValue());
				 this.clientRepository.saveAndFlush(clientforUpdate);				 
				 Long groupId=(long) 0;
				 for(Group group :clientforUpdate.getGroups()){
					 groupId= group.getId();
				 }
			  CodeValue codevaluseStatus =this.codeValueRepositoryWrapper.findOneWithNotFoundDetection((long) 288);
			  final  PartialLoan partialLoanforUpdate = this.partialLoanRepositoryWrapper.findActiveLoansByLoanIdAndGroupId(clientId, groupId, 1,0);
			  partialLoanforUpdate.updatestatus(codevaluseStatus);
			  partialLoanforUpdate.updateisActive(0);
			  this.partialLoanRepositoryWrapper.saveAndFlush(partialLoanforUpdate);
			 }
			 else{				 
				 Long groupId=(long) 0;
				 final Client clientforUpdate =this.clientRepository.findOneWithNotFoundDetection(clientId);
				 for(Group group :clientforUpdate.getGroups()){
					 groupId= group.getId();
				 }
			        CodeValue codevaluseStatus =this.codeValueRepositoryWrapper.findOneWithNotFoundDetection((long) 289);
					  final  PartialLoan partialLoanforUpdate = this.partialLoanRepositoryWrapper.findActiveLoansByLoanIdAndGroupId(clientId, groupId, 1,0);
					  partialLoanforUpdate.updatestatus(codevaluseStatus);
					  partialLoanforUpdate.updateisActive(0);
				  this.partialLoanRepositoryWrapper.saveAndFlush(partialLoanforUpdate);

			 }
			 JSONObject rsponse =(JSONObject) RblCreditBureauResponseData.getJSONObject("getConsumerCreditReport").getJSONObject("Header");
			 CreditBureaoResponse creditBureaoResponse=
					 CreditBureaoResponse.create(centerId, rsponse.getString("RequestUUID"), rsponse.getString("ServiceName"), rsponse.getString("ChannelId"), rsponse.getString("CorpId"),
							 rblCreditReportReply.getString("creditApproved"), rblcreditReason.getString("reason"), rblCreditReportReply.getString("eligibleLoanAmount"), null, clientId);
			 
			 this.credeitBureauResponseRepositoryWrapper.save(creditBureaoResponse);
			
		   }
		}		
	 }
	 catch(Exception e){
		  e.printStackTrace();	 
		  }
	  }
    if(isValidate){
    		try {
    			 fr.close();			
    			  ValidatefileRecord validatefileRecord = new ValidatefileRecord(centersId,"CB",fileNames,fileLocation);
    		      this.validateRblFileRepository.save(validatefileRecord); 
        	    }
    	catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    	
		return true;
	
	}
	
}

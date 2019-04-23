package org.mifosplatform.portfolio.rblvalidation.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;


import org.joda.time.DateTime;
import org.json.JSONObject;
import org.json.XML;
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
import org.mifosplatform.portfolio.rblvalidation.data.RblHeaderData;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoan;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoanRepository;
import org.mifosplatform.portfolio.loanaccount.domain.PartialLoanRepositoryWrapper;

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
    private final PartialLoanRepository partialLoanRepository;
    private final String keyStoreUrl1;
    private final String trustStoreUrl1;
    private final String password;
    private final String rblUrl;

	@Autowired
	public RblEquifaxWritePlatformServiceImpl(final PlatformSecurityContext context,
			final CodeValueReadPlatformService codeValueReadPlatformService, final RoutingDataSource dataSource,
			final RblCreditBurequReadPlatfoemServie rblCreditBurequReadPlatfoemServie,
			final ClientRepositoryWrapper clientRepository,final CreditBureauValidationErrorRepositoryWrapper creditBureauValidationErrorRepositoryWrapper,
			final CreditBureauRequestRepositoryWrapper creditBureauRequestRepositoryWrapper,
			final CredeitBureauResponseRepositoryWrapper credeitBureauResponseRepositoryWrapper,
			final ValidateRblFileRepository validateRblFileRepository,
			final PartialLoanRepositoryWrapper partialLoanRepositoryWrapper,
			final CodeValueRepositoryWrapper codeValueRepositoryWrapper,
			final PartialLoanRepository partialLoanRepository,@Value("${keyStoreUrl1}") final String keyStoreUrl1, @Value("${trustStoreUrl1}") final String trustStoreUrl1, 
            @Value("${password}") final String password,@Value("${rblUrl}") final String rblUrl) {
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
		this.partialLoanRepository=partialLoanRepository;
		this.keyStoreUrl1=keyStoreUrl1;
		this.trustStoreUrl1=trustStoreUrl1;
		this.password=password;
		this.rblUrl=rblUrl;
	}

	@Override
	public boolean rblequifaxIntregation(String clientIds,boolean isValidate) {
		FileWriter fr =null;
		Long centersId=null;
		String fileLocation=null;
		String centerNames =null;
		String fileNames=null;
		String responsexml=null;
		int x=1;
		String [] stringclientsId =clientIds.split(",");
    	for(int i=0 ;i<stringclientsId.length;i++){
    	   Long clientId =Long.parseLong(stringclientsId[i]);
    	       
		
		RblEquifaxData rblEquifaxData =this.rblCreditBurequReadPlatfoemServie.generateDataforCreditBureau(clientId);
		
			 try{

		RblClientsData rblClientsData =rblEquifaxData.getGetConsumerCreditReportBody();
		RblNomineeData rblNomineeData =rblClientsData.getNominee();
		RblAddressData rblAddressData =rblClientsData.getAddress();
		RblAddressData RblNomineeAddressData = rblClientsData.getAddress();
		RblOperatingRegion rblOperatingRegion = rblClientsData.getOperatingRegion();
		final Client client =this.clientRepository.findOneWithNotFoundDetection(clientId);
		
		JSONObject json1 = new JSONObject(rblEquifaxData);
		String xml1 = XML.toString(json1,"getConsumerCreditReport");
		System.out.println(xml1);
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
			Error=Error+"BarCode Number Cannto Bel Null\n";
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
		
		if(isValidate){
			 if(x==1){
				 x++;
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
					 , rblClientsData.getIsRenewalLoan(), rblClientsData.getCustomerName(), rblClientsData.getLoanAmount(),null, 
					 rblNomineeData.getName(), rblNomineeData.getRelation(), rblAddressData.getLine1(), rblAddressData.getLine2(), rblAddressData.getLine3(),
					 rblAddressData.getCityCode(), rblAddressData.getCityName(), rblAddressData.getStateCode(), rblAddressData.getPin(), rblOperatingRegion.getOperatingRegionCode(), rblOperatingRegion.getOperatingRegionName(), 
					 rblClientsData.getDateOfBirth(), rblClientsData.getBranchCode(), rblClientsData.getBranchName(), Error, clientId);
					this.creditBureauValidationErrorRepositoryWrapper.save(creditBureauValidationError);    		    
					//return false;    
		      }
		 else{
			 String pwd = password;				
				try {
					
					KeyStore keyStore  = KeyStore.getInstance("jks");
				    FileInputStream instream = new FileInputStream(new File(keyStoreUrl1));
				    try {
				        keyStore.load(instream, pwd.toCharArray());
				    } finally {
				        instream.close();
				    }

				    // Trust own CA and all self-signed certs
				    SSLContext sslcontext = SSLContexts.custom()
				        .loadKeyMaterial(keyStore, pwd.toCharArray())
				        .build();
					
					  // Allow TLSv1.2 protocol only
				    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				    		sslcontext,
				            new String[] { "TLSv1.2" },
				            null,
				            SSLConnectionSocketFactory.getDefaultHostnameVerifier());
				    
				    //build httpclient
				    CloseableHttpClient httpclient = HttpClients.custom()
				            .setSSLSocketFactory(sslsf)
				            .build();
						
		             // this is input request body. TO DO - Please replace this with actual input				    

				    	try {
				    	JSONObject json = new JSONObject(rblEquifaxData);
				    	String xml = XML.toString(json,"getConsumerCreditReport");
						
				      
				      HttpPost post = new HttpPost(rblUrl);
				      post.setEntity(new StringEntity(xml));
				      
				      post.setHeader("Content-type", "application/xml");
				      post.setHeader("Authorization", "Basic TkVYVFJVTElWRTpsZGFwQHNyaW5pdmFzQDE5ODI=");
				      
				      // make post call and get response
				      CloseableHttpResponse response =httpclient.execute(post);
		              
				      // Retrieve content  form response
				      HttpEntity entity = response.getEntity();
		               responsexml = EntityUtils.toString(entity);
		              responsexml=responsexml.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
				    }catch (Exception e){
				    	// TODO Auto-generated catch block
						e.printStackTrace();
				    } finally {
				           httpclient.close();
				   }
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			 
			 CreditBureaRequest creditBureaRequest =CreditBureaRequest.create(centerId, rblClientsData.getBarcodeNo(), rblClientsData.getExternalId()
					 , rblClientsData.getIsRenewalLoan(), rblClientsData.getCustomerName(), rblClientsData.getLoanAmount(), null, 
					 rblNomineeData.getName(), rblNomineeData.getRelation(), rblAddressData.getLine1(), rblAddressData.getLine2(), rblAddressData.getLine3(),
					 rblAddressData.getCityCode(), rblAddressData.getCityName(), rblAddressData.getStateCode(), rblAddressData.getPin(), rblOperatingRegion.getOperatingRegionCode(), rblOperatingRegion.getOperatingRegionName(), 
					 rblClientsData.getDateOfBirth(), rblClientsData.getBranchCode(), rblClientsData.getBranchName(), Error, clientId);
					this.creditBureauRequestRepositoryWrapper.save(creditBureaRequest);  
					
					//Response Code
					//Conver Response to Json
			 JSONObject RblCreditBureauResponseData =XML.toJSONObject(responsexml.toString());
			 JSONObject rblCreditReportBody = RblCreditBureauResponseData.getJSONObject("getConsumerCreditReport").getJSONObject("getConsumerCreditReportBody");
			 JSONObject rblCreditReportReply =rblCreditReportBody.getJSONObject("getConsumerCreditReportReply");
			 JSONObject rblcreditReason =rblCreditReportReply.getJSONObject("creditDecisionReasons");
			 String eligibleAmount="0";
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
			  partialLoanforUpdate.updateisDisburse(1);
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
				  eligibleAmount=rblCreditReportReply.getString("eligibleLoanAmount");

			 }
			 JSONObject rsponse =(JSONObject) RblCreditBureauResponseData.getJSONObject("getConsumerCreditReport").getJSONObject("Header");
			 RblHeaderData rblHeaderData= rblEquifaxData.getHeader();
			 CreditBureaoResponse creditBureaoResponse=
					 CreditBureaoResponse.create(centerId, rblHeaderData.getRequestUUID(), rblHeaderData.getServiceName(), rblHeaderData.getChannelId(), rblHeaderData.getCorpId(),
							 rblCreditReportReply.getString("creditApproved"), rblcreditReason.getString("reason"),eligibleAmount , null, clientId);
			 
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

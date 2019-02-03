package org.mifosplatform.portfolio.equifax.service;



import java.util.Date;

import org.json.JSONObject;
import org.json.XML;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.equifax.data.RequestBody;
import org.mifosplatform.portfolio.equifax.domain.EquifaxError;
import org.mifosplatform.portfolio.equifax.domain.EquifaxErrorRepositoryWrapper;
import org.mifosplatform.portfolio.equifax.domain.EquifaxRequest;
import org.mifosplatform.portfolio.equifax.domain.EquifaxRequestRepositoryWrapper;
import org.mifosplatform.portfolio.equifax.domain.EquifaxResponseRepositoeyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service

public class EquifaxServiceImpl  implements EquifaxService{
       
    	private final JdbcTemplate jdbcTemplate;
        private final PlatformSecurityContext context;
        private final EquifaxClientDetailsService equifaxClientDetailsService;
        private final EquifaxRequestRepositoryWrapper equifaxRequestRepositoryWrapper;
        private final EquifaxResponseRepositoeyWrapper equifaxResponseRepositoeyWrapper;
        private final EquifaxErrorRepositoryWrapper equifaxErrorRepositoryWrapper;
      
        @Autowired
        public EquifaxServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource,
        		final EquifaxClientDetailsService equifaxClientDetailsService,final EquifaxRequestRepositoryWrapper equifaxRequestRepositoryWrapper,
        		final EquifaxResponseRepositoeyWrapper equifaxResponseRepositoeyWrapper,final EquifaxErrorRepositoryWrapper equifaxErrorRepositoryWrapper) {                
            this.context = context;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
            this.equifaxClientDetailsService=equifaxClientDetailsService;
            this.equifaxRequestRepositoryWrapper=equifaxRequestRepositoryWrapper;
            this.equifaxResponseRepositoeyWrapper=equifaxResponseRepositoeyWrapper;
            this.equifaxErrorRepositoryWrapper=equifaxErrorRepositoryWrapper;
         }

		@Override
		public void getCreditBureauResult(Long clientId,Long centerId) {
			try{
			
			RequestBody requestBody =this.equifaxClientDetailsService.getRequestBody(clientId);			
			 String xml1 ="<soapenv:Envelope xmlns:soapenv="+"http://schemas.xmlsoap.org/soap/envelope/"+" xmlns:ns="+"http://services.equifax.com/eport/ws/schemas/1.0"+">"
			+" <soapenv:Header/>"
			+" <soapenv:Body>"
			+" <ns:InquiryRequest>";
			
           JSONObject inputJSON = new JSONObject();
           inputJSON.append("ns:CustomerId", "someValue1");
           inputJSON.append("ns:UserId", "someValue2");
           inputJSON.append("ns:Password", "someValue1");
           inputJSON.append("ns:MemberNumber", "someValue2");
           inputJSON.append("ns:SecurityCode", "someValue1");
           inputJSON.append("ns:ProductCode", "someValue2");
           inputJSON.append("ns:CustomerId", "someValue1");
           inputJSON.append("ns:ProductVersion", "someValue2");
           inputJSON.append("ns:ReportFormat", "someValue2");
           inputJSON.append("ns:CustRefField", "someValue2");
           String headeInput = XML.toString(inputJSON,"ns:RequestHeader");

           
           JSONObject customerJSON = new JSONObject();
           customerJSON.append("ns:InquiryPurpose", requestBody.getInquiryPurpose());
           customerJSON.append("ns:TransactionAmount",requestBody.getTransactionAmount());
           customerJSON.append("ns:FirstName", requestBody.getFirstName());
           customerJSON.append("ns:LastName", requestBody.getLastName());
           customerJSON.append("ns:AddrLine1",requestBody.getAddrLine1());
           customerJSON.append("ns:State", requestBody.getState());
           customerJSON.append("ns:Postal", requestBody.getPostal());
           customerJSON.append("ns:DOB", requestBody.getDOB());
           customerJSON.append("ns:Gender", requestBody.getGender());
           customerJSON.append("ns:PANId", requestBody.getPANId());
           customerJSON.append("ns:MobilePhone", requestBody.getMobilePhone());
           
           String errsg="";
           boolean  isError=false;
           if(requestBody.getInquiryPurpose()==null ){
        	   errsg ="Enquiry purpose Cannot be Null \n";
        	   isError=true;
           }
        	   if(requestBody.getInquiryPurpose().length()>2){
            	   errsg =errsg +"Enquiry purpose length cannot be greater than 2 \n";
            	   isError=true;
        	   }
           if(requestBody.getTransactionAmount()==null){
        	   errsg =errsg+"Transaction Amount  Cannot be Null \n";
        	   isError=true;
           }
           
           if(requestBody.getFirstName()==null){
        	   errsg =errsg+"First Name  Cannot be Null \n";
        	   isError=true;
           }
           if(requestBody.getFirstName().length()>40){
        	   errsg =errsg+"First Name  length cannot be greater than 40 \n";
        	   isError=true;
           }
           
           if(requestBody.getLastName()==null){
        	   errsg =errsg+"Last Name  Cannot be Null \n";
        	   isError=true;
           }
           if(requestBody.getLastName().length()>40){
        	   errsg =errsg+"Last Name  length cannot be greater than 40 \n";
        	   isError=true;
           }
           if(requestBody.getState()==null){
        	   errsg =errsg+"State Line1 Cannot be Null \n";
        	   isError=true;
           }
           if(requestBody.getState().length()>2){
        	   errsg =errsg+"State  length cannot be greater than 200 \n";
        	   isError=true;
           }
           if(requestBody.getPostal()==null){
        	   errsg =errsg+"Postal Line1 Cannot be Null \n";
        	   isError=true;
           }
           if(requestBody.getPostal().length()>10){
        	   errsg =errsg+"Postal Line1 cannot be greater than 10 \n";
        	   isError=true;
           }
           
           if(requestBody.getAddrLine1()==null){
        	   errsg =errsg+"Address Line1 Cannot be Null \n";
        	   isError=true;
           }
           if(requestBody.getAddrLine1().length()>200){
        	   errsg =errsg+"Address Line1  length cannot be greater than 200 \n";
        	   isError=true;
           }
           
           if(requestBody.getDOB()==null){
        	   errsg =errsg+"Dob  Cannot be Null \n";
        	   isError=true;
           }
           
           if(requestBody.getGender()==null){
        	   errsg =errsg+"Gender Cannot be Null \n";
        	   isError=true;
           }
           if(requestBody.getPANId()==null){
        	   errsg =errsg+"PanId Cannot be Null \n";
        	   isError=true;
           }
           if(requestBody.getMobilePhone()==null){
        	   errsg =errsg+"Mobile Phone Cannot be Null \n";
        	   isError=true;
           }
            if(isError){
            	EquifaxError equifaxError =EquifaxError.createEuifaxError(centerId, clientId, errsg);
            	this.equifaxErrorRepositoryWrapper.save(equifaxError);
              }
            else{
            	Date dateOfBirt =new Date(requestBody.getDOB());
            	EquifaxRequest equifaxRequest =EquifaxRequest.create(centerId, clientId,
            			Long.parseLong(requestBody.getInquiryPurpose()), Long.parseLong(requestBody.getTransactionAmount()), requestBody.getFirstName(), requestBody.getAddrLine1(),
            			requestBody.getState(), requestBody.getPostal(), dateOfBirt, requestBody.getGender(), requestBody.getPANId(),
            			requestBody.getMobilePhone(), requestBody.getHomePhone());
                   	this.equifaxRequestRepositoryWrapper.save(equifaxRequest);
            }
           
           
           
          String  requestbodyInput = XML.toString(customerJSON,"ns:RequestBody");
          String footerxml="</ns:InquiryRequest>"+"</soapenv:Body>"+"</soapenv:Envelope>";
          
          System.out.println("xml Value is"+ xml1);
          System.out.println(headeInput);
          System.out.println(requestbodyInput);
          System.out.println(footerxml);

			}
			catch(Exception e){
				
			}
         
           
           
        
    	

		
  }
}
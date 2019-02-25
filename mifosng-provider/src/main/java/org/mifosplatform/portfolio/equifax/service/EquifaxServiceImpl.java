package org.mifosplatform.portfolio.equifax.service;



import java.util.Date;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
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
import org.springframework.beans.factory.annotation.Value;
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
        private final String CustomerId;
        private final String UserId;
        private final String Password;
        private final String MemberNumber;
        private final String SecurityCode;
        private final String ProductCode;
        private final String ProductVersion;
        private final String ReportFormat;
        private final String CustRefField;

      
        @Autowired
        public EquifaxServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource,
        		final EquifaxClientDetailsService equifaxClientDetailsService,final EquifaxRequestRepositoryWrapper equifaxRequestRepositoryWrapper,
        		final EquifaxResponseRepositoeyWrapper equifaxResponseRepositoeyWrapper,final EquifaxErrorRepositoryWrapper equifaxErrorRepositoryWrapper,
        		@Value("${CustomerId}") final String CustomerId,@Value("${UserId}") final String UserId,
        		@Value("${Password}") final String Password,@Value("${MemberNumber}") final String MemberNumber,
        		@Value("${SecurityCode}") final String SecurityCode,@Value("${ProductCode}") final String ProductCode,
        		@Value("${ReportFormat}") final String ReportFormat,@Value("${CustRefField}") final String CustRefField,
        		@Value("${ProductVersion}") final String ProductVersion) {                
            this.context = context;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
            this.equifaxClientDetailsService=equifaxClientDetailsService;
            this.equifaxRequestRepositoryWrapper=equifaxRequestRepositoryWrapper;
            this.equifaxResponseRepositoeyWrapper=equifaxResponseRepositoeyWrapper;
            this.equifaxErrorRepositoryWrapper=equifaxErrorRepositoryWrapper;
            this.CustomerId=CustomerId;
            this.UserId=UserId;
            this.Password=Password;
            this.MemberNumber=MemberNumber;
            this.SecurityCode=SecurityCode;
            this.ProductCode=ProductCode;
            this.ProductVersion=ProductVersion;
            this.ReportFormat=ReportFormat;
            this.CustRefField=CustRefField;
         }

		@Override
		public void getCreditBureauResult(Long clientId,Long centerId) {
			try{
			
			RequestBody requestBody =this.equifaxClientDetailsService.getRequestBody(clientId);			
			 
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
           
           if(requestBody.getFirstName()!=null){
        	   if(requestBody.getFirstName().length()>40){
        	   errsg =errsg+"First Name  length cannot be greater than 40 \n";
        	   isError=true;
        	   }
           }
           else{
        	   errsg =errsg+"First Name  Cannot be Null \n";
        	   isError=true;
           }           
           
           
           if(requestBody.getLastName()!=null){
        	   if(requestBody.getLastName().length()>40){
        	   errsg =errsg+"Last Name  length cannot be greater than 40 \n";
        	   isError=true;
        	   }
           }
           else{
        	   errsg =errsg+"Last Name  Cannot be Null \n";
        	   isError=true;   
           }
           
           if(requestBody.getState()!=null){
        	   if(requestBody.getState().length()>2){
            	   errsg =errsg+"State  length cannot be greater than 200 \n";
            	   isError=true;
               }
           }
           else{
        	   errsg =errsg+"State Line1 Cannot be Null \n";
        	   isError=true;   
           }
         
           if(requestBody.getPostal()!=null){
        	   if(requestBody.getPostal().length()>10){
            	   errsg =errsg+"Postal Line1 cannot be greater than 10 \n";
            	   isError=true;
               } 
           }
           else{
        	   errsg =errsg+"Postal Line1 Cannot be Null \n";
        	   isError=true; 
           }
           
           
           if(requestBody.getAddrLine1()!=null){
        	   if(requestBody.getAddrLine1().length()>200){
            	   errsg =errsg+"Address Line1  length cannot be greater than 200 \n";
            	   isError=true;
               }
           }
           else{
        	   errsg =errsg+"Address Line1 Cannot be Null \n";
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
           	EquifaxError equifaxError =EquifaxError.createEuifaxError(centerId, clientId, errsg,requestBody.getTransactionAmount(),requestBody.FirstName);
           	try{
           	this.equifaxErrorRepositoryWrapper.save(equifaxError);
           	}
           	catch(Exception e){
           		System.out.println(e.getMessage());
           	}
             }
           else{
        	   String xml1 ="<soapenv:Envelope xmlns:soapenv="+"\"http://schemas.xmlsoap.org/soap/envelope/\""+" xmlns:ns="+"\"http://services.equifax.com/eport/ws/schemas/1.0\""+">"
        				+" <soapenv:Header/>"
        				+" <soapenv:Body>"
        				+" <ns:InquiryRequest>";
        				
        	           JSONObject inputJSON = new JSONObject();
        	           inputJSON.append("ns:UserId", "UAT_NIRANT");
        	           inputJSON.append("ns:Password", "abcd*1234");
        	           inputJSON.append("ns:MemberNumber", "028FZ00016");
        	           inputJSON.append("ns:SecurityCode", "FR7");
        	           inputJSON.append("ns:ProductCode", "MCR");
        	           inputJSON.append("ns:CustomerId", "21");
        	           inputJSON.append("ns:ProductVersion", "1.0");
        	           inputJSON.append("ns:ReportFormat", "XML");
        	           //inputJSON.append("ns:CustRefField", "someValue2");
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
        	           
        	           String  requestbodyInput = XML.toString(customerJSON,"ns:RequestBody");
        	           String footerxml="</ns:InquiryRequest>"+"</soapenv:Body>"+"</soapenv:Envelope>";
        	           
        	           System.out.println("xml Value is"+ xml1);
        	           xml1=xml1+headeInput+requestbodyInput+footerxml;
        	           System.out.println(xml1);
        	          

           	DateTime dateOfBirt =new DateTime(requestBody.getDOB());
           	EquifaxRequest equifaxRequest =EquifaxRequest.create(centerId, clientId,
           			requestBody.getInquiryPurpose(),requestBody.getTransactionAmount(), requestBody.getFirstName(), requestBody.getAddrLine1(),
           			requestBody.getState(), requestBody.getPostal(), dateOfBirt.toDate(), requestBody.getGender(), requestBody.getPANId(),
           			requestBody.getMobilePhone(), requestBody.getHomePhone());
                  	this.equifaxRequestRepositoryWrapper.save(equifaxRequest);
           
          
          
		    //build httpclient
		    CloseableHttpClient httpclient = HttpClients.custom()
		            .build();
				
            // this is input request body. TO DO - Please replace this with actual input				    

		    	try {
		    						
		      
		      HttpPost post = new HttpPost("https://eportuat.equifax.co.in/creditreportws/CreditReportWSInquiry/v1.0?wsdl");
		      post.setEntity(new StringEntity(xml1));
		      
		      post.setHeader("Content-type", "application/xml");
		     // post.setHeader("Authorization", "Basic TkVYVFJVTElWRTpTcmluaXZhc0BuZXh0cnU=");
		      
		      // make post call and get response
		      CloseableHttpResponse response =httpclient.execute(post);
             
		      // Retrieve content  form response
		      HttpEntity entity = response.getEntity();
             String responsexml = EntityUtils.toString(entity);
             responsexml=responsexml.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
             System.out.println(responsexml);
		    }catch (Exception e){
		    	// TODO Auto-generated catch block
				e.printStackTrace();
		    } finally {
		           httpclient.close();
		   }
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			 

            /*if(isError){
            	EquifaxError equifaxError =EquifaxError.createEuifaxError(centerId, clientId, errsg);
            	try{
            	this.equifaxErrorRepositoryWrapper.save(equifaxError);
            	}
            	catch(Exception e){
            		System.out.println(e.getMessage());
            	}
              }
            else{*/
            	/*DateTime dateOfBirt =new DateTime(requestBody.getDOB());
            	EquifaxRequest equifaxRequest =EquifaxRequest.create(centerId, clientId,
            			Long.parseLong(requestBody.getInquiryPurpose()), Long.parseLong(requestBody.getTransactionAmount()), requestBody.getFirstName(), requestBody.getAddrLine1(),
            			requestBody.getState(), requestBody.getPostal(), dateOfBirt.toDate(), requestBody.getGender(), requestBody.getPANId(),
            			requestBody.getMobilePhone(), requestBody.getHomePhone());
                   	this.equifaxRequestRepositoryWrapper.save(equifaxRequest);
            //}
           
           
           
        
			}
			catch(Exception e){
				
			}*/
         
           
           
        
    	

		
  }
}
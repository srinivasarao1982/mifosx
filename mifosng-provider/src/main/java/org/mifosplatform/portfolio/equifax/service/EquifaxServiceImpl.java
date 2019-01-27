package org.mifosplatform.portfolio.equifax.service;



import org.json.JSONObject;
import org.json.XML;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.equifax.data.EquifaxHeaderData;
import org.mifosplatform.portfolio.equifax.data.InquiryRequest;
import org.mifosplatform.portfolio.equifax.data.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service

public class EquifaxServiceImpl  implements EquifaxService{
       
    	private final JdbcTemplate jdbcTemplate;
        private final PlatformSecurityContext context;
        private final EquifaxClientDetailsService equifaxClientDetailsService;
      
        @Autowired
        public EquifaxServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource,
        		final EquifaxClientDetailsService equifaxClientDetailsService) {                
            this.context = context;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
            this.equifaxClientDetailsService=equifaxClientDetailsService;
         }

		@Override
		public void getCreditBureauResult(Long clientId) {
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
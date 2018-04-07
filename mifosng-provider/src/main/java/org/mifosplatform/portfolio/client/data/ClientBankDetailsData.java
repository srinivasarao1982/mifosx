package org.mifosplatform.portfolio.client.data;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

public class ClientBankDetailsData {
	  
	    private final long id;  	    
	    private final String beneficiaryname;
	    private final String ifsccode;
	    private final String branchname;
	    private final String branchaddress;
	    private final LocalDate lasttransactiondate;
	    private final BigDecimal lasttransactionamount;
	    private final String accountnumber;
        private final String createdby;
        private final LocalDate createddate;
        private final String lastmodifyby;
        private final LocalDate lastmodifiedDate;
        private final long clientId;
        
    
        
        public static ClientBankDetailsData bankdetailsData(final long id,final long clientid,final String beneficiaryname,final String ifsccode,
        		final String branchname,final String branchaddress,final LocalDate lasttransactiondate,final BigDecimal lasttransactionamount,
        		final String accountnumber,final String createdby,final LocalDate createddate,final String lastmodifyby,
        		final LocalDate lastmodifiedDate){
        	
        	return new ClientBankDetailsData(id,clientid,beneficiaryname,ifsccode,branchname,branchaddress,lasttransactiondate,lasttransactionamount,
        			accountnumber,createdby,createddate,lastmodifyby,lastmodifiedDate);
        }
		public ClientBankDetailsData(Long id, long clientId ,String beneficiaryname, String ifsccode,
				String branchname, String branchaddress, LocalDate lasttransactiondate,
				BigDecimal lasttransactionamount, String accountnumber, String createdby, LocalDate createddate,
				String lastmodifyby, LocalDate lastmodifiedDate) {
			super();
			this.id = id;
			this.clientId=clientId;
			this.beneficiaryname = beneficiaryname;
			this.ifsccode = ifsccode;
			this.branchname = branchname;
			this.branchaddress = branchaddress;
			this.lasttransactiondate = lasttransactiondate;
			this.lasttransactionamount = lasttransactionamount;
			this.accountnumber = accountnumber;
			this.createdby = createdby;
			this.createddate = createddate;
			this.lastmodifyby = lastmodifyby;
			this.lastmodifiedDate = lastmodifiedDate;
		}
        
        
        
        
}

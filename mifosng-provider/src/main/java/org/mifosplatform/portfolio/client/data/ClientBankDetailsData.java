package org.mifosplatform.portfolio.client.data;

import java.math.BigDecimal;
import java.util.Collection;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;

public class ClientBankDetailsData {
	  
	    private final Long id;  	    
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
        private final Long clientId;
        private final String bankname;
        private final String micrcode;
        private final boolean isPrimaryAccount;
        private final Collection<CodeValueData> accountTypesList;
        private final CodeValueData accountType;
        
    
        
        public static ClientBankDetailsData bankdetailsData(final long id,final long clientid,final String beneficiaryname,final String ifsccode,
        		final String branchname,final String branchaddress,final LocalDate lasttransactiondate,final BigDecimal lasttransactionamount,
        		final String accountnumber,final String bankName,final String micrcode,final String createdby,final LocalDate createddate,final String lastmodifyby,
        		final LocalDate lastmodifiedDate, final boolean isPrimaryAccount, final Collection<CodeValueData> accountTypesList, final CodeValueData accountType){
        	
        	return new ClientBankDetailsData(id,clientid,beneficiaryname,ifsccode,branchname,branchaddress,lasttransactiondate,lasttransactionamount,
        			accountnumber,bankName,micrcode,createdby,createddate,lastmodifyby,lastmodifiedDate,isPrimaryAccount,accountTypesList,accountType);
        }
		public ClientBankDetailsData(Long id, Long clientId ,String beneficiaryname, String ifsccode,
				String branchname, String branchaddress, LocalDate lasttransactiondate,
				BigDecimal lasttransactionamount, String accountnumber,String bankName,String micrcode, String createdby, LocalDate createddate,
				String lastmodifyby, LocalDate lastmodifiedDate, boolean isPrimaryAccount, Collection<CodeValueData> accountTypesList, CodeValueData accountType) {
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
			this.bankname=bankName;
			this.micrcode=micrcode;
			this.createdby = createdby;
			this.createddate = createddate;
			this.lastmodifyby = lastmodifyby;
			this.lastmodifiedDate = lastmodifiedDate;
			this.isPrimaryAccount = isPrimaryAccount;
			this.accountTypesList = accountTypesList;
			this.accountType = accountType;
		}
        
		
		//Template data for create
		@SuppressWarnings("null")
		public static ClientBankDetailsData templateData(final Collection<CodeValueData> accountTypesList){
			 final Long id = null;  	    
			 final String beneficiaryname = null;
			 final String ifsccode = null;
			 final String branchname = null;
			 final String branchaddress = null;
			 final LocalDate lasttransactiondate = null;
			 final BigDecimal lasttransactionamount = null;
			 final String accountnumber = null;
			 final String bankname = null;
		     final String createdby = null;
		     final LocalDate createddate = null;
		     final String lastmodifyby = null;
		     final LocalDate lastmodifiedDate = null;
		     final Long clientId = null;
             final String micrcode = null;
             final boolean isPrimaryAccount = false;	
             final CodeValueData accountType = null;
		   
		 return new ClientBankDetailsData(id, clientId, beneficiaryname, ifsccode, branchname, branchaddress, lasttransactiondate, lasttransactionamount, 
				 accountnumber, bankname, micrcode, createdby, createddate, lastmodifyby, lastmodifiedDate, isPrimaryAccount, accountTypesList,accountType);
			
		}
        
		//Template data for edit
		public static ClientBankDetailsData templateData(final ClientBankDetailsData clientBankData, final Collection<CodeValueData> accountTypesList){
			
			return new ClientBankDetailsData(clientBankData.id,clientBankData.clientId, clientBankData.beneficiaryname, clientBankData.ifsccode, clientBankData.branchname, clientBankData.branchaddress, clientBankData.lasttransactiondate, clientBankData.lasttransactionamount, 
					clientBankData.accountnumber, clientBankData.bankname, clientBankData.micrcode, clientBankData.createdby, clientBankData.createddate, clientBankData.lastmodifyby, clientBankData.lastmodifiedDate, clientBankData.isPrimaryAccount, accountTypesList,clientBankData.accountType);
		}
		public String getAccountnumber() {
			return accountnumber;
		}
		public boolean isPrimaryAccount() {
			return isPrimaryAccount;
		}
		public Long getId() {
			return id;
		}
		
        
        
}

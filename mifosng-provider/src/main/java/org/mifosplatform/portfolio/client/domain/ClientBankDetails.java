package org.mifosplatform.portfolio.client.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.portfolio.client.api.ClientsBankDetailsApiConstants;
import org.mifosplatform.useradministration.domain.AppUser;
@SuppressWarnings("serial")
@Entity
@Table(name = "m_bankdetails", uniqueConstraints = { @UniqueConstraint(columnNames = { "account_no","ifsc_code" }, name = "account_no_UNIQUE")}) //
public class ClientBankDetails extends AbstractAuditableCustom<AppUser, Long>{
	  
	  

		@ManyToOne
	    @JoinColumn(name = "client_id", nullable = false)
	    private Client client;
	    
	    @Column(name = "beneficiary_name", length = 256)
	    private String beneficiaryname;
	    
	    @Column(name = "account_no", length = 256)
	    private String accountno;
	    
	    @Column(name = "lasttransaction_amount", length = 256)
	    private BigDecimal lasttransactionAmount;


	    @Column(name = "ifsc_code", length = 256)
	    private String ifsccode;
	    
	    @Column(name = "micr_code", length = 256)
	    private String micrcode;
	    
	    @Column(name = "bank_name", length = 256)
	    private String bankname;
	    
	    @Column(name = "branch_name", length = 256)
	    private String branchname;    
	    

	    @Column(name = "branch_address", length = 1000)
	    private String branchaddress;

	    @Column(name = "lasttransaction_date" )
	    private Date lasttransactiondate;
	    
	    
	   public static ClientBankDetails registerbankdetails(final Client client, final String  beneficiaryname, final String  accountno,final String bankName,final String micrCode,final BigDecimal lastTransactionAmount, final String  ifsccode,
                final String branchname, final String branchaddress,final JsonCommand command) {
	        final LocalDate lasttransactiondate = command.localDateValueOfParameterNamed(ClientsBankDetailsApiConstants.lasttransactiondateparamname);

		   return new ClientBankDetails(client,beneficiaryname,accountno,lastTransactionAmount,ifsccode,branchname,branchaddress,lasttransactiondate.toDate(),bankName,micrCode);
	   }
      
	   public Map<String, Object> update(final JsonCommand command) {

	        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);

	        if (command.isChangeInStringParameterNamed(ClientsBankDetailsApiConstants.beneficiarynameparamname, this.beneficiaryname)) {
	            final String newValue = command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.beneficiarynameparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.beneficiarynameparamname, newValue);
	            this.beneficiaryname=StringUtils.defaultIfEmpty(newValue, null);
	        }

	        if (command.isChangeInStringParameterNamed(ClientsBankDetailsApiConstants.accountnumberparamname, this.accountno)) {
	            final String newValue = command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.accountnumberparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.accountnumberparamname, newValue);
	            this.accountno = StringUtils.defaultIfEmpty(newValue, null);
	        }

	        if (command.isChangeInStringParameterNamed(ClientsBankDetailsApiConstants.ifsccodeparamname, this.ifsccode)) {
	            final String newValue = command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.ifsccodeparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.ifsccodeparamname, newValue);
	            this.ifsccode = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(ClientsBankDetailsApiConstants.micrcodeparamname, this.micrcode)) {
	            final String newValue = command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.micrcodeparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.micrcodeparamname, newValue);
	            this.micrcode = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(ClientsBankDetailsApiConstants.banknameparamname, this.bankname)) {
	            final String newValue = command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.banknameparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.banknameparamname, newValue);
	            this.bankname = StringUtils.defaultIfEmpty(newValue, null);
	        }

	        if (command.isChangeInStringParameterNamed(ClientsBankDetailsApiConstants.branchnameparamname, this.branchname)) {
	            final String newValue = command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.branchnameparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.branchnameparamname, newValue);
	            this.branchname = StringUtils.defaultIfEmpty(newValue, null);
	        }

	        if (command.isChangeInStringParameterNamed(ClientsBankDetailsApiConstants.branchaddressparamname, this.branchaddress)) {
	            final String newValue = command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.branchaddressparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.branchaddressparamname, newValue);
	            this.branchaddress = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInBigDecimalParameterNamed(ClientsBankDetailsApiConstants.lasttransactionamountparamname, this.lasttransactionAmount)) {
	            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(ClientsBankDetailsApiConstants.lasttransactionamountparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.lasttransactionamountparamname, newValue);
	            this.lasttransactionAmount =newValue ;
	        }


	        final String dateFormatAsInput = command.dateFormat();
	        final String localeAsInput = command.locale();

	        if (command.isChangeInLocalDateParameterNamed(ClientsBankDetailsApiConstants.lasttransactiondateparamname, getLastTransactionOnDate())) {
	            final String valueAsInput = command.stringValueOfParameterNamed(ClientsBankDetailsApiConstants.lasttransactiondateparamname);
	            actualChanges.put(ClientsBankDetailsApiConstants.lasttransactiondateparamname, valueAsInput);
	            actualChanges.put(ClientsBankDetailsApiConstants.dateFormatParamName, dateFormatAsInput);
	            actualChanges.put(ClientsBankDetailsApiConstants.localeParamName, localeAsInput);

	            final LocalDate newValue = command.localDateValueOfParameterNamed(ClientsBankDetailsApiConstants.lasttransactiondateparamname);
	            this.lasttransactiondate = newValue.toDate();
	        }

	       return actualChanges;
	    }
	   public LocalDate getLastTransactionOnDate() {
	        return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.lasttransactiondate), null);
	    }

		public ClientBankDetails(Client client, String beneficiaryname, String accountno,BigDecimal lasttransactionAmount, String ifsccode,
				String branchname, String branchaddress, Date lasttransactiondate,String bankName,String micrCode) {
			super();
			this.client = client;
			this.beneficiaryname = beneficiaryname;
			this.accountno = accountno;
			this.lasttransactionAmount=lasttransactionAmount;
			this.ifsccode = ifsccode;
			this.branchname = branchname;
			this.branchaddress = branchaddress;
			this.lasttransactiondate = lasttransactiondate;
			this.bankname=bankName;
			this.micrcode=micrCode;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public String getBeneficiaryname() {
			return beneficiaryname;
		}

		public void setBeneficiaryname(String beneficiaryname) {
			this.beneficiaryname = beneficiaryname;
		}

		public String getAccountno() {
			return accountno;
		}

		public void setAccountno(String accountno) {
			this.accountno = accountno;
		}

		public String getIfsccode() {
			return ifsccode;
		}

		public void setIfsccode(String ifsccode) {
			this.ifsccode = ifsccode;
		}

		public String getBranchname() {
			return branchname;
		}

		public void setBranchname(String branchname) {
			this.branchname = branchname;
		}

		public String getBranchaddress() {
			return branchaddress;
		}

		public void setBranchaddress(String branchaddress) {
			this.branchaddress = branchaddress;
		}

		public Date getLasttransactiondate() {
			return lasttransactiondate;
		}

		public void setLasttransactiondate(Date lasttransactiondate) {
			this.lasttransactiondate = lasttransactiondate;
		}
		
		  public BigDecimal getLasttransactionAmount() {
			return lasttransactionAmount;
		}

		public void setLasttransactionAmount(BigDecimal lasttransactionAmount) {
			this.lasttransactionAmount = lasttransactionAmount;
		}

		public String getMicrcode() {
			return micrcode;
		}

		public void setMicrcode(String micrcode) {
			this.micrcode = micrcode;
		}

		public String getBankname() {
			return bankname;
		}

		public void setBankname(String bankname) {
			this.bankname = bankname;
		}

		public ClientBankDetails() {
				super();
			}
	   


}

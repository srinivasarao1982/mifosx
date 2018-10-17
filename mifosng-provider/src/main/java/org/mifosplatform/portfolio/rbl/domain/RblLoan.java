package org.mifosplatform.portfolio.rbl.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.portfolio.rbl.api.RblLoanDetailsApiConstant;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_rblloan") 
public class RblLoan extends AbstractAuditableCustom<AppUser, Long>{	
	
	@Column (name ="loan_id")
	private Long loanId; 

	@Column(name = "psl_code", length = 8)
    private Integer pslcode;
	
	@Column(name = "to_Up_flag", length = 8)
    private Integer topUpflag;
	
	 @Column(name = "hosiptal_cash", length = 256)
	 private String hosiptalCash;
	 
	 @Column(name = "prepaid_charge", length = 256)
	 private String prpaidcharge;
	 
	 public static RblLoan craete(Long loanId,Integer pslcode, Integer topUpflag, String hosiptalCash, String prpaidcharge){
		 return new RblLoan(loanId,pslcode,topUpflag,hosiptalCash,prpaidcharge);
	 }
	 
	 
	 public RblLoan() {
		super();
	}


	public Map<String, Object> update(final JsonCommand command) {
			
	        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);
	       	        
	        if (command.isChangeInIntegerParameterNamed(RblLoanDetailsApiConstant.pslcodeparamName, this.pslcode)) {
	            final Integer newValue = command.integerValueOfParameterNamed(RblLoanDetailsApiConstant.pslcodeparamName);
	            actualChanges.put(RblLoanDetailsApiConstant.pslcodeparamName, newValue);
	            this.pslcode=newValue;
	        }

	        if (command.isChangeInIntegerParameterNamed(RblLoanDetailsApiConstant.topuploanfl, this.topUpflag)) {
	            final Integer newValue = command.integerValueOfParameterNamed(RblLoanDetailsApiConstant.topuploanfl);
	            actualChanges.put(RblLoanDetailsApiConstant.topuploanfl, newValue);
	            this.topUpflag=newValue;
	        }
	        		        
	        if (command.isChangeInStringParameterNamed(RblLoanDetailsApiConstant.hospitalcashparamname, this.hosiptalCash)) {
	            final String newValue = command.stringValueOfParameterNamed(RblLoanDetailsApiConstant.hospitalcashparamname);
	            actualChanges.put(RblLoanDetailsApiConstant.hospitalcashparamname, newValue);
	            this.hosiptalCash = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        
	        if (command.isChangeInStringParameterNamed(RblLoanDetailsApiConstant.prepaidchargeparamname, this.prpaidcharge)) {
	            final String newValue = command.stringValueOfParameterNamed(RblLoanDetailsApiConstant.prepaidchargeparamname);
	            actualChanges.put(RblLoanDetailsApiConstant.prepaidchargeparamname, newValue);
	            this.prpaidcharge = StringUtils.defaultIfEmpty(newValue, null);
	        }
	        	       
	       return actualChanges;
	    }
	 
	public RblLoan(Long loanId,Integer pslcode, Integer topUpflag, String hosiptalCash, String prpaidcharge) {
		super();
		this.loanId=loanId;
		this.pslcode = pslcode;
		this.topUpflag = topUpflag;
		this.hosiptalCash = hosiptalCash;
		this.prpaidcharge = prpaidcharge;		
	}


	public Integer getPslcode() {
		return pslcode;
	}


	public void setPslcode(Integer pslcode) {
		this.pslcode = pslcode;
	}


	public Integer getTopUpflag() {
		return topUpflag;
	}


	public void setTopUpflag(Integer topUpflag) {
		this.topUpflag = topUpflag;
	}


	public String getHosiptalCash() {
		return hosiptalCash;
	}


	public void setHosiptalCash(String hosiptalCash) {
		this.hosiptalCash = hosiptalCash;
	}


	public String getPrpaidcharge() {
		return prpaidcharge;
	}


	public void setPrpaidcharge(String prpaidcharge) {
		this.prpaidcharge = prpaidcharge;
	}

		 

}

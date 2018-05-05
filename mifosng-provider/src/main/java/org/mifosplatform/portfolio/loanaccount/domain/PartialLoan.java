package org.mifosplatform.portfolio.loanaccount.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.organisation.office.domain.Office;
import org.mifosplatform.organisation.staff.domain.Staff;
import org.mifosplatform.portfolio.client.api.ClientsBankDetailsApiConstants;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientBankDetails;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.loanaccount.api.PartialLoanApiConstant;
import org.mifosplatform.portfolio.loanproduct.domain.LoanProduct;
import org.mifosplatform.useradministration.domain.AppUser;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_partial_loan") 
public class PartialLoan  extends AbstractAuditableCustom<AppUser, Long>{
	
	@ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)
    private Group group;
    
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
    private LoanProduct product;
    
	@ManyToOne
	@JoinColumn(name = "office_id", nullable = false)
    private Office office;
	
	@ManyToOne
	@JoinColumn(name = "loanofficer_id", nullable = false)
    private Staff staff;
    
	@ManyToOne
	@JoinColumn(name = "loanpurpose_cv_id")
    private CodeValue loanpurpose;

    @Column(name = "rpdo_no", length = 256)
    private String rpdonumber;
    
    @Column(name = "loan_amount", nullable =false)
    private BigDecimal loanAmount;
    
    @Column(name = "loan_tenure", nullable =false)
    private long loantenure;
    
    @Column(name = "fixed_emi", nullable =false)
    private BigDecimal fixedemi;  

    @Column(name = "submitted_date" )
    private Date submittedDate;
    
    @Column(name = "cbReportReceive_date" )
    private Date cbReportReceivedate;
    
    @ManyToOne
    @JoinColumn(name = "status" )
    private CodeValue status;
    
    @Column(name = "remark", length = 256)
    private String remark;
    
    @Column(name = "is_active", length = 256)
    private int isActive;
    
      public static PartialLoan createpartialloan(final Client client, final Group group, final LoanProduct product,
            final Office office,final Staff staff,final CodeValue loanpurpose, final String rpdonumber,
            final  BigDecimal loanAmount, final long loantenure,final BigDecimal fixedemi,final  Date submittedDate,
            final CodeValue status,final String remark,final int isActive) {
    	  Date cbReportReceivedate=null;
	   return new PartialLoan(client,group,product,office,staff,loanpurpose,rpdonumber,loanAmount,
			   loantenure,fixedemi,submittedDate,status,remark,isActive,cbReportReceivedate);
   }
  
   public PartialLoan() {
		super();
	}

public Map<String, Object> update(final JsonCommand command) {

        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);

        /*if (command.isChangeInLongParameterNamed(PartialLoanApiConstant.loanofficeridparamname, this.staff.getId())) {
            final Long newValue = command.longValueOfParameterNamed(PartialLoanApiConstant.loanofficeridparamname);
            actualChanges.put(PartialLoanApiConstant.loanofficeridparamname, newValue);
        }
        if (command.isChangeInLongParameterNamed(PartialLoanApiConstant.productidparamname, this.product.getId())) {
            final Long newValue = command.longValueOfParameterNamed(PartialLoanApiConstant.productidparamname);
            actualChanges.put(PartialLoanApiConstant.productidparamname, newValue);
        }
        
        if (command.isChangeInLongParameterNamed(PartialLoanApiConstant.loanpurposeparamname, this.loanpurpose.getId())) {
            final Long newValue = command.longValueOfParameterNamed(PartialLoanApiConstant.loanpurposeparamname);
            actualChanges.put(PartialLoanApiConstant.loanpurposeparamname, newValue);
        }*/

        if (command.isChangeInStringParameterNamed(PartialLoanApiConstant.rpdonumberparamname, this.rpdonumber)) {
            final String newValue = command.stringValueOfParameterNamed(PartialLoanApiConstant.rpdonumberparamname);
            actualChanges.put(PartialLoanApiConstant.rpdonumberparamname, newValue);
            this.rpdonumber = StringUtils.defaultIfEmpty(newValue, null);
        }
        
       /* if (command.isChangeInBigDecimalParameterNamed(PartialLoanApiConstant.principalparamname, this.loanAmount)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(PartialLoanApiConstant.principalparamname);
            actualChanges.put(PartialLoanApiConstant.principalparamname, newValue);
            this.loanAmount =newValue; 
        }
        if (command.isChangeInBigDecimalParameterNamed(PartialLoanApiConstant.fixedemiAmountparamname, this.fixedemi)) {
            final BigDecimal newValue = command.bigDecimalValueOfParameterNamed(PartialLoanApiConstant.fixedemiAmountparamname);
            actualChanges.put(PartialLoanApiConstant.fixedemiAmountparamname, newValue);
            this.fixedemi =newValue; 
        }*/
        if (command.isChangeInLongParameterNamed(PartialLoanApiConstant.statusparamname,getstatus() )) {
            final Long newValue = command.longValueOfParameterNamed(PartialLoanApiConstant.statusparamname);
            actualChanges.put(PartialLoanApiConstant.statusparamname, newValue);
        }
        
        if (command.isChangeInStringParameterNamed(PartialLoanApiConstant.remarkparamname, this.remark)) {
            final String newValue = command.stringValueOfParameterNamed(PartialLoanApiConstant.remarkparamname);
            actualChanges.put(PartialLoanApiConstant.remarkparamname, newValue);
            this.remark = StringUtils.defaultIfEmpty(newValue, null);
        }

       
        
       
        final String dateFormatAsInput = command.dateFormat();
        final String localeAsInput = command.locale();

        if (command.isChangeInLocalDateParameterNamed(PartialLoanApiConstant.cbreceiveddateparamname, getLastTransactionOnDate())) {
            final String valueAsInput = command.stringValueOfParameterNamed(PartialLoanApiConstant.cbreceiveddateparamname);
            actualChanges.put(PartialLoanApiConstant.cbreceiveddateparamname, valueAsInput);
            actualChanges.put(PartialLoanApiConstant.cbreceiveddateparamname, dateFormatAsInput);
            actualChanges.put(PartialLoanApiConstant.localeParamName, localeAsInput);

            final LocalDate newValue = command.localDateValueOfParameterNamed(PartialLoanApiConstant.cbreceiveddateparamname);
            this.cbReportReceivedate = newValue.toDate();
        }

       return actualChanges;
    }
   public LocalDate getLastTransactionOnDate() {
        return (LocalDate) ObjectUtils.defaultIfNull(new LocalDate(this.cbReportReceivedate), null);
    }

  public Long getstatus(){
	  Long statusId=(long) 0;
	  if(this.status!=null){
		  statusId=this.status.getId();
	  }
	  return statusId;
  }

	public PartialLoan(Client client, Group group, LoanProduct product, Office office, Staff staff,
			CodeValue loanpurpose, String rpdonumber, BigDecimal loanAmount, long loantenure, BigDecimal fixedemi,
			Date submittedDate, CodeValue status, String remark, int isActive,Date cbReportReceivedate) {
		super();
		this.client = client;
		this.group = group;
		this.product = product;
		this.office = office;
		this.staff = staff;
		this.loanpurpose = loanpurpose;
		this.rpdonumber = rpdonumber;
		this.loanAmount = loanAmount;
		this.loantenure = loantenure;
		this.fixedemi = fixedemi;
		this.submittedDate = submittedDate;
		this.status = status;
		this.remark = remark;
		this.isActive = isActive;
		this.cbReportReceivedate=cbReportReceivedate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public LoanProduct getProduct() {
		return product;
	}

	public void setProduct(LoanProduct product) {
		this.product = product;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public CodeValue getLoanpurpose() {
		return loanpurpose;
	}

	public void setLoanpurpose(CodeValue loanpurpose) {
		this.loanpurpose = loanpurpose;
	}

	public String getRpdonumber() {
		return rpdonumber;
	}

	public void setRpdonumber(String rpdonumber) {
		this.rpdonumber = rpdonumber;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public long getLoantenure() {
		return loantenure;
	}

	public void setLoantenure(long loantenure) {
		this.loantenure = loantenure;
	}

	public BigDecimal getFixedemi() {
		return fixedemi;
	}

	public void setFixedemi(BigDecimal fixedemi) {
		this.fixedemi = fixedemi;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public CodeValue getStatus() {
		return status;
	}

	public void setStatus(CodeValue status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int isActive() {
		return isActive;
	}

	public void setActive(int isActive) {
		this.isActive = isActive;
	}

	public void updateStaff(final Staff staff) {
        this.staff=staff;
    }
   public void updatestatus(final CodeValue status){
	   this.status=status;
   }
   public void updateLoanPurpose (final CodeValue purpose){
	   this.loanpurpose=purpose;
   }
   public void updateisActive(int isActive){
	   this.isActive=isActive;
   }
}

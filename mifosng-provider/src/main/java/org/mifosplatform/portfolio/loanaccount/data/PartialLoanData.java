package org.mifosplatform.portfolio.loanaccount.data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;

public class PartialLoanData {
	
	private final Long clientId;
	private final String clientName;
	private final String rpdoNumber;
	private final BigDecimal loanAmount;
	private final LocalDate submittedDate;
	private final String StatusData;	
	private final String remark;
	private final String isActive;
	
    private final Collection<CodeValueData> status;
    private final List<Long>acceptedclientsId;
    
    public static PartialLoanData template(final Collection<CodeValueData> status){    	 
    	return new PartialLoanData(null,null,null,null,null,null,null,null,status,null);
    }
    public static PartialLoanData getAcceptedclientsId(final Collection<CodeValueData> status,List<Long>acceptedclientsId){    	 
    	return new PartialLoanData(null,null,null,null,null,null,null,null,status,acceptedclientsId);
    }
    
    public static PartialLoanData getPartialLoanData(Long clientId, String clientName, String rpdoNumber, BigDecimal loanAmount,
			LocalDate submittedDate, String statusData, String remark, String isActive,
			Collection<CodeValueData> status ){
    	return new PartialLoanData(clientId,clientName,rpdoNumber,loanAmount,submittedDate,statusData,remark,isActive,null,null);
    }

	public PartialLoanData(Long clientId, String clientName, String rpdoNumber, BigDecimal loanAmount,
			LocalDate submittedDate, String statusData, String remark, String isActive,
			Collection<CodeValueData> status,List<Long>acceptedclientsId) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.rpdoNumber = rpdoNumber;
		this.loanAmount = loanAmount;
		this.submittedDate = submittedDate;
		this.StatusData = statusData;
		this.remark = remark;
		this.isActive = isActive;
		this.status = status;
		this.acceptedclientsId=acceptedclientsId;
	}

	public Long getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public String getRpdoNumber() {
		return rpdoNumber;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public LocalDate getSubmittedDate() {
		return submittedDate;
	}

	public String getStatusData() {
		return StatusData;
	}

	public String getRemark() {
		return remark;
	}

	public String getIsActive() {
		return isActive;
	}

	public Collection<CodeValueData> getStatus() {
		return status;
	}
	public List<Long> getAcceptedclientsId() {
		return acceptedclientsId;
	}

	

}

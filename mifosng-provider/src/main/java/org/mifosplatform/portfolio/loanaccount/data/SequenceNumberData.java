package org.mifosplatform.portfolio.loanaccount.data;

import java.math.BigDecimal;

public class SequenceNumberData {

	private final Long clientId;
	private BigDecimal SequenceNumber;
	private final Long loanPurposeId;
	
	
	
	public static SequenceNumberData createsequenceNumber(Long clientId,BigDecimal sequenceNumber,Long loanPurposeId){
		return new SequenceNumberData(clientId,sequenceNumber,loanPurposeId);
	}
	
	public SequenceNumberData(Long clientId, BigDecimal sequenceNumber,Long loanPurposeId) {
		super();
		this.clientId = clientId;
		SequenceNumber = sequenceNumber;
		this.loanPurposeId=loanPurposeId;
	}
	
	public BigDecimal getSequenceNumber() {
		return SequenceNumber;
	}
	public void setSequenceNumber(BigDecimal sequenceNumber) {
		SequenceNumber = sequenceNumber;
	}
	public Long getClientId() {
		return clientId;
	}

	public Long getLoanPurposeId() {
		return loanPurposeId;
	}
	
	
	
}

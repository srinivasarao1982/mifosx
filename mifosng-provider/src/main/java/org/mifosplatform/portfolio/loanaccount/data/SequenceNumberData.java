package org.mifosplatform.portfolio.loanaccount.data;

import java.math.BigDecimal;

public class SequenceNumberData {

	private final Long clientId;
	private BigDecimal SequenceNumber;
	
	
	
	public static SequenceNumberData createsequenceNumber(Long clientId,BigDecimal sequenceNumber){
		return new SequenceNumberData(clientId,sequenceNumber);
	}
	
	public SequenceNumberData(Long clientId, BigDecimal sequenceNumber) {
		super();
		this.clientId = clientId;
		SequenceNumber = sequenceNumber;
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
	
	
	
}

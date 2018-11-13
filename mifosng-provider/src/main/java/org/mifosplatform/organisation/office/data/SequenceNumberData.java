package org.mifosplatform.organisation.office.data;

import java.math.BigDecimal;

public class SequenceNumberData {
	private final Long id;
	private final Long entityId;
	private final BigDecimal sequenceNo;

	public SequenceNumberData createSequence(Long id, Long entityId, BigDecimal seqNumber) {
		return new SequenceNumberData(id, entityId, seqNumber);
	}

	public SequenceNumberData(Long id, Long entityId, BigDecimal sequenceNo) {
		super();
		this.id = id;
		this.entityId = entityId;
		this.sequenceNo = sequenceNo;
	}

	public Long getId() {
		return id;
	}

	public Long getEntityId() {
		return entityId;
	}

	public BigDecimal getSequenceNo() {
		return sequenceNo;
	}

}

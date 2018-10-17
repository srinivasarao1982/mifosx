package org.mifosplatform.organisation.office.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_sequence_Number")
public class OrganasitionSequenceNumber extends AbstractPersistable<Long> {
	
	@Column(name = "entity_type" )
    private Integer entityType;
	
	@Column(name = "seq_Number", scale = 6, precision = 19)
    private BigDecimal seqNumber;

	
	
	public OrganasitionSequenceNumber(Integer entityType, BigDecimal seqNumber) {
		super();
		this.entityType = entityType;
		this.seqNumber = seqNumber;
	}
	
	public OrganasitionSequenceNumber() {
		super();
	}

	public void updateSeqNumber(BigDecimal seqNumber ){
		this.seqNumber=seqNumber;
		
	}
	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public BigDecimal getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(BigDecimal seqNumber) {
		this.seqNumber = seqNumber;
	}



}

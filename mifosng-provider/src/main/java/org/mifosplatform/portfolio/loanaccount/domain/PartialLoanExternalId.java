package org.mifosplatform.portfolio.loanaccount.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_partialLoanExtId")
public class PartialLoanExternalId extends AbstractPersistable<Long> {

  @ManyToOne
  @JoinColumn(name = "partial_loan_id", nullable = true)
  private PartialLoan partialLoan;

  public PartialLoanExternalId(PartialLoan partialLoan) {
    super();
    this.partialLoan = partialLoan;
  }

  public PartialLoan getPartialLoan() {
    return partialLoan;
  }

  public void setPartialLoan(PartialLoan partialLoan) {
    this.partialLoan = partialLoan;
  }
}

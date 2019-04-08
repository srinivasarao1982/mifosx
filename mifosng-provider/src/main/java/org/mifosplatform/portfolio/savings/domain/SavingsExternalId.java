package org.mifosplatform.portfolio.savings.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "m_savingsAccountExtId")
public class SavingsExternalId extends AbstractPersistable<Long> {

  @ManyToOne
  @JoinColumn(name = "saving_account_id", nullable = true)
  private SavingsAccount savingAccount;

  public SavingsExternalId(SavingsAccount savingAccount) {
    super();
    this.savingAccount = savingAccount;
  }

  public SavingsAccount getSavingAccount() {
    return savingAccount;
  }

  public void setSavingAccount(SavingsAccount savingAccount) {
    this.savingAccount = savingAccount;
  }
}

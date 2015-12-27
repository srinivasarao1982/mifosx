package org.nirantara.client.ext.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.mifosplatform.portfolio.loanaccount.domain.Loan;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "n_loan_ext")
public class LoanExt extends AbstractPersistable<Long> {
	@OneToOne
	@JoinColumn(name = "loan_id", nullable = false)
	private Loan loan;

	@Column(name = "loanApplication_Id", nullable = false)
	private String loanApplicationId;

	public LoanExt(Loan loan, String loanApplicationId) {
		super();
		this.loan = loan;
		this.loanApplicationId = loanApplicationId;
	}

	public LoanExt() {
		super();
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	

	public String getLoanApplicationId() {
		return loanApplicationId;
	}

	public void setLoanApplicationId(String loanApplicationId) {
		this.loanApplicationId = loanApplicationId;
	}

	public static LoanExt createTemparyId(final Loan loan,
			final String loanApplicationId) {

		return new LoanExt(loan, loanApplicationId);

	}

}

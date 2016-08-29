package org.mifosplatform.portfolio.loanaccount.data;

import org.joda.time.LocalDate;

public class ScheduleGeneratorData {

	private final LocalDate firstRepaymentDate;
	private final LocalDate newfirstRepaymentDate;
	private final Long loanId;

	public LocalDate getFirstRepaymentDate() {
		return firstRepaymentDate;
	}

	public LocalDate getNewfirstRepaymentDate() {
		return newfirstRepaymentDate;
	}

	public Long getLoanId() {
		return loanId;
	}

	public ScheduleGeneratorData(LocalDate firstRepaymentDate,
			LocalDate newfirstRepaymentDate, Long loanId) {
		super();
		this.firstRepaymentDate = firstRepaymentDate;
		this.newfirstRepaymentDate = newfirstRepaymentDate;
		this.loanId = loanId;
	}

	public static ScheduleGeneratorData correctScheduleGeneratedData(
			final Long loanId, final LocalDate firstRepaymentDate,
			final LocalDate newfirstRepaymentDate) {
		return new ScheduleGeneratorData(firstRepaymentDate,
				newfirstRepaymentDate, loanId);
	}
}

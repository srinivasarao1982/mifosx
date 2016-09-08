package org.mifosplatform.portfolio.loanaccount.data;

import java.util.Map;

import org.mifosplatform.portfolio.loanaccount.domain.LoanTransaction;

public class AdjustedLoanTransactionDetails {

    private final Map<String, Object> changes;
    private final LoanTransaction transactionToAdjust;
    private final LoanTransaction newTransactionDetail;

    public AdjustedLoanTransactionDetails(final Map<String, Object> changes, final LoanTransaction transactionToAdjust,
            final LoanTransaction newTransactionDetail) {
        this.changes = changes;
        this.transactionToAdjust = transactionToAdjust;
        this.newTransactionDetail = newTransactionDetail;
    }

    public Map<String, Object> getChanges() {
        return this.changes;
    }

    public LoanTransaction getTransactionToAdjust() {
        return this.transactionToAdjust;
    }

    public LoanTransaction getNewTransactionDetail() {
        return this.newTransactionDetail;
    }

}

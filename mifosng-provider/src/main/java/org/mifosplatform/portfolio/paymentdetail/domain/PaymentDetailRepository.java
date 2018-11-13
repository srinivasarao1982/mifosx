/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.paymentdetail.domain;

import java.util.Date;
import java.util.List;

import org.mifosplatform.portfolio.loanaccount.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long>, JpaSpecificationExecutor<Loan> {
    // no added behaviour
	
	@Query("from PaymentDetail payment where payment.receiptNumber = :receiptNumber")
    List<PaymentDetail> getPaymentDetails(@Param("receiptNumber") String receiptNumber);

}
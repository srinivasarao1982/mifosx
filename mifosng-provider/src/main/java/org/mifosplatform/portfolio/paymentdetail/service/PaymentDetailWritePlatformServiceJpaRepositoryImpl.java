/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.paymentdetail.service;

import java.util.List;
import java.util.Map;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.portfolio.paymentdetail.PaymentDetailConstants;
import org.mifosplatform.portfolio.paymentdetail.domain.PaymentDetail;
import org.mifosplatform.portfolio.paymentdetail.domain.PaymentDetailRepository;
import org.mifosplatform.portfolio.paymentdetail.exception.DuplicateReceiptNumberException;
import org.mifosplatform.portfolio.paymentdetail.exception.ReceiptNumberMandatoryException;
import org.mifosplatform.portfolio.paymentdetail.exception.ReceiptNumberMustBeNumericException;
import org.mifosplatform.portfolio.paymenttype.domain.PaymentType;
import org.mifosplatform.portfolio.paymenttype.domain.PaymentTypeRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentDetailWritePlatformServiceJpaRepositoryImpl implements PaymentDetailWritePlatformService {

    private final PaymentDetailRepository paymentDetailRepository;
    // private final CodeValueRepositoryWrapper codeValueRepositoryWrapper;
    private final PaymentTypeRepositoryWrapper paymentTyperepositoryWrapper;

    @Autowired
    public PaymentDetailWritePlatformServiceJpaRepositoryImpl(final PaymentDetailRepository paymentDetailRepository,
            final PaymentTypeRepositoryWrapper paymentTyperepositoryWrapper) {
        this.paymentDetailRepository = paymentDetailRepository;
        this.paymentTyperepositoryWrapper = paymentTyperepositoryWrapper;
    }

    @Override
    public PaymentDetail createPaymentDetail(final JsonCommand command, final Map<String, Object> changes) {
        final Long paymentTypeId = command.longValueOfParameterNamed(PaymentDetailConstants.paymentTypeParamName);
        if (paymentTypeId == null) { return null; }
        
        final String receiptNumber =command.stringValueOfParameterNamed(PaymentDetailConstants.receiptNumberParamName);
        if(receiptNumber!=null && receiptNumber!=""){
        if (!(receiptNumber.matches("[0-9]+"))) {
        	throw  new ReceiptNumberMustBeNumericException(receiptNumber);
          }
        }
        else{
        	throw  new ReceiptNumberMandatoryException(receiptNumber);
        }
        List<PaymentDetail>paymentdetails=this.paymentDetailRepository.getPaymentDetails(receiptNumber);
        if(paymentdetails.size()>0){
        	throw new DuplicateReceiptNumberException(receiptNumber);
        }
        final PaymentType paymentType = this.paymentTyperepositoryWrapper.findOneWithNotFoundDetection(paymentTypeId);
        final PaymentDetail paymentDetail = PaymentDetail.generatePaymentDetail(paymentType, command, changes);
        return paymentDetail;

    }

    @Override
    @Transactional
    public PaymentDetail persistPaymentDetail(final PaymentDetail paymentDetail) {
        return this.paymentDetailRepository.save(paymentDetail);
    }

    @Override
    @Transactional
    public PaymentDetail createAndPersistPaymentDetail(final JsonCommand command, final Map<String, Object> changes) {
        final PaymentDetail paymentDetail = createPaymentDetail(command, changes);
        if (paymentDetail != null) { return persistPaymentDetail(paymentDetail); }
        return paymentDetail;
    }
}
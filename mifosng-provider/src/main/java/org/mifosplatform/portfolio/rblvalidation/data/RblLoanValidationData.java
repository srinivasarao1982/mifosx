package org.mifosplatform.portfolio.rblvalidation.data;

import java.util.Date;

public class RblLoanValidationData {
	

		private String externalId;
		private String customerExternalId;
		private String centerExtrenalId;
		private String groupExternalId;
		private String loanProductCode;
		private Integer loanPurpose;
		private Integer pslcode;
		private Long loanAmount;
		private String disbursementMode;
		private Integer noofInstallments;
		private Integer repaymentfrequency;
		private Integer loanCycle;
		private String barcodeNo;
		private Date loanStratDate;
		private Date repaymentStartDate;
		private String bcBranchCode;
		private String colector;
		private String approver;
		private Date ExceptedDisbursementDate;
		private Integer TopUpLoanFlag;
		private String hosiptalCash;
		private String prepaidCharge;
		public String getExternalId() {
			return externalId;
		}
		public void setExternalId(String externalId) {
			this.externalId = externalId;
		}
		public String getCustomerExternalId() {
			return customerExternalId;
		}
		public void setCustomerExternalId(String customerExternalId) {
			this.customerExternalId = customerExternalId;
		}
		public String getCenterExtrenalId() {
			return centerExtrenalId;
		}
		public void setCenterExtrenalId(String centerExtrenalId) {
			this.centerExtrenalId = centerExtrenalId;
		}
		public String getGroupExternalId() {
			return groupExternalId;
		}
		public void setGroupExternalId(String groupExternalId) {
			this.groupExternalId = groupExternalId;
		}
		public String getLoanProductCode() {
			return loanProductCode;
		}
		public void setLoanProductCode(String loanProductCode) {
			this.loanProductCode = loanProductCode;
		}
		public Integer getLoanPurpose() {
			return loanPurpose;
		}
		public void setLoanPurpose(Integer loanPurpose) {
			this.loanPurpose = loanPurpose;
		}
		public Integer getPslcode() {
			return pslcode;
		}
		public void setPslcode(Integer pslcode) {
			this.pslcode = pslcode;
		}
		public Long getLoanAmount() {
			return loanAmount;
		}
		public void setLoanAmount(Long loanAmount) {
			this.loanAmount = loanAmount;
		}
		public String getDisbursementMode() {
			return disbursementMode;
		}
		public void setDisbursementMode(String disbursementMode) {
			this.disbursementMode = disbursementMode;
		}
		public Integer getNoofInstallments() {
			return noofInstallments;
		}
		public void setNoofInstallments(Integer noofInstallments) {
			this.noofInstallments = noofInstallments;
		}
		public Integer getRepaymentfrequency() {
			return repaymentfrequency;
		}
		public void setRepaymentfrequency(Integer repaymentfrequency) {
			this.repaymentfrequency = repaymentfrequency;
		}
		public Integer getLoanCycle() {
			return loanCycle;
		}
		public void setLoanCycle(Integer loanCycle) {
			this.loanCycle = loanCycle;
		}
		public String getBarcodeNo() {
			return barcodeNo;
		}
		public void setBarcodeNo(String barcodeNo) {
			this.barcodeNo = barcodeNo;
		}
		public Date getLoanStratDate() {
			return loanStratDate;
		}
		public void setLoanStratDate(Date loanStratDate) {
			this.loanStratDate = loanStratDate;
		}
		public Date getRepaymentStartDate() {
			return repaymentStartDate;
		}
		public void setRepaymentStartDate(Date repaymentStartDate) {
			this.repaymentStartDate = repaymentStartDate;
		}
		public String getBcBranchCode() {
			return bcBranchCode;
		}
		public void setBcBranchCode(String bcBranchCode) {
			this.bcBranchCode = bcBranchCode;
		}
		public String getColector() {
			return colector;
		}
		public void setColector(String colector) {
			this.colector = colector;
		}
		public String getApprover() {
			return approver;
		}
		public void setApprover(String approver) {
			this.approver = approver;
		}
		public Date getExceptedDisbursementDate() {
			return ExceptedDisbursementDate;
		}
		public void setExceptedDisbursementDate(Date exceptedDisbursementDate) {
			ExceptedDisbursementDate = exceptedDisbursementDate;
		}
		public Integer getTopUpLoanFlag() {
			return TopUpLoanFlag;
		}
		public void setTopUpLoanFlag(Integer topUpLoanFlag) {
			TopUpLoanFlag = topUpLoanFlag;
		}
		public String getHosiptalCash() {
			return hosiptalCash;
		}
		public void setHosiptalCash(String hosiptalCash) {
			this.hosiptalCash = hosiptalCash;
		}
		public String getPrepaidCharge() {
			return prepaidCharge;
		}
		public void setPrepaidCharge(String prepaidCharge) {
			this.prepaidCharge = prepaidCharge;
		}
		public RblLoanValidationData(String externalId, String customerExternalId, String centerExtrenalId,
				String groupExternalId, String loanProductCode, Integer loanPurpose, Integer pslcode, Long loanAmount,
				String disbursementMode, Integer noofInstallments, Integer repaymentfrequency, Integer loanCycle,
				String barcodeNo, Date loanStratDate, Date repaymentStartDate, String bcBranchCode, String colector,
				String approver, Date exceptedDisbursementDate, Integer topUpLoanFlag, String hosiptalCash,
				String prepaidCharge) {
			super();
			this.externalId = externalId;
			this.customerExternalId = customerExternalId;
			this.centerExtrenalId = centerExtrenalId;
			this.groupExternalId = groupExternalId;
			this.loanProductCode = loanProductCode;
			this.loanPurpose = loanPurpose;
			this.pslcode = pslcode;
			this.loanAmount = loanAmount;
			this.disbursementMode = disbursementMode;
			this.noofInstallments = noofInstallments;
			this.repaymentfrequency = repaymentfrequency;
			this.loanCycle = loanCycle;
			this.barcodeNo = barcodeNo;
			this.loanStratDate = loanStratDate;
			this.repaymentStartDate = repaymentStartDate;
			this.bcBranchCode = bcBranchCode;
			this.colector = colector;
			this.approver = approver;
			ExceptedDisbursementDate = exceptedDisbursementDate;
			TopUpLoanFlag = topUpLoanFlag;
			this.hosiptalCash = hosiptalCash;
			this.prepaidCharge = prepaidCharge;
		}
	

}

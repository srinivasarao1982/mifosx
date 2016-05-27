ALTER TABLE `m_loan`
	ADD COLUMN `first_installment_emi_amount` DECIMAL(19,6) NULL DEFAULT NULL AFTER `fixed_emi_amount`;
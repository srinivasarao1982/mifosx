ALTER TABLE `m_product_loan` 
	ADD COLUMN `first_instalment_amount_in_multiples_of`  INT(4) NULL DEFAULT NULL, 
	ADD COLUMN `adjust_first_emi_amount` TINYINT(1) NOT NULL DEFAULT '0';
	
ALTER TABLE `m_loan`
	CHANGE COLUMN `first_installment_emi_amount` `first_emi_amount` DECIMAL(19,6) NULL DEFAULT NULL AFTER `fixed_emi_amount`;	
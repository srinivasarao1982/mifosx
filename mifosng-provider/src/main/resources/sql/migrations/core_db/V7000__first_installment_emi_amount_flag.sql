ALTER TABLE `m_product_loan` 
	ADD COLUMN `first_instalment_amount_in_multiples_of`  INT(4) NULL DEFAULT NULL, 
	ADD COLUMN `adjust_first_emi_amount` TINYINT(1) NOT NULL DEFAULT '0';
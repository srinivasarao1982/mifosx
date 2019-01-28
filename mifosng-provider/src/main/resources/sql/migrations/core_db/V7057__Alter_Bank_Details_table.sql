
-- a)  add is_primary_account column 
    ALTER TABLE `m_bankdetails`
	ADD COLUMN `is_primary_account` TINYINT(1) NOT NULL DEFAULT '0' AFTER `lastmodified_date`;
	
-- b) add account type foregin key reference


    ALTER TABLE `m_bankdetails`
	ADD COLUMN `account_type_cv_id` INT(11) NULL AFTER `is_primary_account`,
	ADD CONSTRAINT `FK_m_bankdetails_m_code_value` FOREIGN KEY (`account_type_cv_id`) REFERENCES `m_code_value` (`id`);

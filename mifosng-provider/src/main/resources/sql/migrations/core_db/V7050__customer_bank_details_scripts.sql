CREATE TABLE m_bankdetails (
	`id` BIGINT(20) NOT NULL  AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`beneficiary_name` VARCHAR(256) NOT NULL,
	`account_no` VARCHAR(50) NOT NULL,
	`lasttransaction_amount` DECIMAL(10,0) NOT NULL,
	`ifsc_code` VARCHAR(50) NOT NULL,
	`branch_name` VARCHAR(256) NOT NULL,
	`branch_address` VARCHAR(400) NOT NULL,
	`lasttransaction_date` DATE NOT NULL,
	`createdby_id` BIGINT(20) NOT NULL,
	`created_date` DATE NOT NULL,
	`lastmodifiedby_id` BIGINT(20) NOT NULL,
	`lastmodified_date` DATE NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `account_no_UNIQUE` (`account_no`, `ifsc_code`)
);

INSERT INTO m_permission ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'CREATE_BANKDETAILS', 'BANKDETAILS', 'CREATE', 0);
INSERT INTO m_permission ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'READ_BANKDETAILS', 'BANKDETAILS', 'READ', 0);
INSERT INTO m_permission ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_BANKDETAILS', 'BANKDETAILS', 'UPDATE', 0);
INSERT INTO m_permission ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'DELETE_BANKDETAILS', 'BANKDETAILS', 'DELETE', 0);

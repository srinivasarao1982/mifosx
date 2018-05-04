CREATE TABLE `m_partial_loan` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`group_id` BIGINT(20) NOT NULL,
	`product_id` BIGINT(20) NULL DEFAULT NULL,
	`office_id` BIGINT(20) NOT NULL,
	`loanofficer_id` BIGINT(20) NULL DEFAULT NULL,
	`rpdo_no` VARCHAR(50) NOT NULL,
	`loan_amount` BIGINT(20) NOT NULL,
	`loan_tenure` INT(11) NOT NULL,
	`fixed_emi` BIGINT(20) NULL DEFAULT NULL,
	`loanpurpose_cv_id` INT(11) NOT NULL,
	`submitted_date` DATE NOT NULL,
	`status` INT(11) NULL DEFAULT NULL,
	`is_active` TINYINT(4) NULL DEFAULT NULL,
	`remark` VARCHAR(400) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NOT NULL,
	`created_date` DATE NOT NULL,
	`lastmodifiedby_id` BIGINT(20) NOT NULL,
	`lastmodified_date` DATE NOT NULL,
	PRIMARY KEY (`id`)
)
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'CREATE_PARTIALLOAN', 'PARTIALLOAN', 'CREATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_PARTIALLOAN', 'PARTIALLOAN', 'UPDATE', 0);
INSERT INTO `m_code` ( `code_name`, `is_system_defined`) VALUES ('CB Check Status', 0);
INSERT INTO `m_code_value` ( `code_id`, `code_value`, `code_description`, `order_position`, `code_score`) VALUES ( 61, 'Rejected', '', 1, NULL);
INSERT INTO `m_code_value` ( `code_id`, `code_value`, `code_description`, `order_position`, `code_score`) VALUES ( 61, 'Accepted', '', 0, NULL);


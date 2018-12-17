CREATE TABLE `m_rblcenter` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`center_id` BIGINT(20) NULL DEFAULT NULL,
	`max_individual` INT(11) NULL DEFAULT NULL,
	`meting_time` VARCHAR(50) NULL DEFAULT NULL,
	`house_no` VARCHAR(256) NULL DEFAULT NULL,
	`street_no` VARCHAR(256) NULL DEFAULT NULL,
	`area_loc` VARCHAR(256) NULL DEFAULT NULL,
	`landmark` VARCHAR(256) NULL DEFAULT NULL,
	`village` VARCHAR(256) NULL DEFAULT NULL,
	`taluk` VARCHAR(256) NULL DEFAULT NULL,
	`district` INT(11) NULL DEFAULT NULL,
	`state` INT(11) NULL DEFAULT NULL,
	`pin` INT(6) NULL DEFAULT NULL,
	`description` VARCHAR(30) NULL DEFAULT NULL,
	`createdby_id` INT(11) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` INT(11) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_rblcustomer` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NULL DEFAULT NULL,
	`pension_card` VARCHAR(50) NULL DEFAULT NULL,
	`adharSeeding_constant` VARCHAR(50) NULL DEFAULT NULL,
	`health` VARCHAR(50) NULL DEFAULT NULL,
	`language` VARCHAR(50) NULL DEFAULT NULL,
	`card_issue_fl` INT(11) NULL DEFAULT NULL,
	`cb_check` INT(11) NULL DEFAULT NULL,
	`renewal_fl` INT(11) NULL DEFAULT NULL,
	`mother_tounge` VARCHAR(50) NULL DEFAULT NULL,
	`gurdian_name` VARCHAR(256) NULL DEFAULT NULL,
	`gurdian_DatofBirth` DATE NULL DEFAULT NULL,
	`gurdian_gender` INT(11) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	`spouse_name` VARCHAR(256) NULL DEFAULT NULL,
	`spouse_DatofBirth` DATE NULL DEFAULT NULL,
	`relation_cv_id` INT(11) NULL DEFAULT NULL,
	`gurdian_mobileNo` VARCHAR(10) NULL DEFAULT NULL,
	`title` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_rblgroup` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`group_Id` BIGINT(20) NULL DEFAULT NULL,
	`maximum_center` INT(11) NULL DEFAULT NULL,
	`group_type` INT(11) NULL DEFAULT NULL,
	`min_number` INT(11) NULL DEFAULT NULL,
	`max_numbeer` INT(11) NULL DEFAULT NULL,
	`distance_from_center` INT(11) NULL DEFAULT NULL,
	`meeting_time` VARCHAR(50) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_rblloan` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`loan_id` BIGINT(20) NULL DEFAULT NULL,
	`psl_code` INT(11) NULL DEFAULT NULL,
	`to_Up_flag` INT(11) NULL DEFAULT NULL,
	`hosiptal_cash` VARCHAR(50) NULL DEFAULT NULL,
	`prepaid_charge` VARCHAR(50) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);



CREATE TABLE `m_rblsendfile` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`file_type` VARCHAR(100) NULL DEFAULT NULL,
	`file_Name` VARCHAR(100) NULL DEFAULT NULL,
	`file_path` VARCHAR(256) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_rblreceivefile` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`file_type` VARCHAR(100) NULL DEFAULT NULL,
	`file_Name` VARCHAR(100) NULL DEFAULT NULL,
	`file_path` VARCHAR(256) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_rblvalidatefile` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`center_id` BIGINT(20) NULL DEFAULT NULL,
	`file_type` VARCHAR(50) NULL DEFAULT NULL,
	`file_name` VARCHAR(200) NULL DEFAULT NULL,
	`file_location` VARCHAR(200) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_creditbureau_error` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`center_id` BIGINT(20) NULL DEFAULT NULL,
	`client_id` BIGINT(20) NULL DEFAULT NULL,
	`barcode_no` VARCHAR(256) NULL DEFAULT NULL,
	`external_id` VARCHAR(256) NULL DEFAULT NULL,
	`is_Renewal_Loan` VARCHAR(50) NULL DEFAULT NULL,
	`customer_Name` VARCHAR(50) NULL DEFAULT NULL,
	`loanAmount` BIGINT(20) NULL DEFAULT NULL,
	`title` INT(11) NULL DEFAULT NULL,
	`name` VARCHAR(256) NULL DEFAULT NULL,
	`relation` VARCHAR(50) NULL DEFAULT NULL,
	`line1` VARCHAR(256) NULL DEFAULT NULL,
	`line2` VARCHAR(256) NULL DEFAULT NULL,
	`line3` VARCHAR(256) NULL DEFAULT NULL,
	`city_code` VARCHAR(256) NULL DEFAULT NULL,
	`city_Name` VARCHAR(256) NULL DEFAULT NULL,
	`state_code` VARCHAR(256) NULL DEFAULT NULL,
	`pin` VARCHAR(256) NULL DEFAULT NULL,
	`operating_RegionCode` VARCHAR(256) NULL DEFAULT NULL,
	`operating_RegionName` VARCHAR(256) NULL DEFAULT NULL,
	`dateOfBirth` DATE NULL DEFAULT NULL,
	`branch_code` VARCHAR(256) NULL DEFAULT NULL,
	`branch_name` VARCHAR(256) NULL DEFAULT NULL,
	`json` VARCHAR(18000) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_creditbureau_request` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`center_id` BIGINT(20) NULL DEFAULT NULL,
	`client_id` BIGINT(20) NULL DEFAULT NULL,
	`barcode_no` VARCHAR(256) NULL DEFAULT NULL,
	`external_id` VARCHAR(256) NULL DEFAULT NULL,
	`is_Renewal_Loan` VARCHAR(50) NULL DEFAULT NULL,
	`customer_Name` VARCHAR(50) NULL DEFAULT NULL,
	`loanAmount` BIGINT(20) NULL DEFAULT NULL,
	`title` INT(11) NULL DEFAULT NULL,
	`name` VARCHAR(256) NULL DEFAULT NULL,
	`relation` VARCHAR(50) NULL DEFAULT NULL,
	`line1` VARCHAR(256) NULL DEFAULT NULL,
	`line2` VARCHAR(256) NULL DEFAULT NULL,
	`line3` VARCHAR(256) NULL DEFAULT NULL,
	`city_code` VARCHAR(256) NULL DEFAULT NULL,
	`city_Name` VARCHAR(256) NULL DEFAULT NULL,
	`state_code` VARCHAR(256) NULL DEFAULT NULL,
	`pin` VARCHAR(256) NULL DEFAULT NULL,
	`operating_RegionCode` VARCHAR(256) NULL DEFAULT NULL,
	`operating_RegionName` VARCHAR(256) NULL DEFAULT NULL,
	`dateOfBirth` DATE NULL DEFAULT NULL,
	`branch_code` VARCHAR(256) NULL DEFAULT NULL,
	`branch_name` VARCHAR(256) NULL DEFAULT NULL,
	`json` VARCHAR(18000) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE `m_creditbureau_response` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NULL DEFAULT NULL,
	`center_id` INT(11) NULL DEFAULT NULL,
	`request_uid` VARCHAR(256) NULL DEFAULT NULL,
	`service_name` VARCHAR(256) NULL DEFAULT NULL,
	`channel_id` VARCHAR(256) NULL DEFAULT NULL,
	`cor_Id` VARCHAR(256) NULL DEFAULT NULL,
	`credit_Approved` VARCHAR(256) NULL DEFAULT NULL,
	`reason` VARCHAR(256) NULL DEFAULT NULL,
	`eligible_Amount` VARCHAR(256) NULL DEFAULT NULL,
	`json` VARCHAR(256) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATETIME NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);
CREATE TABLE `m_sequence_number` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`entity_type` INT(11) NOT NULL DEFAULT '0',
	`seq_Number` BIGINT(20) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
	
);
ALTER TABLE `m_group`
	ADD COLUMN `is_cbchecked` INT(11) NULL DEFAULT NULL AFTER `is_cbcheck_required`,
	ADD COLUMN `is_grt_completed` INT(11) NULL DEFAULT NULL AFTER `is_cbchecked`;

ALTER TABLE `m_task`
	ADD COLUMN `task_start_time_id` INT NULL DEFAULT NULL AFTER `created_date`,
	ADD COLUMN `task_end_time_id` INT NULL DEFAULT NULL AFTER `task_start_time_id`;
	
ALTER TABLE `m_code_value`
	CHANGE COLUMN `code_score` `code_score` VARCHAR(50) NULL DEFAULT NULL AFTER `order_position`;
ALTER TABLE `m_partial_loan`
	ADD COLUMN `is_disburse` INT(11) NULL DEFAULT NULL AFTER `remark`;	
	

INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_RBLCUSTOMER', 'RBLCUSTOMER', 'UPDATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_RBLGROUP', 'RBLGROUP', 'UPDATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'CREATE_RBLGROUP', 'RBLGROUP', 'CREATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'CREATE_RBLCENTER', 'RBLCENTER', 'CREATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_RBLCENTER', 'RBLCENTER', 'UPDATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'CREATE_RBLLOAN', 'RBLLOAN', 'CREATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_SEQNUMBER', 'SEQNUMBER', 'UPDATE', 1);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_RBLLOAN', 'RBLLOAN', 'UPDATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'CREATE_RBLCUSTOMER', 'RBLCUSTOMER', 'CREATE', 0);

INSERT INTO `c_configuration` ( `name`, `value`, `enabled`, `description`) VALUES ( 'rbl file check', 0, 1, NULL);
INSERT INTO `c_configuration` ( `name`, `value`, `enabled`, `description`) VALUES ( 'min-clients-in-group-task-time', 1, 2, NULL);
INSERT INTO `c_configuration` ( `name`, `value`, `enabled`, `description`) VALUES ( 'max-clients-in-group-task-time', 1, 2, NULL);

INSERT INTO `m_code` ( `code_name`, `is_system_defined`) VALUES ( 'gurdianRelations', 0);
INSERT INTO `m_code` ( `code_name`, `is_system_defined`) VALUES ('gurdianTitles', 0);
INSERT INTO `m_code` ( `code_name`, `is_system_defined`) VALUES ( 'Task Time Options', 0);
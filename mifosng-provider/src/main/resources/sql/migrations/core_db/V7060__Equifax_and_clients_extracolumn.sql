CREATE TABLE `m_equifax_error` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`center_id` BIGINT(20) NOT NULL,
	`client_id` BIGINT(20) NOT NULL,
	`customer_name` VARCHAR(200) NULL DEFAULT NULL,
	`transaction_amount` BIGINT(20) NULL DEFAULT NULL,
	`error` VARCHAR(4000) NOT NULL,
	`createdby_id` BIGINT(20) NOT NULL,
	`created_date` DATE NOT NULL,
	`lastmodifiedby_id` BIGINT(20) NOT NULL,
	`lastmodified_date` DATE NOT NULL,
	PRIMARY KEY (`id`)
)
AUTO_INCREMENT=21
;

CREATE TABLE `m_equifax_request` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`center_id` BIGINT(20) NULL DEFAULT NULL,
	`client_id` BIGINT(20) NULL DEFAULT NULL,
	`inquiry_purpose` VARCHAR(50) NULL DEFAULT NULL,
	`transaction_amount` BIGINT(20) NULL DEFAULT NULL,
	`first_name` VARCHAR(100) NULL DEFAULT NULL,
	`address_line1` VARCHAR(400) NULL DEFAULT NULL,
	`state_code` VARCHAR(50) NULL DEFAULT NULL,
	`postal` VARCHAR(50) NULL DEFAULT NULL,
	`date_of_birth` DATE NULL DEFAULT NULL,
	`gender` VARCHAR(50) NULL DEFAULT NULL,
	`pan_id` VARCHAR(50) NULL DEFAULT NULL,
	`mobile_phone` VARCHAR(50) NULL DEFAULT NULL,
	`home_phone` VARCHAR(50) NULL DEFAULT NULL,
	`createdby_id` BIGINT(20) NULL DEFAULT NULL,
	`created_date` DATE NULL DEFAULT NULL,
	`lastmodifiedby_id` BIGINT(20) NULL DEFAULT NULL,
	`lastmodified_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
AUTO_INCREMENT=3
;
ALTER TABLE `n_client_ext`
	ADD COLUMN `maiden_name` VARCHAR(100) NULL DEFAULT NULL AFTER `external_Id2`,
	ADD COLUMN `customer_mother_name` VARCHAR(100) NULL DEFAULT NULL AFTER `maiden_name`;

INSERT INTO `m_permission` (`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ('portfolio', 'READ_EQUIFAX', 'EQUIFAX', 'READ', 0);
INSERT INTO `c_configuration` ( `name`, `value`, `enabled`, `description`) VALUES ('loan-counter', 1, 0, 'increase based on number of loan to customer not on product wise');
ALTER TABLE `m_rblcenter`
	ADD COLUMN `distance_from_branch` INT(11) NULL DEFAULT NULL AFTER `center_id`;
	
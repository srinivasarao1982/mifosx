CREATE TABLE `n_coapplicant` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`first_name` VARCHAR(150) NOT NULL,
	`middle_name` VARCHAR(150) NULL DEFAULT NULL,
	`last_name` VARCHAR(150) NULL DEFAULT NULL,
	`sp_relationship_cv_id`  INT(11) NOT NULL,
	`date_of_birth` DATE NULL DEFAULT NULL,
	`age` INT(3) NULL DEFAULT NULL,
	`mothers_maiden_name` VARCHAR(150) NOT NULL,
	`email_id` VARCHAR(150) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK1_n_coapplicant_details_client_id` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK2_n_coapplicant_details_sp_relationship_cv_id` FOREIGN KEY (`sp_relationship_cv_id`) REFERENCES `m_code_value` (`id`)
);

INSERT INTO `m_code` (`code_name`, `is_system_defined`) VALUES ('SpouseRelationShip', 1);
INSERT IGNORE INTO m_code_value(code_id, code_value, order_position) VALUES((SELECT id FROM m_code WHERE m_code.code_name ='addressType'), 'Spouse Address', 0);
INSERT IGNORE INTO m_code_value(code_id, code_value, order_position) VALUES((SELECT id FROM m_code WHERE m_code.code_name ='SpouseRelationShip'), 'Father', 0);
INSERT IGNORE INTO m_code_value(code_id, code_value, order_position) VALUES((SELECT id FROM m_code WHERE m_code.code_name ='SpouseRelationShip'), 'Spouse', 0);
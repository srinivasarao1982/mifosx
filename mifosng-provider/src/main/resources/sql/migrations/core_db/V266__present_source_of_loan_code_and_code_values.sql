ALTER TABLE `n_family_details`
	ALTER `firstname` DROP DEFAULT,
	ALTER `relationship_cv_id` DROP DEFAULT,
	ALTER `gender_cv_id` DROP DEFAULT,
	ALTER `age` DROP DEFAULT,
	ALTER `occupation_cv_id` DROP DEFAULT,
	ALTER `educational_status_cv_id` DROP DEFAULT;
ALTER TABLE `n_family_details`
	CHANGE COLUMN `firstname` `firstname` VARCHAR(50) NULL AFTER `client_id`,
	CHANGE COLUMN `relationship_cv_id` `relationship_cv_id` INT(11) NULL AFTER `lastname`,
	CHANGE COLUMN `gender_cv_id` `gender_cv_id` INT(11) NULL AFTER `relationship_cv_id`,
	CHANGE COLUMN `age` `age` INT(3) NULL AFTER `date_of_birth`,
	CHANGE COLUMN `occupation_cv_id` `occupation_cv_id` INT(11) NULL AFTER `age`,
	CHANGE COLUMN `educational_status_cv_id` `educational_status_cv_id` INT(11) NULL AFTER `occupation_cv_id`;
	
	
ALTER TABLE `n_nominee_details`
	ALTER `salutation_cv_id` DROP DEFAULT,
	ALTER `name` DROP DEFAULT,
	ALTER `gender_cv_id` DROP DEFAULT,
	ALTER `age` DROP DEFAULT,
	ALTER `relationship_cv_id` DROP DEFAULT,
	ALTER `guardian_name` DROP DEFAULT,
	ALTER `address` DROP DEFAULT;
ALTER TABLE `n_nominee_details`
	CHANGE COLUMN `salutation_cv_id` `salutation_cv_id` INT(11) NULL AFTER `client_id`,
	CHANGE COLUMN `name` `name` VARCHAR(150) NULL AFTER `salutation_cv_id`,
	CHANGE COLUMN `gender_cv_id` `gender_cv_id` INT(11) NULL AFTER `name`,
	CHANGE COLUMN `age` `age` INT(3) NULL AFTER `gender_cv_id`,
	CHANGE COLUMN `relationship_cv_id` `relationship_cv_id` INT(11) NULL AFTER `age`,
	CHANGE COLUMN `guardian_name` `guardian_name` VARCHAR(150) NULL AFTER `date_of_birth`,
	CHANGE COLUMN `address` `address` VARCHAR(500) NULL AFTER `guardian_name`;
	
ALTER TABLE `n_client_ext`
	ALTER `salutation_cv_id` DROP DEFAULT,
	ALTER `marital_status_cv_id` DROP DEFAULT,
	ALTER `profession_cv_id` DROP DEFAULT,
	ALTER `educational_qualification_cv_id` DROP DEFAULT,
	ALTER `annual_income_cv_id` DROP DEFAULT,
	ALTER `landholding_cv_id` DROP DEFAULT,
	ALTER `house_type_cv_id` DROP DEFAULT,
	ALTER `pan_form_cv_id` DROP DEFAULT;
ALTER TABLE `n_client_ext`
	CHANGE COLUMN `salutation_cv_id` `salutation_cv_id` INT(11) NULL AFTER `client_id`,
	CHANGE COLUMN `marital_status_cv_id` `marital_status_cv_id` INT(11) NULL AFTER `salutation_cv_id`,
	CHANGE COLUMN `profession_cv_id` `profession_cv_id` INT(11) NULL AFTER `marital_status_cv_id`,
	CHANGE COLUMN `educational_qualification_cv_id` `educational_qualification_cv_id` INT(11) NULL AFTER `profession_others`,
	CHANGE COLUMN `annual_income_cv_id` `annual_income_cv_id` INT(11) NULL AFTER `educational_qualification_cv_id`,
	CHANGE COLUMN `landholding_cv_id` `landholding_cv_id` INT(11) NULL AFTER `annual_income_cv_id`,
	CHANGE COLUMN `house_type_cv_id` `house_type_cv_id` INT(11) NULL AFTER `landholding_cv_id`,
	CHANGE COLUMN `pan_form_cv_id` `pan_form_cv_id` INT(11) NULL AFTER `pan_no`;

-- present Loan Source Code Types 
INSERT IGNORE INTO `m_code` (`code_name`) VALUES ('presentLoanSourceTypes');

-- present Loan source code values

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanSourceTypes'), 'Spandana', 1);

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanSourceTypes'), 'Sks', 1);

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanSourceTypes'), 'Chaitanya', 1);

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanSourceTypes'), 'Grameena Koota', 1);

-- present loan purpose type code and code values

INSERT IGNORE INTO `m_code` (`code_name`) VALUES ('presentLoanPurposeTypes');

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanPurposeTypes'), 'Business', 1);
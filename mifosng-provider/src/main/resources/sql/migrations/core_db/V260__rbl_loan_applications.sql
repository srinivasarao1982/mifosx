CREATE TABLE `n_client_ext` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id`  BIGINT(20) NOT NULL,
	`marital_status_cv_id` INT(11) NOT NULL,
	`profession_cv_id`   INT(11) NOT NULL,
	`profession_others`  VARCHAR(100) NULL DEFAULT NULL,
	`educational_qualification_cv_id`  INT(11) NOT NULL,
	`annual_income_cv_id`  INT(11) NOT NULL,
	`landholding_cv_id`  INT(11) NOT NULL,
	`house_type_cv_id`  INT(11) NOT NULL,
	`aadhaar_no` VARCHAR(100) NULL DEFAULT NULL,
	`pan_no` VARCHAR(100) NULL DEFAULT NULL,
	`pan_form_cv_id`  INT(11) NOT NULL,
	`nrega_no` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `client_id_UNIQUE` (`client_id`),
	CONSTRAINT `fk_n_client_ext_client_id_m_client` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `fk_n_client_ext_marital_status_cv_id` FOREIGN KEY (`marital_status_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `fk_n_client_ext_profession_cv_id` FOREIGN KEY (`profession_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `fk_n_client_ext_eq_cv_id` FOREIGN KEY (`educational_qualification_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `fk_n_client_ext_annual_income_cv_id` FOREIGN KEY (`annual_income_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `fk_n_client_ext_landholding_cv_id` FOREIGN KEY (`landholding_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `fk_n_client_ext_house_type_cv_id` FOREIGN KEY (`house_type_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `fk_n_client_ext_pan_form_cv_id` FOREIGN KEY (`pan_form_cv_id`) REFERENCES `m_code_value` (`id`)
);

CREATE TABLE `n_address` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`address_type_cv_id` INT(11) NULL DEFAULT NULL,
	`house_no` VARCHAR(20) NULL DEFAULT NULL,
	`street_no` VARCHAR(20) NULL DEFAULT NULL,
	`area_locality` VARCHAR(100) NULL DEFAULT NULL,
	`landmark` VARCHAR(100) NULL DEFAULT NULL,
	`village_town` VARCHAR(100) NULL DEFAULT NULL,
	`taluka` VARCHAR(100) NOT NULL,
	`district_cv_id` INT(11) NOT NULL,
	`state_cv_id` INT(11) NOT NULL,
	`pin_code` INT(6) NOT NULL,
	`landline_no` BIGINT(20) NULL DEFAULT NULL,
	`mobile_no` BIGINT(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `client_id_UNIQUE` (`client_id`),
	UNIQUE INDEX `client_id_address_type_cv_id_UNIQUE` (`client_id`,`address_type_cv_id`),
	CONSTRAINT `fk_n_address_client_id` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `fk_n_address_address_type_cv_id` FOREIGN KEY (`address_type_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `fk_n_address_district_cv_id` FOREIGN KEY (`district_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `fk_n_address_state_cv_id` FOREIGN KEY (`state_cv_id`) REFERENCES `m_code_value` (`id`)
);

CREATE TABLE `n_family_details` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`firstname` VARCHAR(50) NOT NULL,
	`middlename` VARCHAR(50) NULL DEFAULT NULL,
	`lastname` VARCHAR(50) NULL DEFAULT NULL,
	`relationship_cv_id`  INT(11) NOT NULL,
	`gender_cv_id`  INT(11) NOT NULL,
	`date_of_birth` DATE NULL DEFAULT NULL,
	`age`  INT(3) NOT NULL,
	`occupation_cv_id` INT(11) NOT NULL,
	`educational_status_cv_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK1_n_family_details_client_id` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK2_n_family_details_relationship_cv_id` FOREIGN KEY (`relationship_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK3_n_family_details_sex_cv_id` FOREIGN KEY (`sex_cv_id`) REFERENCES `sex_cv_id` (`id`),
	CONSTRAINT `FK4_n_family_details_occupation_cv_id` FOREIGN KEY (`occupation_cv_id`) REFERENCES `sex_cv_id` (`id`),
	CONSTRAINT `FK5_n_family_details_educational_status_cv_id` FOREIGN KEY (`educational_status_cv_id`) REFERENCES `m_code_value` (`id`)
);

CREATE TABLE `n_financial_details` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`cfa_occupation_cv_id`  INT(11) NOT NULL,
	`annual_revenue` DECIMAL(19,6) NOT NULL,
	`annual_expenditure` DECIMAL(19,6) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK1_n_financial_details_client_id` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK2_n_financial_details_relationship_crop_cv_id` FOREIGN KEY (`crop_cv_id`) REFERENCES `m_code_value` (`id`)	)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;


-- Do not use below tables

CREATE TABLE `n_agri_occupational_details` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`crop_cv_id` VARCHAR(20) NOT NULL,
	`area`  SMALLINT(3) NOT NULL,
	`yes_no_cv_id`  INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK1_n_agri_occupational_details_client_id` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK2_n_agri_occupational_details_relationship_crop_cv_id` FOREIGN KEY (`crop_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK3_n_agri_occupational_details_yes_no_cv_id` FOREIGN KEY (`sex_cv_id`) REFERENCES `yes_no_cv_id` (`id`)
	)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `n_agri_investment` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`investment_type_cv_id` VARCHAR(20) NOT NULL,
	`amount`  SMALLINT(3) NOT NULL
	PRIMARY KEY (`id`),
	CONSTRAINT `FK1_n_agri_occupational_details_client_id` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK2_n_agri_occupational_details_relationship_crop_cv_id` FOREIGN KEY (`crop_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK3_n_agri_occupational_details_yes_no_cv_id` FOREIGN KEY (`sex_cv_id`) REFERENCES `yes_no_cv_id` (`id`)
	)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `n_cfaoccupations` (
`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
`client_id` BIGINT(20) NOT NULL,
`occupation_type_cv_id` INT(11) NOT NULL,
`annual_revenue` DECIMAL(10,2) NULL DEFAULT NULL,
`annual_expense` DECIMAL(10,2) NULL DEFAULT NULL,
`annual_surplus` DECIMAL(10,2) NULL DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `client_id_UNIQUE` (`client_id`),
INDEX `fk_n_cfa_occupation_type_cv_id` (`occupation_type_cv_id`),
CONSTRAINT `fk_n_cfa_occ_client_id_m_client` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
CONSTRAINT `fk_n_cfa_occupation_type_cv_id` FOREIGN KEY (`occupation_type_cv_id`) REFERENCES `m_code_value` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

ALTER TABLE `n_client_ext`
ADD COLUMN `sp_firstname` VARCHAR(100) NULL DEFAULT NULL AFTER `nrega_no`,
ADD COLUMN `sp_middlename` VARCHAR(100) NULL DEFAULT NULL AFTER `sp_firstname`,
ADD COLUMN `sp_lastname` VARCHAR(100) NULL DEFAULT NULL AFTER `sp_middlename`;
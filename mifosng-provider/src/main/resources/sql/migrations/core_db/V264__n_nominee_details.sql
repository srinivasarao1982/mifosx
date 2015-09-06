CREATE TABLE `n_nominee_details` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`salutation_cv_id` INT(11) NOT NULL,
	`name` VARCHAR(150) NOT NULL,
	`gender_cv_id`  INT(11) NOT NULL,
	`age`  INT(3) NOT NULL,
	`relationship_cv_id`  INT(11) NOT NULL,	
	`date_of_birth` DATE NULL DEFAULT NULL,
	`guardian_name` VARCHAR(150) NOT NULL,
	`address` VARCHAR(500) NOT NULL,
	`guardian_date_of_birth` DATE NULL DEFAULT NULL,
	`guardian_relationship` VARCHAR(150) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK1_n_nominee_details_client_id` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK2_n_nominee_details_salutation_cv_id` FOREIGN KEY (`salutation_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK3_n_nominee_details_gender_cv_id` FOREIGN KEY (`gender_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK4_n_nominee_details_relationship_cv_id` FOREIGN KEY (`relationship_cv_id`) REFERENCES `m_code_value` (`id`)
);
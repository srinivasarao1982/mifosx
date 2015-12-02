ALTER TABLE `n_coapplicant`
	ADD COLUMN `father_first_name` VARCHAR(150) NOT NULL AFTER `email_id`,
	ADD COLUMN `father_middle_name` VARCHAR(150) NULL DEFAULT NULL AFTER `father_first_name`,
	ADD COLUMN `father_last_name` VARCHAR(150) NULL DEFAULT NULL AFTER `father_middle_name`;

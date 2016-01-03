ALTER TABLE `n_address`
	ALTER `landline_no` DROP DEFAULT,
	ALTER `mobile_no` DROP DEFAULT;
ALTER TABLE `n_address`
	CHANGE COLUMN `landline_no` `landline_no` BIGINT(20) NULL AFTER `pin_code`,
	CHANGE COLUMN `mobile_no` `mobile_no` BIGINT(11) NULL AFTER `landline_no`;
ALTER TABLE `n_coapplicant`
	CHANGE COLUMN `father_first_name` `father_first_name` VARCHAR(150) NULL DEFAULT NULL AFTER `email_id`;
ALTER TABLE `n_address`
	CHANGE COLUMN `district_cv_id` `district_cv_id` INT(11) NULL DEFAULT NULL AFTER `taluka`;

ALTER TABLE `n_address`
	CHANGE COLUMN `house_no` `house_no` VARCHAR(50) NULL DEFAULT NULL AFTER `address_type_cv_id`,
	CHANGE COLUMN `street_no` `street_no` VARCHAR(50) NULL DEFAULT NULL AFTER `house_no`;

ALTER TABLE `n_address`
	CHANGE COLUMN `pin_code` `pin_code` INT(6) NULL DEFAULT NULL AFTER `state_cv_id`;

ALTER TABLE `n_address`
	DROP INDEX `client_id_address_type_cv_id_UNIQUE`,
	DROP INDEX `client_id_UNIQUE`,
	ADD UNIQUE INDEX `client_id_UNIQUE` (`client_id`, `address_type_cv_id`);
	
ALTER TABLE `n_address`
	DROP INDEX `client_id_UNIQUE`,
	ADD INDEX `fk_n_adress_client_id` (`client_id`);
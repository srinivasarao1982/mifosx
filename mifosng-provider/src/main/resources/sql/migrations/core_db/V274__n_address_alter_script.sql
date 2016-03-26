ALTER TABLE `n_address`
    ALTER `address_type_cv_id` DROP DEFAULT;
    
ALTER TABLE `n_address`
    CHANGE COLUMN `address_type_cv_id` `address_type_cv_id` INT(11) NOT NULL AFTER `client_id`;
ALTER TABLE `n_address`
    ADD UNIQUE INDEX `UQ_client_id_address_type` (`client_id`, `address_type_cv_id`);
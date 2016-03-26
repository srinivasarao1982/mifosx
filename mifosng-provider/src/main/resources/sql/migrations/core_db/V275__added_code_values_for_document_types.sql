INSERT IGNORE INTO `m_code` (`code_name`, `is_system_defined`) VALUES 
('salutation', 1),
('identityProof', 0),
('addressProof', 0);

INSERT INTO `m_code_value` (`code_id`, `code_value`, `code_description`, `order_position`, `code_score`) VALUES 
((SELECT c.id FROM m_code c WHERE c.code_name = 'identityProof'), 'Coapplicant-Aadhaar', '', 0, NULL),
((SELECT c.id FROM m_code c WHERE c.code_name = 'identityProof'), 'Coapplicant-Votor ID', '', 1, NULL),
((SELECT c.id FROM m_code c WHERE c.code_name = 'addressProof'), 'Coapplicant-Aadhaar', '', 0, NULL),
((SELECT c.id FROM m_code c WHERE c.code_name = 'addressProof'), 'Coapplicant-Electricity Bill', '', 3, NULL),
((SELECT c.id FROM m_code c WHERE c.code_name = 'addressProof'), 'Coapplicant-Ration Card', '', 2, NULL),
((SELECT c.id FROM m_code c WHERE c.code_name = 'addressProof'), 'Coapplicant-Votor ID', '', 1, NULL),
((SELECT c.id FROM m_code c WHERE c.code_name = 'salutation'), 'Mr', '', 2, NULL);

ALTER TABLE `n_coapplicant`
	ADD COLUMN `salutation_cv_id` INT(11) NULL AFTER `client_id`,
	ADD COLUMN `gender_cv_id` INT(11) NULL DEFAULT NULL AFTER `last_name`;
ALTER TABLE `n_coapplicant`
	ADD CONSTRAINT `FK1_n_coapplicant_salutation_cv_id` FOREIGN KEY (`salutation_cv_id`) REFERENCES `m_code_value` (`id`),
	ADD CONSTRAINT `FK1_n_coapplicant_gender_cv_id` FOREIGN KEY (`gender_cv_id`) REFERENCES `m_code_value` (`id`);
	
ALTER TABLE `n_address`
	DROP INDEX `UQ_client_id_address_type`;

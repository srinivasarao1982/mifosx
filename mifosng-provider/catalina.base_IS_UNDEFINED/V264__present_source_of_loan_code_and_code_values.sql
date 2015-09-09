-- present Loan Source Code Types 
INSERT IGNORE INTO `m_code` (`code_name`) VALUES ('presentLoanSourceTypes');

-- present Loan source code values

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanSourceTypes'), 'Spandana', 1);

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanSourceTypes'), 'Sks', 1);

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanSourceTypes'), 'Chaitanya', 1);

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanSourceTypes'), 'Grameena Koota', 1);

-- present loan purpose type code and code values

INSERT IGNORE INTO `m_code` (`code_name`) VALUES ('presentLoanPurposeTypes');

INSERT IGNORE INTO `mifostenant-default`.`m_code_value` (`code_id`, `code_value`, `order_position`) 
VALUES ((SELECT id FROM m_code WHERE code_name = 'presentLoanPurposeTypes'), 'Business', 1);


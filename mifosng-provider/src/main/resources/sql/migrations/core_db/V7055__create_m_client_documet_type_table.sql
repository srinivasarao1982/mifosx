CREATE TABLE `m_client_document_types` (
	`id` INT(11) NOT NULL,
	`documentTypeId` INT(11) NULL DEFAULT NULL,
	`documentTypeName` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

INSERT INTO `m_client_document_types` (`id`, `documentTypeId`, `documentTypeName`) VALUES (1, 34, 'Address Proof');
INSERT INTO `m_client_document_types` (`id`, `documentTypeId`, `documentTypeName`) VALUES (2, 35, 'Identity Prrof');

CREATE TABLE `n_loan_ext` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`loan_id` BIGINT(20) NOT NULL,
	`loanApplication_Id` VARCHAR(50) NULL,
	PRIMARY KEY (`id`),
	INDEX `FK__m_loan` (`loan_id`),
	CONSTRAINT `FK__m_loan` FOREIGN KEY (`loan_id`) REFERENCES `m_loan` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10;

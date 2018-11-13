ALTER TABLE `m_group`
	ADD COLUMN `is_new_center` INT NULL DEFAULT NULL AFTER `closedon_userid`,
	ADD COLUMN `is_cbcheck_required` INT NULL DEFAULT NULL AFTER `is_new_center`;

CREATE TABLE `m_task` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`office_id` BIGINT(20) NOT NULL,
	`staff_id` BIGINT(20) NOT NULL,
	`center_id` BIGINT(20) NOT NULL,
	`task_type` INT(11) NOT NULL,
	`task_date` DATE NOT NULL,
	`task_completed_date` DATE NULL DEFAULT NULL,
	`note` VARCHAR(400) NULL DEFAULT NULL,
	`task_status` INT(11) NOT NULL,
	`createdby_id` BIGINT(20) NOT NULL,
	`created_date` DATE NOT NULL,
	`lastmodifiedby_id` BIGINT(20) NOT NULL,
	`lastmodified_date` DATE NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `m_taskattendence` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`task_id` BIGINT(20) NOT NULL,
	`client_id` BIGINT(20) NOT NULL,
	`attendance_type_enum` TINYINT(4) NOT NULL,	
	PRIMARY KEY (`id`),
	INDEX `FK_m_taskattendence_m_task` (`task_id`),
	CONSTRAINT `FK_m_taskattendence_m_task` FOREIGN KEY (`task_id`) REFERENCES `m_task` (`id`)
);

CREATE TABLE `m_taskdetails` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`task_id` BIGINT(20) NULL DEFAULT NULL,
	`details_description` INT(11) NULL DEFAULT NULL,
	`description_value` VARCHAR(400) NULL DEFAULT NULL,	
	PRIMARY KEY (`id`),
	INDEX `FK_m_taskdetails_m_task` (`task_id`),
	CONSTRAINT `FK_m_taskdetails_m_task` FOREIGN KEY (`task_id`) REFERENCES `m_task` (`id`)
);

CREATE TABLE `m_task_configuration` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`task_type` INT(11) NULL DEFAULT NULL,
	`no_of_days` INT(11) NULL DEFAULT NULL,
	`completed_by` INT(11) NULL DEFAULT NULL,
	`center_type` INT(11) NULL DEFAULT NULL,
	`no_of_task` INT(11) NULL DEFAULT NULL,
	`order_no` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'CREATE_TASK', 'TASK', 'CREATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'READ_TASK', 'TASK', 'READ', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_TASK', 'TASK', 'UPDATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'CREATE_TASKCONFIGURATION', 'TASKCONFIGURATION', 'CREATE', 0);
INSERT INTO `m_permission` ( `grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES ( 'portfolio', 'UPDATE_TASKCONFIGURATION', 'TASKCONFIGURATION', 'UPDATE', 0);


INSERT INTO `m_code` ( `code_name`, `is_system_defined`) VALUES (62, 'Task type', 0);
INSERT INTO `m_code` ( `code_name`, `is_system_defined`) VALUES (63, 'Task Description', 0);
INSERT INTO `m_code` ( `code_name`, `is_system_defined`) VALUES (64, 'Task Completed By', 0);

	


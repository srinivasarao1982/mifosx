CREATE TABLE `deleteme` (
	`loan_id` BIGINT(20) NULL DEFAULT NULL,
	`first_repayment_date` DATE NULL DEFAULT NULL,
	`new_first_repayment_date` DATE NULL DEFAULT NULL
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB;
INSERT INTO `job` (`name`, `display_name`, `cron_expression`, `create_time`, `task_priority`, `group_name`, `previous_run_start_time`, `next_run_time`, `job_key`, `initializing_errorlog`, `is_active`, `currently_running`, `updates_allowed`, `scheduler_group`, `is_misfired`) VALUES ('Generate Correct Repayment Schedule', 'Generate Correct Repayment Schedule', '0 0  1 1/1 *?*', '2016-08-16 17:15:01', 5, NULL, '2016-08-22 16:40:48', NULL, 'Generate Correct Repayment ScheduleJobDetail1 _ DEFAULT', NULL, 1, 0, 1, 0, 0);

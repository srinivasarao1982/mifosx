INSERT INTO `c_configuration` ( `name`, `value`, `enabled`, `description`) VALUES ( 'allowed-cente- max-customers', 5, 0, 'allow maximum customer to center');
ALTER TABLE `rbl branch name`
	ADD COLUMN `operating region` VARCHAR(50) NULL DEFAULT NULL AFTER `Opening Date`,
	ADD COLUMN `bc branch code` VARCHAR(50) NULL DEFAULT NULL AFTER `operating region`,
	ADD COLUMN `collector` VARCHAR(50) NULL DEFAULT NULL AFTER `bc branch code`,
	ADD COLUMN `approver` VARCHAR(50) NULL DEFAULT NULL AFTER `collector`,
	ADD COLUMN `city code` VARCHAR(50) NULL DEFAULT NULL AFTER `approver`,
	ADD COLUMN `city name` VARCHAR(50) NULL DEFAULT NULL AFTER `city code`,
	ADD COLUMN `operating region Name` VARCHAR(50) NULL DEFAULT NULL AFTER `city name`,
	ADD COLUMN `Branch Name` VARCHAR(50) NULL DEFAULT NULL AFTER `operating region Name`;

-- used to identify the client is reprocessed for cb check 
ALTER TABLE `m_client`
	ADD COLUMN `is_reprocessed` TINYINT(1) NOT NULL DEFAULT '0' AFTER `reactivated_on_userid`;
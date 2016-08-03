CREATE TABLE `ct_meeting_schedule` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`officeId` BIGINT(20) NOT NULL,
	`center_id` BIGINT(20) NULL DEFAULT NULL,
	`center_name` VARCHAR(100) NULL DEFAULT NULL,
	`staff_name` VARCHAR(100) NULL DEFAULT NULL,
	`no_of_clients` INT(11) NULL DEFAULT NULL,
	`installment_no` INT(11) NULL DEFAULT NULL,
	`demand_Amount` DECIMAL(10,0) NULL DEFAULT NULL,
	`meeting_date` DATE NULL DEFAULT NULL,
	`future_meeting_date` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=0;

INSERT INTO `job` (`name`, `display_name`, `cron_expression`, `create_time`, `task_priority`, `group_name`, `previous_run_start_time`, `next_run_time`, `job_key`, `initializing_errorlog`, `is_active`, `currently_running`, `updates_allowed`, `scheduler_group`, `is_misfired`) VALUES ('Generate Center Meeting Schedule', 'Generate Center Meeting Schedule', '0 0 1 1/1 * ? *', '2016-07-28 23:42:54', 5, NULL, '2016-07-29 00:33:23', '2016-07-29 12:00:00', 'Generate Center Meeting ScheduleJobDetail1 _ DEFAULT', NULL, 1, 1, 1, 0, 0);

INSERT INTO `stretchy_report` (`report_name`, `report_type`, `report_subtype`, `report_category`, `report_sql`, `description`, `core_report`, `use_report`) VALUES ('Center Schedule As On Date', 'Pentaho', NULL, NULL, NULL, NULL, 0, 1);

INSERT INTO `stretchy_report_parameter` (`report_id`, `parameter_id`, `report_parameter_name`) VALUES 
((select id from stretchy_report where report_name='Center Schedule As On Date'), 
(select id from stretchy_parameter where parameter_name='Selectbranch'), 'branch');


# script for procedure
DELIMITER $$
CREATE DEFINER = `root`@`%`PROCEDURE `ct_futuremeeting` ()
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
      
    Declare calenderId  integer;
    Declare startDate  Date;
    Declare recurrance  varchar(100);
    Declare currenctDate Date;    
    Declare nextmeetiingDate Date;
    Declare futuremeetingDate Date;
    Declare previousmeetingDate Date; 
    Declare freq varchar(100);
    Declare intervals integer DEFAULT 1;
    Declare byday varchar(100);
	 Declare days integer;  
	 DECLARE done INT DEFAULT 0;
	 DECLARE centerId integer;
	 DECLARE centerName varchar(100);	 
	 DECLARE noOfClients integer;
	 DECLARE demandAmount decimal(10,2);
	 DECLARE officeId integer;	 
	 DECLARE staffName varchar(100);
	 DECLARE  installments integer;
	  
	 
	
Declare  calenderInstance Cursor for 	        
	       SELECT  mcal.calendar_id as calenderId, mcal.entity_id as centerId,center.display_name as centerIdName,staff.display_name as staff,mc.start_date, mc.recurrence,center.office_id           
	       FROM  m_calendar_instance mcal	  
	       INNER JOIN m_group center on center.id=mcal.entity_id and mcal.entity_type_enum=4 and center.level_id=1 
	       LEFT JOIN m_calendar mc on mc.id =mcal.calendar_id 
	       LEFT JOIN m_staff staff on staff.id=center.staff_id; 
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
     
 TRUNCATE table  ct_meeting_schedule;
open calenderInstance;    
getCalenderId : loop   
Fetch calenderInstance into calenderId,centerId,centerName,staffName,startDate,recurrance,officeId;
SET freq := SUBSTR(SUBSTRING_INDEX(recurrance,';',1),6,length(SUBSTRING_INDEX(recurrance,';',1)) );
IF INSTR(recurrance,'INTERVAL')>0 THEN
SET intervals := right(SUBSTRING_INDEX(recurrance,';',2),1);
END IF;
IF INSTR(recurrance,'BYDAY')>0 THEN
SET byday := right(SUBSTRING_INDEX(recurrance,';',3),2);
END IF;
SET nextmeetiingDate := startDate;
IF freq ='MONTHLY' THEN             
WHILE nextmeetiingDate < curdate() DO         
SET nextmeetiingDate := DATE_ADD(nextmeetiingDate,INTERVAL intervals MONTH);        	
END WHILE;
IF   nextmeetiingDate =curdate() THEN
SET futuremeetingDate:= DATE_ADD(nextmeetiingDate,INTERVAL intervals MONTH);
END IF;   
            
ELSEIF freq ='WEEKLY' THEN 			       
WHILE nextmeetiingDate < curdate() DO                 
SET nextmeetiingDate := DATE_ADD(nextmeetiingDate,INTERVAL intervals WEEK);		         	
END WHILE;	
             
IF INSTR(recurrance,'BYDAY')>0 THEN
IF byday ='SU' THEN
SET days := 1;	                
ELSEIF byday = 'MO' THEN
SET days := 2;	                  
ELSEIF byday ='TU' THEN
SET days := 3;	                
ELSEIF byday ='WE' THEN	              
SET days :=4;  
ELSEIF byday ='TH' THEN	              
SET days :=5;  
 ELSEIF byday ='FR' THEN
SET days := 6;	                 
ELSEIF byday ='SA' THEN
SET days := 7; 
END IF;
IF DAYOFWEEK(nextmeetiingDate) >  days THEN
 SET nextmeetiingDate := DATE_ADD(nextmeetiingDate,INTERVAL (days-DAYOFWEEK(nextmeetiingDate)) day);					 
 ELSEIF DAYOFWEEK(nextmeetiingDate) <  days THEN
 SET nextmeetiingDate := DATE_ADD(nextmeetiingDate,INTERVAL (DAYOFWEEK(nextmeetiingDate)-days+7) day);
 ELSEIF  nextmeetiingDate = CURDATE() THEN
 SET futuremeetingDate:= DATE_ADD(nextmeetiingDate,INTERVAL intervals WEEK);
 END IF;					  
 END IF;
 ELSEIF freq ='DAILY' THEN
       
 WHILE nextmeetiingDate < curdate() DO
 SET nextmeetiingDate := DATE_ADD(nextmeetiingDate,INTERVAL intervals DAY);		         
  END WHILE;	
  IF   nextmeetiingDate =curdate() THEN
  SET futuremeetingDate:= DATE_ADD(nextmeetiingDate,INTERVAL intervals DAY);   
   END IF;
  END IF; 
			     select count(mgc.client_id) into noofClients from m_group_client mgc where mgc.group_id in (select id from m_group mg where mg.parent_id=centerId);
			     select sum(ifnull(mlrs.principal_amount,0)-ifnull(mlrs.principal_completed_derived,0)+ifnull(mlrs.interest_amount,0)-ifnull(mlrs.interest_completed_derived,0)
              + ifnull(mlrs.penalty_charges_amount,0)-ifnull(mlrs.penalty_charges_completed_derived,0)+ifnull(mlrs.fee_charges_amount,0)-ifnull(mlrs.fee_charges_completed_derived,0))
               ,mlrs.installment into demandAmount, installments
              from m_loan_repayment_schedule mlrs where mlrs.loan_id in (select ml.id  from m_loan ml where ml.client_id in ( 
              select mgc.client_id from m_group_client mgc where mgc.group_id in (select id from m_group mg where mg.parent_id=centerId))and ml.loan_type_enum=3
              and ml.loan_status_id=300)
              and mlrs.duedate =nextmeetiingDate;  
			            
			     
				
	 INSERT INTO `ct_meeting_schedule` (`officeId`, `center_id`, `center_name`,`staff_name` ,`no_of_clients`, `installment_no`, `demand_Amount`, `meeting_date`, `future_meeting_date`) VALUES (officeId, centerId, centerName,staffName, noofClients, installments, demandAmount, nextmeetiingDate, futuremeetingDate);
                SET nextmeetiingDate:=NULL;
                SET futuremeetingDate:=NULL;
                SET noofClients:=NULL;
                SET demandAmount:=NULL;
                SET installments:=NULL;
                SET staffName:=NULL;               
               SET intervals :=1;
	
IF done=1 THEN
LEAVE getCalenderId;
END IF;     
end loop getCalenderId;     
 Close calenderInstance; 
END $$


package org.mifosplatform.portfolio.cgtgrt.service;

import java.util.List;

import org.mifosplatform.portfolio.cgtgrt.data.TaskConfigurationData;

public interface TaskConfigurationReadplatformService {
	TaskConfigurationData retrieveTemplate();
	TaskConfigurationData retrievetaskConfigurationDetails(final Long taskconfigurationId) ;
    List<TaskConfigurationData> retrieveDetails() ;

}

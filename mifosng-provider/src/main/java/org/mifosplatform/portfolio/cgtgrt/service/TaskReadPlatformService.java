package org.mifosplatform.portfolio.cgtgrt.service;

import java.util.List;

import org.mifosplatform.portfolio.cgtgrt.data.Taskdata;
public interface TaskReadPlatformService {
	Taskdata retrieveTemplate(final Long officeId,final Long roleId);
    Taskdata retrievetaskDetail(final Long taskId) ;
    List<Taskdata> retrievetaskDetails(final Long centerId) ;
    
}

package org.mifosplatform.portfolio.rbl.service;

import org.mifosplatform.portfolio.rbl.data.RblGroupData;

public interface RblGroupReadPlatformService {
	RblGroupData retriveRblGroup(final Long groupId);

}

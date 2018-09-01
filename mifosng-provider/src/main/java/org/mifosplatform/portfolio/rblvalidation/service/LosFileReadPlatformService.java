package org.mifosplatform.portfolio.rblvalidation.service;

import java.util.List;

import org.mifosplatform.portfolio.rblvalidation.data.RblLosFileData;

public interface LosFileReadPlatformService {
	List<RblLosFileData>readLosFile(String fromDate,String toDate,String fileType);

}

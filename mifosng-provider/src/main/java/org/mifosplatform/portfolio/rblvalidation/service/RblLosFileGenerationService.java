package org.mifosplatform.portfolio.rblvalidation.service;

public interface RblLosFileGenerationService {
	void generateLosFile(String clientId,String centerId,String groupId,boolean centerDatatobesent,boolean groupdataTobesend,boolean isreprocess,boolean isImagetobesent);

}
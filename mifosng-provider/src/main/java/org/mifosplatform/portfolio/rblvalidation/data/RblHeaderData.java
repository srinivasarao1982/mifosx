package org.mifosplatform.portfolio.rblvalidation.data;

public class RblHeaderData {
	
	private String RequestUUID;
    private String ServiceName;
    private String MessageDateTime;
    private String ChannelId;
    private String CorpId;
    
	public RblHeaderData(String requestUUID, String serviceName, String messageDateTime, String channelId,
			String corpId) {
		super();
		RequestUUID = requestUUID;
		ServiceName = serviceName;
		MessageDateTime = messageDateTime;
		ChannelId = channelId;
		CorpId = corpId;
	}

	public RblHeaderData() {
		super();
	}

	public String getRequestUUID() {
		return RequestUUID;
	}

	public void setRequestUUID(String requestUUID) {
		RequestUUID = requestUUID;
	}

	public String getServiceName() {
		return ServiceName;
	}

	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}

	public String getMessageDateTime() {
		return MessageDateTime;
	}

	public void setMessageDateTime(String messageDateTime) {
		MessageDateTime = messageDateTime;
	}

	public String getChannelId() {
		return ChannelId;
	}

	public void setChannelId(String channelId) {
		ChannelId = channelId;
	}

	public String getCorpId() {
		return CorpId;
	}

	public void setCorpId(String corpId) {
		CorpId = corpId;
	}
    

}

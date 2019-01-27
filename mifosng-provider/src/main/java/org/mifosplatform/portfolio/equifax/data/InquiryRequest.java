package org.mifosplatform.portfolio.equifax.data;

public class InquiryRequest {
	public RequestBody RequestBody;
	public EquifaxHeaderData RequestHeader;
	
	
	public static InquiryRequest createInquireyRequest(org.mifosplatform.portfolio.equifax.data.RequestBody requestBody,
			EquifaxHeaderData requestHeader){
		
		return new InquiryRequest(requestBody,requestHeader);
	}
	
	public InquiryRequest(org.mifosplatform.portfolio.equifax.data.RequestBody requestBody,
			EquifaxHeaderData requestHeader) {
		super();
		RequestBody = requestBody;
		RequestHeader = requestHeader;
	}

	public RequestBody getRequestBody() {
		return RequestBody;
	}

	public void setRequestBody(RequestBody requestBody) {
		RequestBody = requestBody;
	}

	public EquifaxHeaderData getRequestHeader() {
		return RequestHeader;
	}

	public void setRequestHeader(EquifaxHeaderData requestHeader) {
		RequestHeader = requestHeader;
	}

}

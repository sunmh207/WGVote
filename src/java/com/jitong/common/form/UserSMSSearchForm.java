package com.jitong.common.form;


public class UserSMSSearchForm extends SearchForm{
	String recipientName;
	String businessId;
	String businessType;
	String status;
	String phoneNumber;
	String senderInfo;
	private String requestTime_start;
	private String requestTime_end;
	
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	@FieldCondition(op=Operation.eq)
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	@FieldCondition(op=Operation.eq)
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	@FieldCondition(op=Operation.eq)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestTime_start() {
		return requestTime_start;
	}
	public void setRequestTime_start(String requestTimeStart) {
		requestTime_start = requestTimeStart;
	}
	public String getRequestTime_end() {
		return requestTime_end;
	}
	public void setRequestTime_end(String requestTimeEnd) {
		requestTime_end = requestTimeEnd;
	}
	@FieldCondition(op=Operation.like)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@FieldCondition(op=Operation.like)
	public String getSenderInfo() {
		return senderInfo;
	}
	public void setSenderInfo(String senderInfo) {
		this.senderInfo = senderInfo;
	}
	
}

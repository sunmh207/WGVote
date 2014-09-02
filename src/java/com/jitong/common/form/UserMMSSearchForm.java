package com.jitong.common.form;


public class UserMMSSearchForm extends SearchForm{
	String recipientName;
	String businessId;
	String businessType;
	
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
	
	
}

package com.jitong.common.form;


public class UserDialSearchForm extends SearchForm{
	String recipientName;
	String finger;
	private String dtCreate_start;
	private String dtCreate_end;
	
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getFinger() {
		return finger;
	}
	public void setFinger(String finger) {
		this.finger = finger;
	}
	public String getDtCreate_start() {
		return dtCreate_start;
	}
	public void setDtCreate_start(String dtCreateStart) {
		dtCreate_start = dtCreateStart;
	}
	public String getDtCreate_end() {
		return dtCreate_end;
	}
	public void setDtCreate_end(String dtCreateEnd) {
		dtCreate_end = dtCreateEnd;
	}
	
	
}

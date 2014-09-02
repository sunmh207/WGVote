package com.jitong.console.domain;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;

public class Feedback {
	private String id;
	private String time;
	private String respondentId;//反馈人
	private String phoneNumber;
	private String email;
	private String title;
	private String desc;
	private String isFixed;
	private String solution;
	
	private String operatorId;
	private String sev;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="时间",order=0)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@JTField(fieldType=JTFieldType.UserID,chineseName="反馈人",order=5)
	public String getRespondentId() {
		return respondentId;
	}

	public void setRespondentId(String respondentId) {
		this.respondentId = respondentId;
	}
	@JTField(chineseName="电话",order=10)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@JTField(chineseName="邮件",order=15)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@JTField(chineseName="问题标题",order=20)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@JTField(chineseName="问题描述",order=25)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIsFixed() {
		return isFixed;
	}
	@JTField(chineseName="是否解决",order=30)
	public String getIsFixedTXT() {
		return "1".equals(isFixed)?"已解决":"未解决";
	}

	public void setIsFixed(String isFixed) {
		this.isFixed = isFixed;
	}
	@JTField(chineseName="解决方案",order=35)
	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	@JTField(fieldType=JTFieldType.UserID,chineseName="解决人",order=40)
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getSev() {
		return sev;
	}

	public void setSev(String sev) {
		this.sev = sev;
	}
	
	
}

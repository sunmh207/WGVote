package com.jitong.console.domain;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;

public class LimitIP {
	private String id;
	private String ip;
	private String caption;
	private String creatorId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="IP",order=0)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	@JTField(chineseName="机器名",order=5)
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	@JTField(fieldType=JTFieldType.UserID,chineseName="创建者",order=10)
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

}

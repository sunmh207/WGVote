package com.jitong;

import com.jitong.console.domain.User;
import com.opensymphony.xwork2.ActionContext;

public class JitongAppContext {

	public static User getOperator() {
		return (User) ActionContext.getContext().getSession()
				.get(JitongConstants.USER);
	}

	public static String getSenderInfo() {
		User operator = getOperator();
		return "用户名："+operator.getName()+" IP:"+operator.getLastLoginIp();
	}
}

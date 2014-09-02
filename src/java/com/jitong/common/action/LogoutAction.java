package com.jitong.common.action;

import java.util.Map;

import com.jitong.JitongConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.remove(JitongConstants.USER);
		return SUCCESS;
	}
}

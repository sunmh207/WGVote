package com.jitong.common.action;

import com.opensymphony.xwork2.ActionSupport;

public class NoAuthAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}

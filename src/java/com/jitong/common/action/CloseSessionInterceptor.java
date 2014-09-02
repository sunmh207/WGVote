package com.jitong.common.action;

import org.apache.log4j.Logger;

import com.jitong.common.util.DBtools;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CloseSessionInterceptor extends AbstractInterceptor {
	private static Logger logger = Logger.getLogger(CloseSessionInterceptor.class);

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {

		String result = actionInvocation.invoke();
		DBtools.closeSession();
		return result;
	}

}

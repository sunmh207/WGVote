package com.stanley.common.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.stanley.common.JitongConstants;

public class AuthInterceptor extends AbstractInterceptor {
	private static Logger logger = Logger.getLogger(AuthInterceptor.class);
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		logger.debug(session);
		if(session.get(JitongConstants.USER)==null){
			return Action.LOGIN;
		}
		
		return actionInvocation.invoke();
	}

}

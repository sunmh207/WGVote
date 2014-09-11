package com.stanley.common.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;



public class WelcomeAction extends ActionSupport implements ServletRequestAware, SessionAware {
	private static Logger logger = Logger.getLogger(WelcomeAction.class);
	protected HttpServletRequest request;
	protected Map<String, Object> session;
	
	@Override
	public String execute() throws Exception {
		logger.debug("in welcome action.");
		logger.debug(this.session);
		return SUCCESS;
	}
	public HttpServletRequest getRequest() {
		return this.request;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

}

package com.jitong.common.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.jitong.JitongConstants;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends JITActionBase {

	private static Logger logger = Logger.getLogger(LoginAction.class);
	private String username;
	private String password;
	private String redirect;

	private String isInIpRange;

	protected HttpServletRequest request;
	protected Map<String, Object> session;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	

	@Override
	public String execute() throws Exception {
		/*String ip = ActionUtil.getRemoteAddr(request);
		if (SysCache.isPermittedIP(ip)) {
			isInIpRange = "1";
		} else {
			isInIpRange = "0";
		}*/
		return INPUT;
	}

/**
 * internal login
 * @return
 * @throws Exception
 */
	public String login() throws Exception {
		if (StringUtil.isEmpty(getUsername())) {
			addActionError("用户名不能为空。");
			return INPUT;
		}
		if(JitongConstants.ADMIN.equalsIgnoreCase(username)&&JitongConstants.PASSWORD.equals(password)){
			User u = new User();
			u.setName(username);
			session.put(JitongConstants.USER, u);
			return SUCCESS; 
		}else{
			addActionError("用户名或密码错误。");
			return INPUT;
		}
		/*String ip = ActionUtil.getRemoteAddr(request);
		UserService userService = new UserService();
		try {
			User user = userService.verifyLogon(username, password, session);
			if (user == null) {
				addActionError("用户名或密码错误。");
				return INPUT;
			}
			user.setLastLoginIp(ip);
			user.setLastLoginTime(DateUtil.getCurrentTime());
			logger.debug(getUsername());
			session.put(JitongConstants.USER, user);
		} catch (JTException e) {
			String errorMsg = e.getMessage();
			addActionError(errorMsg);
			return INPUT;
		}*/
		
		//return SUCCESS;

	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}


	public String getIsInIpRange() {
		return isInIpRange;
	}

	public void setIsInIpRange(String isInIpRange) {
		this.isInIpRange = isInIpRange;
	}

}

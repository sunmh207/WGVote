package com.jitong.console.action;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.console.service.UserService;
import com.opensymphony.xwork2.Preparable;

public class ChangePasswordAction extends JITActionBase implements Preparable {
	private String newPassword;
	private String newPasswordConfirm;

	private static UserService userService;


	public void prepare() throws JTException {
		if (userService == null) {
			userService = new UserService();
		}
	}

	public String input() {
		return INPUT;
	}

	public String save() throws Exception {
		User u = this.getLoginUser();
		if(u==null){
			addActionError("用户超时");
			return input();
		}
		User user = userService.findUser(u.getId());
		if(StringUtil.isEmpty(newPassword)||StringUtil.isEmpty(newPasswordConfirm)){
			addActionError("新密码不能为空");
			return input();
		}
		if(!newPassword.equals(newPasswordConfirm)){
			addActionError("两次输入的密码不相同");
			return input();
		}
		user.setPassword(StringUtil.md5(newPassword));
		userService.updateBo(user);
		SysCache.reloadUserById(user.getId(), SysCache.OPER_UPDATE);
		
		addActionError("密码修改成功。");
		return input();
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	
}

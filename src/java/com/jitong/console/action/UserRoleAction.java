package com.jitong.console.action;

import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.Role;
import com.jitong.console.domain.User;
import com.jitong.console.service.RoleService;
import com.jitong.console.service.UserRoleService;
import com.opensymphony.xwork2.Preparable;

public class UserRoleAction extends JITActionBase implements Preparable {

	private static UserRoleService urService;
	private static RoleService roleService;
	private Role role;
	private List<User> users;
	private String userId;

	public void prepare() throws JTException {
		if (urService == null) {
			urService = new UserRoleService();
		}
		if (roleService == null) {
			roleService = new RoleService();
		}
		if (role != null && role.getRoleId() != null) {
			role = roleService.findRole(role.getRoleId());
			users = urService.queryUserByRoleId(role.getRoleId());
		}

	}

	public String listUsers() throws JTException {
		if (role != null && role.getRoleId() != null) {
			users = urService.queryUserByRoleId(role.getRoleId());
		}
		return INPUT;
	}

	public String deleteUser() throws JTException {
		urService.deleteRoleUser(userId, role.getRoleId());
		return listUsers();
	}
	public String deleteUsers() throws JTException {
		String[] userIds = request.getParameterValues("chk");
		if(userIds.length>0){
			for(String uid: userIds){
				urService.deleteRoleUser(uid, role.getRoleId());
			}
		}
		return listUsers();
	}
	public String addUsers() throws JTException {
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for(String id:ids){
			if(role.getCreatorId().equals(id)){
				addActionError("不能把用户加入自己创建的角色：本角色是由用户‘"+SysCache.interpertUserName(id)+"’创建的，不能将'"+SysCache.interpertUserName(id)+"'加入本角色");
				return INPUT;
			}else if(urService.isSuperUser(role.getCreatorId(),id)){
				addActionError("不能把上级用户加入本角色：‘"+SysCache.interpertUserName(id)+"’是’"+SysCache.interpertUserName(role.getCreatorId())+"‘的上级用户");
				return INPUT;
			}
		}
		urService.addRoleUsers(ids, role.getRoleId());
		return listUsers();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryId() {
		return "console.role";
	}
}

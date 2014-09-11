package com.stanley.console.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.stanley.common.JitongConstants;
import com.stanley.common.action.JITActionBase;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.DateUtil;
import com.stanley.common.util.StringUtil;
import com.stanley.common.util.SysCache;
import com.stanley.console.domain.Role;
import com.stanley.console.domain.User;
import com.stanley.console.service.RoleService;

public class RoleAction extends JITActionBase implements Preparable {

	private static RoleService roleService;
	private Role role;
	private List<Role> roles;
	private String qryCreatorName;
	private String qryRoleName;

	public void prepare() throws JTException {
		if (roleService == null) {
			roleService = new RoleService();
		}
		if (role != null && role.getRoleId() != null) {
			role = roleService.findRole(role.getRoleId());
		}

	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		roleService.deleteRole(role.getRoleId());
		SysCache.roleMap.remove(role.getRoleId());
		return list();
	}

	public String save() throws JTException {
		if (role.getRoleId() == null || "".equals(role.getRoleId())) {
			User user = (User) session.get(JitongConstants.USER);
			if (user != null) {
				role.setCreatorId(user.getId());
				role.setCreatorName(user.getName());
				role.setCreateTime(DateUtil.getCurrentTime(JitongConstants.JT_DATE_FORMAT));
			}
			roleService.insertRole(role);
			SysCache.roleMap.put(role.getRoleId(), role);
		} else {
			roleService.updateRole(role);
			SysCache.roleMap.put(role.getRoleId(), role);
		}
		return SUCCESS;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getCategoryId() {
		return "console.role";
	}

	public String getQryCreatorName() {
		return qryCreatorName;
	}

	public void setQryCreatorName(String qryCreatorName) {
		this.qryCreatorName = qryCreatorName;
	}

	public String getQryRoleName() {
		return qryRoleName;
	}

	public void setQryRoleName(String qryRoleName) {
		this.qryRoleName = qryRoleName;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "";
		User loginUser = (User) session.get(JitongConstants.USER);
		// user can only see his/her own role (created by themselvies).
		if(JitongConstants.ADMIN.equals(loginUser.getLoginName())){
			hql = "select r from Role r where 1=1 ";
		}else{
			hql = "select r from User u, Role r where u.id = r.creatorId and u.loginName ='" + loginUser.getLoginName() + "'";
		}
		
		if(!StringUtil.isEmpty(qryCreatorName)){
			hql += " and r.creatorName like '%"+qryCreatorName+"%'";
		}
		if(!StringUtil.isEmpty(qryRoleName)){
			hql += " and r.roleName like '%"+qryRoleName+"%'";
		}
		return hql;
	}
}

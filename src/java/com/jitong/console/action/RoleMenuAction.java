package com.jitong.console.action;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.console.domain.Menu;
import com.jitong.console.domain.Role;
import com.jitong.console.domain.RoleMenu;
import com.jitong.console.domain.User;
import com.jitong.console.service.RoleMenuService;
import com.jitong.console.service.RoleService;
import com.jitong.console.service.UserService;
import com.opensymphony.xwork2.Preparable;

public class RoleMenuAction extends JITActionBase implements Preparable {

	private static RoleService roleService;
	private static RoleMenuService roleMenuService;
	private Role role;

	public void prepare() throws JTException {
		if (roleService == null) {
			roleService = new RoleService();
		}
		if (roleMenuService == null) {
			roleMenuService = new RoleMenuService();
		}

		if (role != null && role.getRoleId() != null) {
			role = roleService.findRole(role.getRoleId());
			Hashtable<String, RoleMenu> menuHash = roleMenuService.queryRoleMenus(role.getRoleId());
			request.setAttribute("menuHash", menuHash);
			User user = (User) session.get(JitongConstants.USER);
			UserService uService=new UserService();
			Map<String, List<Menu>> userModuleMenuMap = uService.queryUserModuleMenu(user.getId());
			request.setAttribute("userModuleMenuMap", userModuleMenuMap);
		}

	}

	public String listRole() throws JTException {
		return INPUT;
	}

	public String updateMenus() throws JTException {
		String[] ids = request.getParameterValues("chk");
		roleMenuService.cleanAndInsertRoleMenus(ids, role.getRoleId());
		return SUCCESS;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCategoryId() {
		return "console.role";
	}
}

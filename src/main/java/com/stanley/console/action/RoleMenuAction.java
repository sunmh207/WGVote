package com.stanley.console.action;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Preparable;
import com.stanley.common.JitongConstants;
import com.stanley.common.action.JITActionBase;
import com.stanley.common.exception.JTException;
import com.stanley.console.domain.Menu;
import com.stanley.console.domain.Role;
import com.stanley.console.domain.RoleMenu;
import com.stanley.console.domain.User;
import com.stanley.console.service.RoleMenuService;
import com.stanley.console.service.RoleService;
import com.stanley.console.service.UserService;

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

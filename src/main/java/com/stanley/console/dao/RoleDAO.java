package com.stanley.console.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.stanley.common.dao.BaseDAO;
import com.stanley.common.exception.JTException;
import com.stanley.console.domain.Role;

public class RoleDAO extends BaseDAO {


	public Role findRole(String roleId) throws JTException {
		try {
			return (Role) super.findById(Role.class, roleId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find Role. roleId=" + roleId, e, this.getClass());
		}
	}

	public List<Role> queryRoles() throws JTException {
		try {
			return (List<Role>) super.find("from Role r");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query Roles.", e, this.getClass());
		}
	}

	/*public Hashtable<String, RoleMenu> queryRoleMenus(String roleId) throws JTException {
		List<RoleMenu> list = super.find("from RoleMenu rm where rm.roleId = '" + roleId + "'");
		Hashtable<String, RoleMenu> roleMenuHash = new Hashtable<String, RoleMenu>();
		if (!list.isEmpty()) {
			for(int i=0;i<list.size();i++){
			RoleMenu menu = new RoleMenu();
			BeanUtil.copyProperties(menu, list.get(i));
			roleMenuHash.put(menu.getMenuId(), menu);
			}
		}
		return roleMenuHash;
	}

	public void insertOrUpdateRoleMenus(RoleMenu[] menus) throws JTException {
		if (menus == null || menus.length == 0) {
			return;
		}

		for (int i = 0; i < menus.length; i++) {
			RoleMenu menu = menus[i];
			if (menu.getRoleMenuId() == null) {
				super.createBo(menu);
			} else {
				super.updateBo(menu);
			}
		}
	}

	public void cleanAndInsertRoleMenus(String[] menuIds, String roleId) throws JTException {
		String hql = "from RoleMenu u where u.roleId='" + roleId + "'";
		List<UserRole> list = super.find(hql);
		super.deleteAll(list);

		if (menuIds == null || menuIds.length == 0)
			return;

		for (int i = 0; i < menuIds.length; i++) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setMenuId(menuIds[i]);
			roleMenu.setRoleId(roleId);
			super.createBo(roleMenu);
		}
	}*/

}

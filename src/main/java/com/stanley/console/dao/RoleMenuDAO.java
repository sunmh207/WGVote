package com.stanley.console.dao;

import java.util.Hashtable;
import java.util.List;

import com.stanley.common.dao.BaseDAO;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.BeanUtil;
import com.stanley.console.domain.RoleMenu;
import com.stanley.console.domain.UserRole;

public class RoleMenuDAO extends BaseDAO {



	public Hashtable<String, RoleMenu> queryRoleMenus(String roleId) throws JTException {
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
	
	/*
	public Hashtable<String, RoleMenu> queryUserMenus(String userId) throws JTException {
		List<RoleMenu> list = super.find("select rm from UserRole ur, RoleMenu rm where ur.roleId=rm.roleId and ur.userId= '" + userId + "'");
		Hashtable<String, RoleMenu> roleMenuHash = new Hashtable<String, RoleMenu>();
		if (!list.isEmpty()) {
			for(int i=0;i<list.size();i++){
			RoleMenu menu = new RoleMenu();
			BeanUtil.copyProperties(menu, list.get(i));
			roleMenuHash.put(menu.getMenuId(), menu);
			}
		}
		return roleMenuHash;
	}*/
	

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
	}

}

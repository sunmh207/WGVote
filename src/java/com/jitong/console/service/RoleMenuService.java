package com.jitong.console.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.console.dao.RoleMenuDAO;
import com.jitong.console.domain.Menu;
import com.jitong.console.domain.RoleMenu;

public class RoleMenuService extends BaseService {
	RoleMenuDAO dao;

	public RoleMenuService(Session s) {
		super(s);
		dao = new RoleMenuDAO(s);
	}

	public RoleMenuService() throws JTException {
		super();
		dao = new RoleMenuDAO(s);
	}

	/**
	 * <menuId,menu obj>
	 */
	public Hashtable<String, RoleMenu> queryRoleMenus(String roleId) throws JTException {
		return dao.queryRoleMenus(roleId);
	}

	/*public Hashtable<String, RoleMenu> queryUserMenus(String userId) throws JTException {
		return dao.queryUserMenus(userId);
	}*/
	

	/*
	 * public Hashtable<String, RoleMenu> queryAdminMenus() throws JTException {
	 * Hashtable<String, RoleMenu> menuHash = new Hashtable<String, RoleMenu>();
	 * List<CategoryItem> topCategoryItems =
	 * ContentItemManager.getTopCategoryItems(); if (topCategoryItems != null &&
	 * topCategoryItems.size() > 0) { for (CategoryItem topItem :
	 * topCategoryItems) { RoleMenu topMenu = new RoleMenu();
	 * topMenu.setMenuId(topItem.getKey()); menuHash.put(topMenu.getMenuId(),
	 * topMenu);
	 * 
	 * List<CategoryItem> subCategoryItems =
	 * ContentItemManager.getSubCategoryItems(topItem.getKey()); if
	 * (subCategoryItems != null && subCategoryItems.size() > 0) { for
	 * (CategoryItem subItem : subCategoryItems) { RoleMenu subMenu = new
	 * RoleMenu(); subMenu.setMenuId(subItem.getKey());
	 * menuHash.put(subMenu.getMenuId(), subMenu); } }
	 * 
	 * } } return menuHash; }
	 */

	public Map<String, List<Menu>> queryAdminModuleMenus() throws JTException {
		Map<String, List<Menu>> moduleMenuMap = new HashMap<String, List<Menu>>();
		List<Menu> allMenuList = queryAllMenus();
		for (Menu menu : allMenuList) {
			String moduleId = menu.getModuleId();
			if (moduleMenuMap.containsKey(moduleId)) {
				List<Menu> menuList = moduleMenuMap.get(moduleId);
				menuList.add(menu);
			} else {
				List<Menu> menuList = new ArrayList<Menu>();
				menuList.add(menu);
				moduleMenuMap.put(moduleId, menuList);
			}
		}
		return moduleMenuMap;
	}

	public void insertOrUpdateRoleMenus(RoleMenu[] menus) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.insertOrUpdateRoleMenus(menus);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}

	public void cleanAndInsertRoleMenus(String[] menuIds, String roleId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.cleanAndInsertRoleMenus(menuIds, roleId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}

	public List<Menu> queryAllMenus() throws JTException {
		List<Menu> menuList = new ArrayList<Menu>();
		//List<Object> l = super.queryAll(Menu.class);
		List<Object> l = super.queryByHql("from Menu m order by m.order+0");
		
		for (Object o : l) {
			menuList.add((Menu) o);
		}
		return menuList;
	}

}

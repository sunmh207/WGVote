package com.jitong.console.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.SysCache;
import com.jitong.console.dao.RoleDAO;
import com.jitong.console.domain.Role;
import com.jitong.console.domain.User;

public class RoleService extends BaseService {
	RoleDAO dao;

	public RoleService(Session s) {
		super(s);
		dao = new RoleDAO(s);
	}

	public RoleService() throws JTException {
		super();
		dao = new RoleDAO(s);
	}

	public Role findRole(String roleId) throws JTException {
		return dao.findRole(roleId); 
	}

	public List<Role> queryRoles() throws JTException {
		return dao.queryRoles();
	}

	public void deleteRole(String roleId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(Role.class, roleId);
			UserRoleService urs= new UserRoleService(s);
			List<User> mgrs = urs.queryUserByRoleId(roleId);
			for(User u:mgrs){
				urs.deleteRoleUser(u.getId(), roleId);
			}
			
			RolePersonService rps= new RolePersonService(s);
			List<User> persons = rps.queryPersonsByRoleId(roleId);
			for(User p:persons){
				rps.deleteRolePerson(p.getId(), roleId);
			}
		//	SysCache.reloadPersonMgrMapByRoleId(roleId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public void insertRole(Role role) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.createBo(role);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}
	
	public void updateRole(Role role) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(role);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}
	
	//////////////////RoleMenu/////////////////
	/**
	 * <menuId,menu obj>
	 */
	/*public Hashtable<String,RoleMenu> queryRoleMenus(String roleId) throws JTException {
		return dao.queryRoleMenus(roleId);
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
	}*/
	
}

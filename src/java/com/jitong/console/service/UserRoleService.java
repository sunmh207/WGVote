package com.jitong.console.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.JitongConstants;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.SysCache;
import com.jitong.console.dao.UserRoleDAO;
import com.jitong.console.domain.Role;
import com.jitong.console.domain.User;
import com.jitong.console.domain.UserRole;

public class UserRoleService extends BaseService {
	UserRoleDAO dao;

	public UserRoleService(Session s) {
		super(s);
		dao = new UserRoleDAO(s);
	}

	public UserRoleService() throws JTException {
		super();
		dao = new UserRoleDAO(s);
	}

	public List<User> queryUserByRoleId(String roleID) throws JTException {
		return dao.queryUserByRoleId(roleID);
	}

	public List<Role> queryRoleByUserId(String userID) throws JTException {
		return dao.queryRoleByUserId(userID);
	}
	
	/**
	 * show the role list which contain this member
	 */
	public List<Role> queryRoleByMemberId(String userID) throws JTException {
		return dao.queryRoleByMemberId(userID);
	}

	/**
	 * 查询上级角色（递归查找，直到最上层）
	 * 
	 * @param userID
	 * @return
	 * @throws JTException
	 */
	private List<Role> querySuperRole(String userID, int dept) throws JTException {
		if(dept<=0){
			return new ArrayList<Role>();
		}
		List<Role> superRoleList = new ArrayList<Role>();
		List<Role> rolelist = dao.queryRoleByUserId(userID);
		if (rolelist != null && rolelist.size() > 0) {
			superRoleList.addAll(rolelist);
			for (Role role : rolelist) {

				User creator = SysCache.interpertUser(role.getCreatorId());
				//System.out.println(role.getRoleName() + "==>" + (creator == null ? "null creator" : creator.getLoginName()));
				if (creator == null || JitongConstants.ADMIN.equals(creator.getLoginName())) {
					continue;
				}
				//System.out.println(role.getRoleName() + "->" + creator.getLoginName());
				superRoleList.addAll(querySuperRole(role.getCreatorId(),dept-1));
			}
		}
		return superRoleList;
	}

	public List<Role> queryRoleAndSuperRoleByUserId(String userID) throws JTException {
		List<Role> list = querySuperRole(userID,4);
		List<Role> rolelist = new ArrayList<Role>();
		if (list != null && list.size() > 0) {
			Map map = new HashMap();
			for (Role role : list) {
				map.put(role.getRoleId(), role);
			}
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				rolelist.add((Role) map.get(it.next()));
			}

		}
		return rolelist;
	}

	/**
	 * delete some members from the role
	 * 
	 * @param ids
	 * @param roleId
	 * @throws JTException
	 */
	public void deleteRoleUsers(String[] ids, String roleId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			if(ids==null&&ids.length==0) return;
			for(String id:ids){
				deleteRoleUser(id,roleId);
			}
			//dao.deleteRoleUsers(ids, roleId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除失败", e, this.getClass());
		}
	}

	/**
	 * delete a member from a role
	 * 
	 * @param id
	 * @param roleId
	 * @throws JTException
	 */
	public void deleteRoleUser(String userId, String roleId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteRoleUser(userId, roleId);
			//SysCache.updateUserRolesMap(id, roleId, "delete");
			
			//update personManagerMap
			updatePersonManagersMap(userId,roleId,"delmanager");
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除失败", e, this.getClass());
		}
	}
	/**
	 * 删除用户下的所有角色
	 * @param userId
	 * @throws JTException
	 */
	public void deleteRoleUserByUserId(String userId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			
			List<UserRole> oldRoles = dao.queryUserRoleByUserId(userId);
			if(oldRoles==null){
				return;
			}
			for(UserRole ur:oldRoles){
				deleteRoleUser(userId,ur.getRoleId());
			}
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除失败", e, this.getClass());
		}
	}
	/**
	 * add a member into a role
	 * 
	 * @param userId
	 * @param roleId
	 * @throws JTException
	 */
	public void addRoleUser(String userId, String roleId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			if (this.existUserRole(userId, roleId)) {
				return;
			}
			UserRole ru = new UserRole();
			ru.setUserId(userId);
			ru.setRoleId(roleId);
			dao.createBo(ru);
			//SysCache.updateUserRolesMap(userId, roleId, "add");
			
			//update personManagerMap
			//updatePersonManagersMap(roleId);
			updatePersonManagersMap(userId,roleId,"addmanager");
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public UserRole findRoleUser(String userId, String roleId) throws JTException {
		return dao.findRoleUser(userId, roleId);
	}

	public boolean existUserRole(String userId, String roleId) throws JTException {
		UserRole ur = this.findRoleUser(userId, roleId);
		if (ur != null) {
			return true;
		}
		return false;
	}

	/**
	 * add members into a role
	 * 
	 * @param userIds
	 * @param roleId
	 * @throws JTException
	 */
	public void addRoleUsers(String[] userIds, String roleId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			if (userIds != null && userIds.length > 0) {
				for (int i = 0; i < userIds.length; i++) {
					addRoleUser(userIds[i], roleId);
				}
			}
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public boolean isSuperUser(String userId, String superUserId) throws JTException {
		List<Role> list = queryRoleAndSuperRoleByUserId(userId);
		for (Role r : list) {
			if (superUserId.equals(r.getCreatorId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * get all person in role(roleId), and reload their managers.
	 * @param roleId
	 * @throws JTException
	 */
	/*private void updatePersonManagersMap(String roleId) throws JTException{
		RolePersonService rps= new RolePersonService(s);
		List<User> persons =rps.queryPersonsByRoleId(roleId);
		if(persons!=null&&!persons.isEmpty()){
			for(User person:persons){
				SysCache.reloadPersonMgrMap(person.getId(),s);
			}
		}
	}*/
	private void updatePersonManagersMap(String managerId,String roleId,String act) throws JTException{
		if("addmanager".equals(act)){
			RolePersonService rps= new RolePersonService(s);
			List<User> persons =rps.queryPersonsByRoleId(roleId);
			for(User person:persons){
				SysCache.updatePersonMgrMap(person.getId(),managerId,"add");
			}
		}else if("delmanager".equals(act)){
			RolePersonService rps= new RolePersonService(s);
			List<User> persons =rps.queryPersonsByRoleId(roleId);
			for(User person:persons){
				SysCache.updatePersonMgrMap(person.getId(),managerId,"del");
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		SysCache.loadSysCache();
		UserRoleService service = new UserRoleService();
		List<Role> list = service.queryRoleAndSuperRoleByUserId("8a8288fe2d7e6636012d7e70495f000a");
		for (Role role : list) {
			System.out.println("==" + role.getRoleName());
		}
	}
}

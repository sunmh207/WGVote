package com.stanley.console.dao;

import java.util.ArrayList;
import java.util.List;

import com.stanley.common.dao.BaseDAO;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.BeanUtil;
import com.stanley.console.domain.Role;
import com.stanley.console.domain.User;
import com.stanley.console.domain.UserRole;

public class UserRoleDAO extends BaseDAO {


	/**
	 * 查询指定角色所拥有的用户列表
	 * 
	 * @param roleID
	 *            角色ID
	 * @return List
	 */
	public List<User> queryUserByRoleId(String roleID) throws JTException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select u from User u,UserRole ur where u.id=ur.userId and ur.roleId='").append(roleID).append("'");
			String s = sb.toString();
			List<User> list = super.find(s);
			List rList = new ArrayList();

			if (list != null) {
				int len = list.size();
				for (int i = 0; i < len; i++) {
					User u = new User();
					Object o = list.get(i);
					BeanUtil.copyProperties(u, o);
					rList.add(u);
				}
			}
			return rList;

		} catch (Exception e) {
			throw new JTException("读取数据错误", e, this.getClass());
		}
	}

	/**
	 * 查询指定用户所属的角色列表
	 * 
	 * @param userID
	 *            用户ID
	 * @return list
	 */
	public List<Role> queryRoleByUserId(String userID) throws JTException {
		try {
			String hql = "select r from Role r,UserRole ur where r.roleId=ur.roleId and ur.userId='" + userID + "'";
			List<Role> list = super.find(hql);
			List<Role> list2 = new ArrayList<Role>();

			int len = list.size();
			for (int i = 0; i < len; i++) {
				Object obj = list.get(i);
				Role role = new Role();
				BeanUtil.copyProperties(role, obj);
				list2.add(role);
			}
			return list2;
		} catch (Exception e) {
			throw new JTException("读取数据错误", e, this.getClass());
		}

	}
	public List<Role> queryRoleByMemberId(String personId) throws JTException {
		try {
			String hql = "select r from Role r,RolePerson rp where r.roleId=rp.roleId and rp.personId='" + personId + "'";
			List<Role> list = super.find(hql);
			List<Role> list2 = new ArrayList<Role>();
			
			int len = list.size();
			for (int i = 0; i < len; i++) {
				Object obj = list.get(i);
				Role role = new Role();
				BeanUtil.copyProperties(role, obj);
				list2.add(role);
			}
			return list2;
		} catch (Exception e) {
			throw new JTException("读取数据错误", e, this.getClass());
		}
		
	}

	/**
	 * 将用户从角色中移除
	 * 
	 * @param ids
	 *            用户ID数组
	 * @param roleId
	 *            角色ID
	 * @throws JTException
	 */

	public void deleteRoleUsers(String[] ids, String roleId) throws JTException {
		try {
			String InStr = "";
			for (int i = 0; i < ids.length; i++) {
				if (i == 0)
					InStr += "'" + ids[i] + "'";
				else
					InStr += ",'" + ids[i] + "'";
			}
			String sql = "from UserRole u where u.userId in (" + InStr + ") and u.roleId='" + roleId + "'";
			List<UserRole> list = super.find(sql);
			super.deleteAll(list);

		} catch (Exception e) {
			throw new JTException("移出用户失败", e, this.getClass());
		}

	}

	public void deleteRoleUser(String userId, String roleId) throws JTException {
		try {
			String sql = "from UserRole u where u.userId = '" + userId + "'  and u.roleId='" + roleId + "'";
			List<UserRole> list = super.find(sql);
			super.deleteAll(list);

		} catch (Exception e) {
			throw new JTException("移出用户失败", e, this.getClass());
		}
	}

	public UserRole findRoleUser(String userId, String roleId) throws JTException {
		try {
			String sql = "from UserRole u where u.userId = '" + userId + "'  and u.roleId='" + roleId + "'";
			List<UserRole> list = super.find(sql);
			if (list != null && !list.isEmpty()) {
				return (UserRole) list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new JTException("查询用户失败", e, this.getClass());
		}
	}
	public List<UserRole>  queryUserRoleByUserId(String userId) throws JTException {
		try {
			String sql = "from UserRole u where u.userId = '" + userId + "' ";
			List<UserRole> list = super.find(sql);
			return list;
		} catch (Exception e) {
			throw new JTException("查询用户角色关系失败", e, this.getClass());
		}
	}
}

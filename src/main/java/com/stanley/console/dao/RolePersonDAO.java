package com.stanley.console.dao;

import java.util.ArrayList;
import java.util.List;

import com.stanley.common.dao.BaseDAO;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.BeanUtil;
import com.stanley.console.domain.RolePerson;
import com.stanley.console.domain.User;
import com.stanley.console.domain.UserRole;

public class RolePersonDAO extends BaseDAO {

	/**
	 * 查询指定角色的人员权限列表
	 * 
	 * @param roleID
	 *            角色ID
	 * @return List
	 */
	public List<User> queryPersonsByRoleId(String roleID) throws JTException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select u from User u,RolePerson up where u.id=up.personId and up.roleId='").append(roleID).append("'");
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
	 * 将人员从角色中移除
	 * 
	 * @param ids
	 *            人员ID数组
	 * @param roleId
	 *            角色ID
	 * @throws JTException
	 */

	/*public void deleteRolePersons(String[] ids, String roleId) throws JTException {
		try {
			String InStr = "";
			for (int i = 0; i < ids.length; i++) {
				if (i == 0)
					InStr += "'" + ids[i] + "'";
				else
					InStr += ",'" + ids[i] + "'";
			}
			String sql = "from RolePerson u where u.personId in (" + InStr + ") and u.roleId='" + roleId + "'";
			List<UserRole> list = super.find(sql);
			super.deleteAll(list);

		} catch (Exception e) {
			throw new JTException("移出人员失败", e, this.getClass());
		}
	}*/

	public void deleteRolePerson(String personId, String roleId) throws JTException {
		try {
			String sql = "from RolePerson u where u.personId = '" + personId + "'  and u.roleId='" + roleId + "'";
			List<UserRole> list = super.find(sql);
			super.deleteAll(list);

		} catch (Exception e) {
			throw new JTException("移出人员失败", e, this.getClass());
		}

	}

	public RolePerson findRolePerson(String personId, String roleId) throws JTException {
		try {
			String sql = "from RolePerson u where u.personId = '" + personId + "'  and u.roleId='" + roleId + "'";
			List<RolePerson> list = super.find(sql);
			if (list != null && !list.isEmpty()) {
				return (RolePerson) list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new JTException("查询人员失败", e, this.getClass());
		}
	}
}

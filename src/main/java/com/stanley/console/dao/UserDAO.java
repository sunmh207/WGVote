package com.stanley.console.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.hibernate.HibernateException;

import com.stanley.common.JitongConstants;
import com.stanley.common.dao.BaseDAO;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.BeanUtil;
import com.stanley.common.util.SysCache;
import com.stanley.console.domain.Menu;
import com.stanley.console.domain.RolePerson;
import com.stanley.console.domain.User;

public class UserDAO extends BaseDAO {


	public User findUser(String userId) throws JTException {
		try {
			return (User) super.findById(User.class, userId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find User. userId=" + userId, e, this.getClass());
		}
	}

	public User findUserByLoginName(String loginName) throws JTException {
		try {
			List<User> list = (List<User>) super.find("from User u where u.loginName='" + loginName + "'");
			if (!list.isEmpty()) {
				return list.get(0);
			}
			return null;
		} catch (HibernateException e) {
			throw new JTException("Error occured when find User.loginName=" + loginName, e, this.getClass());
		}
	}

	public List<User> queryUsers() throws JTException {
		try {
			return (List<User>) super.find("from User u");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query Users.", e, this.getClass());
		}
	}
	/**
	 * 查询用户所属的角色
	 * @return
	 * @throws JTException
	 */
	/*public List<Role> queryRolesByUser(String userId) throws JTException {
		try {
			return (List<Role>) super.find("select r from Role r,UserRole ur where r.roleId=ur.roleId and ur.userId='"+userId+"'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query Users.", e, this.getClass());
		}
	}*/

	public User verifyLogon(String loginName, String password) throws JTException {
		try {
			User user;
			loginName = loginName.trim();
			password = password.trim();
			List list = super.find("from User u where u.loginName='" + loginName + "'");
			if (!list.isEmpty()) {
				user = new User();
				BeanUtil.copyProperties(user, list.get(0));
				if (password.equals(user.getPassword()))
					return user;
			}
			return null;
		} catch (Exception e) {
			throw new JTException("读取用户信息错误", e, this.getClass());
		}
	}

	/**
	 * 查询用户的菜单权限列表
	 * 
	 * @param userId
	 * @return
	 * @throws JTException
	 */
	public Map<String, List<Menu>> queryUserModuleMenu(String userId) throws JTException {

		User user = SysCache.interpertUser(userId);
		if (JitongConstants.ADMIN.equals(user.getLoginName())) {
			return SysCache.moduleMenuMap;
		} else {
			Map<String, List<Menu>> userModuleMenuMap = new TreeMap<String, List<Menu>>();
			List<Menu> list = super.find("select menu from Menu menu, RoleMenu rm, UserRole ur where rm.roleId = ur.roleId and ur.userId = '" + userId
					+ "' and menu.menuId=rm.menuId order by menu.moduleId,menu.order+0");
			if (!list.isEmpty()) {
				Map<String, TreeSet<Menu>> tmpModuleMenuMap = new HashMap<String, TreeSet<Menu>>();

				for (Menu menu : list) {
					String moduleId = menu.getModuleId();
					TreeSet<Menu> menuSet = tmpModuleMenuMap.get(moduleId);
					if (menuSet == null) {
						menuSet = new TreeSet<Menu>();
						tmpModuleMenuMap.put(moduleId, menuSet);
						menuSet.add(menu);
					} else {
						menuSet.add(menu);
					}
				}

				Iterator<String> it = tmpModuleMenuMap.keySet().iterator();
				while (it.hasNext()) {
					String moduleId = it.next();
					TreeSet<Menu> menuSet = tmpModuleMenuMap.get(moduleId);
					Iterator<Menu> menuIt = menuSet.iterator();
					while (menuIt.hasNext()) {
						List<Menu> menuList = userModuleMenuMap.get(moduleId);
						if (menuList == null) {
							menuList = new ArrayList<Menu>();
							userModuleMenuMap.put(moduleId, menuList);
							menuList.add(menuIt.next());
						} else {
							menuList.add(menuIt.next());
						}
					}
				}
			}
			return userModuleMenuMap;
		}
	}

	/**
	 * 查找用户的人员权限列表
	 * 
	 * @param userId
	 * @return
	 * @throws JTException
	 */
	public Map<String, User> queryUserPerson(String userId) throws JTException {
		Map<String, User> userPersonMap = new HashMap<String, User>();
		User user = SysCache.interpertUser(userId);
		// Load all person to ADMIN
		if (JitongConstants.ADMIN.equals(user.getLoginName())) {
			List<User> personList = this.queryUsers();
			if (personList != null && personList.size() > 0) {
				for (User person : personList) {
					userPersonMap.put(person.getId(), person);
				}
			}
		} else {
			List<RolePerson> list = super.find("select rp from RolePerson rp, UserRole ur where rp.roleId = ur.roleId and ur.userId = '" + userId + "'");
			if (!list.isEmpty()) {
				for (RolePerson rolePerson : list) {
					User per = SysCache.interpertUser(rolePerson.getPersonId());
					userPersonMap.put(rolePerson.getPersonId(), per);
				}
			}
		}
		return userPersonMap;
	}
}

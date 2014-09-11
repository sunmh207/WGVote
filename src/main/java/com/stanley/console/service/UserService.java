package com.stanley.console.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.stanley.common.exception.JTException;
import com.stanley.common.service.BaseService;
import com.stanley.common.util.StringUtil;
import com.stanley.common.util.SysCache;
import com.stanley.console.dao.UserDAO;
import com.stanley.console.domain.Menu;
import com.stanley.console.domain.Role;
import com.stanley.console.domain.User;

public class UserService extends BaseService {
	UserDAO dao;

	public User findUser(String userId) throws JTException {
		return dao.findUser(userId);
	}

	public User findUserByLoginName(String loginName) throws JTException {
		return dao.findUserByLoginName(loginName);
	}

	public List<User> queryUsers() throws JTException {
		return dao.queryUsers();
	}

	public List<User> queryUsersByDept(String unit, String dept) throws JTException {
		boolean hasUnit = !StringUtil.isEmpty(unit);
		boolean hasDept = !StringUtil.isEmpty(dept);
		String op;
		String value;
		if (hasUnit && hasDept) {
			op = "=";
			value = unit + "-" + dept;
		} else if (hasUnit) {
			op = "like";
			value = unit + "-%";
		} else {
			op = "like";
			value = "%-" + dept;
		}

		return dao.find("from User u where u.unitDept " + op + " ?", new Object[] { value });
	}
	
	@Transactional
	public void deleteUser(String userId) throws JTException {
			dao.deleteBo(User.class, userId);
			SysCache.reloadUserById(userId, SysCache.OPER_DELETE);
	}

	/**
	 * user当前用户 person:需要增加的人
	 */
	/*@Transactional
	public void insertUser(User user, User person) throws JTException {
			if(phoneNumberExists(person.getPhoneNumber())){
				throw new JTException("电话号码"+person.getPhoneNumber()+"已经存在", this.getClass());
			}
			if(loginNameExists(person.getLoginName())){
				throw new JTException("登录名"+person.getLoginName()+"已经存在", this.getClass());
			}
			
			person.setCreatorId(user==null?"":user.getId());
			String userId = dao.createBo(person);
			SysCache.reloadUserById(userId, SysCache.OPER_ADD);
			insertUserPerson(user, person);
	}*/
	
	public void updateUser(User user, String[] roleIds) throws JTException {
			if(roleIds==null||roleIds.length==0){
				updateUser(user);
			}else{
				UserRoleService urs = new UserRoleService();
				urs.deleteRoleUserByUserId(user.getId());//删除用户的所有角色
				for(String roleId:roleIds){
					urs.addRoleUser(user.getId(), roleId);//添加选中的所有新角色所有角色
				}
				
			}
	}
	
	private void updateUser(User user) throws JTException {
			User u = SysCache.interpertUserByPhone(user.getPhoneNumber());
			if(u!=null&&!u.getId().equals(user.getId())){
				throw new JTException("电话号码"+user.getPhoneNumber()+"已经存在", this.getClass());
			}
			
			u = dao.findUserByLoginName(user.getLoginName());
			if(u!=null&&!u.getId().equals(user.getId())){
				throw new JTException("登录名"+user.getLoginName()+"已经存在", this.getClass());
			}
			dao.updateBo(user);
			SysCache.reloadUserById(user.getId(), SysCache.OPER_UPDATE);
	}
	
	/**
	 * check if there the user whose phoneNumber = @phoneNumber exist 
	 * @param phoneNumber
	 * @return
	 */
	public boolean phoneNumberExists(String phoneNumber){
		if(SysCache.interpertUserNameByPhone(phoneNumber)!=null){
			return true;
		}
		return false;
	}
	public boolean loginNameExists(String loginName) throws JTException {
		User u = dao.findUserByLoginName(loginName);
		if(u!=null){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 *            put user information into session
	 * @return
	 * @throws JTException
	 */
	@Transactional
	public User verifyLogon(String userName, String password, Map<String, Object> session) throws JTException {
			String pwd = StringUtil.md5(password);// 加密
			User user = dao.verifyLogon(userName, pwd);

			if (user == null) {
				//throw new JTException("用户名或密码错误", this.getClass());
				return null;
			}
			loadUserInfo(user, session); //
			return user;
	}
	@Transactional
	private void loadUserInfo(User user, Map<String, Object> session) throws JTException {
		// load
		user.setMenusMap(dao.queryUserModuleMenu(user.getId()));
		user.setPersonMap(dao.queryUserPerson(user.getId()));
	}
	
	public Map<String, List<Menu>> queryUserModuleMenu(String userId) throws JTException {
		return dao.queryUserModuleMenu(userId);
	}

	/**
	 * 将person加入user的人员权限中去 1.找到user的所有角色 2.给每个角色加入person人员权限 (未考虑上级角色的人员权限)
	 * 
	 * @param user
	 * @param person
	 * @throws JTException
	 */
	@Transactional
	public void insertUserPerson(User user, User person) throws JTException {
			UserRoleService urService = new UserRoleService();
			// find all roles which the user has
			List<Role> roles = urService.queryRoleAndSuperRoleByUserId(user.getId());
			if (roles != null && roles.size() > 0) {
				RolePersonService rpService = new RolePersonService();
				for (int i = 0; i < roles.size(); i++) {
					Role role = roles.get(i);
					rpService.addRolePerson(person.getId(), role.getRoleId());
				}
			}
	}
}

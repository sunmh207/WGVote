package com.stanley.console.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.stanley.common.JitongConstants;
import com.stanley.common.action.JITActionBase;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.StringUtil;
import com.stanley.common.util.SysCache;
import com.stanley.console.domain.Role;
import com.stanley.console.domain.User;
import com.stanley.console.service.RoleService;
import com.stanley.console.service.UserRoleService;
import com.stanley.console.service.UserService;

public class UserAction extends JITActionBase implements Preparable {
	// private UserSearchForm searchForm;
	private String qryName;
	private String qryNumber;
	private String qryUnitName;
	private String qryDeptName;
	private String qryZhiwu;
	
	private String[] myRoleIds;

	public String getQryNumber() {
		return qryNumber;
	}

	public void setQryNumber(String qryNumber) {
		this.qryNumber = qryNumber;
	}

	public String getQryUnitName() {
		return qryUnitName;
	}

	public void setQryUnitName(String qryUnitName) {
		this.qryUnitName = qryUnitName;
	}

	public String getQryDeptName() {
		return qryDeptName;
	}

	public void setQryDeptName(String qryDeptName) {
		this.qryDeptName = qryDeptName;
	}

	public String getQryZhiwu() {
		return qryZhiwu;
	}

	public void setQryZhiwu(String qryZhiwu) {
		this.qryZhiwu = qryZhiwu;
	}

	private static UserService userService;
	private User user;
	private List<User> users;
	private List<String> genderList;
	private List<String> deptList;
	private User loginUser;

	private String confirmpassword;

	public void prepare() throws JTException {
		if (userService == null) {
			userService = new UserService();
		}
		if (user != null && user.getId() != null) {
			user = userService.findUser(user.getId());
		}
		genderList = new ArrayList<String>();
		genderList.add("");
		genderList.add("男");
		genderList.add("女");
		
			Iterator it = SysCache.deptMap.values().iterator();
			deptList = new ArrayList<String>();
			deptList.add("");
			while(it.hasNext()){
				deptList.add(it.next().toString());
			}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;

	}

	public String delete() throws JTException {
		userService.deleteUser(user.getId());
		SysCache.reloadUserById(user.getId(), SysCache.OPER_DELETE);
		return list();
	}

	public String emptyPassword() throws Exception {
		User u = userService.findUser(user.getId());
		u.setPassword(StringUtil.md5(""));
		userService.updateBo(u);
		SysCache.reloadUserById(user.getId(), SysCache.OPER_UPDATE);
		addActionError("清除密码成功。");
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (user.getId() == null || "".equals(user.getId())) {
				if (!StringUtil.isEmpty(user.getPassword()) || !StringUtil.isEmpty(confirmpassword)) {
					if (!user.getPassword().equals(confirmpassword)) {
						addFieldError("confirmpassword", "两次输入的密码不匹配！");
						return INPUT;
					}
				}
				user.setPassword(StringUtil.md5(user.getPassword()));
				if(user.getAmountPerScore()==null){
					user.setAmountPerScore("100");
				}
				//userService.insertUser((User) session.get(JitongConstants.USER), user);
				SysCache.reloadUserById(user.getId(), SysCache.OPER_ADD);
				// modify
			} else {
				// password is not empty
				if (!StringUtil.isEmpty(user.getPassword())) {
					if (!user.getPassword().equals(confirmpassword)) {
						addFieldError("confirmpassword", "两次输入的密码不匹配！");
						return INPUT;
					}

					user.setPassword(StringUtil.md5(user.getPassword()));

					// password is empty, no change
				} else {
					String oldpassword = userService.findUser(user.getId()).getPassword();
					user.setPassword(oldpassword);
				}
				
				//处理角色
				RoleService rs = new RoleService();
				UserRoleService urService = new UserRoleService();
				for(String roleId: myRoleIds){
					Role role = rs.findRole(roleId);
					if(role.getCreatorId().equals(user.getId())){
						addActionError("不能把用户加入自己创建的角色：本角色是由用户‘"+user.getName()+"’创建的，不能将'"+user.getName()+"'加入本角色");
						return INPUT;
					}else if(urService.isSuperUser(role.getCreatorId(),user.getId())){
						addActionError("不能把上级用户加入本角色：‘"+user.getName()+"’是’"+SysCache.interpertUserName(role.getCreatorId())+"‘的上级用户");
						return INPUT;
					}
				}
				userService.updateUser(user,myRoleIds);
				SysCache.reloadUserById(user.getId(), SysCache.OPER_UPDATE);
				
			}
			
			/*if(isNotCMCCNumber(user.getPhoneNumber())){
				addFieldError("user.phoneNumber", "只支持移动号码！");
				return INPUT;
			}*/
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getCategoryId() {
		return "console.user";
	}

	public List<String> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<String> genderList) {
		this.genderList = genderList;
	}


	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "";
		loginUser = (User) session.get(JitongConstants.USER);
		// if login user is not admin, check person previlige
		if (!JitongConstants.ADMIN.equals(loginUser.getLoginName())) {
			hql = "select distinct person from User u, UserRole ur, RolePerson rp, User person where u.id = ur.userId and ur.roleId =  rp.roleId and rp.personId=person.id and u.loginName ='"
					+ loginUser.getLoginName() + "'";
		} else {
			hql = " from User person where 1=1 ";
		}

		if (!StringUtil.isEmpty(qryName) && !"null".equals(qryName)) {
			hql += " and person.name like '%" + qryName + "%'";
		}
		if (!StringUtil.isEmpty(qryNumber) && !"null".equals(qryNumber)) {
			hql += " and person.phoneNumber like '%" + qryNumber + "%'";
		}
		if (!StringUtil.isEmpty(qryUnitName) && !"null".equals(qryUnitName)) {
			hql += " and person.unit = '" + qryUnitName + "'";
		}
		if (!StringUtil.isEmpty(qryDeptName) && !"null".equals(qryDeptName)) {
			hql += " and person.dept = '" + qryDeptName + "'";
		}
		if (!StringUtil.isEmpty(qryZhiwu) && !"null".equals(qryZhiwu)) {
			hql += " and person.zhiwu like '%" + qryZhiwu + "%'";
		}
		return hql;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	

	public List<String> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<String> deptList) {
		this.deptList = deptList;
	}

	public List<Role> getRoles(){
		List<Role> rolelist = new ArrayList<Role>();
		Iterator<Role> it =SysCache.roleMap.values().iterator();
		while(it.hasNext()){
			Role r =it.next();
			rolelist.add(r);
		}
		return rolelist;
	}
	public void setMyRoles(String[] roleIds){
		myRoleIds=roleIds;
	}
	public List<Role> getMyRoles(){
		List<Role> myroles = new ArrayList<Role>();
		try{
		UserRoleService us = new UserRoleService();
		myroles= us.queryRoleByUserId(user.getId());
		}catch(JTException jte){
			addActionError(jte.getMessage());
		}
		return myroles;
	}
	
}

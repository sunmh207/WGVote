package com.stanley.console.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stanley.common.exception.JTException;
import com.stanley.common.service.BaseService;
import com.stanley.common.util.SysCache;
import com.stanley.console.dao.RoleDAO;
import com.stanley.console.domain.Role;
import com.stanley.console.domain.User;

public class RoleService extends BaseService {
	RoleDAO dao;

	public Role findRole(String roleId) throws JTException {
		return dao.findRole(roleId); 
	}

	public List<Role> queryRoles() throws JTException {
		return dao.queryRoles();
	}

	public void deleteRole(String roleId) throws JTException {
		   
			dao.deleteBo(dao.findById(Role.class, roleId));
			UserRoleService urs= new UserRoleService();
			List<User> mgrs = urs.queryUserByRoleId(roleId);
			for(User u:mgrs){
				urs.deleteRoleUser(u.getId(), roleId);
			}
			
			RolePersonService rps= new RolePersonService();
			List<User> persons = rps.queryPersonsByRoleId(roleId);
			for(User p:persons){
				rps.deleteRolePerson(p.getId(), roleId);
			}
	}

	public void insertRole(Role role) throws JTException {
			dao.createBo(role);
	}
	
	public void updateRole(Role role) throws JTException {
			dao.updateBo(role);
	}
	
	
	
}

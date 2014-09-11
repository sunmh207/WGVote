package com.stanley.console.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stanley.common.exception.JTException;
import com.stanley.common.service.BaseService;
import com.stanley.common.util.SysCache;
import com.stanley.console.dao.RolePersonDAO;
import com.stanley.console.domain.RolePerson;
import com.stanley.console.domain.User;

public class RolePersonService extends BaseService {
	RolePersonDAO dao;

	public List<User> queryPersonsByRoleId(String roleID) throws JTException {
		return dao.queryPersonsByRoleId(roleID);
	}

	/**
	 * delete persons from the role
	 * 
	 * @param ids
	 * @param roleId
	 * @throws JTException
	 */
	public void deleteRolePersons(String[] ids, String roleId) throws JTException {
			if(ids!=null&&ids.length>0){
				for(String id:ids){
					deleteRolePerson(id,roleId);
				}}
	}

	/**
	 * delete a person from the role
	 * 
	 * @param personId
	 * @param roleId
	 * @throws JTException
	 */
	public void deleteRolePerson(String personId, String roleId) throws JTException {
			dao.deleteRolePerson(personId, roleId);
	}

	/**
	 * add a person to the role
	 * 
	 * @param personId
	 * @param roleId
	 * @throws JTException
	 */
	public void addRolePerson(String personId, String roleId) throws JTException {
			if (this.existRolePerson(personId, roleId)) {
				return;
			}
			RolePerson rp = new RolePerson();
			rp.setPersonId(personId);
			rp.setRoleId(roleId);
			dao.createBo(rp);
			//updatePersonManagerMap
			//SysCache.reloadPersonMgrMap(personId,s);
			updatePersonManagersMap(personId,roleId,"addperson");
	}

	/**
	 * add persons to the role
	 * 
	 * @param personIds
	 * @param roleId
	 * @throws JTException
	 */
	public void addRolePersons(String[] personIds, String roleId) throws JTException {
			if (personIds != null && personIds.length > 0) {
				for (int i = 0; i < personIds.length; i++) {
					addRolePerson(personIds[i], roleId);
				}
			}
	}

	public RolePerson findRolePerson(String personId, String roleId) throws JTException {
		return dao.findRolePerson(personId, roleId);
	}

	public boolean existRolePerson(String personId, String roleId) throws JTException {
		RolePerson ur = this.findRolePerson(personId, roleId);
		if (ur != null) {
			return true;
		}
		return false;
	}
	
	private void updatePersonManagersMap(String personId,String roleId,String act) throws JTException{
		if("addperson".equals(act)){
			UserRoleService urs= new UserRoleService();
			List<User> managers =urs.queryUserByRoleId(roleId);
			for(User manager:managers){
				SysCache.updatePersonMgrMap(personId,manager.getId(),"add");
			}
		}else if("delperson".equals(act)){
			UserRoleService urs= new UserRoleService();
			List<User> managers =urs.queryUserByRoleId(roleId);
			for(User manager:managers){
				SysCache.updatePersonMgrMap(personId,manager.getId(),"del");
			}
		}
	}
}

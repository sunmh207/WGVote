package com.stanley.console.action;

import java.util.List;

import com.stanley.common.action.JITActionBase;
import com.stanley.common.exception.JTException;
import com.stanley.common.util.StringUtil;
import com.stanley.console.domain.Role;
import com.stanley.console.domain.User;
import com.stanley.console.service.RolePersonService;
import com.stanley.console.service.RoleService;
import com.opensymphony.xwork2.Preparable;

public class RolePersonAction extends JITActionBase implements Preparable {

	private static RolePersonService rpService;
	private static RoleService roleService;
	private Role role;
	private List<User> persons;
	private String personId;

	public void prepare() throws JTException {
		if (rpService == null) {
			rpService = new RolePersonService();
		}
		if (roleService == null) {
			roleService = new RoleService();
		}
		if (role != null && role.getRoleId() != null) {
			role = roleService.findRole(role.getRoleId());
			persons = rpService.queryPersonsByRoleId(role.getRoleId());
		}
	}

	public String listPersons() throws JTException {
		if (role != null && role.getRoleId() != null) {
			persons = rpService.queryPersonsByRoleId(role.getRoleId());
		}
		return INPUT;
	}

	public String deletePerson() throws JTException {
		rpService.deleteRolePerson(personId, role.getRoleId());
		return listPersons();
	}
	
	public String deletePersons() throws JTException {
		String[] userIds = request.getParameterValues("chk");
		if(userIds.length>0){
			for(String uid: userIds){
				rpService.deleteRolePerson(uid, role.getRoleId());
			}
		}
		return listPersons();
	}

	public String addPersons() throws JTException {
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		if (ids != null && ids.length > 0) {
			rpService.addRolePersons(ids, role.getRoleId());
		}
		return listPersons();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<User> getPersons() {
		return persons;
	}

	public void setPersons(List<User> persons) {
		this.persons = persons;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getCategoryId() {
		return "console.role";
	}
}

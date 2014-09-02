package com.jitong.console.domain;

public class RoleMenu {
	private String roleMenuId; // pk

	private String roleId;
	private String menuId;//

	public String getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(String roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}

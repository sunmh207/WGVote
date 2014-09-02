package com.jitong.console.domain;

public class Role {
	private String roleId;
	private String roleName;
	private String createTime;
	private String creatorId;
	private String creatorName;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	public int hashCode(){
		if(this.getRoleId()==null){
			return 0;
		}
		return this.getRoleId().hashCode();
	}
	public boolean equals(Object o){
		if(o instanceof Role){
			Role r = (Role)o;
			if(this.getRoleId()==null){
				return false;
			}
			if(r.getRoleId()==null){
				return false;
			}
			return this.getRoleId().equals(r.getRoleId());
		}
		return false;
	}
	public String toString(){
		return this.getRoleId();
	}
}

package com.stanley.console.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stanley.common.form.JTField;
import com.stanley.common.util.StringUtil;

public class User {
	private String id;
	private String name;
	private String loginName;
	private String password;
	private String isAdmin;
	private String phoneNumber;
	private String unit;// 单位，车间
	private String dept;// 部门
	

	private String GH;// 工号
	private String gender;// 性别
	private String birthday;

	private String lastLoginIp;
	private String lastLoginTime;
	private String creatorId;
	
	private String gongzhong;//工种
	private String gangwei;//岗位
	private String zhiwu;//职务
	private String jianzhi;//兼职
	
	private String evaRatio;//生产奖系数
	private String lastEvaMonth;//最新考核月份
	private String amountPerScore;//每分对应的金额
	
	
	
	/**
	 * 角色权限
	 * Map<moduleId, List<Menu>>
	 */
	//private Map<String, RoleMenu> menusMap = new HashMap<String, RoleMenu>();
	private Map<String, List<Menu>> menusMap = new HashMap<String, List<Menu>>();
	/**
	 * 人员权限
	 * Map<person id, User>
	 */
	private Map<String, User> personMap = new HashMap<String, User>();
	
	
	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="姓名",order=0)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JTField(chineseName="性别",order=5)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@JTField(chineseName="登录名",order=35)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	@JTField(chineseName="手机号",order=40)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	//@JTField(chineseName="单位",order=15)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@JTField(chineseName="车间/部门",order=20)
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getUnitDept() {
		return StringUtil.trim(unit) + "-" + StringUtil.trim(dept);
	}

	
	@JTField(chineseName="工号",order=10)
	public String getGH() {
		return GH;
	}

	public void setGH(String gH) {
		GH = gH;
	}
	
	//@JTField(chineseName="出生日期",order=25)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Map<String, List<Menu>> getMenusMap() {
		return menusMap;
	}

	public void setMenusMap(Map<String, List<Menu>> menusMap) {
		this.menusMap = menusMap;
	}

	public Map<String, User> getPersonMap() {
		return personMap;
	}

	public void setPersonMap(Map<String, User> personMap) {
		this.personMap = personMap;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	
	
	@JTField(chineseName="工种",order=12)
	public String getGongzhong() {
		return gongzhong;
	}

	public void setGongzhong(String gongzhong) {
		this.gongzhong = gongzhong;
	}
	@JTField(chineseName="岗位",order=45)
	public String getGangwei() {
		return gangwei;
	}

	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}
	@JTField(chineseName="职务",order=50)
	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}
	@JTField(chineseName="兼职",order=55)
	public String getJianzhi() {
		return jianzhi;
	}

	public void setJianzhi(String jianzhi) {
		this.jianzhi = jianzhi;
	}

	
	@JTField(chineseName="系数",order=25)
	public String getEvaRatio() {
		return evaRatio;
	}

	public void setEvaRatio(String evaRatio) {
		this.evaRatio = evaRatio;
	}

	public String getLastEvaMonth() {
		return lastEvaMonth;
	}

	public void setLastEvaMonth(String lastEvaMonth) {
		this.lastEvaMonth = lastEvaMonth;
	}

	/*public String getReturnableAmount() {
		return returnableAmount;
	}

	public void setReturnableAmount(String returnableAmount) {
		this.returnableAmount = returnableAmount;
	}*/
	@JTField(chineseName="考核对应金额",order=30)
	public String getAmountPerScore() {
		if(amountPerScore==null){
			amountPerScore="100";
		}
		return amountPerScore;
	}

	public void setAmountPerScore(String amountPerScore) {
		this.amountPerScore = amountPerScore;
	}

	@Override
	public int hashCode() {
		return id==null?"".hashCode():id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof User)) {
			return false;
		}
		if(id==null){
			return false;
		}
		User u = (User) obj;
		return id.equals(u.getId());
	}
}

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
import com.stanley.console.domain.User;
import com.stanley.console.service.UserService;

public class PopUserQueryAction extends JITActionBase implements Preparable {
	private String qryGH;
	private String qryName;
	private static UserService userService;
	private List<User> users;
	private User loginUser;
	
	private List<String> unitList ;
	//private String qryUnitName;
	private List<String> deptList;
	private String qryDeptName;
	private String gender;
	private String gongzhong;
	private String gangwei;
	private String zhiwu;
	private String jianzhi;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGongzhong() {
		return gongzhong;
	}

	public void setGongzhong(String gongzhong) {
		this.gongzhong = gongzhong;
	}

	public String getGangwei() {
		return gangwei;
	}

	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}

	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}

	public String getJianzhi() {
		return jianzhi;
	}

	public void setJianzhi(String jianzhi) {
		this.jianzhi = jianzhi;
	}

	public void prepare() throws JTException {
		if (userService == null) {
			userService = new UserService();
		}
		unitList = new ArrayList<String>();
		unitList.add("延安机务段");
		//deptList = new ArrayList<String>();
		Iterator<String> it= SysCache.deptMap.values().iterator();
		deptList = new  ArrayList<String>();
		deptList.add("");
		while(it.hasNext()){
			deptList.add(it.next());
		}
	}

	public String list() throws JTException {
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr==null||pageSizeStr.trim().length()==0){
			setPageSize(500);
		}
		this.setBusinessClass("com.stanley.console.domain.User");

		return super.list();
	}

	public String select() throws JTException {
		String[] ids = request.getParameterValues("chk");
		if (ids != null && ids.length > 0) {
			String idStr = "";
			String nameStr = "";
			for (int i = 0; i < ids.length; i++) {
				if (i > 0) {
					idStr += ",";
					nameStr += ",";
				}
				idStr += ids[i];
				nameStr += SysCache.interpertUserName(ids[i]);
			}
			request.setAttribute("idStr", idStr);
			request.setAttribute("nameStr", nameStr);
		}
		return SUCCESS;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public List<String> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<String> unitList) {
		this.unitList = unitList;
	}

	/*public String getQryUnitName() {
		return qryUnitName;
	}

	public void setQryUnitName(String qryUnitName) {
		this.qryUnitName = qryUnitName;
	}*/
	

	public List<String> getDeptList() {
		return deptList;
	}

	public String getQryGH() {
		return qryGH;
	}

	public void setQryGH(String qryGH) {
		this.qryGH = qryGH;
	}

	public void setDeptList(List<String> deptList) {
		this.deptList = deptList;
	}

	public String getQryDeptName() {
		return qryDeptName;
	}

	public void setQryDeptName(String qryDeptName) {
		this.qryDeptName = qryDeptName;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "";
		loginUser = (User) session.get(JitongConstants.USER);
		// if login user is not admin, check person previlige
		if (JitongConstants.ADMIN.equals(loginUser.getLoginName())) {
			hql = "from User person where 1=1 ";
		} else {
			hql = "select distinct person from User u, UserRole ur, RolePerson rp, User person where u.id = ur.userId and ur.roleId =  rp.roleId and rp.personId=person.id and u.loginName ='"
					+ loginUser.getLoginName() + "'";
		}
		
		if (!StringUtil.isEmpty(qryName) && !"null".equals(qryName)) {
			hql += " and person.name like '%" + qryName + "%'";
		}
		if (!StringUtil.isEmpty(qryGH) && !"null".equals(qryGH)) {
			hql += " and person.GH = '" + qryGH + "'";
		}
		

		//String unitDept = composeUnitDept(qryUnitName, qryDeptName);
		/*if (!StringUtil.isEmpty(unitDept)&& !"null".equals(unitDept)) {
			hql += " and person.unitDept like '" + unitDept + "%'";
		}*/
		/*if (!StringUtil.isEmpty(qryUnitName)) {
			hql += " and person.unit like '%" + qryUnitName + "%'";
		}*/
		if (!StringUtil.isEmpty(qryDeptName)) {
			hql += " and person.dept like '%" + qryDeptName + "%'";
		}
		if(!StringUtil.isEmpty(gender)&& !"null".equals(gender)){
			hql += " and person.gender = '" + gender + "'";
		}
		if(!StringUtil.isEmpty(gongzhong)&& !"null".equals(gongzhong)){
			hql += " and person.gongzhong like '%" + gongzhong + "%'";
		}
		if(!StringUtil.isEmpty(gangwei)&& !"null".equals(gangwei)){
			hql += " and person.gangwei like '%" + gangwei + "%'";
		}
		if(!StringUtil.isEmpty(zhiwu)&& !"null".equals(zhiwu)){
			hql += " and person.zhiwu like '%" + zhiwu + "%'";
		}
		if(!StringUtil.isEmpty(jianzhi)&& !"null".equals(jianzhi)){
			hql += " and person.jianzhi like '%" + jianzhi + "%'";
		}
		
		hql += " order by person.GH";
		return hql;
	}

	private String composeUnitDept(String unitName, String deptName) {
		String unitDept = null;
		if (!StringUtil.isEmpty(unitName) && !StringUtil.isEmpty(deptName)) {
			unitDept = unitName + "-" + deptName;
		} else if (!StringUtil.isEmpty(unitName)) {
			unitDept = unitName;
		} else if (!StringUtil.isEmpty(deptName)) {
			unitDept = deptName;
		}
		return unitDept;
	}
	public String getCategoryId() {
		return "console.PopUserQuery";
	}

}

package com.jitong.console.domain;

public class Menu implements Comparable {
	public static final String MODULEID_OA = "OA";

	/*综合信息查询
	段积分式考核
	车间积分考核
	考核返奖计算
	信息字典管理
	系统维护管理*/

	public static final String[][] MODULES = new String[][] { { MODULEID_OA, "综合信息查询" } };
	

	private String menuId;
	private String moduleId;
	private String name;
	private String desc;
	private String url;
	private String order;

	public String getMenuId() {
		return menuId;
	}
 
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return menuId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Menu)) {
			return false;
		}
		Menu menu = (Menu) obj;
		return menuId.equals(menu.getMenuId());
	}

	public int compareTo(Object arg0) {
		if (arg0 == null || (!(arg0 instanceof Menu))) {
			return 1;
		}
		Menu menu = (Menu) arg0;
		return this.getOrder().compareTo(menu.getOrder());
	}
}

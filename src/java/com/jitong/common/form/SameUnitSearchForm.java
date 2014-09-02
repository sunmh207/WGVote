package com.jitong.common.form;

/**
 * 根据'相同单位'查询短信内容的 基础SearchForm类
 * 判断业务对象(NewsPaper,Festival...)中的属性unitName是否=用户的unit值。
 * @author stanley.sun
 *
 */
public class SameUnitSearchForm extends SearchForm{
	/**
	 * 管理用戶
	 */
	private String unitName;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}

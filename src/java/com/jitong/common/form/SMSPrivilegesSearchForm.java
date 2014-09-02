package com.jitong.common.form;

/**
 * 根据权限查询短信内容的 基础SearchForm类
 * 判断短信(SMSVO,ReceivedSMS...)中的属性mgrIds是否包含当前用户的id。
 * @author stanley.sun
 *
 */
public class SMSPrivilegesSearchForm extends SearchForm{
	/**
	 * 管理用戶
	 */
	private String mgrIds;
	@FieldCondition(op = Operation.like)
	public String getMgrIds() {
		return mgrIds;
	}

	public void setMgrIds(String mgrIds) {
		this.mgrIds = mgrIds;
	}

	

}

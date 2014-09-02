package com.jitong.console.action;

import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.JTMath;
import com.jitong.common.util.StringUtil;
import com.jitong.console.service.StatisticService;
import com.opensymphony.xwork2.Preparable;

public class StatisticAction extends JITActionBase implements Preparable {
	private String startDate;
	private String endDate;
	private static StatisticService service;

	private String smsStatus0;
	private String smsStatus1;
	private String smsStatus2;
	private String smstotal;
	
	private String mmsStatus0;
	private String mmsStatus1;
	private String mmsStatus2;
	private String mmstotal;
	
	private String dialStatus0;
	private String dialStatus1;
	private String dialStatus2;
	private String dialtotal;
	
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSmsStatus0() {
		return smsStatus0;
	}

	public void setSmsStatus0(String smsStatus0) {
		this.smsStatus0 = smsStatus0;
	}

	public String getSmsStatus1() {
		return smsStatus1;
	}

	public void setSmsStatus1(String smsStatus1) {
		this.smsStatus1 = smsStatus1;
	}

	public String getSmsStatus2() {
		return smsStatus2;
	}

	public void setSmsStatus2(String smsStatus2) {
		this.smsStatus2 = smsStatus2;
	}

	public String getMmsStatus0() {
		return mmsStatus0;
	}

	public void setMmsStatus0(String mmsStatus0) {
		this.mmsStatus0 = mmsStatus0;
	}

	public String getMmsStatus1() {
		return mmsStatus1;
	}

	public void setMmsStatus1(String mmsStatus1) {
		this.mmsStatus1 = mmsStatus1;
	}

	public String getMmsStatus2() {
		return mmsStatus2;
	}

	public void setMmsStatus2(String mmsStatus2) {
		this.mmsStatus2 = mmsStatus2;
	}

	public String getDialStatus0() {
		return dialStatus0;
	}

	public void setDialStatus0(String dialStatus0) {
		this.dialStatus0 = dialStatus0;
	}

	public String getDialStatus1() {
		return dialStatus1;
	}

	public void setDialStatus1(String dialStatus1) {
		this.dialStatus1 = dialStatus1;
	}

	public String getDialStatus2() {
		return dialStatus2;
	}

	public void setDialStatus2(String dialStatus2) {
		this.dialStatus2 = dialStatus2;
	}

	public String getSmstotal() {
		return smstotal;
	}

	public void setSmstotal(String smstotal) {
		this.smstotal = smstotal;
	}

	public String getMmstotal() {
		return mmstotal;
	}

	public void setMmstotal(String mmstotal) {
		this.mmstotal = mmstotal;
	}

	public String getDialtotal() {
		return dialtotal;
	}

	public void setDialtotal(String dialtotal) {
		this.dialtotal = dialtotal;
	}

	public void prepare() throws JTException {
		if (service == null) {
			service = new StatisticService();
		}
	}

	public String list() throws JTException {
		Map<String,String> dialMap = service.calcDial(startDate, endDate);
		Map<String,String> smsMap = service.calcSMS(startDate, endDate);
		Map<String,String> mmsMap = service.calcMMS(startDate, endDate);
		smsStatus0=smsMap.get("0"); smsStatus0= StringUtil.isEmpty(smsStatus0)?"0":smsStatus0;
		smsStatus1=smsMap.get("1");smsStatus1= StringUtil.isEmpty(smsStatus1)?"0":smsStatus1;
		smsStatus2=smsMap.get("2");smsStatus2= StringUtil.isEmpty(smsStatus2)?"0":smsStatus2;
		smstotal=JTMath.add(JTMath.add(smsStatus0,smsStatus1),smsStatus2);
		
		mmsStatus0=mmsMap.get("0");mmsStatus0= StringUtil.isEmpty(mmsStatus0)?"0":mmsStatus0;
		mmsStatus1=mmsMap.get("1");mmsStatus1= StringUtil.isEmpty(mmsStatus1)?"0":mmsStatus1;
		mmsStatus2=mmsMap.get("2");mmsStatus2= StringUtil.isEmpty(mmsStatus2)?"0":mmsStatus2;
		mmstotal=JTMath.add(JTMath.add(mmsStatus0,mmsStatus1),mmsStatus2);
		
		dialStatus0 = dialMap.get("0");dialStatus0= StringUtil.isEmpty(dialStatus0)?"0":dialStatus0;
		dialStatus1 = dialMap.get("1");dialStatus1= StringUtil.isEmpty(dialStatus1)?"0":dialStatus1;
		dialStatus2 = dialMap.get("2");dialStatus2= StringUtil.isEmpty(dialStatus2)?"0":dialStatus2;
		dialtotal=JTMath.add(JTMath.add(dialStatus0,dialStatus1),dialStatus2);
		return SUCCESS;
	}

}

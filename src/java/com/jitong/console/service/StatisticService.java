package com.jitong.console.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jitong.JitongConstants;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;

public class StatisticService extends BaseService {
	BaseDAO dao;

	public StatisticService(Session s) {
		super(s);
		dao = new BaseDAO(s);
	}

	public StatisticService() throws JTException {
		super();
		dao = new BaseDAO(s);
	}

	/**
	 * Get Dial record number for each status 1,2,3,4...
	 * 
	 * @return Map<dialStatuCode,recordNumber>
	 * @throws JTException
	 */
	public Map<String, String> calcDial(String startDate, String endDate) throws JTException {
		Map<String, String> dialMap = new HashMap<String, String>();
		String hql = "select dial.finger, count(dial.finger) from OutBoxDialVO dial where 1=1 ";
		if (!StringUtil.isEmpty(startDate)) {
			hql += " and dial.dtCreate >=:startDate";
		}
		if (!StringUtil.isEmpty(endDate)) {
			hql += " and dial.dtCreate <=:endDate";
		}

		hql += " group by dial.finger";
		Query query = this.s.createQuery(hql);
			if (!StringUtil.isEmpty(startDate)) {
				query.setDate("startDate", java.sql.Date.valueOf(startDate));
			}
			if (!StringUtil.isEmpty(endDate)) {
				query.setDate("endDate", java.sql.Date.valueOf(endDate));
			}
		List list = query.list();
		for (int i = 0; i < list.size(); i++) {
			Object[] os = (Object[]) list.get(i);
			dialMap.put(StringUtil.trim(os[0].toString()), os[1].toString());
		}
		return dialMap;
	}

	/**
	 * Get SMS record number for each status 1,2,3,4...
	 * 
	 * @return Map<dialStatuCode,recordNumber>
	 * @throws JTException
	 */
	public Map<String, String> calcSMS(String startDate, String endDate) throws JTException {
		Map<String, String> smsMap = new HashMap<String, String>();
		String hql = "select sms.status, count(sms.status) from SMSVO sms where 1=1 ";
		if (!StringUtil.isEmpty(startDate)) {
			hql += " and sms.requestTime >=:startDate";
		}
		if (!StringUtil.isEmpty(endDate)) {
			hql += " and sms.requestTime <=:endDate";
		}

		hql += " group by sms.status";
		Query query = this.s.createQuery(hql);
		if (!StringUtil.isEmpty(startDate)) {
			query.setString("startDate", startDate);
		}
		try {
			if (!StringUtil.isEmpty(endDate)) {
				query.setString("endDate", DateUtil.datePlus(endDate, JitongConstants.JT_DATE_FORMAT, Calendar.DAY_OF_MONTH, 1));
			}
		} catch (ParseException e) {
			throw new JTException("Cannot parse date " + endDate, e, StatisticService.class);
		}
		List list = query.list();
		for (int i = 0; i < list.size(); i++) {
			Object[] os = (Object[]) list.get(i);
			smsMap.put(StringUtil.trim(os[0].toString()), os[1].toString());
		}
		return smsMap;
	}

	/**
	 * Get MMS record number for each status 1,2,3,4...
	 * 
	 * @return Map<dialStatuCode,recordNumber>
	 * @throws JTException
	 */
	public Map<String, String> calcMMS(String startDate, String endDate) throws JTException {
		Map<String, String> mmsMap = new HashMap<String, String>();
		String hql = "select mms.status, count(mms.status) from MMSVO mms where 1=1 ";
		if (!StringUtil.isEmpty(startDate)) {
			hql += " and mms.requestTime >=:startDate";
		}
		if (!StringUtil.isEmpty(endDate)) {
			hql += " and mms.requestTime <=:endDate";
		}

		hql += " group by mms.status";
		Query query = this.s.createQuery(hql);
		if (!StringUtil.isEmpty(startDate)) {
			query.setString("startDate", startDate);
		}
		try {
			if (!StringUtil.isEmpty(endDate)) {
				query.setString("endDate", DateUtil.datePlus(endDate, JitongConstants.JT_DATE_FORMAT, Calendar.DAY_OF_MONTH, 1));
			}
		} catch (ParseException e) {
			throw new JTException("Cannot parse date " + endDate, e, StatisticService.class);
		}
		List list = query.list();
		for (int i = 0; i < list.size(); i++) {
			Object[] os = (Object[]) list.get(i);
			mmsMap.put(StringUtil.trim(os[0].toString()), os[1].toString());
		}
		return mmsMap;
	}

}

package com.jitong.common.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.jitong.common.exception.JTException;

public class DefaultBaseService extends BaseService {
	public DefaultBaseService(Session s) {
		super(s);
	}

	public DefaultBaseService() throws JTException {
		super();
	}

	public List<Object> queryByHql(String hql) throws JTException {
			return dao.queryByHql(hql);
	}

	public int queryForInt(String hql) throws JTException {
		return ((Long) this.s.createQuery(hql).iterate().next()).intValue();
	}
}

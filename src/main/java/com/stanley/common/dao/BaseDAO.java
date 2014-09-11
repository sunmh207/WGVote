package com.stanley.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stanley.common.Pager;
import com.stanley.common.exception.JTException;

@Repository("baseDAO")
public class BaseDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void createBo(Object po) {
		sessionFactory.getCurrentSession().persist(po);
	}
	public Serializable save(Object po) {
		return sessionFactory.getCurrentSession().save(po);
	}

	public Object findById(Class clz, String id) {
		return sessionFactory.getCurrentSession().get(clz, id);
	}

	public void updateBo(Object po) {
		sessionFactory.getCurrentSession().update(po);
	}
	public Object mergeBo(Object po) {
		return sessionFactory.getCurrentSession().merge(po);
	}

	public void deleteBo(Object po) {
		sessionFactory.getCurrentSession().delete(po);
	}
	public void deleteBo(Class clz, String id) {
		Object o=findById(clz,id);
		sessionFactory.getCurrentSession().delete(o);
	}
	public void deleteAll(Collection c) {
		Iterator it=c.iterator();
		while(it.hasNext()){
		sessionFactory.getCurrentSession().delete(it.next());
		}
	}
	/**
	 * @param queryString
	 * @param values
	 * @return
	 * @throws JTException
	 */

	public List findWithPager(String queryString, Pager pager) throws JTException {
		return findWithPager(queryString, pager, new Object[0]);
	}

	
	public List findWithPager(String queryString, Pager pager, Object[] values) throws JTException {
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			if (queryString != null && values != null) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			
			if (pager != null) {
				int pageNumber = pager.getCurrentPage();
				pageNumber = pageNumber < 1 ? 1 : pageNumber;
				pager.setCurrentPage(pageNumber);
				query = query.setFirstResult(pager.getPageSize() * (pageNumber - 1));
				query.setMaxResults(pager.getPageSize());
			}
			
			List list = query.list();
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					sessionFactory.getCurrentSession().evict(list.get(i));
				}
			}
			if (pager != null) {
				countResult(queryString, pager, values);
			}
			return list;
	}

	private void countResult(String queryString, Pager pager, Object[] values) {
		int idx = queryString.toLowerCase().indexOf("from ");
		String countQueryString = "select count(*) " + queryString.substring(idx);
		Query countQuery = sessionFactory.getCurrentSession().createQuery(countQueryString);
		if (countQueryString != null && values != null) {
			for (int i = 0; i < values.length; i++) {
				countQuery.setParameter(i, values[i]);
			}
		}
		Number numCount = (Number) countQuery.uniqueResult();
		int totalNumber = numCount == null ? 0 : numCount.intValue();
		pager.setTotalRecords(totalNumber);
		int totalPages = totalNumber / pager.getPageSize();
		totalPages += totalNumber % pager.getPageSize() == 0 ? 0 : 1;
		pager.setTotalPages(totalPages);
	}

	public List find(String queryString, Object[] values) throws JTException {
		return findWithPager(queryString, null, values);
	}

	public List find(String queryString) throws JTException {
		return findWithPager(queryString, null, null);
	}

	/**
	 * @param hql
	 * @return
	 * @throws JTException
	 */
	public List queryByHql(String hql){
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List list = query.list();
		/*if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				sessionFactory.getCurrentSession().evict(list.get(i));
			}
		}*/
		return list;		
	}

	public int executeSQLUpdate(String sql){
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return	query.executeUpdate();
	}
	/**
	 * @param obj
	 * @throws JTException
	 */
	public void evict(Object obj) throws JTException {
		try {
			sessionFactory.getCurrentSession().evict(obj);
		} catch (HibernateException e) {
			throw new JTException("持久化对象错误", e, this.getClass());
		}
	}
	public Session getCurrentSession()  {
			return sessionFactory.getCurrentSession();
	}

	/*public static void main(String[] args) throws Exception {
		Session session = DBtools.getSession();
		BaseDAO dao = new BaseDAO(session);
		Pager pager = new Pager();
		pager.setPageSize(1);
		dao.findWithPager("from com.stanley.yunshuzhihui.domain.Yunshuzhihui", pager);
		System.out.println(pager);
		List list = dao.queryByHql("select dial.finger, count(dial.finger) from OutBoxDialVO dial group by dial.finger");
		for(int i=0;i<list.size();i++){
			Object[] os =(Object[])list.get(i);
			System.out.println(os[0]+"-"+os[1]);
		}
	}*/
}

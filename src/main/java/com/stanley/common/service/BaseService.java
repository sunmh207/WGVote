package com.stanley.common.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanley.common.Pager;
import com.stanley.common.dao.BaseDAO;
import com.stanley.common.exception.JTException;
import com.stanley.vote.domain.Voter;

@Service("baseService")
public class BaseService {
	@Autowired
	protected BaseDAO baseDAO;

	@Transactional
	public Object findBoById(Class cl, String pkId) throws JTException {
		return baseDAO.findById(cl, pkId);
	}

	@Transactional
	public List<Object> queryAll(Class cl) throws JTException {
		try {
			List<Object> list =baseDAO.find("from " + cl.getName() + " u");
			return list;
		} catch (HibernateException e) {
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}
	@Transactional
	public boolean checkSubmit(Voter voter) throws JTException {
		if(voter==null||voter.getVote()==null){
			return false;
		}
		try {
			List<Object> list =baseDAO.find("from Voter v where v.vote.id='"+voter.getVote().getId()+"' and v.ip='"+voter.getId()+"'");
			if(list!=null&&!list.isEmpty()){
				for(Object o:list){//如果有多条记录，可能是并发导致的；只要有一条已经提交，就说明已经提交。
					if("1".equals(((Voter)o).getSubmit())){
						return true;
					}
				}
			}

		} catch (HibernateException e) {
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
		return false;
	}

	
	@Transactional
	public Object mergeBo(Object bo){
			return baseDAO.mergeBo(bo);
	}
	@Transactional
	public List queryByHql(String hql) throws JTException {
		try {
			List list =baseDAO.queryByHql(hql);
			return list;
		} catch (HibernateException e) {
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}
	
	@Transactional
	public void deleteAll(String hql) throws JTException {
		try {
			List list =baseDAO.queryByHql(hql);
			for(Object o:list){
				this.deleteBo(o);
			}
		} catch (HibernateException e) {
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}
	@Transactional
	public boolean objectsExist(String hql)  {
			List<Object> list = baseDAO.queryByHql(hql);
			return !list.isEmpty();
	}
	@Transactional
	public List findWithPager(String queryString, Pager pager, Object[] values) throws JTException {
		try {
			List list =baseDAO.findWithPager(queryString,pager,values);
			return list;
		} catch (HibernateException e) {
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}
	/**
	 * @param bo
	 * @return
	 * @throws JTException
	 */
	@Transactional
	public String createBo(Object bo) {
		Serializable id=  baseDAO.save(bo);
		return (String)id;
	}
	@Transactional
	public void saveBoList(List bolist) throws JTException {
			for(Object o:bolist){
				if (o != null) {
					baseDAO.mergeBo(o);
				}
			}
	}

	@Transactional
	public void deleteBo(Class clazz, String pk) throws JTException {
			Object bo = baseDAO.findById(clazz, pk);
			if (bo != null) {
				baseDAO.deleteBo(bo);
			}
	}
	@Transactional
	public void deleteBo(Object bo) throws JTException {
			baseDAO.deleteBo(bo);
	}
	
	@Transactional
	public void updateBo(Object bo) throws JTException {
			baseDAO.updateBo(bo);
	}
	@Transactional
	public int executeSQLUpdate(String sql){
		return	baseDAO.executeSQLUpdate(sql);
	}
	public Session getCurrentSession()  {
		return baseDAO.getCurrentSession();
}
}

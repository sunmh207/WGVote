package com.jitong.common.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.Pager;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;

public class BaseService {
	public boolean selfCommit;
	public String beginTransactionMethod;
	public boolean isTransOpen;
	protected Session s;
	
	BaseDAO dao;

	/**
	 * @param s
	 */
	public BaseService(Session s) {
		this.s = s;
		this.selfCommit = false;
		dao = new BaseDAO(s);
	}

	public BaseService() throws JTException {
		this.s = DBtools.getSession();
		this.selfCommit = true;
		dao = new BaseDAO(s);
	}

	/**
	 * 基类中提供事务管理方法.所有的manage都要使用beginTrans开启事务. 根据事务处理标记进行事务开启.
	 * 
	 * @return Transaction
	 * 
	 * 
	 */
	public Transaction beginTransaction() throws JTException {
		Throwable t = new Throwable();
		if (this.selfCommit && !this.isTransOpen) {
			this.beginTransactionMethod = t.getStackTrace()[1].getMethodName();
			this.isTransOpen = true;
			try {
				return this.s.beginTransaction();
			} catch (HibernateException e) {
				throw new JTException(this.beginTransactionMethod + " 开启事务失败", e, this.getClass());
			}
		}
		return null;
	}

	/**
	 * manage中要提交事务需要调用本方法.将beginTransaction返回的transaction传入提交. 判断事务处理标记 在提交.
	 * 
	 * @param transaction
	 */
	public void commitTransaction(Transaction transaction) throws JTException {
		Throwable t = new Throwable();
		String methodname = t.getStackTrace()[1].getMethodName();
		if (this.selfCommit && this.isTransOpen && methodname.equals(this.beginTransactionMethod)) {
			try {
				if (null != transaction) {
					transaction.commit();
					this.isTransOpen = false;
					this.beginTransactionMethod = "";
				}
			} catch (HibernateException e) {
				throw new JTException(this.beginTransactionMethod + " 提交事务失败", e, this.getClass());
			}
		}
	}

	/**
	 * 使用本方法回滚事务,在方法中根据事务处理标记选择是否要进行回滚.
	 * 
	 * @param transaction
	 */
	public void rollbackTransaction(Transaction transaction) throws JTException {
		Throwable t = new Throwable();
		String methodname = t.getStackTrace()[1].getMethodName();
		if (this.selfCommit && this.isTransOpen && methodname.equals(this.beginTransactionMethod)) {
			try {
				if (null != transaction) {
					transaction.rollback();
					this.isTransOpen = false;
					this.beginTransactionMethod = "";
				}
			} catch (HibernateException e) {
				throw new JTException(this.beginTransactionMethod + " 回滚事务失败", e, this.getClass());
			}
		}
	}

	public Object findBoById(Class cl, String pkId) throws JTException {
		return dao.findBoById(cl, pkId);
	}

	public List<Object> queryAll(Class cl) throws JTException {
		try {
			List<Object> list =dao.find("from " + cl.getName() + " u");
			return list;
		} catch (HibernateException e) {
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}

	public void deleteByHQL(String hql) throws JTException {
		Transaction tx= null;
		try {
			tx=this.beginTransaction();
			List<Object> list = dao.find(hql);
			deleteAll(list);
			this.commitTransaction(tx);
		} catch (HibernateException e) {
			this.rollbackTransaction(tx);
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}
	public Object mergeBO(Object bo) throws JTException {
		Transaction tx= null;
		Object obj=null;
		try {
			tx=this.beginTransaction();
			obj = dao.mergeBO(bo);
			this.commitTransaction(tx);
			return obj;
		} catch (HibernateException e) {
			this.rollbackTransaction(tx);
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}
	
	public List queryByHql(String hql) throws JTException {
		try {
			List list =dao.queryByHql(hql);
			return list;
		} catch (HibernateException e) {
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}

	public boolean objectsExist(String hql) throws JTException {
		try {
			List<Object> list = dao.find(hql);
			return !list.isEmpty();
		} catch (HibernateException e) {
			throw new JTException("读取持久化对象错误", e, this.getClass());
		}
	}
	public List findWithPager(String queryString, Pager pager, Object[] values) throws JTException {
		try {
			List list =dao.findWithPager(queryString,pager,values);
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
	public String createBo(Object bo) throws JTException {
		String id = null;
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			id = dao.createBo(bo);
			this.commitTransaction(tx);
			return id;
		} catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增对象失败", e, this.getClass());
		}
	}
	public void saveBoList(List bolist) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			for(Object o:bolist){
				if (o != null) {
					dao.mergeBO(o);
				}
			}
			this.commitTransaction(tx);
		} catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增对象失败", e, this.getClass());
		}
	}
	public void saveOrUpdateBo(Object bo) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			if (bo != null) {
				dao.saveOrUpdateBo(bo);
			}
			this.commitTransaction(tx);
		} catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增对象失败", e, this.getClass());
		}
	}

	/**
	 * 根据主键删除一行记录.
	 * 
	 * @param clazz
	 *            待删除BO的类名
	 * @param pk
	 * @throws JTException
	 */
	public void deleteBo(Class clazz, String pk) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			Object bo = dao.findBoById(clazz, pk);
			if (bo != null) {
				dao.deletePo(bo);
			}
			this.commitTransaction(tx);
		} catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除对象失败", e, this.getClass());
		}
	}

	public void deleteAll(Collection list) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteAll(list);
			this.commitTransaction(tx);
		} catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除对象失败", e, this.getClass());
		}
	}

	/**
	 * 将传入的bo数据更新到数据库中 ,需要传入主键值,bo数据
	 * 
	 * @param pk
	 *            待更新bo的主键
	 * @param bo
	 *            待更新bo
	 */
	public void updateBo(String pk, Object bo) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(pk, bo);
			this.commitTransaction(tx);
		} catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新对象失败", e, this.getClass());
		}
	}

	public void updateBo(Object bo) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(bo);
			this.commitTransaction(tx);
		} catch (Exception e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新对象失败", e, this.getClass());
		}
	}

	public Session getS() {
		return s;
	}

	public void setS(Session s) {
		this.s = s;
	}
}

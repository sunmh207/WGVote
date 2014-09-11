package com.stanley.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stanley.model.Employee;

@Repository("employeeDAO")
public class EmployeeDAOImpl implements EmployeeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persistEmployee(Employee employee) {
		sessionFactory.getCurrentSession().persist(employee);
	}

	@Override
	public Employee findEmployeeById(String id) {
		try{
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
		//Employee e=	 (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
		//sessionFactory.getCurrentSession().evict(e);
		//return e;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
	}
	@Override
	public void mergeEmployee(Employee employee) {
		sessionFactory.getCurrentSession().merge(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		sessionFactory.getCurrentSession().delete(employee);
	}
}
package com.stanley.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanley.dao.EmployeeDAO;
import com.stanley.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Override
	@Transactional
	public void persistEmployee(Employee employee) {
		employeeDAO.persistEmployee(employee);

	}

	@Override
	@Transactional
	public Employee findEmployeeById(String id) {
		return employeeDAO.findEmployeeById(id);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		employeeDAO.updateEmployee(employee);
	}

	@Override
	@Transactional
	public void deleteEmployee(Employee employee) {
		employeeDAO.deleteEmployee(employee);
	}
	
	@Override
	@Transactional
	public void test() {
		try{
		String id="4";
		Employee em = new Employee();
		em.setId(id);
		em.setName("Johnxxx");
		em.setAge(35);
		employeeDAO.persistEmployee(em);
		
		Employee em2 =employeeDAO.findEmployeeById(id);
		em2.setAge(60);
		employeeDAO.mergeEmployee(em2);
		/*String n="";
		for(int i=0;i<55;i++){
			n+=i;
		}
		em2.setName(n);
		employeeDAO.updateEmployee(em2);*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

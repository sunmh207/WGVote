package com.stanley.dao;

import com.stanley.model.Employee;

public interface EmployeeDAO {
	void persistEmployee(Employee employee);

	Employee findEmployeeById(String id);

	void updateEmployee(Employee employee);
	void mergeEmployee(Employee employee);

	void deleteEmployee(Employee employee);
}
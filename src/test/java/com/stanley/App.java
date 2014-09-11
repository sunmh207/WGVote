package com.stanley;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.stanley.model.Employee;
import com.stanley.service.EmployeeService;

public class App {
	@Transactional
	public static void main(String[] args) {
/*		System.out.println("load context");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Employee em = new Employee();
		em.setId("12345");
		em.setName("Johnxxx");
		em.setAge(35);
		EmployeeService emService = (EmployeeService) context.getBean("employeeService");
		emService.persistEmployee(em);
		System.out.println("Updated age :" + emService.findEmployeeById("123").getAge());		
		
		em.setAge(32);
		em.setName("qqq");
		emService.updateEmployee(em);
		System.out.println("Updated age :" + emService.findEmployeeById("123").getAge());
		//emService.deleteEmployee(em);
		context.close();*/
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		EmployeeService emService = (EmployeeService) context.getBean("employeeService");
		emService.test();
		context.close();
	}
}

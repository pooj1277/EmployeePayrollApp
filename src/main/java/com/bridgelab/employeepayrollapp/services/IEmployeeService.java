package com.bridgelab.employeepayrollapp.services;

import java.util.List;

import com.bridgelab.employeepayrollapp.model.Employee;

public interface IEmployeeService {

	List<Employee> getAllEmp();
	
	Employee getEmpById(int id);
	
	Employee save(Employee emp);
	
	Employee update(int id, Employee emp);
	
	String deleteById(int id);
}

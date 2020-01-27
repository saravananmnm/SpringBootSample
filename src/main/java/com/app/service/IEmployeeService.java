package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface IEmployeeService {
	public Employee saveEmployee(Employee s);
	public Employee getEmployeeById(Integer id);
	public List<Employee> getAllEmployees();
	public void deleteEmployeeById(Integer id);
	public boolean EmployeeExist(Integer id);
}

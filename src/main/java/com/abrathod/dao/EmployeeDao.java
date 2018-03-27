package com.abrathod.dao;

import java.util.List;

import com.abrathod.entity.Employee;

public interface EmployeeDao {

	public Employee addEmployee(Employee employee);
	 public Employee updateEmployee(Employee employee);
	 public void deleteEmployee(Employee employee);
	 public Employee getEmployee(long empId);
	 public List<Employee> getAllEmployees();
}

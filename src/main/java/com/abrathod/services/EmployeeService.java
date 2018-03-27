package com.abrathod.services;

import java.util.List;

import com.abrathod.rest.dto.EmployeeDTO;

public interface EmployeeService {

	public EmployeeDTO addEmployee(EmployeeDTO emp);
	 public EmployeeDTO updateEmployee(EmployeeDTO emp);
	 public void deleteEmployee(long empId);
	 public EmployeeDTO getEmployee(long empId);
	 public List<EmployeeDTO> getAllEmployees();
}

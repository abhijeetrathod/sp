package com.abrathod.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abrathod.dao.EmployeeDao;
import com.abrathod.entity.Employee;
import com.abrathod.rest.dto.EmployeeDTO;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	@Transactional(readOnly = false)
	public EmployeeDTO addEmployee(EmployeeDTO emp) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		employee.setDesignation(emp.getDesignation());
		employee.setEmail(emp.getEmail());
		employee.setEmployeeId(emp.getEmpId());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setProjectName(emp.getProjectName());
		
		
		employee =employeeDao.addEmployee(employee);
		emp.setEmpId(employee.getEmployeeId());
		return  emp;
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeDTO updateEmployee(EmployeeDTO emp) {
		// TODO Auto-generated method stub
		
		Employee employee = new Employee();
		employee.setDesignation(emp.getDesignation());
		employee.setEmail(emp.getEmail());
		employee.setEmployeeId(emp.getEmpId());
		employee.setFirstName(emp.getFirstName());
		employee.setLastName(emp.getLastName());
		employee.setProjectName(emp.getProjectName());
		employee=employeeDao.updateEmployee(employee);
		emp.setEmpId(employee.getEmployeeId());
		
		return emp;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(long empId) {
		// TODO Auto-generated method stub
		
		Employee employee=employeeDao.getEmployee(empId);
		employeeDao.deleteEmployee(employee);
	}

	
	@Override
	@Transactional(readOnly = true)
	public EmployeeDTO getEmployee(long empId) {
		
		Employee employee=employeeDao.getEmployee(empId);
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setDesignation(employee.getDesignation());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setEmpId(employee.getEmployeeId());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setProjectName(employee.getProjectName());
		
		
		return employeeDTO;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDTO> getAllEmployees() {
		
		List<Employee> employeesList = employeeDao.getAllEmployees();
		
		List<EmployeeDTO> employeeDTOsList = new ArrayList<>();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		for(Employee employee :employeesList) {
			
			employeeDTO.setDesignation(employee.getDesignation());
			employeeDTO.setEmail(employee.getEmail());
			employeeDTO.setEmpId(employee.getEmployeeId());
			employeeDTO.setFirstName(employee.getFirstName());
			employeeDTO.setLastName(employee.getLastName());
			employeeDTO.setProjectName(employee.getProjectName());
			
			employeeDTOsList.add(employeeDTO);
			
		}
		
		return employeeDTOsList;
	}

}

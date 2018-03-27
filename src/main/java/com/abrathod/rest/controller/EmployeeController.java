package com.abrathod.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abrathod.entity.Employee;
import com.abrathod.rest.dto.EmployeeDTO;
import com.abrathod.rest.dto.StatusDTO;
import com.abrathod.services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	@Autowired
	EmployeeService employeeService ;
	
	
	@RequestMapping(value ="/add" , method =RequestMethod.POST , produces=MediaType.APPLICATION_JSON_VALUE ,consumes =MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAuthorize(role='ADMIN')")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO emp) {
		
		emp = employeeService.addEmployee(emp);
		return new ResponseEntity<EmployeeDTO>(emp, HttpStatus.OK);
		
	}
	
	
	 @RequestMapping(value="/update" , method = RequestMethod.POST ,produces=MediaType.APPLICATION_JSON_VALUE ,consumes =MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAuthorize(role='ADMIN')")
	 public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO emp) throws Exception {
		 
		 if(emp.getEmpId() > 0) {
			 emp =employeeService.updateEmployee(emp);
		 }
		 else {
			 
			 throw new Exception("Employee Id must be greater than zero");
		 }
			 
		return new ResponseEntity<EmployeeDTO>(emp, HttpStatus.OK);
		 
	 }
	 
	 
	 @RequestMapping(value="/delete/{empId}" , method = RequestMethod.GET  ,produces =MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAuthorize(role='ADMIN')")
	 public ResponseEntity<StatusDTO> deleteEmployeeById(@PathVariable("empId") long empId) {
		 
		 
		 employeeService.deleteEmployee(empId);
		 
		 StatusDTO status = new StatusDTO();
		 status.setMessage("Employee Deleted Successfully");
		 status.setStatus(200);
		 
		 return new ResponseEntity<StatusDTO>(status , HttpStatus.OK);
		  
		 
	 }
	 
	 @RequestMapping(value="/{empId}" , method = RequestMethod.GET  ,produces =MediaType.APPLICATION_JSON_VALUE)
	 //@PreAuthorize("hasAuthorize(role='USER')")
	 public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("empId") long empId) {
		 
		 
		 EmployeeDTO employee = employeeService.getEmployee(empId);
		 
		 return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
		 
	 }
	 
	 @RequestMapping(value="/all" , method = RequestMethod.GET  ,produces =MediaType.APPLICATION_JSON_VALUE)
	 //@PreAuthorize("hasAuthorize(role='ADMIN')")
	 public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		 
		 List<EmployeeDTO> employees = employeeService.getAllEmployees();
		return new ResponseEntity<List<EmployeeDTO>>(employees,HttpStatus.OK);
		 
	 }
	
	
	
	
}

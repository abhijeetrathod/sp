package com.abrathod.dao;

import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abrathod.entity.Employee;

@Repository("employeeDao")
@Transactional
public class EmployeeDaoImpl implements EmployeeDao{

	@PersistenceContext(name="em")
	EntityManager em ;
	
	@Override
	@Transactional(readOnly= false)
	public Employee addEmployee(Employee employee) {
		
		em.persist(employee);
		return employee;
	}

	@Override
	@Transactional(readOnly= false)
	public Employee updateEmployee(Employee employee) {
		em.merge(employee);
		return employee;
	}

	@Override
	@Transactional(readOnly= false)
	public void deleteEmployee(Employee employee) {
		
		em.remove(employee);
		
	}

	@Override
	@Transactional(readOnly= true)
	public Employee getEmployee(long empId) {
		// TODO Auto-generated method stub
		return (Employee)em.createNamedQuery("Employee.getEmployeeById").setParameter("employeeId", empId).getSingleResult();
		/*String sql = "select emp from Employee emp where emp.employeeId="+empId;
		  
		      return (Employee) em.createQuery(sql).getSingleResult();*/
		   
		  
		 
	}

	@Override
	@Transactional(readOnly= true)
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Employee.getAllEmployees").getResultList();
		//return em.createQuery("select emp from Employee emp").getResultList();
	}

}

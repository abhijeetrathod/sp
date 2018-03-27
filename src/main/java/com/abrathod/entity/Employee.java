package com.abrathod.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="employeed")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name="Employee.getEmployeeById" , query="select l from Employee l where l.employeeId =:employeeId"),
	
	@NamedQuery(name="Employee.getAllEmployees" , query="select l from Employee l"),
	})
public class Employee {

	private static final long serialVersionUID = 2L;
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @NotNull
	 @Column(name ="employeeId")
	 private long employeeId;
	 
	 @JsonView(DataTablesOutput.View.class)
	 @Column(name ="email")
	 private String email;
	 
	 @JsonView(DataTablesOutput.View.class)
	 @Column(name ="firstName")
	 private String firstName;
	 
	 
	 @JsonView(DataTablesOutput.View.class)
	 @Column(name ="lastName")
	 private String lastName;
	 
	 
	 @JsonView(DataTablesOutput.View.class)
	 @Column(name ="designation")
	 private String designation;
	 
	 
	 @JsonView(DataTablesOutput.View.class)
	 @Column(name ="projectName")
	 private String projectName;
	 
	 
	 public long getEmployeeId() {
	 return employeeId;
	 }
	 public void setEmployeeId(long employeeId) {
	 this.employeeId = employeeId;
	 }
	 public String getEmail() {
	 return email;
	 }
	 public void setEmail(String email) {
	 this.email = email;
	 }
	 public String getFirstName() {
	 return firstName;
	 }
	 public void setFirstName(String firstName) {
	 this.firstName = firstName;
	 }
	 public String getLastName() {
	 return lastName;
	 }
	 public void setLastName(String lastName) {
	 this.lastName = lastName;
	 }
	 public String getDesignation() {
	 return designation;
	 }
	 public void setDesignation(String designation) {
	 this.designation = designation;
	 }
	 public String getProjectName() {
	 return projectName;
	 }
	 public void setProjectName(String projectName) {
	 this.projectName = projectName;
	 }
}

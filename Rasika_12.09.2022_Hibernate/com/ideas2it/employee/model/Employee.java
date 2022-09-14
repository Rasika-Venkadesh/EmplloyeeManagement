package com.ideas2it.employee.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**
 * <p>
 * Employee class holds common fields of employee and having its own getter and setter methods.
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class Employee {
    private String name;
    private int employeeId ;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoin;
    private String gender;
    private long phoneNumber;
    private String emailId;
    private double salary;
    private long aadharId;
    private String bloodGroup;
    private Qualification qualification;
    private Role role;

    public Employee() {}

    public Employee(String name, String dateOfBirth, String dateOfJoin, String gender, long phoneNumber,
             String emailId, double salary, long aadharId, String bloodGroup, Qualification qualification, Role role) {
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.dateOfJoin = LocalDate.parse(dateOfJoin);
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.salary = salary;
        this.aadharId = aadharId;
        this.bloodGroup = bloodGroup; 
        this.qualification = qualification; 
        this.role = role;        
    }
  
    public Employee(Employee employee) {
        super();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoin = dateOfJoin;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.salary = salary;
        this.aadharId = aadharId;
        this.bloodGroup = bloodGroup; 
        this.qualification = qualification; 
        this.role = role;        
    }
 
 


    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDateOfBirth() {
	return dateOfBirth;
    }
        
    public void setDateOfBirth(LocalDate dateOfBirth) {
       this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(LocalDate dateOfJoin) {
       this.dateOfJoin = dateOfJoin;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
	return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
     }

    public double getSalary() {
	return salary;
    }

    public void setSalary(double salary) {	
        this.salary = salary;
    }

    public long getAadharId() {
	return aadharId;
    }

    public void setAadharId(long aadharId) {
	this.aadharId = aadharId;
    }

    public String getBloodGroup() {
	return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
 	this.bloodGroup = bloodGroup;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    
   
    }

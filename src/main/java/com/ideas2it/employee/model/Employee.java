//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Employee {
    private String name;
    private int employeeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoin;
    private String gender;
    private long phoneNumber;
    private String emailId;
    private double salary;
    private long aadharId;
    private String bloodGroup;
    private Qualification qualification;
    private Role role;

    public Employee() {
    }

    public Employee(String name, String dateOfBirth, String dateOfJoin, String gender, long phoneNumber, String emailId, double salary, long aadharId, String bloodGroup, Qualification qualification, Role role) {
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
        this.name = this.name;
        this.dateOfBirth = this.dateOfBirth;
        this.dateOfJoin = this.dateOfJoin;
        this.gender = this.gender;
        this.phoneNumber = this.phoneNumber;
        this.emailId = this.emailId;
        this.salary = this.salary;
        this.aadharId = this.aadharId;
        this.bloodGroup = this.bloodGroup;
        this.qualification = this.qualification;
        this.role = this.role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoin() {
        return this.dateOfJoin;
    }

    public void setDateOfJoin(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getAadharId() {
        return this.aadharId;
    }

    public void setAadharId(long aadharId) {
        this.aadharId = aadharId;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Qualification getQualification() {
        return this.qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

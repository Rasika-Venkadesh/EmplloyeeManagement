//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.model;

import com.ideas2it.employee.utility.NumberUtil;
import java.util.Set;

public class Trainer extends Employee {
    private int experience;
    private int noOfTrainees;
    private int trainerId;
    private Employee employee;
    private Set<Trainee> trainees;

    public Trainer() {
    }

    public Trainer(String name, String dateOfBirth, String dateOfJoin, String gender, long phoneNumber, String emailId, double salary, long aadharId, String bloodGroup, Qualification qualification, int experience, Role role) {
        super(name, dateOfBirth, dateOfJoin, gender, phoneNumber, emailId, salary, aadharId, bloodGroup, qualification, role);
        this.experience = experience;
    }

    public Trainer(Employee employee, int experience) {
        this.employee = employee;
        this.experience = experience;
    }

    public Trainer(Trainer trainer) {
        super(trainer);
        this.experience = trainer.experience;
        this.trainerId = trainer.trainerId;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getNoOfTrainees() {
        return this.noOfTrainees;
    }

    public void setNoOfTrainees(int noOfTrainees) {
        this.noOfTrainees = noOfTrainees;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getTrainerId() {
        return this.trainerId;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Set<Trainee> getTrainees() {
        return this.trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    public String toString() {
        return String.format("|%1$-10s|%2$-20s|%3$-5s|%4$-10s|%5$-7s|%6$-13s|%7$-20s|%8$-10s|%9$-18s|%10$-11s|%11$-14s|\n", "I2I-" + this.employee.getEmployeeId(), this.employee.getName(), NumberUtil.calculateEmployeeAge(this.employee.getDateOfBirth()), NumberUtil.calculateEmployeeExperience(this.employee.getDateOfJoin()), this.employee.getGender(), this.employee.getPhoneNumber(), this.employee.getEmailId(), this.employee.getSalary(), this.employee.getAadharId(), this.employee.getBloodGroup(), this.employee.getQualification().getQualification());
    }

    public boolean equals(Object o) {
        Trainer trainer = (Trainer)o;
        return this.getEmployeeId() == trainer.getEmployeeId();
    }

    public int hashCode() {
        return 0;
    }
}


//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.model;

import com.ideas2it.employee.utility.NumberUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Trainee extends Employee {
    private int trainingPeriod;
    private Employee employee;
    private Set<Trainer> trainers;
    private List<Integer> trainersId;
    private int traineeId;

    public Trainee() {
    }

    public Trainee(String name, String dateOfBirth, String dateOfJoin, String gender, long phoneNumber, String emailId, double salary, long aadharId, String bloodGroup, Qualification qualification, int trainingPeriod, Role role, List<Integer> trainersId) {
        super(name, dateOfBirth, dateOfJoin, gender, phoneNumber, emailId, salary, aadharId, bloodGroup, qualification, role);
        this.trainingPeriod = trainingPeriod;
        this.trainersId = trainersId;
    }

    public Trainee(Employee employee, int trainingPeriod, List<Integer> trainersId) {
        this.employee = employee;
        this.trainingPeriod = trainingPeriod;
        this.trainersId = trainersId;
    }

    public Trainee(Trainee trainee) {
        super(trainee);
        this.trainingPeriod = trainee.trainingPeriod;
        this.trainersId = trainee.trainersId;
    }

    public void setTrainingPeriod(int trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public int getTrainingPeriod() {
        return this.trainingPeriod;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Set<Trainer> getTrainers() {
        return this.trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Integer> getTrainersId() {
        return this.trainersId;
    }

    public void setTrainersId(List<Integer> trainersId) {
        this.trainersId = trainersId;
    }

    public int getTraineeId() {
        return this.traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    public String toString() {
        this.trainersId = new ArrayList();
        Iterator var1 = this.trainers.iterator();

        while(var1.hasNext()) {
            Trainer trainer = (Trainer)var1.next();
            this.trainersId.add(trainer.getEmployee().getEmployeeId());
        }

        return String.format("|%1$-10s|%2$-20s|%3$-5s|%4$-10s|%5$-7s|%6$-13s|%7$-20s|%8$-10s|%9$-18s|%10$-11s|%11$-14s|%12$-15s|\n", "I2I-" + this.employee.getEmployeeId(), this.employee.getName(), NumberUtil.calculateEmployeeAge(this.employee.getDateOfBirth()), NumberUtil.calculateEmployeeExperience(this.employee.getDateOfJoin()), this.employee.getGender(), this.employee.getPhoneNumber(), this.employee.getEmailId(), this.employee.getSalary(), this.employee.getAadharId(), this.employee.getBloodGroup(), this.employee.getQualification().getQualification(), this.getTrainersId().toString());
    }

    public boolean equals(Object o) {
        Trainee trainee = (Trainee)o;
        if (this.getEmployeeId() != trainee.getEmployeeId()) {
            return false;
        } else if (!trainee.getName().equals(this.getName())) {
            return false;
        } else {
            return this.getBloodGroup().equals(trainee.getBloodGroup());
        }
    }

    public int hashCode() {
        return 0;
    }
}

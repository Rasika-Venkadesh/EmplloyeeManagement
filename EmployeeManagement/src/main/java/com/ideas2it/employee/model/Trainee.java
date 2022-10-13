package com.ideas2it.employee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainee")
public class Trainee extends Employee {

    @Column(name = "Training_Period", nullable = false)
    private int trainingPeriod;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "emp_relation",
            joinColumns = @JoinColumn(name = "traineeId", referencedColumnName = "E_Id"),
            inverseJoinColumns = @JoinColumn(name = "trainerId", referencedColumnName = "E_Id"))
    private Set<Trainer> trainers;

    @Transient
    private List<Integer> trainersId;



    public Trainee() {
        super();
    }

    public Trainee(int employeeId,String name, String dateOfBirth, String dateOfJoin, String gender,
                    long phoneNumber, String emailId, double salary, long aadharId, String bloodGroup,
                    Qualification qualification, Role role,int trainingPeriod, List<Integer> trainersId) {
        super(employeeId,name, dateOfBirth, dateOfJoin, gender, phoneNumber, emailId, salary, aadharId, bloodGroup, qualification, role);
        this.trainingPeriod = trainingPeriod;
        this.trainersId = trainersId;
    }

    public void setTrainingPeriod(int trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    public int getTrainingPeriod() {
        return this.trainingPeriod;
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


    public boolean equals(Object o) {
        Trainee trainee = (com.ideas2it.employee.model.Trainee) o;
        if (trainee.getEmployeeId() != trainee.getEmployeeId()) {
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



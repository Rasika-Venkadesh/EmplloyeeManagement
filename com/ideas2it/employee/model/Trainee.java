package com.ideas2it.employee.model;

import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.utility.NumberUtil;
import com.ideas2it.employee.common.constant.Constant;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

/**
 * <p>
 * Trainee class extends from Employee class
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class Trainee extends Employee {
    private int trainingPeriod;
    private Employee employee;
    private Set<Trainer> trainers;
    private List<Integer> trainersId;
    private int traineeId;

    public Trainee() {}
    public Trainee(String name, String dateOfBirth, String dateOfJoin, String gender, long phoneNumber,
                   String emailId, double salary, long aadharId, String bloodGroup, Qualification qualification, int trainingPeriod, Role role,List<Integer> trainersId ) {
    super(name, dateOfBirth, dateOfJoin, gender, phoneNumber, emailId, salary, aadharId, bloodGroup,qualification, role); 
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
	return trainingPeriod;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
	return employee;
    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public List<Integer> getTrainersId() {
	return trainersId;
    }

    public void setTrainersId(List<Integer> trainersId) {
	this.trainersId = trainersId;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }


    /**
     * <p>
     * Trainee class overrides from Object class toString method.
     * </p>
     * @param Object class object
     *
     @ return {@Link String} return String representation of Trainee Object
     **/
    @Override
    public String toString() {
        trainersId = new ArrayList<>();
        for(Trainer trainer : trainers) {
            trainersId.add(trainer.getEmployee().getEmployeeId());
        }
        return String.format(Constant.TRAINEE_FORMAT,"I2I-" + employee.getEmployeeId(), employee.getName(), 
                                 NumberUtil.calculateEmployeeAge(employee.getDateOfBirth()),
                                 NumberUtil.calculateEmployeeExperience(employee.getDateOfJoin()), 
                                 employee.getGender(), employee.getPhoneNumber(),              
                                 employee.getEmailId(), employee.getSalary(),
                                 employee.getAadharId(), employee.getBloodGroup(),
                                 employee.getQualification().getQualification(), getTrainersId().toString());
    }

    
    /**
     * <p>
     * Trainee class overrides from Object class override method.
     * </p>
     * @param Object class object
     *
     @ return {@Link boolean} return true or false
     **/
    @Override
    public boolean equals(Object o) {
        Trainee trainee = (Trainee)o;

        if (this.getEmployeeId() != trainee.getEmployeeId()) {
            return false;
        }

        if (!(trainee.getName().equals(this.getName()))) {
            return false;
        }

        if (!(this.getBloodGroup().equals(trainee.getBloodGroup()))) {
            return false;
        }

        return true;
    }

    /**
     * <p>
     * Trainee class overrides from Object class hashcode method.
     * </p>
     *
     @ return {@Link int} return zero
     **/
    @Override
    public int hashCode() {
        return 0;
    }



} 
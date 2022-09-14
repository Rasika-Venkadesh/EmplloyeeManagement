package com.ideas2it.employee.model;
import com.ideas2it.employee.model.Employee;
import java.util.Set;

/**
 * <p>
 * Trainer class extends from Employee class
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class Trainer extends Employee {
    private int experience;
    private int noOfTrainees;
    private int trainerId;
    private Employee employee;
    private Set<Trainee> trainees;

    public Trainer() {}
    public Trainer(String name, String dateOfBirth, String dateOfJoin, String gender, long phoneNumber,
                   String emailId, double salary, long aadharId, String bloodGroup, Qualification qualification,
                   int experience, Role role) {
        super(name, dateOfBirth, dateOfJoin, gender, phoneNumber, emailId, salary, aadharId, bloodGroup, qualification, role);
        this.experience = experience;
    } 
   
    public Trainer(Employee employee,int experience) {
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
	return experience;
    }

    public int getNoOfTrainees() {
        return noOfTrainees;
    }

    public void setNoOfTrainees(int noOfTrainees) {
        this.noOfTrainees = noOfTrainees;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getTrainerId() {
	return trainerId;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
	return employee;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }
    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
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
        return String.format(Constant.TRAINER_FORMAT,"I2I-" + employee.getEmployeeId(), employee.getName(), 
                                 NumberUtil.calculateEmployeeAge(employee.getDateOfBirth()),
                                 NumberUtil.calculateEmployeeExperience(employee.getDateOfJoin()), 
                                 employee.getGender(), employee.getPhoneNumber(),              
                                 employee.getEmailId(), employee.getSalary(),
                                 employee.getAadharId(), employee.getBloodGroup(),
                                 employee.getQualification().getQualification());
    }    
    
    /**
     * <p>
     * Trainer class overrides from Object class override method.
     * </p>
     * @param Object class object
     *
     @ return {@Link boolean} return true or false
     **/
    @Override
    public boolean equals(Object o) {
        Trainer trainer = (Trainer)o;

        if (this.getEmployeeId() != trainer.getEmployeeId()) {
            return false;
        }


        return true;
    }

    /**
     * <p>
     * Trainer class overrides from Object class hashcode method.
     * </p>
     *
     @ return {@Link int} return zero
     **/
    @Override
    public int hashCode() {
        return 0;
    }


} 
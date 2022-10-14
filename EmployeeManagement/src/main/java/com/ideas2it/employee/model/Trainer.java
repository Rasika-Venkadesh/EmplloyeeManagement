package com.ideas2it.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * <p>
 * Trainer class extends from Employee class
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
@Entity
@Table(name = "trainer")
public class Trainer extends Employee {

    @Column(name = "Experience", nullable = false)
    private int experience;

    public Trainer(int employeeId,String name, String dateOfBirth, String dateOfJoin, String gender,
                   long phoneNumber, String emailId, double salary, long aadharId, String bloodGroup,
                   Qualification qualification, Role role,int experience) {
        super(employeeId,name, dateOfBirth, dateOfJoin, gender, phoneNumber, emailId, salary, aadharId,
                bloodGroup, qualification, role);
        this.experience = experience;
    }

    public Trainer() {
        super();
        this.experience = experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


    public int getExperience() {
        return this.experience;
    }


    public boolean equals(Object o) {
        Trainer trainer = (com.ideas2it.employee.model.Trainer) o;
        if (this.getEmployeeId() != trainer.getEmployeeId()) {
            return false;
        } else if (!trainer.getName().equals(this.getName())) {
            return false;
        } else {
            return this.getBloodGroup().equals(trainer.getBloodGroup());
        }
    }

    public int hashCode() {
        return 0;
    }


}



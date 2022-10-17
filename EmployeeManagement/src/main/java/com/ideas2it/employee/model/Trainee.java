package com.ideas2it.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Trainee class extends from Employee class
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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



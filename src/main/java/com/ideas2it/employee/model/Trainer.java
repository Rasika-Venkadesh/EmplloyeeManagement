package com.ideas2it.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Trainer class extends from Employee class
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainer")
public class Trainer extends Employee {

    @Column(name = "Experience", nullable = false)
    private int experience;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "emp_relation",
            joinColumns = @JoinColumn(name = "trainerId", referencedColumnName = "E_Id"),
            inverseJoinColumns = @JoinColumn(name = "traineeId", referencedColumnName = "E_Id"))
    private Set<Trainee> trainees;

    @Transient
    private List<Integer> traineesId;

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



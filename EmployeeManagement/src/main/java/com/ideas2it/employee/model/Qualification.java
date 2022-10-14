//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * <p>
 * Qualification class holds qualification properties of employee
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/

@Entity
@Table(name = "qualification")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_Id",  nullable = false)
    private int qualificationId;


    @Column(name = "qualification",  nullable = false)
    private String qualification;

    public Qualification() {
    }

    public String getQualification() {
        return this.qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getQualificationId() {
        return this.qualificationId;
    }

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }
}

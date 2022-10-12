//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qualification")
public class Qualification {

    @Id
    @Column(name = "Qualification_Id", unique = true, nullable = false)
    private int qualificationId;

    @Column(name = "Qualification", unique = true,  nullable = false)
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

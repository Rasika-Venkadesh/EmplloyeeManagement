//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * <p>
 * Qualification class holds qualification properties of employee
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "qualification")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_Id",  nullable = false)
    private int qualificationId;


    @Column(name = "qualification",  nullable = false)
    private String qualification;

}

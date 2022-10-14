//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.model;

import javax.persistence.*;

/**
 * <p>
 * Role class holds role properties of employee
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id", unique = true,  nullable = false)
    private int roleId;

    @Column(name = "Role", nullable = false)
    private String role;

    public Role() {
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}

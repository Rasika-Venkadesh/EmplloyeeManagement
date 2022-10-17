package com.ideas2it.employee.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * <p>
 * Employee class holds common fields of employee and having its own getter and setter methods.
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "E_Id", insertable = false, updatable = false)
    private int employeeId;

    @Column(name = "E_Name", nullable = false)
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Date_Of_Birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "Date_Of_Join", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoin;

    @Column(name = "Gender", nullable = false)
    private String gender;

    @Column(name = "Phone_Number", nullable = false)
    private long phoneNumber;

    @Column(name = "Email_Id", nullable = false)
    private String emailId;

    @Column(name = "Salary", nullable = false)
    private double salary;

    @Column(name = "Aadhar_Id", nullable = false)
    private long aadharId;

    @Column(name = "Blood_Group", nullable = false)
    private String bloodGroup;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "qualification_Id", referencedColumnName = "qualification_Id")
    private Qualification qualification;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name = "Role_Id", referencedColumnName = "Role_Id")
    private Role role;

    @Override
    public String toString() {
        return "Employee[" +
                "employeeId=" + employeeId +
                ", name='" + name +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfJoin=" + dateOfJoin +
                ", gender=" + gender +
                ", phoneNumber=" + phoneNumber +
                ", emailId='" + emailId  +
                ", salary=" + salary +
                ", aadharId=" + aadharId +
                ", bloodGroup='" + bloodGroup +
                ", qualification=" + qualification +
                ", role=" + role +
                ']';
    }
}


package com.ideas2it.employee.model;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

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

    public Employee() {
    }

    public Employee(int employeeId,String name, String dateOfBirth, String dateOfJoin, String gender, long phoneNumber, String emailId,
                    double salary, long aadharId, String bloodGroup, Qualification qualification, Role role) {
        this.employeeId = employeeId;
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.dateOfJoin = LocalDate.parse(dateOfJoin);
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.salary = salary;
        this.aadharId = aadharId;
        this.bloodGroup = bloodGroup;
        this.qualification = qualification;
        this.role = role;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoin() {
        return this.dateOfJoin;
    }

    public void setDateOfJoin(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public long getAadharId() {
        return this.aadharId;
    }

    public void setAadharId(long aadharId) {
        this.aadharId = aadharId;
    }
    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Qualification getQualification() {
        return this.qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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


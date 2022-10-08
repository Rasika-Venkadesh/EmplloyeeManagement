//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.common;

public class CommonUtil {
    public static int employeeId = 1;

    public CommonUtil() {
    }

    public static void displayTrainerFormat() {
        System.out.format("|%1$-10s|%2$-20s|%3$-5s|%4$-10s|%5$-7s|%6$-13s|%7$-20s|%8$-10s|%9$-18s|%10$-11s|%11$-14s|\n", "EMPLOYEEID", "NAME", "AGE", "EXPERIENCE", "GENDER", "MOBILE NUMBER", "EMAIL ID", "SALARY", "AADHAR ID", "BLOOD GROUP", "QUALIFICATION");
    }

    public static void displayTraineeFormat() {
        System.out.format("|%1$-10s|%2$-20s|%3$-5s|%4$-10s|%5$-7s|%6$-13s|%7$-20s|%8$-10s|%9$-18s|%10$-11s|%11$-14s|%12$-15s|\n", "EMPLOYEEID", "NAME", "AGE", "EXPERIENCE", "GENDER", "MOBILE NUMBER", "EMAIL ID", "SALARY", "AADHAR ID", "BLOOD GROUP", "QUALIFICATION", "TRAINERID");
    }

    public static void displayUpdateChoices() {
        System.out.println("Press 1 for Update Phone Number");
        System.out.println("Press 2 for Update EmailId");
        System.out.println("Press 3 for Update Aadhar Id");
        System.out.println("Press 4 to Quit update");
    }
}

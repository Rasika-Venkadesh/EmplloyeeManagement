//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.utility;

import java.time.LocalDate;
import java.time.Period;

public class NumberUtil {
    public NumberUtil() {
    }

    public static boolean validNumberCheck(String number, int length) {
        return number.length() != length;
    }

    public static boolean validSalaryCheck(double number, int length) {
        return String.valueOf(number).length() < length;
    }

    public static int calculateEmployeeAge(LocalDate dateOfBirth) {
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears();
    }

    public static int calculateEmployeeExperience(LocalDate dateOfJoin) {
        Period period = Period.between(dateOfJoin, LocalDate.now());
        return period.getYears();
    }
}


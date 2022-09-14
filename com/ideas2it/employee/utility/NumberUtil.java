package com.ideas2it.employee.utility;

import com.ideas2it.employee.model.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * NumberUtil class is for Number related methods.
 * </p> 
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class NumberUtil {
    

    /**
     * <p>
     * This method checks the given number length is equal to required length
     * </p> 
     * @param number
     * @param length
     * @return {@link boolean} return true or false
     **/
    public static boolean validNumberCheck(String number, int length) {
        return (number.length() == length);
    }

    /**
     * <p>
     * This method checks the Salary amount is greater than the required digit
     * </p> 
     * @param number
     * @param length
     * @return {@link boolean} return true or false
     **/
    public static boolean validSalaryCheck(double number, int length) {
        return (String.valueOf(number).length() >= length);        
    }

    /**
     * <p>
     * This method is used to calculate Employee's age based on Date of Birth attribute.
     * </p> 
     *  
     * @return {@link int} return age
     **/    
    public static int calculateEmployeeAge(LocalDate dateOfBirth) {
       Period period = Period.between(dateOfBirth, LocalDate.now());
       int employeeAge = period.getYears();
       return employeeAge;
    }

    /**
     * <p>
     * This method is used to calculate Employee's experience based on Date of Join attribute.
     * </p> 
     *  
     * @return {@link int} return age
     **/
    public static int calculateEmployeeExperience(LocalDate dateOfJoin) {
       Period period = Period.between(dateOfJoin, LocalDate.now());
       int employeeExperience = period.getYears();
       return employeeExperience;
    }



}
  
    
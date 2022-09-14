package com.ideas2it.employee.common;
import com.ideas2it.employee.common.constant.Constant;


/**
 * <p>
 * Constant class holds constant values and common methods for Employee.
 * </p>
 *
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class CommonUtil {           
    public static int employeeId = 1;

    /**
     * <p>
     * This method displays attributes of Trainer in String Format.
     * </p> 
     *
     * @return {@link void} return nothing
     **/
    public static void displayFormat() {         
        System.out.format(Constant.TRAINER_FORMAT, "EMPLOYEEID", "NAME", "AGE",  "EXPERIENCE","GENDER", "MOBILE NUMBER",
                                  "EMAIL ID","SALARY","AADHAR ID","BLOOD GROUP","QUALIFICATION"); 
    } 

    public static void displayFormat1() {         
        System.out.format(Constant.TRAINEE_FORMAT, "EMPLOYEEID", "NAME", "AGE",  "EXPERIENCE","GENDER", "MOBILE NUMBER",
                                  "EMAIL ID","SALARY","AADHAR ID","BLOOD GROUP","QUALIFICATION", "TRAINERID"); 
    } 


 
}




package com.ideas2it.employee.common;
import com.ideas2it.employee.common.constant.Constant;


/**
 * <p>
 * CommonUtil class holds common methods for Employee.
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
    public static void displayTrainerFormat() {         
        System.out.format(Constant.TRAINER_FORMAT, "EMPLOYEEID", "NAME", "AGE",  "EXPERIENCE","GENDER", "MOBILE NUMBER",
                                  "EMAIL ID","SALARY","AADHAR ID","BLOOD GROUP","QUALIFICATION"); 
    } 

    /**
     * <p>
     * This method displays attributes of Trainee in String Format.
     * </p> 
     *
     * @return {@link void} return nothing
     **/
    public static void displayTraineeFormat() {         
        System.out.format(Constant.TRAINEE_FORMAT, "EMPLOYEEID", "NAME", "AGE",  "EXPERIENCE","GENDER", "MOBILE NUMBER",
                                  "EMAIL ID","SALARY","AADHAR ID","BLOOD GROUP","QUALIFICATION", "TRAINERID"); 
    }

    /**
     * <p>
     * This method displays choices for updation.
     * </p>
     *
     * @return {@link void} return nothing 
     **/
    public static void displayUpdateChoices() {
        System.out.println("Press 1 for Update Phone Number");
        System.out.println("Press 2 for Update EmailId");
        System.out.println("Press 3 for Update Aadhar Id");
        System.out.println("Press 4 to Quit update");
    }  
}




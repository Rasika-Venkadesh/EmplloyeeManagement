package com.ideas2it.employee.utility;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>
 * StringUtil class String related methods.
 * </p> 
 *
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class StringUtil {

    /**
     * <p>
     * This method checks the given name is valid based on given conditions.
     * Name length should be greater than 3, and it will not have any digits and special characters.
     * </p> 
     * Ex: name = rasika , return true;
     * Ex: name = rasika@26 , return false;
     * @param name
     * @return {@link boolean} return true or false
     **/
    public static boolean isValidName(String name) {
        boolean isValidName = false;
        for (int i=0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (name.length() >= 3 && (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
               isValidName = true; 
            }         
        }
        return isValidName;
    } 

   /**
     * <p>
     * This method checks the given emailid is valid based on given conditions.
     * </p> 
     * Ex: emailId = rasika123@gmail12.com, return true
     * Ex: emailId = ras.gmail..com, return false
     * @param emailId
     * @return {@link boolean} return true or false
     **/ 
    public static boolean isValidEmailId(String emailId) {       
        String regex = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"; 
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }
}

    
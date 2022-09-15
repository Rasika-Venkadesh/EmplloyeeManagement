package com.ideas2it.employee.common.constant;

/**
 * <p>
 * Enum Gender holds the gender values.
 * </p> 
 **/
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHERS("Others");
    
    public String gender;

    Gender(String gender) {
        this.gender = gender;
   }
}


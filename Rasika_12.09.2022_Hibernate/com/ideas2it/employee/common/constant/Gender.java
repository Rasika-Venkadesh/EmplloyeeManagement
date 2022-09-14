package com.ideas2it.employee.common.constant;


public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHERS("Others");
    
    public String gender;

    Gender(String gender) {
        this.gender = gender;
   }
}


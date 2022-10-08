package com.ideas2it.employee.common.constant;

public enum BloodGroup {
        A_POSITIVE("A Positive"),
        B_POSITIVE("B Positive"),
        O_POSITIVE("O Positive"),
        AB_POSITIVE("AB Positive"),
        A_NEGATIVE("A Negative"),
        B_NEGATIVE("B Negative"),
        O_NEGATIVE("O Negative"),
        AB_NEGATIVE("AB Negative");

        public String bloodGroup;

        private BloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
        }
    }



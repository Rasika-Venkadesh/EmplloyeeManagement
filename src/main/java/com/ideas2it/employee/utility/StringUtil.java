//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public StringUtil() {
    }

    public static boolean isValidName(String name) {
        boolean isValidName = false;

        for(int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            if (name.length() >= 3 && c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
                isValidName = true;
            }
        }

        return isValidName;
    }

    public static boolean isValidEmailId(String emailId) {
        String regex = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }
}

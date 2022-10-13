//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.utility;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {
    public DateUtil() {
    }

    public static int computePeriod(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        return period.getYears();
    }

    public static int computeDays(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        return period.getDays();
    }
}

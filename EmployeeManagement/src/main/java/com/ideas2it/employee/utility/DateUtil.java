//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ideas2it.employee.utility;

import java.time.LocalDate;
import java.time.Period;
/**
 * <p>
 * DateUtil class validates the date properties
 * </p>
 * @author Rasika Venkadesh
 * @version 1.0
 **/
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

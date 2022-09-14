package com.ideas2it.employee.utility;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * <p>
 * DateUtil class is for Date and time related methods.
 * </p> 
 * @author Rasika Venkadesh
 * @version 1.0
 **/   
public class DateUtil {

    /**
     * <p>
     * This method calculates years between given 2 dates. 
     * </p> 
     *
     * Ex: startDate = 24/02/2000, endDate = 12/04/2021, return 21
     *
     * @param startDate - Starting date
     * @param endDate   - Finishing date
     * @return {@link int} return number of years
     **/
    public static int computePeriod(LocalDate startDate, LocalDate endDate) {
	int computedYears;
	Period period = Period.between(startDate, endDate);
	computedYears = period.getYears();
	return computedYears;
    }

    /**
     * <p>
     * This method calculates days between given 2 dates. 
     * </p>
     * 
     * Ex: startDate = 24/02/2000, endDate = 25/02/2000, return 1
     *
     * @param startDate - Starting date
     * @param endDate   - Finishing date
     * @return {@link int} return number of days
     **/
    public static int computeDays(LocalDate startDate, LocalDate endDate) {
	int computedDays;
	Period period = Period.between(startDate, endDate);
	computedDays = period.getDays();
	return computedDays;
    }
}


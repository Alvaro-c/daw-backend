package com.freetoursegovia.freetoursegovia.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to make format transformations
 * @author Alvaro Canas
 */
public class Utils {

    /**
     * Given a date in SQL format it returns the same date as a LocalDate object
     * @param date The String in SQL format
     * @return The LocalDate object
     */
    public static LocalDate stringToLocalDate(String date) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lDate = LocalDate.parse(date, dtf);

        return lDate;

    }

    public static String SQLtoString(LocalDate date) {

        String dateS = date.toString();

        String[] array = dateS.split("-");
        String newDate = array[2] + "/" + array[1] + "/" + array[0];


        return newDate;

    }

}


package com.freetoursegovia.freetoursegovia.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class to make format transformations
 * @author Alvaro Canas
 */
public class Utils {

    /**
     * Returns a String containing the date of today in SQL format (yyyy-MM-dd)
     * @return the string with the date
     */
    public String todaySQLFormat() {

        String dateAndTime = java.time.LocalDateTime.now().toString();
        String dateSQL = dateAndTime.substring(0, 10);

        return dateSQL;

    }

    /**
     * Given a date in format dd/MM/YYYY
     * it returns the same date with SQL format yyyy-MM-dd
     * @param date The string with he date
     * @return The String with the date in SQL format
     */
    public static String dateToSQLFormat(String date) {

        String[] dateArray = date.split("/");
        String newDate = dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0];

        return newDate;

    }

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


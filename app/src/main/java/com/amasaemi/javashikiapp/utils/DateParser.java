package com.amasaemi.javashikiapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.DataFormatException;

/**
 * Created by Alex on 18.02.2018.
 */

public class DateParser {
    public static String parse(String pattern, Date date, String error) {
        try {
            return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
        } catch (Exception ex) {
            return error;
        }
    }

    public static String parseToMMddyyy(Date date) {
        String DATE_PATTERN = "MMM dd, yyyy";

        try {
            StringBuilder dateBuilder = new StringBuilder(new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(date));
            dateBuilder.replace(0, 1, String.valueOf(dateBuilder.charAt(0)).toUpperCase());
            return dateBuilder.toString().replace(".", "");
        } catch (Exception ex) {
            return "?";
        }
    }

    public static String parseSeason(Date date, String[] seasons) {
        String age = new SimpleDateFormat("yyyy").format(date);

        switch (new SimpleDateFormat("MM").format(date)) {
            case "12":
            case "01":
            case "02":
                return String.format("%s %s", seasons[0], age);

            case "03":
            case "04":
            case "05":
                return String.format("%s %s", seasons[1], age);

            case "06":
            case "07":
            case "08":
                return String.format("%s %s", seasons[2], age);

            case "09":
            case "10":
            case "11":
                return String.format("%s %s", seasons[3], age);

            default:
                throw new NullPointerException(new SimpleDateFormat("MM").format(date) + " is not valid month number");
        }
    }
}

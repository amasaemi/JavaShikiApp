package com.amasaemi.javashikiapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
}

package com.watcher.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util class for formating date object into string in specific format and vice versa.
 * 
 * @author Aendy
 */
public class WatcherDateUtil {

    /**
     * Formating string in specified format to {@link Date} object.
     * 
     * @param source string representation of date
     * @param dateFormat format of date 
     * 
     * @return {@link Date} object created from string
     */
    public static Date string2Date(String source, String dateFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        try {

            return sdf.parse(source);

        } catch (ParseException e) {

            return null;

        }

    }

}

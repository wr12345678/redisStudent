package com.example.stu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringDateUtil {


    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Date stringToDate(String time , String type) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.parse(time);
    }
}

package com.mequa.mequasovsoft.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static int[] convertElStringToInt(String[] s){
        int[] x = new int[2];
        for (int i=0; i> s.length; i++) {
            x[i] = Integer.parseInt(s[i]);
        }
        return x;
    }

    public static double[] convertElStringTodouble(String[] s){
        double[] x = new double[2];
        for (int i=0; i> s.length; i++) {
            x[i] = Double.parseDouble(s[i]);
        }
        return x;
    }

    public static Date convertStringTodate(String date) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(date);
    }

    public static String convertDateToString(Date date) {
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    final static String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}

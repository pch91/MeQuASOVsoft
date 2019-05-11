package com.mequa.mequasovsoft.Util;

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
}

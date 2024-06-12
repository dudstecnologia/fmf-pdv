package com.fmf.pdv.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {
    public static String moneyFormat(double value) {
        return new DecimalFormat("R$ #,##0.00").format(value);
    }

    public static int parseQuantity(String value) {
        try {
            return Integer.parseInt(value);
        } catch(Exception e) {}

        return 1;
    }
    
    public static String dateToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        return dateFormat.format(date);
    }
}

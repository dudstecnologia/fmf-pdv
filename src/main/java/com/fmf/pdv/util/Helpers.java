package com.fmf.pdv.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

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
    
    public static String dateTodayBr() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }
    
    public static String dateToDb(String date) {
        String[] d = date.split("/");

	return d[2] + "-" + d[1] + "-" + d[0];
    }
    
    public static void maskDate(JFormattedTextField jFormattedTextField) {
        try {
            jFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (ParseException ex) {}
    }
}

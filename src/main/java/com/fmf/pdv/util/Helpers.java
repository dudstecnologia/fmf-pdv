package com.fmf.pdv.util;

import java.text.DecimalFormat;

public class Helpers {
    public static String moneyFormat(double value) {
        return new DecimalFormat("R$ #,##0.00").format(value);
    }
}

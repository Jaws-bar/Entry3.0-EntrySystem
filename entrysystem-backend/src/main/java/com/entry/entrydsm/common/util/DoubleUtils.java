package com.entry.entrydsm.common.util;

public class DoubleUtils {
    public static double roundByN(double number, int n) {
        int divide = (int) Math.pow(10, n - 1);
        return Math.round(number * divide) / (double) divide;
    }
}

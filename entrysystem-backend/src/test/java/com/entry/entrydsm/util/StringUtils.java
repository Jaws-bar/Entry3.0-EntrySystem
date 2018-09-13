package com.entry.entrydsm.util;

public class StringUtils {

    public static String repeat(String s, int times) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i <= times; i++) {
            buffer.append(s);
        }
        return buffer.toString();
    }
}

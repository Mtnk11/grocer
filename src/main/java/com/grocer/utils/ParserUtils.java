package com.grocer.utils;

public class ParserUtils {
    public static boolean isNotParsableToInt(String s) {
        if (s == null || s.isBlank()) {
            return true;
        }
        if (s.equalsIgnoreCase("null")) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return true;
        }
        return false;
    }
}
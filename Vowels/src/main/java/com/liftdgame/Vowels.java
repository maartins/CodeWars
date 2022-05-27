package com.liftdgame;

public class Vowels {
    public static int getCount(String str) {
        if (str == null || str.isEmpty())
            return 0;

        return str.replaceAll("[^aeiou]", "").length();
    }
}

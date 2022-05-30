package com.liftdgame;

public class DRoot {
    public static int digital_root(int n) {
        String number = n + "";
        if (number.length() > 1)
            return digital_root(number.chars().map(c -> c - 48).sum());

        return Integer.parseInt(number);
    }
}

package com.liftdgame;

public class DigPow {
    public static long digPow(int n, int p) {
        String intToString = n + "";
        double pToDouble = p;
        double result = 0.0;

        for (int digit: intToString.chars().toArray()) {
            result += Math.pow(digit - 48, pToDouble++);
        }

        return (result % n) == 0 ? (long) (result / n) : -1;
    }
}

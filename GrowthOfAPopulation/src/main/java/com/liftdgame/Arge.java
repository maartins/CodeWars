package com.liftdgame;

public class Arge {

    public static int nbYear(int p0, double percent, int aug, int p) {
        int result = 0;
        int pop = p0;
        double calcPercent = percent / 100;

        while (pop < p) {
            pop = (int) (pop + (pop * calcPercent) + aug);
            result++;
        }

        return result;
    }
}

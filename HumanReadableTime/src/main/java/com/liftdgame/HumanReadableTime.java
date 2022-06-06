package com.liftdgame;

public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        if (seconds > 359999)
            return "99:59:59";

        if (seconds <= 0)
            return "00:00:00";

        int hh = 0;
        int mm = 0;
        int ss = 0;

        int timer = seconds;

        while (timer > 0) {
            ss++;

            if (ss == 60) {
                mm++;
                ss = 0;
            }
            if (mm == 60) {
                hh++;
                mm = 0;
            }

            timer--;
        }

        String h = hh <= 9 ? "0" + hh : hh + "";
        String m = mm <= 9 ? "0" + mm : mm + "";
        String s = ss <= 9 ? "0" + ss : ss + "";

        return h + ":" + m + ":" + s;
    }
}

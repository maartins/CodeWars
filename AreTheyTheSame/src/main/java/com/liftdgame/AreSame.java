package com.liftdgame;

import java.util.List;
import java.util.stream.IntStream;

public class AreSame {
    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null)
            return false;

        if (a.length == 0 && b.length == 0)
            return true;

        if (a.length == 0 || b.length == 0)
            return false;

        List<Integer> aList = new java.util.ArrayList<>(IntStream.of(a).map(Math::abs).boxed().toList());

        for (double e : b) {
            double c = Math.sqrt(e);

            if (c % 1 != 0)
                return false;

            if (aList.contains((int) Math.round(c))) {
                aList.remove(Integer.valueOf((int) Math.round(c)));
            }
        }

        return aList.size() <= 0;
    }
}

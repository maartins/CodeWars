package com.liftdgame;

import java.util.*;

public class Kata {
    public static int[] sortArray(int[] array) {
        if (array == null || array.length == 0)
            return new int[0];

        List<Integer> odds = new ArrayList<>();
        List<Integer> oddIndexes = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                odds.add(array[i]);
                oddIndexes.add(i);
            }
        }

        Collections.sort(odds);

        int[] result = array.clone();
        int i = 0;
        for (int index : oddIndexes) {
            result[index] = odds.get(i);
            i++;
        }

        return result;
    }
}

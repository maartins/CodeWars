package com.liftdgame;

import java.util.*;

public class EnoughIsEnough {
    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        if (elements == null
                || elements.length == 0
                || maxOccurrences < 1)
            return  new int[0];

        Map<String, Integer> occurred = new HashMap<>();

        for (int e : Arrays.stream(elements).distinct().toArray()) {
            occurred.put("n" + e, 0);
        }

        List<Integer> result = new ArrayList<>();

        for (int e : elements) {
            if (occurred.get("n" + e) < maxOccurrences) {
                result.add(e);
                occurred.put("n" + e, occurred.get("n" + e) + 1);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

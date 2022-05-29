package com.liftdgame;

import java.util.*;

public class Kata {
    public static String high(String s) {
        if (s == null || s.isEmpty())
            return "";

        if (!s.contains(" "))
            return s;

        Map<Character, Integer> alphabet = new HashMap<>();

        int i = 1;
        for (char ch = 'a'; ch <= 'z'; ++ch)
            alphabet.put(ch, i++);

        String[] words = s.split(" ");
        List<Integer> leaderboards = new ArrayList<>(Arrays.stream(words)
                .map(word -> word.chars()
                        .map(c -> alphabet.get((char) c))
                        .sum())
                .toList());
        Integer max = leaderboards.stream().max(Integer::compare).get();
        return words[leaderboards.indexOf(max)];
    }
}

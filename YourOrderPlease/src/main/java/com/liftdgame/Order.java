package com.liftdgame;

public class Order {
    public static String order(String words) {
        if (words == null || words.isEmpty())
            return "";

        String[] splitWords = words.split(" ");

        if (splitWords.length == 1)
            return splitWords[0];

        for (int i = 0; i < splitWords.length - 1; i++) {
            for (int j = 0; j < splitWords.length - i - 1; j++) {
                int lhs = Integer.parseInt(splitWords[j].replaceAll("\\D", ""));
                int rhs = Integer.parseInt(splitWords[j + 1].replaceAll("\\D", ""));
                if (lhs > rhs) {
                    String temp = splitWords[j];
                    splitWords[j] = splitWords[j + 1];
                    splitWords[j + 1] = temp;
                }
            }
        }

        return java.util.Arrays.toString(splitWords).replaceAll("[,\\[\\]]", "");
    }
}

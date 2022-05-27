package liftdgame;

public class Maskify {
    public static String maskify(String str) {
        if (str == null)
            return "";

        if (str.length() <= 4)
            return str;

        int index = str.length() - 4;

        return str.substring(0, index).replaceAll(".", "#") + str.substring(index);
    }
}

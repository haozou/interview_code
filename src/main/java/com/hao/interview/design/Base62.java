package com.hao.interview.design;

/**
 * Created by hzou on 1/24/18.
 */
public final class Base62 {
    private static String ALPHABET = "0123456789abcdefghijklmnopqrsstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";



    public static String fromBase10(long base10) {
        return fromBase10(base10, 6, ALPHABET);
    }
    public static String fromBase10(long base10, int numOfPadding, String alphabet) {
        StringBuilder builder = new StringBuilder();
        while (base10 > 0) {
            int remain = (int) (base10 % alphabet.length());
            builder.append(alphabet.charAt(remain));
            base10 = base10 / alphabet.length();
        }
        String result = builder.reverse().toString();

        return result;
    }

    public static long toBase10(String base62) {
        return toBase10(base62, ALPHABET);
    }
    public static long toBase10(String base62, String alphabet) {
        char[] chars = base62.toCharArray();
        long result = 0L;
        for (int i = chars.length - 1; i >= 0; i--) {
            result += Math.pow(alphabet.length(), i) * alphabet.indexOf(chars[chars.length - i - 1]);
        }
        return result;
    }
}

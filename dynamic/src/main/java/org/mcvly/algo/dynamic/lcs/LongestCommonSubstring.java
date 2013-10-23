package org.mcvly.algo.dynamic.lcs;

import java.util.AbstractList;
import java.util.List;

/**
 * @author: mcvly
 * @since: 10/23/13
 */
public class LongestCommonSubstring {

    public static String longestCommonSubstring(String first, String second) {
        return asString(longestCommonSublist(asList(first), asList(second)));
    }

    public static <T> List<T> longestCommonSublist(List<T> a, List<T> b) {
        int[] array = new int[b.size()];

        System.out.println(a.size());
        System.out.println(b.size());

        int maxI = -1;
        int maxVal = -1;

        for (int i = 0; i < a.size(); i++) {
            for (int j = b.size()-1; j > 0; j--) {
                if (a.get(i).equals(b.get(j))) {
                    array[j] = get(array, j-1) + 1;
                    if (array[j] > maxVal) {
                        maxVal = array[j];
                        maxI = i;
                    }
                } else {
                    array[j] = 0;
                }
            }
        }

        return a.subList(maxI-maxVal+1, maxI+1);
    }

    private static int get(int[] array, int j) {
        if (j < 0) {
            return 0;
        }

        return array[j];
    }

    private static List<Character> asList(final String string) {
        return new AbstractList<Character>() {
            public int size() { return string.length(); }
            public Character get(int index) { return string.charAt(index); }
        };
    }

    private static String asString(List<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list) {
            builder.append(ch);
        }
        return builder.toString();
    }

}

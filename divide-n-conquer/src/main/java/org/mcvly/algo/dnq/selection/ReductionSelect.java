package org.mcvly.algo.dnq.selection;

import java.util.Arrays;

/**
 * User: Ruslan
 * Date: 12.02.13
 * Time: 20:05
 */
public class ReductionSelect {
    /**
     * Finds i-th order statistic by sorting copy of input array and returning i-th element of it
     * Running time is O(n*log(n)) since we're using quick sort
     * @param array input array
     * @param i i-th order statistic to find
     * @return i-th order statistic
     */
    public static int reductionSelect(int[] array, int i) {
        if (i < 1 || i>array.length-1) {
            throw new IllegalArgumentException("i is not within array boundaries");
        }
        int[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        return sortedArray[i-1];
    }
}

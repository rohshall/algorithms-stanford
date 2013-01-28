package org.mcvly.algo.merge;

import java.util.Arrays;

/**
 * User: RMalyona
 * Date: 28.01.13
 */
public class MergeSort {

    public static int[] merge(int[] x, int[] y) {
        int[] result = new int[x.length + y.length];
        for(int k=0, i=0, j=0; k<result.length; k++) {
            if (x[i] < y[j]) {
                result[k] = x[i];

                if (i+1 == x.length) {
                    x[i] = Integer.MAX_VALUE;
                } else {
                    i++;
                }
            } else {
                result[k] = y[j];

                if (j+1 == y.length) {
                    y[j] = Integer.MAX_VALUE;
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int x[] = {1,3,5,7,9};
        int y[] = {2,4,6, 8};

        System.out.println(Arrays.toString(merge(x,y)));
    }
}

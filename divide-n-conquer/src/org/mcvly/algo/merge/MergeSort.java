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
            if (j >= y.length || (i < x.length && x[i] <= y[j])) {
                result[k] = x[i];
                i++;
            } else {
                result[k] = y[j];
                j++;
            }
        }
        return result;
    }

    public static int[] sort(int[] array) {

        if (array.length == 1) {
            return array;
        }

        int length = array.length;
        int halfSize = length/2;

        int[] ar1 = Arrays.copyOfRange(array, 0, halfSize);
        int[] ar2 = Arrays.copyOfRange(array, halfSize, length);
        return merge(sort(ar1), sort(ar2));
    }

    public static void main(String[] args) {

        int x[] = {1,3,5,7,8,9, 2,4,6,8};
        System.out.println(Arrays.toString(sort(x)));
    }
}

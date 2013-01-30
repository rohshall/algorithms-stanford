package org.mcvly.algo.dnq.inversions;

import java.util.Arrays;

/**
 * User: RMalyona
 * Date: 30.01.13
 */
public class CountingInversions {

    public static long countInversionsAndSort(int[] array) {

        int length = array.length;

        if (length == 1) {
            return 0;
        }

        int halfSize = length/2;
        long x,y,z;

        // first half
        int[] arrX = Arrays.copyOfRange(array, 0, halfSize);
        int[] arrY = Arrays.copyOfRange(array, halfSize, length);
        x = countInversionsAndSort(arrX);
        y = countInversionsAndSort(arrY);

        // assume arrX and arrY are already sorted arrays
        z = countSplitInvAndMerge(array, arrX, arrY);

        return x+y+z;
    }

    private static long countSplitInvAndMerge(int[] resultArray, int[] x, int[] y) {

        long inversions = 0;

        for(int k=0, i=0, j=0; k<resultArray.length; k++) {
            if (j >= y.length || (i < x.length && x[i] <= y[j])) {
                resultArray[k] = x[i];
                i++;
            } else {
                resultArray[k] = y[j];
                j++;
                inversions += (x.length - i);
            }
        }

        return inversions;
    }
}

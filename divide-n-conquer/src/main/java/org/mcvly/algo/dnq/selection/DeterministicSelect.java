package org.mcvly.algo.dnq.selection;

import java.util.Arrays;

/**
 * User: Ruslan
 * Date: 12.02.13
 * Time: 22:17
 */
public class DeterministicSelect {
        //extends AbstractLinearSelection {
//    @Override
//    protected int getPivotIndex(int[] array, int n) {
//        //1. break into groups of 5, sort each group
//        int chunks = floor(n, 5);
//        int[][] groups = new int[chunks][];
//        for (int i=0; i<chunks; i++) {
//            if (i*5 + 4 < n) {
//                groups[i] = new int[5];
//            } else {
//                groups[i] = new int[n - 5*i];
//            }
//            for (int j=0; j<5; j++) {
//                if (i*5 + j < n) {
//                    groups[i][j] = array[i*5 + j];
//                } else {
//                    break;
//                }
//            }
//        }
//        //2. C=n/5 "middle elements"
//        int[] arrayC = new int[chunks];
//        for (int i=0; i<chunks; i++) {
//            arrayC[i] = medianOfArray(groups[i]);
//        }
//
//        //3. recursive compute median
//        int pivot = select(arrayC, chunks, chunks/2);
//        int pivotIndex = getIndexByValue(array, pivot);
//        if (pivotIndex < 0) {
//            throw new RuntimeException("Error in algorithm");
//        } else {
//            return pivotIndex;
//        }
//    }
//
//    private int medianOfArray(int[] a) {
//        if (a.length == 0) {
//            throw new RuntimeException("Error in algorithm");
//        }
//        if (a.length == 1) {
//            return a[0];
//        }
//        Arrays.sort(a);
//        return a[(a.length-1)/2];
//    }
//
//    private int floor(int n, int d) {
//        if (n % d == 0) {
//            return n / d;
//        } else {
//            return (n / d) + 1;
//        }
//    }
//
//    private int getIndexByValue(int[] array, int value) {
//        for (int i=0; i<array.length; i++) {
//            if (array[i] == value) {
//                return i;
//            }
//        }
//        return -1;
//    }
}

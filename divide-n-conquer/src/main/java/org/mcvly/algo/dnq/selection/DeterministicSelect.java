package org.mcvly.algo.dnq.selection;

import java.util.Arrays;

/**
 * User: Ruslan
 * Date: 12.02.13
 * Time: 22:17
 */
public class DeterministicSelect extends AbstractLinearSelection {
    @Override
    protected int getPivotIndex(int low, int high) {
        //1. break into groups of 5, sort each group
        //2. C=n/5 "middle elements"
        int chunks = (high - low) / 5 + 1;
        int[] arrayC = new int[chunks];
        for (int i=0; i<chunks-1; i++) {
            arrayC[i] = medianOfArray(Arrays.copyOfRange(array, 5*i, 5*i+5));
        }

        //3. recursive compute median
        return select();
    }

    private int medianOfArray(int[] a) {
        if (a.length == 0) {
            throw new RuntimeException("Error in algorithm");
        }
        if (a.length == 1) {
            return a[0];
        }
        Arrays.sort(a);
        return a[(a.length-1)/2];
    }
}

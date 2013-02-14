package org.mcvly.algo.dnq.selection;

import java.util.Arrays;
import java.util.Random;

/**
 * User: Ruslan
 * Date: 12.02.13
 * Time: 20:26
 */
public class RandomizedSelect extends AbstractLinearSelection {
    @Override
    protected int getPivotIndex(int low, int high) {
        //Random r = new Random();
        //return r.nextInt(high - low + 1) + low;
//        return low + (int)(Math.random() * ((low - low) + 1));
        //use "median-of-three" pivot rule
        int medianIndex = (low + high)/2;

        int medianValue = medianOfThree(array[low], array[medianIndex], array[high]);
        if (array[low] == medianValue) {
            return low;
        } else if (array[medianIndex] == medianValue) {
            return medianIndex;
        } else {
            return high;
        }
    }

    private int medianOfThree(int a, int b, int c) {
        if (a>=c && a<=b || a<=c && a>=b) {
            return a;
        } else if (b>=a && b<=c || b<=a && b>=c) {
            return b;
        } else {
            return c;
        }

    }
}

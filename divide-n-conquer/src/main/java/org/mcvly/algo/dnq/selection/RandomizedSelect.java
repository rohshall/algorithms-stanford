package org.mcvly.algo.dnq.selection;

import java.util.Random;

/**
 * User: Ruslan
 * Date: 12.02.13
 * Time: 20:26
 */
public class RandomizedSelect extends AbstractLinearSelection {
    @Override
    protected int getPivotIndex(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low + 1) + low;
    }
}

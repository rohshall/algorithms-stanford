package org.mcvly.algo.dnq.selection;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * User: RMalyona
 * Date: 13.02.13
 */
public class SelectionPerformance {
    @Test
    public void performanceTest() {
        AbstractLinearSelection randomSelection = new RandomizedSelect();

        int[] array = SelectionTest.getArray(10000000);

        long startTime = System.currentTimeMillis();
        int random = randomSelection.getOrderedStatistic(array, array.length/2);
        long stopTime = System.currentTimeMillis();
        long randomSelectionTime = stopTime - startTime;

        startTime = System.currentTimeMillis();
        int reduced = ReductionSelect.reductionSelect(array, array.length/2);
        stopTime = System.currentTimeMillis();
        long reducedSelectionTime = stopTime - startTime;

        Assert.assertEquals(random, reduced);
        System.out.println("Randomized selection " + randomSelectionTime);
        System.out.println("Reduced selection " + reducedSelectionTime);
    }
}

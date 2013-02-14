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
//        AbstractLinearSelection determSelection = new RandomizedSelect();

        int arraySize = 90000;

        int[] array = new int[arraySize];

        long startTime = System.currentTimeMillis();
        int random = randomSelection.getOrderedStatistic(array, array.length/2);
        long stopTime = System.currentTimeMillis();
        long randomSelectionTime = stopTime - startTime;

//        startTime = System.currentTimeMillis();
//        int determ = randomSelection.getOrderedStatistic(array, array.length/2);
//        stopTime = System.currentTimeMillis();
//        long deterSelectionTime = stopTime - startTime;

//        Assert.assertEquals(random, determ);
        System.out.println("Randomized selection " + randomSelectionTime);
       // System.out.println("Determined selection " + deterSelectionTime);
    }
}

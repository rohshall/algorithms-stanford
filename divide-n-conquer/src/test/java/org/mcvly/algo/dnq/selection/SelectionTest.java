package org.mcvly.algo.dnq.selection;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * User: Ruslan
 * Date: 12.02.13
 * Time: 21:48
 */
public class SelectionTest {

    @Test
    public void testEven() {
        int[] array = getArray(1000);
        AbstractLinearSelection linearSelection = new DeterministicSelect();
        int actual = linearSelection.getOrderedStatistic(array, 6);
        int expected = ReductionSelect.reductionSelect(array, 6);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testOdd() {
        int[] array = getArray(1001);
        int expected = ReductionSelect.reductionSelect(array, 6);

        AbstractLinearSelection linearSelection = new DeterministicSelect();
        int actual = linearSelection.getOrderedStatistic(array, 6);
        Assert.assertEquals(expected, actual);
    }

    public static int[] getArray(int n) {
        Random random = new Random();
        int[] array = new int[n];
        for (int i=0; i<n; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }
}

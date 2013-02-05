package org.mcvly.algo.dnq.quicksort;

/**
 * User: RMalyona
 * Date: 05.02.13
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ComparisonsTest {
    @Test
    public void testComparisons10InOrder() {
        QuickSort sorter = new QuickSort();
        int[] test = generateArrayInOrder(10);
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(19, sorter.getComparisons());
    }

    @Test
    public void testComparisons10InReverseOrder() {
        QuickSort sorter = new QuickSort();
        int[] test = generateArrayInReverseOrder(10);
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(19, sorter.getComparisons());
    }

    @Test
    public void testComparisons100InOrder() {
        QuickSort sorter = new QuickSort();
        int[] test = generateArrayInOrder(100);
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(480, sorter.getComparisons());
    }

    @Test
    public void testComparisons100InReverseOrder() {
        QuickSort sorter = new QuickSort();
        int[] test = generateArrayInReverseOrder(100);
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(1302, sorter.getComparisons());
    }

    @Test
    public void testCustomArray() {
        QuickSort sorter = new QuickSort();
        int[] test = {2, 8, 9, 3, 7, 5, 10, 1, 6, 4};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(19, sorter.getComparisons());
    }

    @Test
    public void testCustomArray2() {
        QuickSort sorter = new QuickSort();
        int[] test = {1,2,3,4};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(4, sorter.getComparisons());
    }

    @Test
    public void testCustomArray3() {
        QuickSort sorter = new QuickSort();
        int[] test = {9, 6, 3, 7, 2, 4};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(8, sorter.getComparisons());
    }

    @Test
    public void testCustomArray4() {
        QuickSort sorter = new QuickSort();
        int[] test = {9, 6, 3, 7, 4, 2};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(9, sorter.getComparisons());
    }

    @Test
    public void extraTest3() {
        QuickSort sorter = new QuickSort();
        int[] test = {0, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(25, sorter.getComparisons());
    }

    @Test
    public void extraTest4() {
        QuickSort sorter = new QuickSort();
        int[] test = {1, 11, 5, 15, 2, 12, 9, 99, 77, 0};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(22, sorter.getComparisons());
    }

    @Test
    public void extraTest1() {
        QuickSort sorter = new QuickSort();
        int[] test = {999, 3, 2, 98, 765, 8, 14, 15, 16, 88, 145, 100};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(29, sorter.getComparisons());
    }

    @Test
    public void extraTest2() {
        QuickSort sorter = new QuickSort();
        int[] test = {1, 11, 5, 15, 2, 999, 3, 2, 98, 765, 8, 14, 15, 16, 88, 145, 100, 12, 9, 99, 77, 0};
        sorter.sort(test);
        if (!validate(test)) {
            fail("Should not happen");
        }
        assertEquals(82, sorter.getComparisons());
    }

    private boolean validate(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private int[] generateArrayInOrder(int n) {
        int[] array = new int[n];
        for (int i=0; i<n; i++) {
            array[i] = i+1;
        }
        return array;
    }

    private int[] generateArrayInReverseOrder(int n) {
        int[] array = new int[n];
        for (int i=0; i<n; i++) {
            array[i] = n-i;
        }
        return array;
    }
}

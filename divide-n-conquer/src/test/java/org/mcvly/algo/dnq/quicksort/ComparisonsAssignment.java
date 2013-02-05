package org.mcvly.algo.dnq.quicksort;

import junit.framework.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * User: RMalyona
 * Date: 05.02.13
 */
public class ComparisonsAssignment {
    @Test
    public void testAssignment() throws IOException {
        int[] array = fillArray("QuickSort.txt");
        Assert.assertEquals(10000, array.length);
        long comparisons = countComparisons(array);
        System.out.println("Answer is: " + comparisons);
        Assert.assertEquals(138382, comparisons);
    }

    private int[] fillArray(String name) throws IOException {
        InputStream is = ComparisonsAssignment.class.getClassLoader().getResourceAsStream(name);
        List<Integer> list = new LinkedList<Integer>();

        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        String s = in.readLine();

        while(s != null) {
            if (s.length() > 0) {
                list.add(Integer.parseInt(s));
            }
            s = in.readLine();
        }

        in.close();
        is.close();

        return toIntArray(list);
    }

    private long countComparisons(int[] x) {
        QuickSort sorter = new QuickSort();
        sorter.sort(x);
        return sorter.getComparisons();
    }

    int[] toIntArray(List<Integer> list)  {
        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer e : list)
            ret[i++] = e.intValue();
        return ret;
    }
}

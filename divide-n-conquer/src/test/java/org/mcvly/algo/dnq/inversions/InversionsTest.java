package org.mcvly.algo.dnq.inversions;

import junit.framework.Assert;
import org.junit.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * User: RMalyona
 * Date: 30.01.13
 */
public class InversionsTest {

    @Test
    public void testTrivialCase() {
        int[] array = {1,2,3,4};
        Assert.assertEquals(0, countInversions(array));
    }

    @Test
    public void testLeftSide() {
        int[] array = {2,1,3,4};
        Assert.assertEquals(1, countInversions(array));
    }

    @Test
    public void testRightSide() {
        int[] array = {1,2,4,3};
        Assert.assertEquals(1, countInversions(array));
    }

    @Test
    public void testSplit() {
        int[] array = {1,3,2,4};
        Assert.assertEquals(1, countInversions(array));
    }

    @Test
    public void testNonTrivial() {
        int[] array = {3,2,1,4};
        Assert.assertEquals(3, countInversions(array));
    }

    @Test
    public void testWorstCase() {
        int[] array = {6,5,4,3,2,1};
        Assert.assertEquals(15, countInversions(array));
    }

    @Test
    public void testLargeArray() throws IOException {
        int[] array = fillArray("gistfile1.txt");
        Assert.assertEquals(100000, array.length);
        Assert.assertEquals(2507223936L, countInversions(array));
    }

    @Test
    public void testAssignment() throws IOException {
        int[] array = fillArray("assignmentInversions.txt");
        Assert.assertEquals(100000, array.length);
        long inversions = countInversions(array);
        System.out.println("Answer is: " + inversions);
        Assert.assertEquals(2407905288L, inversions);
    }

    private int[] fillArray(String name) throws IOException {
        InputStream is = InversionsTest.class.getClassLoader().getResourceAsStream(name);
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

    private long countInversions(int[] x) {
        return CountingInversions.countInversionsAndSort(x);
    }

    int[] toIntArray(List<Integer> list)  {
        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer e : list)
            ret[i++] = e.intValue();
        return ret;
    }
}

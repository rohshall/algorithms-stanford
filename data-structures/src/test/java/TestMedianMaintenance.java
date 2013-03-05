import junit.framework.Assert;
import org.junit.Test;
import org.mcvly.algo.data.structures.heaps.MedianMaintenance;

import java.io.IOException;
import java.util.List;

/**
 * User: RMalyona
 * Date: 05.03.13
 */
public class TestMedianMaintenance {
    @Test
    public void testManual() {
        int[] x = {9,6,14,19,8,4};
        MedianMaintenance algorithm = new MedianMaintenance();

        long result = 0;
        for (int i : x) {
            result += algorithm.median(i);
        }
        Assert.assertEquals(50, result);
    }

    @Test
    public void testSimple1() {
        int[] x = {1,2,3,4,5,6,7,8,9,10};
        MedianMaintenance algorithm = new MedianMaintenance();

        long result = 0;
        for (int i : x) {
            result += algorithm.median(i);
        }
        Assert.assertEquals(30, result);
    }

    @Test
    public void testSimple2() {
        int[] x = {9,8,7,6,5,4,3,2,1};
        MedianMaintenance algorithm = new MedianMaintenance();

        long result = 0;
        for (int i : x) {
            result += algorithm.median(i);
        }
        Assert.assertEquals(61, result);
    }

    @Test
    public void testAssignment() throws IOException {
        String fileName = TestMedianMaintenance.class.getClassLoader().getResource("Median.txt").getFile();
        List<Integer> list = Test2Sum.readInputFile(fileName);
        MedianMaintenance algorithm = new MedianMaintenance();

        long result = 0;
        for (int i : list) {
            result += algorithm.median(i);
        }
        System.out.println(result);
    }
}

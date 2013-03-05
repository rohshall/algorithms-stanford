import junit.framework.Assert;
import org.junit.Test;
import org.mcvly.algo.data.structures.hashtables.TwoSumAlgorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: RMalyona
 * Date: 05.03.13
 */
public class Test2Sum {
    @Test
    public void testSimple() {
        Integer[] x = {0,1,2,3,4,5,6,7,8,9,10};
        TwoSumAlgorithm algorithm = new TwoSumAlgorithm();
        System.out.println(algorithm.sumNumber(Arrays.asList(x),6));
    }

    @Test
    public void test100() throws IOException {
        String fileName = Test2Sum.class.getClassLoader().getResource("100.txt").getFile();
        List<Integer> list = readInputFile(fileName);

        TwoSumAlgorithm algorithm = new TwoSumAlgorithm();
        int allSum = 0;
        for (int t=30; t<=60; t++) {
            if (algorithm.sumNumber(list,t)) {
                allSum++;
            }
        }

        Assert.assertEquals(9, allSum);
    }

    @Test
    public void testAssignment() throws IOException {
        String fileName = Test2Sum.class.getClassLoader().getResource("HashInt.txt").getFile();
        List<Integer> list = readInputFile(fileName);
        TwoSumAlgorithm algorithm = new TwoSumAlgorithm();
        int allSum = 0;
        for (int t=2500; t<=4000; t++) {
            if (algorithm.sumNumber(list,t)) {
                //System.out.println(t);
                allSum++;
            }
        }
        System.out.println(allSum);
    }

    public static List<Integer> readInputFile(String name) throws IOException {
        List<Integer> list = new ArrayList<Integer>();
        InputStream is = new FileInputStream(name);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        String s = in.readLine();

        while(s != null) {
            list.add(Integer.valueOf(s));
            s = in.readLine();
        }

        in.close();
        is.close();
        return list;
    }
}

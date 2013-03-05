package org.mcvly.algo.data.structures.hashtables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: RMalyona
 * Date: 05.03.13
 */
public class TwoSumAlgorithm {
    private Set<Integer> numbers;
    /**
     * computes whether exists x,y pair such that x+y=t (x,y є array)
     * @param list source array
     * @param t target sum
     * @return true when there is such pair (at least one)
     */
    public boolean sumNumber(List<Integer> list, int t) {
        if (numbers == null) {
            fillHashTable(list);
        }
        for (int x : numbers) {
            if (x*2 == t) { // only distinct x,y
                continue;
            }
            if (numbers.contains(new Integer(t-x))) {
                //System.out.println(x + " " + (t-x));
                return true;
            }
        }
        return false;
    }

    private void fillHashTable(List<Integer> array) {
        numbers = new HashSet<Integer>();
        for (int n : array) {
            numbers.add(n);
        }
    }
}

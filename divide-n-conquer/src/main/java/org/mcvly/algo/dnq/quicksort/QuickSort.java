package org.mcvly.algo.dnq.quicksort;

import java.util.Arrays;

/**
 * User: RMalyona
 * Date: 05.02.13
 */
public class QuickSort {

    private int[] array;
    private long comparisons = 0;

    public void sort(int[] array) {
        // Check for empty or null array
        if (array == null || array.length==0){
            return;
        }

        this.array = array;
        int n = array.length;

        quickSort(0, n-1);
    }

    public long getComparisons() {
        return comparisons;
    }

    /**
     * sorts given slice of array
     * @param low lower bound inclusive
     * @param high higher bound, inclusive
     */
    private void quickSort(int low, int high) {
        comparisons += (high-low);
        if (low > high) {
            throw new RuntimeException("Error in algorithm");
        } else if (low == high) {
            return;
        }

        //get pivot index and place it the lowest position
        int pInd = getPivotIndex(low, high);
        swap(low, pInd);

        //rearrange array and put p on it's "rightful" place
        pInd = partitionAroundPivot(low, high);
        // check whether it makes sense a recursive call (i.e. sort at least 2 element)
        if (pInd-1-low > 0) {
            quickSort(low, pInd-1);
        }
        if (high-(pInd+1) > 0) {
            quickSort(pInd+1, high);
        }

    }

    private int getPivotIndex(int low, int high) {
        //use "median-of-three" pivot rule
        int medianIndex = (low + high)/2;

        int medianValue = medianOfThree(array[low], array[medianIndex], array[high]);
        if (array[low] == medianValue) {
            return low;
        } else if (array[medianIndex] == medianValue) {
            return medianIndex;
        } else {
            return high;
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * places pivot in its "rightful position",
     * also rearranges array that all elements to the left of p are less and from the right are bigger than p
     * O(n) time
     * @return position of p in resulting array
     */
    private int partitionAroundPivot(int low, int high) {
        int p = array[low];
        // defines index of the first element greater than p in resulting array
        int i = low+1;
        for (int j=low+1; j<=high; j++) {
            if (array[j] < p) {
                // put element to the left of p and increase counter i
                swap(i,j);
                i++;
            }
        }
        // move pivot to the "right" position
        swap(low, i-1);
        return i-1;
    }

    private int medianOfThree(int a, int b, int c) {
        int[] array = new int[] {a,b,c};
        Arrays.sort(array);
        return array[1];
    }
}

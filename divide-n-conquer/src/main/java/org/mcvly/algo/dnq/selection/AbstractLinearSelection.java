package org.mcvly.algo.dnq.selection;

/**
 * User: Ruslan
 * Date: 12.02.13
 * Time: 19:58
 */
public abstract class AbstractLinearSelection {

    protected int[] array;

    public int getOrderedStatistic(int[] array, int i) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("empty array");
        } else if (array.length == 1) {
            if (i != 1) {
                throw new IllegalArgumentException("wrong ordered statistic for given array");
            } else {
                return array[0];
            }
        } else if (i > array.length-1) {
            throw new IllegalArgumentException("wrong ordered statistic for given array");
        }

        this.array = array;

        return select(0, array.length-1, i-1);
    }

    protected abstract int getPivotIndex(int low, int high);

    protected int select(int low, int high, int i) {
        if (high < low) {
            throw new RuntimeException("Error in algorithm");
        } else if (high == low) {
            if (i != low) {
                throw new RuntimeException("Error in algorithm");
            } else {
                return array[low];
            }
        }

        int pivotIndex = getPivotIndex(low, high);
        swap(low, pivotIndex);
        // puts pivot in its rightful position
        int j = partitionAroundPivot(low, high);
        // if we guessed the "right" pivot element
        if (j == i) {
            return array[i];
        }
        if (j > i) {
            return select(low, j-1, i);
        } else {
            return select(j+1, high, i);
        }
    }

    protected void swap(int i, int j) {
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
    protected int partitionAroundPivot(int low, int high) {
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
}

package org.mcvly.algo.dynamic.knapsack;

/**
 * @author: mcvly
 * @since: 9/27/13
 */
public class KnapsackItem {

    private int value;
    private int size;

    public KnapsackItem(int value, int size) {
        this.value = value;
        this.size = size;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnapsackItem that = (KnapsackItem) o;

        if (size != that.size) return false;
        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (value ^ (value >>> 32));
        result = 31 * result + (int) (size ^ (size >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "KnapsackItem{" +
                "value=" + value +
                ", size=" + size +
                '}';
    }
}

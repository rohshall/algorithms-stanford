package org.mcvly.algo.dynamic.tsp;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 11.10.13
 */
public class City {

    private int n;
    private double x,y;

    public City(int n, double x, double y) {
        this.n = n;
        this.x = x;
        this.y = y;
    }

    public int getN() {
        return n;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

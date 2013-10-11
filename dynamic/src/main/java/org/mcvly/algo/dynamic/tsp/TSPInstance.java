package org.mcvly.algo.dynamic.tsp;

import java.util.List;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 11.10.13
 */
public class TSPInstance {

    private City[] cities;
    private int n;

    public TSPInstance(int n) {
        this.cities = new City[n];
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setCity(int i, double x, double y) {
        cities[i] = new City(i, x, y);
    }

    public City getCity(int i) {
        return cities[i];
    }

    public double getDistanceBetweenTwoCities(int i, int j) {
        City x = getCity(i);
        City y = getCity(j);

        return Math.sqrt(Math.pow((x.getX() - y.getX()), 2) + Math.pow((x.getY() - y.getY()), 2));
    }
}

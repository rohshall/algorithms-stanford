package org.mcvly.algo.dnq.closest.pair;

import static org.mcvly.algo.dnq.closest.pair.PointUtils.euclideanDistance;

/**
 * User: Ruslan
 * Date: 03.02.13
 * Time: 22:11
 */
public class PointPair {
    private Point first;
    private Point second;
    private double distance;

    public PointPair(Point first, Point second) {
        this.first = first;
        this.second = second;
        distance = euclideanDistance(first, second);
    }

    public PointPair(Point first, Point second, double distance) {
        this.first = first;
        this.second = second;
        this.distance = distance;
    }

    public void update(Point point1, Point point2, double distance)
    {
        this.first = point1;
        this.second = point2;
        this.distance = distance;
    }

    public Point getFirst() {
        return first;
    }

    public void setFirst(Point first) {
        this.first = first;
    }

    public Point getSecond() {
        return second;
    }

    public void setSecond(Point second) {
        this.second = second;
    }

    public double getDistance() {
        return distance;
    }

    public String toString()  {
        return first + "-" + second + " : " + distance;
    }
}

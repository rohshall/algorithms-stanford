package org.mcvly.algo.dnq.closest.pair;

/**
 * User: Ruslan
 * Date: 03.02.13
 * Time: 22:11
 */
public class PointPair {
    private Point first;
    private Point second;

    public PointPair(Point first, Point second) {
        this.first = first;
        this.second = second;
    }

    public int hashCode() {
        int hashFirst = first != null ? first.hashCode() : 0;
        int hashSecond = second != null ? second.hashCode() : 0;

        return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
        PointPair otherPair = (PointPair) other;
        return
                ((this.first == otherPair.first ||
                        (this.first != null && otherPair.first != null &&
                                this.first.equals(otherPair.first))) &&
                        (this.second == otherPair.second ||
                                (this.second != null && otherPair.second != null &&
                                        this.second.equals(otherPair.second))));

    }

    public String toString() {
        return "(" + first + ", " + second + ")";
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
}

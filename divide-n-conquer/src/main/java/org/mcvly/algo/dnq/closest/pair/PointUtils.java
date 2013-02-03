package org.mcvly.algo.dnq.closest.pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: Ruslan
 * Date: 03.02.13
 * Time: 21:27
 */
public abstract class PointUtils {
    public static double euclidianDistacne(Point p, Point q) {
        return Math.sqrt(Math.pow(p.getX()-q.getX(),2) + Math.pow(p.getY()-q.getY(),2));
    }

    public static List<Point> sortByX(List<Point> points) {
        List<Point> sortedPoints = cloneList(points);
        Collections.sort(sortedPoints, new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                if (o1.getX() < o2.getX()) {
                    return -1;
                } else if (o1.getX() > o2.getX()) {
                    return +1;
                } else { // assume no points with x1=x2 and y1!=y2
                    return 0;
                }
            }
        });
        return sortedPoints;
    }

    public static List<Point> sortByY(List<Point> points) {
        List<Point> sortedPoints = cloneList(points);
        Collections.sort(points, new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                if (o1.getY() < o2.getY()) {
                    return -1;
                } else if (o1.getY() > o2.getY()) {
                    return +1;
                } else { // assume no points with y1=y2 and x1!=x2
                    return 0;
                }
            }
        });
        return sortedPoints;
    }

    public static List<Point> cloneList(List<Point> originalList) {
        List<Point> clonedList = new ArrayList<Point>(originalList.size());
        for (Point p : originalList) {
            clonedList.add(new Point(p));
        }
        return clonedList;
    }

    public static List<Point> getFirstHalfOfList(List<Point> originalList) {
        int halfList = originalList.size()/2;
        return originalList.subList(0, halfList);
    }

    public static List<Point> getSecondHalfOfList(List<Point> originalList) {
        int halfList = originalList.size()/2;
        return originalList.subList(halfList, originalList.size());
    }

    public static PointPair leastDistance(PointPair p1, PointPair p2, PointPair p3) {
        double d1, d2, d3;
        d1 = euclidianDistacne(p1.getFirst(), p2.getSecond());
        d2 = euclidianDistacne(p2.getFirst(), p2.getSecond());
        d3 = euclidianDistacne(p3.getFirst(), p3.getSecond());

        if (d1 < d2 && d1 < d3) {
            return p1;
        } else if (d2 < d1 && d2 < d3) {
            return p2;
        } else {
            return p3;
        }
    }
}

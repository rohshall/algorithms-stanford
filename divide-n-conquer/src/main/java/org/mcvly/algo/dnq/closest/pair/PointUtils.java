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
    public static double euclideanDistance(Point p, Point q) {
        if (p == null || q == null) {
            throw new IllegalArgumentException("P can't be null");
        }
        return Math.sqrt(Math.pow(p.getX()-q.getX(),2) + Math.pow(p.getY()-q.getY(),2));
    }

    public static double euclideanDistance(PointPair pointPair) {
        if (pointPair == null || pointPair.getFirst() == null || pointPair.getSecond() == null) {
            throw new IllegalArgumentException("Points can't be null");
        }
        return euclideanDistance(pointPair.getFirst(), pointPair.getSecond());
    }


    /**
     *
     * @param points list to be sorted
     * @return sorted copy of list
     */
    public static List<? extends Point> sortByX(List<? extends Point> points) {
        List<Point> cloneList = new ArrayList<Point>(points);
        Collections.sort(cloneList, new Comparator<Point>() {

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

        return cloneList;
    }

    /**
     *
     * @param points list to be sorted
     * @return sorted copy of list
     */
    public static List<? extends Point> sortByY(List<? extends Point> points) {
        List<Point> cloneList = new ArrayList<Point>(points);
        Collections.sort(cloneList, new Comparator<Point>() {

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
        return cloneList;
    }

    public static List<? extends Point> getFirstHalfOfList(List<? extends Point> originalList) {
        int halfList = originalList.size()/2;
        return originalList.subList(0, halfList);
    }

    public static List<? extends Point> getSecondHalfOfList(List<? extends Point> originalList) {
        int halfList = originalList.size()/2;
        return originalList.subList(halfList, originalList.size());
    }

    public static PointPair leastDistance(PointPair p1, PointPair p2, PointPair p3) {
        double d1, d2, d3;

        d1 = euclideanDistance(p1);
        d2 = euclideanDistance(p2);

        if (p3 == null) {
            d3 = Double.MAX_VALUE;
        } else {
            d3 = euclideanDistance(p3);
        }

        if (d1 < d2 && d1 < d3) {
            return p1;
        } else if (d2 < d1 && d2 < d3) {
            return p2;
        } else {
            return p3;
        }
    }

    public static List<? extends Point> getPointsWithXWithinDelta(List<? extends Point> points, double xBar, double delta) {
        List<Point> result = new ArrayList<Point>();
        for (Point p : points) {
            if (Math.abs(xBar - p.getX()) < delta) {
                result.add(p);
            }
        }

        return result;
    }

    public static PointPair bruteForce(List<? extends Point> points) {
        int numPoints = points.size();
        if (numPoints < 2)
            return null;
        PointPair pair = new PointPair(points.get(0), points.get(1));
        if (numPoints > 2)
        {
            for (int i = 0; i < numPoints - 1; i++)
            {
                Point point1 = points.get(i);
                for (int j = i + 1; j < numPoints; j++)
                {
                    Point point2 = points.get(j);
                    double distance = euclideanDistance(point1, point2);
                    if (distance < euclideanDistance(pair))
                        pair = new PointPair(point1, point2);
                }
            }
        }
        return pair;
    }
}

package org.mcvly.algo.dnq.closest.pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mcvly.algo.dnq.closest.pair.PointUtils.*;
/**
 * User: Ruslan
 * Date: 03.02.13
 * Time: 21:26
 */
public class Algorithm {
    public static void main(String[] args) {
        int numPoints = (args.length == 0) ? 20000 : Integer.parseInt(args[0]);
        List<Point> points = new ArrayList<Point>();
        Random r = new Random();
        for (int i = 0; i < numPoints; i++)
            points.add(new Point(r.nextDouble(), r.nextDouble()));
        System.out.println("Generated " + numPoints + " random points");
        long startTime = System.currentTimeMillis();
        PointPair bruteForceClosestPair = bruteForce(points);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Brute force (" + elapsedTime + " ms): " + bruteForceClosestPair);
        startTime = System.currentTimeMillis();
        PointPair dqClosestPair = divideAndConquer(points);
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Divide and conquer (" + elapsedTime + " ms): " + dqClosestPair);
        if (euclideanDistance(bruteForceClosestPair) != euclideanDistance(dqClosestPair))
            System.out.println("MISMATCH");
    }


    public static PointPair divideAndConquer(List<? extends Point> points) {
        List<? extends Point> pointsSortedByX = sortByX(points);
        List<? extends Point> pointsSortedByY = sortByY(points);
        return closestPair(pointsSortedByX, pointsSortedByY);
    }

    public static PointPair closestPair(List<? extends Point> px, List<? extends Point> py) {

        if (px.size() <= 3)
            return bruteForce(px);

        List<? extends Point> qx = getFirstHalfOfList(px);
        List<? extends Point> rx = getSecondHalfOfList(px);

        PointPair p1 = closestPair(qx, sortByY(qx));
        PointPair p2 = closestPair(rx, sortByY(rx));

        double delta = Math.min(p1.getDistance(), p2.getDistance());

        PointPair p3 = closestSplitPair(px, py, delta);

        return leastDistance(p1, p2, p3);
    }

    private static PointPair closestSplitPair(List<? extends Point> px, List<? extends Point> py, double delta) {

        double xBar = px.get(px.size()-1).getX();
        List<? extends Point> sy = getPointsWithXWithinDelta(py, xBar, delta);

        if (sy.size() == 0) {
            return null;
        }

        double best = delta;
        PointPair bestPair = null;

        for (int i=0; i<sy.size()-1; i++) {
            Point p1 = sy.get(i);
            for (int j=i+1; j<sy.size(); j++) {
                Point p2 = sy.get(j);
                if (p2.getY() - p1.getY() >= delta) {
                    break;
                }

                PointPair pair = new PointPair(p1,p2);
                if (euclideanDistance(pair) < best) {
                    bestPair = pair;
                    best = euclideanDistance(pair);
                }
            }
        }

        return bestPair;
    }
}

package org.mcvly.algo.dnq.closest.pair;

import java.util.List;

import static org.mcvly.algo.dnq.closest.pair.PointUtils.*;
/**
 * User: Ruslan
 * Date: 03.02.13
 * Time: 21:26
 */
public class Algorithm {
    public static void main(String[] args) {

    }

    public PointPair closestPair(List<Point> px, List<Point> py) {

        if (px.size() == 2) {
            return new PointPair(px.get(0), px.get(1));
        } else if (px.size() == 3) {
            return leastDistance(new PointPair(px.get(0), px.get(1)), new PointPair(px.get(0), px.get(2)),
                    new PointPair(px.get(1), px.get(2)));
        }

        List<Point> qx = getFirstHalfOfList(px);
        List<Point> rx = getSecondHalfOfList(px);
        List<Point> qy = getFirstHalfOfList(py);
        List<Point> ry = getSecondHalfOfList(py);

        PointPair p1 = closestPair(qx, qy);
        PointPair p2 = closestPair(rx, ry);

        double delta = Math.min(euclidianDistacne(p1.getFirst(), p1.getSecond()),
                euclidianDistacne(p2.getFirst(), p2.getSecond()));

        PointPair p3 = closestSplitPair(px, py, delta);

        return leastDistance(p1, p2, p3);
    }

    private PointPair closestSplitPair(List<Point> px, List<Point> py, double delta) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}

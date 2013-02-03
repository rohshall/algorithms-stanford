package org.mcvly.algo.dnq.closest.pair;

import java.io.IOException;
import java.util.List;

/**
 * User: Ruslan
 * Date: 03.02.13
 * Time: 21:18
 */
public interface PointsReader {
    List<Point> read(String resourceName) throws IOException;
}

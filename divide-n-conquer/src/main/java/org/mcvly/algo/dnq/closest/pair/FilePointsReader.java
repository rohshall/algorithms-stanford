package org.mcvly.algo.dnq.closest.pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Ruslan
 * Date: 03.02.13
 * Time: 21:18
 */
public class FilePointsReader implements PointsReader {
    @Override
    public List<Point> read(String fileName) throws IOException {
        List<Point> points = new ArrayList<Point>();
        InputStream is = null;
        BufferedReader in = null;
        try {
            is = FilePointsReader.class.getClassLoader().getResourceAsStream(fileName);
            List<Integer> list = new LinkedList<Integer>();

            in = new BufferedReader(new InputStreamReader(is));

            String s = in.readLine();

            while(s != null) {
                String[] stringPoints = s.split(" ");
                if (stringPoints.length != 2) {
                    throw new IOException("wrong points format");
                }
                try {
                    double x = Double.parseDouble(stringPoints[0]);
                    double y = Double.parseDouble(stringPoints[1]);
                    points.add(new Point(x,y));
                } catch (NumberFormatException e) {
                    throw new IOException("unable to parse double");
                }
                s = in.readLine();
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (is != null) {
                is.close();
            }
        }

        return points;
    }
}

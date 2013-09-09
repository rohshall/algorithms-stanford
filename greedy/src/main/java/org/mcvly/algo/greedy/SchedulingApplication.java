package org.mcvly.algo.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 09.09.13
 */
public class SchedulingApplication {

    public static class Application {
        private int weight;
        private int time;

        public Application(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    public static List<Application> readData(String fileName) {
        List<Application> list = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(SchedulingApplication.class.getClassLoader().getResourceAsStream(fileName)))) {
            String s = r.readLine();
            try {
                int count = Integer.parseInt(s);

                for (int i = 0; i < count; i++) {
                    s = r.readLine();
                    String[] stringPoints = s.split(" ");
                    if (stringPoints.length != 2) {
                        throw new IOException("wrong points format");
                    }
                    int w = Integer.parseInt(stringPoints[0]);
                    int t = Integer.parseInt(stringPoints[1]);
                    list.add(new Application(w, t));
                }
            } catch (NumberFormatException e) {
                throw new IOException("unable to parse data");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return list;
    }

    public static void main(String[] args) {
        List<Application> data = readData("test.txt");
        System.out.println(data.size());
    }
}

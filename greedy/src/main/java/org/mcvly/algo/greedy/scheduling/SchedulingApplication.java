package org.mcvly.algo.greedy.scheduling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 09.09.13
 */
public class SchedulingApplication {

    public static class Application implements Comparable<Application> {
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

        @Override
        public int compareTo(Application o) {
//            double dif = 1.0 * weight / time - 1.0 * o.weight / o.time;
//            return (int) Math.signum(dif);
            int n1 = weight - time;
            int n2 = o.weight - o.time;
            if (n1 == n2) {
                return weight - o.weight;
            }
            return n1 - n2;
        }

        @Override
        public String toString() {
            return weight + " " + time;
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
            e.printStackTrace();
        }

        return list;
    }

    public static long weightedSum(List<Application> list) {
        long res = 0;
        long currentCompletionSum = 0;
        for (Application ap : list) {
            currentCompletionSum += ap.getTime();
            res += ap.getWeight() * currentCompletionSum;
        }

        return res;
    }

    public static void main(String[] args) {
        List<Application> data = readData("jobs.txt");
        System.out.println(data);
        Collections.sort(data, Collections.reverseOrder());
        System.out.println(data);
        System.out.println(weightedSum(data));
    }
}

package org.mcvly.algo.dynamic.tsp;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 11.10.13
 */
public class TSPReader {

    public static TSPInstance readFromFile(String fileName) {
        try (Scanner scanner = new Scanner(TSPReader.class.getClassLoader().getResourceAsStream(fileName))) {

            scanner.useLocale(Locale.ENGLISH);

            int nodesCount = scanner.nextInt();

            TSPInstance tsp = new TSPInstance(nodesCount);

            int i = 0;

            while (scanner.hasNextLine()) {

                double v1 = scanner.nextDouble();
                double v2 = scanner.nextDouble();

                tsp.setCity(i++, v1, v2);
            }

            return tsp;
        }
    }

}

package org.mcvly.algo.dynamic.csp;

import java.util.Locale;
import java.util.Scanner;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 17.10.13
 */
public class TwoSatReader {

    public static TwoSatInstance readFromFile(String fileName) {
        try (Scanner scanner = new Scanner(TwoSatReader.class.getClassLoader().getResourceAsStream(fileName))) {

            scanner.useLocale(Locale.ENGLISH);

            int nodesCount = scanner.nextInt();

            TwoSatInstance tsp = new TwoSatInstance(nodesCount);

            int i = 0;

            while (scanner.hasNextLine()) {

                int v1 = scanner.nextInt();
                int v2 = scanner.nextInt();

                tsp.setClause(i++, v1, v2);
            }

            return tsp;
        }
    }

}

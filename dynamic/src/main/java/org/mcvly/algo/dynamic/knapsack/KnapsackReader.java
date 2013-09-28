package org.mcvly.algo.dynamic.knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: mcvly
 * @since: 9/27/13
 */
public class KnapsackReader {

    private int capacity;

    public List<KnapsackItem> readItems(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        String s = r.readLine();
        capacity = Integer.valueOf(s.split(" ")[0]);
        int number = Integer.valueOf(s.split(" ")[1]);
        List<KnapsackItem> items = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {
            s = r.readLine();
            String[] stringItem = s.split(" ");
            int value = Integer.parseInt(stringItem[0]);
            int size = Integer.parseInt(stringItem[1]);

            KnapsackItem item = new KnapsackItem(value, size);
            items.add(item);
        }

        return items;
    }

    public int getCapacity() {
        return capacity;
    }

}

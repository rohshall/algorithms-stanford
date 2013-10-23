package org.mcvly.algo.dynamic.lcs;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @author: mcvly
 * @since: 10/23/13
 */
public class LongestCommonSubstringTest {

    @Test
    public void testSimpleString() {
        String a = "abcdefghi";
        String b = "gdefe";

        assertEquals("def", LongestCommonSubstring.longestCommonSubstring(a, b));
    }

    @Test
    public void testNonTrivialString() {
        String a = "xxxabcdefffgghhijkl";
        String b = "abcfffjiilkjnmmoabcdezzz";

        assertEquals("abcde", LongestCommonSubstring.longestCommonSubstring(a, b));
    }

    @Test
    public void testSimpleList() {
        List<Integer> listA = Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> listB = Arrays.asList(7,1,6,2,3,4,5);

        assertEquals(Arrays.asList(2,3,4,5), LongestCommonSubstring.longestCommonSublist(listA, listB));
    }

    @Test
    @Ignore("sadly but it takes about 5 min to complete")
    public void testTexts() throws IOException {
        String alice = readFile("dynamic/src/test/resources/lcs/alice.txt");
        String prince = readFile("dynamic/src/test/resources/lcs/prince.txt");

        System.out.println(LongestCommonSubstring.longestCommonSubstring(alice, prince).length());
    }

    private static String readFile(String pathname) throws IOException {

        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }

}

package org.ea.aoc.day3;

import org.ea.aoc.Util;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3a {
    public static void main(String[] args) throws IOException  {
        List<String> lines = Util.readInput(false, 3, true);
        Pattern pattern = Pattern.compile(
            "(do\\(\\))|(don't\\(\\))|(mul\\((\\d+),(\\d+)\\))"
        );
        BigInteger result = BigInteger.ZERO;
        boolean enable = true;
        for (String line : lines) {
            Matcher m = pattern.matcher(line);

            while (m.find()) {
                if (m.group(1) != null) {
                    enable = true;
                }
                if (m.group(2) != null) {
                    enable = false;
                }
                if (m.group(3) != null && enable) {
                    int val1 = Integer.parseInt(m.group(4));
                    int val2 = Integer.parseInt(m.group(5));
                    result = result.add(BigInteger.valueOf(val1).multiply(BigInteger.valueOf(val2)));
                }
            }
        }
        System.out.println(result);
    }
}

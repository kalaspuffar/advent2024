package org.ea.aoc.day13;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13a {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 13, false);

        Pattern aButton = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)");
        Pattern bButton = Pattern.compile("Button B: X\\+(\\d+), Y\\+(\\d+)");
        Pattern price = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");

        /*
Button A: X+49, Y+60
Button B: X+46, Y+12
Prize: X=2817, Y=1632
         */

        int adx = 0;
        int ady = 0;
        int bdx = 0;
        int bdy = 0;
        int x = 0;
        int y = 0;

        BigInteger count = BigInteger.ZERO;
        for (String line : lines) {
            if (line.isBlank()) {
                count = count.add(calculate(x, y, adx, ady, bdx, bdy));
            }
            if (line.startsWith("Button A")) {
                Matcher m = aButton.matcher(line);
                if (m.find()) {
                    adx = Integer.parseInt(m.group(1));
                    ady = Integer.parseInt(m.group(2));
                }
            }
            if (line.startsWith("Button B")) {
                Matcher m = bButton.matcher(line);
                if (m.find()) {
                    bdx = Integer.parseInt(m.group(1));
                    bdy = Integer.parseInt(m.group(2));
                }
            }
            if (line.startsWith("Prize")) {
                Matcher m = price.matcher(line);
                if (m.find()) {
                    x = Integer.parseInt(m.group(1));
                    y = Integer.parseInt(m.group(2));
                }
            }
        }
        System.out.println(count);

    }

    private static BigInteger calculate(int x, int y, int adx, int ady, int bdx, int bdy) {
        int val = 0;
        for (int a = 0; a < 100; a++) {
            for (int b = 0; b < 100; b++) {
                int compareX = (a * adx) + (b * bdx);
                int compareY = (a * ady) + (b * bdy);
                if (x == compareX && y == compareY) {
                    int newVal = (a * 3) + b;
                    if (val == 0 || val > newVal) {
                        val = newVal;
                    }
                }
            }
        }
        return BigInteger.valueOf(val);
    }
}

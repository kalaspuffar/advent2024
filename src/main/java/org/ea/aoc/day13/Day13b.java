package org.ea.aoc.day13;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13b {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 13, false);

        Pattern aButton = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)");
        Pattern bButton = Pattern.compile("Button B: X\\+(\\d+), Y\\+(\\d+)");
        Pattern price = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");

        int adx = 0;
        int ady = 0;
        int bdx = 0;
        int bdy = 0;
        int x = 0;
        int y = 0;

        BigInteger count = BigInteger.ZERO;
        for (String line : lines) {
            if (line.isBlank()) {
                Instant workStart = Instant.now();
                count = count.add(calculate(x, y, adx, ady, bdx, bdy));
                System.out.println(count);
                Duration time = Duration.between(workStart, Instant.now());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm.SSS");
                System.out.println("Time " + LocalTime.ofNanoOfDay(time.toNanos()).format(formatter));
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
        long xl = x + 10000000000000L;
        long yl = y + 10000000000000L;
        long val = 0;
        for (long a = 0; a < 10000000000000L; a++) {
            long acalx = a * adx;
            long acaly = a * ady;
            if (acalx > xl) break;
            if (acaly > yl) break;
            for (long b = 0; b < 10000000000000L; b++) {
                long bcalx = b * bdx;
                long bcaly = b * bdy;
                if (bcalx > xl) break;
                if (bcaly > yl) break;
                long compareX = acalx + bcalx;
                long compareY = acaly + bcaly;
                if (compareX > xl) break;
                if (compareY > yl) break;
                if (xl == compareX && yl == compareY) {
                    long newVal = (a * 3) + b;
                    if (val == 0 || val > newVal) {
                        val = newVal;
                    }
                }
            }
        }
        return BigInteger.valueOf(val);
    }
}

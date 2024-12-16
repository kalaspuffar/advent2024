package org.ea.aoc.day14;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14a {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 14, false);

        //p=0,4 v=3,-3

        //int roomX = 11;
        //int roomY = 7;
        int roomX = 101;
        int roomY = 103;
        int seconds = 100;

        Pattern robot = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");

        int quad1 = 0;
        int quad2 = 0;
        int quad3 = 0;
        int quad4 = 0;

        for (String line : lines) {
            Matcher m = robot.matcher(line);
            if (m.find()) {
                int px = Integer.parseInt(m.group(1));
                int py = Integer.parseInt(m.group(2));
                int vx = Integer.parseInt(m.group(3));
                int vy = Integer.parseInt(m.group(4));
                BigInteger rx = BigInteger.valueOf(px).add(
                    BigInteger.valueOf(seconds).multiply(BigInteger.valueOf(vx))
                );
                BigInteger ry = BigInteger.valueOf(py).add(
                    BigInteger.valueOf(seconds).multiply(BigInteger.valueOf(vy))
                );
                BigInteger mx = rx.mod(BigInteger.valueOf(roomX));
                BigInteger my = ry.mod(BigInteger.valueOf(roomY));

                int qxsize = (roomX - 1) / 2;
                int qysize = (roomY - 1) / 2;
                if (mx.intValue() < qxsize && my.intValue() < qysize) {
                    quad1++;
                } else if (mx.intValue() > qxsize && my.intValue() < qysize) {
                    quad2++;
                } else if (mx.intValue() < qxsize && my.intValue() > qysize) {
                    quad3++;
                } else if (mx.intValue() > qxsize && my.intValue() > qysize) {
                    quad4++;
                }
            }
        }

        System.out.println(quad1);
        System.out.println(quad2);
        System.out.println(quad3);
        System.out.println(quad4);

        System.out.println(quad1 * quad2 * quad3 * quad4);
    }
}

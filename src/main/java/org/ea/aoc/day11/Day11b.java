package org.ea.aoc.day11;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.time.Instant;
import java.util.*;

public class Day11b {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 11, false);

        String line = lines.get(0);
        String[] arr = line.split(" ");
        Deque<CalPoint> list = new ArrayDeque<>();
        for (String s : arr) {
            if (s.isBlank()) continue;
            list.add(new CalPoint(new BigInteger(s), 0));
        }

        BigInteger count = BigInteger.ZERO;
        while (!list.isEmpty()) {
            CalPoint c = list.pop();
            for (int i = c.getNum(); i < 75; i++) {
                CalPoint newC = c.evolve();
                if (newC != null) {
                    list.push(newC);
                }
            }
            count = count.add(BigInteger.ONE);
        }
        System.out.println(count);
    }
}

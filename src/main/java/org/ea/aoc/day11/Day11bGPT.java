package org.ea.aoc.day11;
import org.ea.aoc.Util;

import java.math.BigInteger;
import java.util.*;

public class Day11bGPT {
    public static void main(String[] args) throws Exception {
        // Input parsing
        List<String> lines = Util.readInput(false, 11, false);
        String line = lines.get(0);
        String[] arr = line.split(" ");

        // Map to track stones (value -> count)
        Map<BigInteger, BigInteger> stones = new HashMap<>();
        for (String s : arr) {
            if (s.isBlank()) continue;
            BigInteger stone = new BigInteger(s);
            stones.put(stone, stones.getOrDefault(stone, BigInteger.ZERO).add(BigInteger.ONE));
        }

        // Simulate 75 blinks
        for (int blink = 0; blink < 75; blink++) {
            Map<BigInteger, BigInteger> nextStones = new HashMap<>();
            for (Map.Entry<BigInteger, BigInteger> entry : stones.entrySet()) {
                BigInteger value = entry.getKey();
                BigInteger count = entry.getValue();

                if (value.equals(BigInteger.ZERO)) {
                    // Rule 1: 0 -> 1
                    nextStones.put(BigInteger.ONE, nextStones.getOrDefault(BigInteger.ONE, BigInteger.ZERO).add(count));
                } else if (value.toString().length() % 2 == 0) {
                    // Rule 2: Split into two stones
                    String strValue = value.toString();
                    int mid = strValue.length() / 2;
                    BigInteger left = new BigInteger(strValue.substring(0, mid));
                    BigInteger right = new BigInteger(strValue.substring(mid));
                    nextStones.put(left, nextStones.getOrDefault(left, BigInteger.ZERO).add(count));
                    nextStones.put(right, nextStones.getOrDefault(right, BigInteger.ZERO).add(count));
                } else {
                    // Rule 3: Multiply by 2024
                    BigInteger newValue = value.multiply(BigInteger.valueOf(2024));
                    nextStones.put(newValue, nextStones.getOrDefault(newValue, BigInteger.ZERO).add(count));
                }
            }
            stones = nextStones;
        }

        // Sum up all stone counts
        BigInteger totalStones = stones.values().stream().reduce(BigInteger.ZERO, BigInteger::add);
        System.out.println(totalStones);
    }
}
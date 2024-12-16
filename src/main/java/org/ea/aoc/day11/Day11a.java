package org.ea.aoc.day11;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day11a {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 11, false);

        String line = lines.get(0);
        String[] arr = line.split(" ");
        List<BigInteger> list = new ArrayList<>();
        for (String s : arr) {
            if (s.isBlank()) continue;
            list.add(new BigInteger(s));
        }

        for (int i = 0; i < 25; i++) {
            List<BigInteger> newList = new ArrayList<>();
            for (BigInteger val : list) {
                newList.addAll(evolve(val));
            }
            list = newList;
        }
        System.out.println(list.size());
    }

    private static Collection<? extends BigInteger> evolve(BigInteger val) {
        List<BigInteger> newList = new ArrayList<>();
        if (val.equals(BigInteger.ZERO)) {
            newList.add(BigInteger.ONE);
        } else if (val.toString().length() % 2 == 0) {
            int index = val.toString().length() / 2;
            newList.add(new BigInteger(val.toString().substring(0, index)));
            newList.add(new BigInteger(val.toString().substring(index)));
        } else {
            newList.add(val.multiply(BigInteger.valueOf(2024)));
        }
        return newList;
    }
}

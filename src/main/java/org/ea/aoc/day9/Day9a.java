package org.ea.aoc.day9;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day9a {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 9, false);
        List<Integer> disk = new ArrayList<>();
        boolean space = false;
        int id = 0;
        for (String character : lines.get(0).split("")) {
            int num = Integer.parseInt(character);
            if (space) {
                for (int i = 0; i < num; i++) disk.add(-1);
            } else {
                for (int i = 0; i < num; i++) disk.add(id);
                id++;
            }
            space = !space;
        }

        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == -1) {
                int val = -1;
                while (val == -1) {
                    val = disk.remove(disk.size() - 1);
                }
                if (disk.size() <= i) {
                    disk.add(val);
                    break;
                }
                disk.remove(i);
                disk.add(i, val);
            }
        }

        // 6288338133779 - Low
        // 6288599492129

        BigInteger count = BigInteger.ZERO;
        for (int i = 0; i < disk.size(); i++) {
            count = count.add(BigInteger.valueOf(i).multiply(BigInteger.valueOf(disk.get(i))));
        }

        System.out.println(count);
    }
}

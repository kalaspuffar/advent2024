package org.ea.aoc.day10;

import org.ea.aoc.Util;
import org.ea.aoc.day8.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10a {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 10, false);

        int height = lines.size();
        int width = lines.get(0).length();

        int[] map = new int[width * height];

        int i = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            for (String character : line.trim().split("")) {
                map[i] = Integer.parseInt(character);
                i++;
            }
        }

        long count = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Set<Point> set = countTrails(map, x, y, width, height,0);
                count += set.size();
            }
        }
        System.out.println(count);
    }

    private static Set<Point> countTrails(int[] map, int x, int y, int width, int height, int val) {
        if (x >= width) return new HashSet<>();
        if (y >= height) return new HashSet<>();
        if (x < 0) return new HashSet<>();
        if (y < 0) return new HashSet<>();

        if (map[y * width + x] != val) return new HashSet<>();
        if (val == 9) {
            Set<Point> newSet = new HashSet();
            newSet.add(new Point("9", x, y));
            return newSet;
        }

        Set<Point> newSet = new HashSet();
        newSet.addAll(countTrails(map, x + 1, y, width, height, val + 1));
        newSet.addAll(countTrails(map, x - 1, y, width, height, val + 1));
        newSet.addAll(countTrails(map, x, y + 1, width, height, val + 1));
        newSet.addAll(countTrails(map, x, y - 1, width, height, val + 1));

        return newSet;
    }
}

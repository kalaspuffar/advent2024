package org.ea.aoc.day10;

import org.ea.aoc.Util;

import java.util.*;

public class Day10b {
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
                Map<Point, Integer> res = countTrails(map, x, y, width, height,0);
                for (Map.Entry<Point, Integer> entry : res.entrySet()) {
                    count += entry.getValue();
                }
            }
        }
        System.out.println(count);
    }

    private static Map<Point, Integer> countTrails(int[] map, int x, int y, int width, int height, int val) {
        if (x >= width) return new HashMap<>();
        if (y >= height) return new HashMap<>();
        if (x < 0) return new HashMap<>();
        if (y < 0) return new HashMap<>();

        if (map[y * width + x] != val) return new HashMap<>();
        if (val == 9) {
            Map<Point, Integer> newSet = new HashMap();
            newSet.put(new Point(1, x, y), 1);
            return newSet;
        }

        Map<Point, Integer> newSet = new HashMap();
        checkDirection(map, x + 1, y, width, height, val, newSet);
        checkDirection(map, x - 1, y, width, height, val, newSet);
        checkDirection(map, x, y + 1, width, height, val, newSet);
        checkDirection(map, x, y - 1, width, height, val, newSet);

        return newSet;
    }

    private static void checkDirection(int[] map, int x, int y, int width, int height, int val, Map<Point, Integer> newSet) {
        Map<Point, Integer> res = countTrails(map, x, y, width, height, val + 1);
        for (Point p : res.keySet()) {
            if (newSet.containsKey(p)) {
                newSet.put(p, newSet.get(p) + res.get(p));
            } else {
                newSet.put(p, res.get(p));
            }
        }
    }
}

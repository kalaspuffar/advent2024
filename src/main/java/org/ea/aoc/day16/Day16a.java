package org.ea.aoc.day16;

import org.ea.aoc.Util;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day16a {
    private static final int NORTH = 1;
    private static final int SOUTH = 2;
    private static final int EAST = 3;
    private static final int WEST = 4;
    private static int width;
    private static int height;

    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 16, false);

        width = lines.get(0).length();
        height = lines.size();
        int startX = 0;
        int startY = 0;

        int[] map = new int[width * height];

        int y = 0;
        for (String line : lines) {
            int x = 0;
            for (String character : line.split("")) {
                switch (character) {
                    case "#" -> map[y * width + x] = -1;
                    case "S" -> {
                        startX = x;
                        startY = y;
                    }
                    case "E" -> map[y * width + x] = -2;
                    case "." -> map[y * width + x] = Integer.MAX_VALUE;
                }
                x++;
            }
            y++;
        }

        Set<Point> visited = new HashSet<>();
        long count = moveInMap(map, visited, startX, startY, width, EAST, 0);
        System.out.println(count);
        System.out.println(visited.size());
        //drawMap(visited, map);
    }

    private static long moveInMap(int[] map, Set<Point> visited, int x, int y, int width, int direction, int points) {
        if (map[y * width + x] == -1) {
            return Integer.MAX_VALUE;
        }
        if (map[y * width + x] == -2) {
            visited.add(new Point(x, y));
            return points;
        }
        if (map[y * width + x] < points && map[y * width + x] + 1000 != points) {
            //None - 493
            //North - 501
            //South - 493
            //East - 512
            //West - 493
            return Integer.MAX_VALUE - 1;
        }
        map[y * width + x] = points;
        visited.add(new Point(x, y));
        switch (direction) {
            case NORTH -> {
                Set<Point> visited1 = new HashSet<>();
                Set<Point> visited2 = new HashSet<>();
                Set<Point> visited3 = new HashSet<>();
                long val1 = moveInMap(map, visited1, x, y - 1, width, NORTH, points + 1);
                long val2 = moveInMap(map, visited2, x + 1, y, width, EAST, points + 1001);
                long val3 = moveInMap(map, visited3, x - 1, y, width, WEST, points + 1001);
                long minimalValue = Math.min(val1, Math.min(val2, val3));
                if (minimalValue == val1) {
                    visited.addAll(visited1);
                }
                if (minimalValue == val2) {
                    visited.addAll(visited2);
                }
                if (minimalValue == val3) {
                    visited.addAll(visited3);
                }
                return minimalValue;
            }
            case SOUTH -> {
                Set<Point> visited1 = new HashSet<>();
                Set<Point> visited2 = new HashSet<>();
                Set<Point> visited3 = new HashSet<>();
                long val1 = moveInMap(map, visited1, x, y + 1, width, SOUTH, points + 1);
                long val2 = moveInMap(map, visited2, x + 1, y, width, EAST, points + 1001);
                long val3 = moveInMap(map, visited3, x - 1, y, width, WEST, points + 1001);
                long minimalValue = Math.min(val1, Math.min(val2, val3));
                if (minimalValue == val1) {
                    visited.addAll(visited1);
                }
                if (minimalValue == val2) {
                    visited.addAll(visited2);
                }
                if (minimalValue == val3) {
                    visited.addAll(visited3);
                }
                return minimalValue;
            }
            case EAST -> {
                Set<Point> visited1 = new HashSet<>();
                Set<Point> visited2 = new HashSet<>();
                Set<Point> visited3 = new HashSet<>();
                long val1 = moveInMap(map, visited1, x + 1, y, width, EAST, points + 1);
                long val2 = moveInMap(map, visited2, x, y - 1, width, NORTH, points + 1001);
                long val3 = moveInMap(map, visited3, x, y + 1, width, SOUTH, points + 1001);
                long minimalValue = Math.min(val1, Math.min(val2, val3));
                if (minimalValue == val1) {
                    visited.addAll(visited1);
                }
                if (minimalValue == val2) {
                    visited.addAll(visited2);
                }
                if (minimalValue == val3) {
                    visited.addAll(visited3);
                }
                return minimalValue;
            }
            case WEST -> {
                Set<Point> visited1 = new HashSet<>();
                Set<Point> visited2 = new HashSet<>();
                Set<Point> visited3 = new HashSet<>();
                long val1 = moveInMap(map, visited1, x - 1, y, width, WEST, points + 1);
                long val2 = moveInMap(map, visited2, x, y - 1, width, NORTH, points + 1001);
                long val3 = moveInMap(map, visited3, x, y + 1, width, SOUTH, points + 1001);
                long minimalValue = Math.min(val1, Math.min(val2, val3));
                if (minimalValue == val1) {
                    visited.addAll(visited1);
                }
                if (minimalValue == val2) {
                    visited.addAll(visited2);
                }
                if (minimalValue == val3) {
                    visited.addAll(visited3);
                }
                return minimalValue;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void drawMap(Set<Point> visited, int[] map) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String v = map[y * width + x] == -1 ? "#" : ".";
                v = visited.contains(new Point(x, y)) ? "O" : v;
                System.out.print(v);
            }
            System.out.println("");
        }
    }
}

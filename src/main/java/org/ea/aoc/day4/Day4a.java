package org.ea.aoc.day4;

import org.ea.aoc.Util;

import java.io.IOException;
import java.util.List;

public class Day4a {

    public static boolean checkAllDirections(int[][] grid, int x, int y, int dx, int dy, int val) {
        if (x < 0) return false;
        if (y < 0) return false;
        if (grid.length <= y) return false;
        if (grid[y].length <= x) return false;
        if (grid[y][x] != val) return false;
        if (val == 4) return true;

        if (checkAllDirections(grid, x + dx, y + dy, dx, dy, ++val)) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Util.readInput(false, 4, false);

        int[][] grid = new int[lines.size()][];
        int j = 0;
        int width = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            width = line.length();
            int[] gline = new int[width];

            int i = 0;
            for (String character : line.split("")) {
                switch (character) {
                    case "X":  gline[i] = 1; break;
                    case "M":  gline[i] = 2; break;
                    case "A":  gline[i] = 3; break;
                    case "S":  gline[i] = 4; break;
                }
                i++;
            }
            grid[j] = gline;
            j++;
        }

        int count = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < width; x++) {
                if (checkAllDirections(grid, x, y, 1, 0,1)) count++;
                if (checkAllDirections(grid, x, y, 1, -1,1)) count++;
                if (checkAllDirections(grid, x, y, 1, 1,1)) count++;

                if (checkAllDirections(grid, x, y, -1, 0,1)) count++;
                if (checkAllDirections(grid, x, y, -1, -1,1)) count++;
                if (checkAllDirections(grid, x, y, -1, 1,1)) count++;

                if (checkAllDirections(grid, x, y, 0, -1,1)) count++;
                if (checkAllDirections(grid, x, y, 0, 1,1)) count++;
            }
        }
        System.out.println(count);
    }
}

package org.ea.aoc.day4;

import org.ea.aoc.Util;

import java.io.IOException;
import java.util.List;

public class Day4b {

    public static boolean checkAllDirections(int[][] grid, int x, int y, int dx, int dy, int val) {
        if (x - 1 < 0) return false;
        if (y - 1 < 0) return false;
        if (grid.length <= y + 1) return false;
        if (grid[y].length <= x + 1) return false;
        if (grid[y][x] != val) return false;

        /**
         * M M
         *  A
         * S S
         */
        if (
            grid[y - 1][x - 1] == 2 && grid[y - 1][x + 1] == 2 &&
            grid[y + 1][x - 1] == 4 && grid[y + 1][x + 1] == 4
        ) return true;

        /**
         * S S
         *  A
         * M M
         */
        if (
            grid[y - 1][x - 1] == 4 && grid[y - 1][x + 1] == 4 &&
            grid[y + 1][x - 1] == 2 && grid[y + 1][x + 1] == 2
        ) return true;

        /**
         * M S
         *  A
         * N S
         */
        if (
            grid[y - 1][x - 1] == 2 && grid[y - 1][x + 1] == 4 &&
            grid[y + 1][x - 1] == 2 && grid[y + 1][x + 1] == 4
        ) return true;

        /**
         * S M
         *  A
         * S M
         */
        if (
            grid[y - 1][x - 1] == 4 && grid[y - 1][x + 1] == 2 &&
            grid[y + 1][x - 1] == 4 && grid[y + 1][x + 1] == 2
        ) return true;

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
                if (checkAllDirections(grid, x, y, 1, 0,3)) count++;
            }
        }
        System.out.println(count);
    }
}

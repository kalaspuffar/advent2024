package org.ea.aoc.day6;

import org.ea.aoc.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6a {

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    public static void main(String[] args) throws IOException {
        List<String> lines = Util.readInput(false, 6, false);

        int x = 0;
        int y = 0;
        int dir = NORTH;
        int height = lines.size();
        int width = lines.get(0).length();
        int[] map = new int[height * width];

        int cy = 0;
        for (String line : lines) {
            int cx = 0;
            for (String character : line.split("")) {
                if(character.equals(".")) {
                    map[cy * width + cx] = 0;
                }
                if(character.equals("#")) {
                    map[cy * width + cx] = 1;
                }
                if(character.equals("^")) {
                    map[cy * width + cx] = 0;
                    x = cx;
                    y = cy;
                }
                cx++;
            }
            cy++;
        }

        while (x >= 0 && y >= 0 && x < width && y < height) {
            map[y * width + x] = -1;
            boolean canWalk = checkDir(map, width, x, y, dir);
            if (!canWalk) {
                dir = dir < WEST ? dir + 1 : NORTH;
            }

            switch (dir) {
                case NORTH: y--; break;
                case SOUTH: y++; break;
                case WEST: x--; break;
                case EAST: x++; break;
            }
        }

        int spaces = 0;
        for (int i : map) {
            spaces += i == -1 ? 1 : 0;
        }

        System.out.println(spaces);
    }

    private static boolean checkDir(int[] map, int width, int x, int y, int dir) {
        if (y + 1 >= width || x + 1 >= width) return true;

        if (dir == NORTH) {
            return map[(y - 1) * width + x] < 1;
        }
        if (dir == SOUTH) {
            return map[(y + 1) * width + x] < 1;
        }
        if (dir == WEST) {
            return map[y * width + (x - 1)] < 1;
        }
        if (dir == EAST) {
            return map[y * width + (x + 1)] < 1;
        }

        return false;
    }
}

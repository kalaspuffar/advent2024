package org.ea.aoc.day6;

import org.ea.aoc.Util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day6b {

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    public static void main(String[] args) throws IOException {
        List<String> lines = Util.readInput(false, 6, false);

        int orgx = 0;
        int orgy = 0;
        int height = lines.size();
        int width = lines.get(0).length();
        int[] orgmap = new int[height * width];

        int cy = 0;
        for (String line : lines) {
            int cx = 0;
            for (String character : line.split("")) {
                if(character.equals(".")) {
                    orgmap[cy * width + cx] = 0;
                }
                if(character.equals("#")) {
                    orgmap[cy * width + cx] = -1;
                }
                if(character.equals("^")) {
                    orgmap[cy * width + cx] = 0;
                    orgx = cx;
                    orgy = cy;
                }
                cx++;
            }
            cy++;
        }

        int loops = 0;

        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx < width; dx++) {
                int[] map = Arrays.copyOf(orgmap, orgmap.length);
                if (map[dy * width + dx] == -1) {
                    continue;
                }
                if (dx == orgx && dy == orgy) {
                    continue;
                }
                map[dy * width + dx] = -1;
                int x = orgx;
                int y = orgy;
                int dir = NORTH;

                // 1454 too high

                while (x > -1 && y > -1 && x < width && y < height) {
                    map[y * width + x]++;

                    if (map[y * width + x] == 100) {
                        loops++;
                        break;
                    }

                    int canWalk = checkDir(map, width, x, y, dir);
                    if (canWalk == 0) {
                        dir = dir < WEST ? dir + 1 : NORTH;
                    } else if (canWalk == 2) {
                        break;
                    }

                    switch (dir) {
                        case NORTH: y--; break;
                        case SOUTH: y++; break;
                        case WEST: x--; break;
                        case EAST: x++; break;
                    }
                }
            }
        }

        System.out.println(loops);
    }

    private static int checkDir(int[] map, int width, int x, int y, int dir) {
        if (dir == NORTH && y - 1 < 0) return 2;
        if (dir == SOUTH && y + 1 == width ) return 2;
        if (dir == WEST && x - 1 < 0) return 2;
        if (dir == EAST && x + 1 == width) return 2;

        if (dir == NORTH) {
            return map[(y - 1) * width + x] > -1 ? 1 : 0;
        }
        if (dir == SOUTH) {
            return map[(y + 1) * width + x] > -1 ? 1 : 0;
        }
        if (dir == WEST) {
            return map[y * width + (x - 1)] > -1 ? 1 : 0;
        }
        if (dir == EAST) {
            return map[y * width + (x + 1)] > -1 ? 1 : 0;
        }

        return 0;
    }
}

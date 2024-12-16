package org.ea.aoc.day15;

import org.ea.aoc.Util;

import java.util.List;

public class Day15b {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 15, false);

        int i = 0;
        int height = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                height = i;
                break;
            }
            i++;
        }
        int width = lines.get(0).length() * 2;

        Wall[] map = new Wall[width * height];

        int robotX = 0;
        int robotY = 0;
        int y = 0;
        int id = 0;
        Robot r = new Robot(-1);
        for (String line : lines) {
            if (line.isBlank()) continue;
            int x = 0;
            for (String character : line.split("")) {
                switch (character) {
                    case "#":
                        map[y * width + x] = new Wall();
                        map[y * width + x + 1] = new Wall();
                        break;
                    case "O":
                        Box b = new Box(id);
                        map[y * width + x] = b;
                        map[y * width + x + 1] = b;
                        id++;
                        break;
                    case "@":
                        map[y * width + x] = r;
                        robotX = x;
                        robotY = y;
                        break;
                    case ">":
                        if (r.moveWide(map, robotX, robotY, width, Wall.EAST, false)) {
                            robotX++;
                        }
                        drawMap(height, width, map);
                        break;
                    case "<":
                        if (r.moveWide(map, robotX, robotY, width, Wall.WEST, false)) {
                            robotX--;
                        }
                        drawMap(height, width, map);
                        break;
                    case "^":
                        if (r.moveWide(map, robotX, robotY, width, Wall.NORTH, false)) {
                            robotY--;
                        }
                        drawMap(height, width, map);
                        break;
                    case "v":
                        if (r.moveWide(map, robotX, robotY, width, Wall.SOUTH, false)) {
                            robotY++;
                        }
                        drawMap(height, width, map);
                        break;
                }
                x += 2;
            }
            y++;
        }

        long count = 0;
        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width - 1; px++) {
                if (
                    map[py * width + px] != null &&
                    map[py * width + px + 1] != null &&
                    map[py * width + px].equals(map[py * width + px + 1]) &&
                    map[py * width + px] instanceof Box &&
                    !(map[py * width + px] instanceof Robot)
                ) {
                    count += py * 100L + px;
                }
            }
        }

        System.out.println(count);
    }

    private static void drawMap(int height, int width, Wall[] map) {
        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width; px++) {
                if (map[py * width + px] instanceof Robot) {
                    System.out.print("@");
                } else if (map[py * width + px] instanceof Box) {
                    if (map[py * width + px].equals(map[py * width + px + 1])) {
                        System.out.print("[");
                    } else if (map[py * width + px].equals(map[py * width + px - 1])) {
                        System.out.print("]");
                    } else {
                        System.out.print("O");
                    }
                } else if (map[py * width + px] instanceof Wall) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }
}

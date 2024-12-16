package org.ea.aoc.day15;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15a {
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
        int width = lines.get(0).length();

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
                        break;
                    case "O":
                        map[y * width + x] = new Box(id);
                        id++;
                        break;
                    case "@":
                        map[y * width + x] = r;
                        robotX = x;
                        robotY = y;
                        break;
                    case ">":
                        if (r.move(map, robotX, robotY, width, Wall.EAST)) {
                            robotX++;
                        }
                        break;
                    case "<":
                        if (r.move(map, robotX, robotY, width, Wall.WEST)) {
                            robotX--;
                        }
                        break;
                    case "^":
                        if (r.move(map, robotX, robotY, width, Wall.NORTH)) {
                            robotY--;
                        }
                        break;
                    case "v":
                        if (r.move(map, robotX, robotY, width, Wall.SOUTH)) {
                            robotY++;
                        }
                        break;
                }
                x++;
            }
            y++;
        }


        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width; px++) {
                if (map[py * width + px] instanceof Robot) {
                    System.out.print("@");
                } else if (map[py * width + px] instanceof Box) {
                    System.out.print("O");
                } else if (map[py * width + px] instanceof Wall) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }

        long count = 0;
        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width; px++) {
                if (
                    map[py * width + px] instanceof Box &&
                    !(map[py * width + px] instanceof Robot)
                ) {
                    count += py * 100L + px;
                }
            }
        }

        System.out.println(count);
    }
}

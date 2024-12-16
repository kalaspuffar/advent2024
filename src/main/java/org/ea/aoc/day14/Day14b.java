package org.ea.aoc.day14;

import org.ea.aoc.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14b {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 14, false);

        int roomX = 101;
        int roomY = 103;
        Pattern robot = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");

        List<Robot> robots = new ArrayList<>();

        for (String line : lines) {
            Matcher m = robot.matcher(line);
            if (m.find()) {
                int px = Integer.parseInt(m.group(1));
                int py = Integer.parseInt(m.group(2));
                int vx = Integer.parseInt(m.group(3));
                int vy = Integer.parseInt(m.group(4));
                robots.add(new Robot(px, py, vx, vy, 0));
            }
        }


        int qxs = 50;
        int qys1 = 25;
        int qys2 = 50;
        int qys3 = 75;

        for (long i = 0; i < 1_000_000_000L; i++) {
            boolean[] map = new boolean[roomX * roomY];

            int quad1x1 = 0;
            int quad1x2 = 0;
            int quad2x1 = 0;
            int quad2x2 = 0;
            int quad3x1 = 0;
            int quad3x2 = 0;
            int quad4x1 = 0;
            int quad4x2 = 0;

            for (Robot b : robots) {
                b.move();
                int ty = b.getY();
                int tx = b.getX();
                map[ty * roomX + tx] = true;
                if (ty < qys1 && tx < qxs) {
                    quad1x1++;
                } else if (ty < qys1 && tx > qxs) {
                    quad1x2++;
                } else if (ty > qys1 && ty < qys2 && tx < qxs) {
                    quad2x1++;
                } else if (ty > qys1 && ty < qys2 && tx > qxs) {
                    quad2x2++;
                } else if (ty > qys2 && ty < qys3 && tx < qxs) {
                    quad3x1++;
                } else if (ty > qys2 && ty < qys3 && tx > qxs) {
                    quad3x2++;
                } else if (ty > qys3 && tx < qxs) {
                    quad4x1++;
                } else if (ty > qys3 && tx > qxs) {
                    quad4x2++;
                }
            }

            if (quad1x1 != quad1x2) continue;
            if (quad2x1 != quad2x2) continue;
            if (quad3x1 != quad3x2) continue;
            if (quad4x1 != quad4x2) continue;
/*
            if (quad1x1 > quad2x1) continue;
            if (quad2x1 > quad3x1) continue;
            if (quad3x1 > quad4x1) continue;
*/
            System.out.println(i + 1);

            BufferedWriter bw = new BufferedWriter(new FileWriter("data/" + (i + 1) + ".txt"));
            for (int y = 0; y < roomY; y++){
                for (int x = 0; x < roomX; x++){
                    bw.write(map[y * roomX + x] ? "#" : ".");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
    }
}

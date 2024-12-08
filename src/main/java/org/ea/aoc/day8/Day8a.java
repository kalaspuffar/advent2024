package org.ea.aoc.day8;

import org.ea.aoc.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8a {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 8, false);

        Set<Point> points = new HashSet<>();
        Set<Point> antiPoints = new HashSet<>();

        int height = lines.size();
        int width = lines.get(0).length();

        int y = 0;
        for (String line : lines) {
            int x = 0;
            for (String character : line.split("")) {
                if (!character.equals(".")) {
                    points.add(new Point(character, x, y));
                }
                x++;
            }
            y++;
        }

        for (Point pointA : points) {
            for (Point pointB : points) {
                if (pointA.equals(pointB)) continue;
                Point newPoint = pointA.getAntiPoint(pointB, width, height);
                if (newPoint != null && !points.contains(newPoint)) {
                    antiPoints.add(newPoint);
                }
            }
        }
/*
        int[] map = new int[width * height];
        for (Point a : antiPoints) {
            map[a.getY() * width + a.getX()] = 1;
        }

        for (int fy = 0; fy < height; fy++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map[fy * width + x]);
            }
            System.out.println("");
        }
*/
        System.out.println(antiPoints.size());
    }
}

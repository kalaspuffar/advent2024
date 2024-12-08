package org.ea.aoc.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    private final String character;
    private final int x;
    private final int y;

    public Point(String character, int x, int y) {
        this.character = character;
        this.x = x;
        this.y = y;
    }

    public Point getAntiPoint(Point pointB, int width, int height) {
        if (!pointB.character.equals(this.character)) return null;
        int dx = pointB.x - this.x;
        int x = pointB.x + dx;
        int dy = pointB.y - this.y;
        int y = pointB.y + dy;

        if (x < 0) return null;
        if (y < 0) return null;
        if (x >= width) return null;
        if (y >= height) return null;

        return new Point("#", x, y);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y && Objects.equals(character, point.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, x, y);
    }

    public List<Point> getAntiPoints(Point pointB, int width, int height) {
        if (!pointB.character.equals(this.character)) return new ArrayList<>();
        int dx = pointB.x - this.x;
        int dy = pointB.y - this.y;

        int sx = pointB.x;
        int sy = pointB.y;

        int x = sx + dx;
        int y = sy + dy;

        List<Point> newPoints = new ArrayList<>();
        while (x > -1 && y > -1 && x < width && y < height) {
            newPoints.add(new Point("#", x, y));
            x += dx;
            y += dy;
        }
        return newPoints;
    }
}










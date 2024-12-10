package org.ea.aoc.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    private int rating;
    private final int x;
    private final int y;

    public Point(int rating, int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getRating() {
        return rating;
    }

    public void incRating() {
        rating++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
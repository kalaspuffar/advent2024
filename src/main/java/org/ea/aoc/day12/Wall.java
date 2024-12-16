package org.ea.aoc.day12;

import java.util.Objects;

public class Wall {
    public final static int RIGHT = 1;
    public final static int LEFT = 2;
    public final static int TOP = 3;
    public final static int BOTTOM = 4;

    public final int wallSide;
    public final int x;
    public final int y;

    public Wall(int wallSide, int x, int y) {
        this.wallSide = wallSide;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall = (Wall) o;
        return wallSide == wall.wallSide && x == wall.x && y == wall.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wallSide, x, y);
    }
}

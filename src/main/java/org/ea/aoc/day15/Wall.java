package org.ea.aoc.day15;

public class Wall {
    public static final int NORTH = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;

    public boolean move(Wall[] map, int x, int y, int width, int dir) {
        return false;
    }
    public boolean moveWide(Wall[] map, int x, int y, int width, int dir, boolean extra) {
        return false;
    }
    public boolean movable(Wall[] map, int x, int y, int width, int dir, boolean extra) {
        return false;
    }
}

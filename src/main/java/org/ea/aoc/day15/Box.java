package org.ea.aoc.day15;

import java.util.Objects;

public class Box extends Wall {

    private final int id;

    public Box(int id) {
        this.id = id;
    }

    @Override
    public boolean move(Wall[] map, int x, int y, int width, int dir) {
        switch (dir) {
            case EAST -> {
                if (map[y * width + x + 1] == null) {
                    map[y * width + x + 1] = this;
                    map[y * width + x] = null;
                    return true;
                } else {
                    if (map[y * width + x + 1].move(map, x + 1, y, width, dir)) {
                        map[y * width + x + 1] = this;
                        map[y * width + x] = null;
                        return true;
                    }
                }
            }
            case WEST -> {
                if (map[y * width + x - 1] == null) {
                    map[y * width + x - 1] = this;
                    map[y * width + x] = null;
                    return true;
                } else {
                    if (map[y * width + x - 1].move(map, x - 1, y, width, dir)) {
                        map[y * width + x - 1] = this;
                        map[y * width + x] = null;
                        return true;
                    }
                }
            }
            case NORTH -> {
                if (map[(y - 1) * width + x] == null) {
                    map[(y - 1) * width + x] = this;
                    map[y * width + x] = null;
                    return true;
                } else {
                    if (map[(y - 1) * width + x].move(map, x, y - 1, width, dir)) {
                        map[(y - 1) * width + x] = this;
                        map[y * width + x] = null;
                        return true;
                    }
                }
            }
            case SOUTH -> {
                if (map[(y + 1) * width + x] == null) {
                    map[(y + 1) * width + x] = this;
                    map[y * width + x] = null;
                    return true;
                } else {
                    if (map[(y + 1) * width + x].move(map, x, y + 1, width, dir)) {
                        map[(y + 1) * width + x] = this;
                        map[y * width + x] = null;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveWide(Wall[] map, int x, int y, int width, int dir, boolean extra) {
        switch (dir) {
            case EAST -> {
                if (map[y * width + x + 1] == null) {
                    map[y * width + x + 1] = this;
                    map[y * width + x] = null;
                    return true;
                } else {
                    if (map[y * width + x + 1].moveWide(map, x + 1, y, width, dir, false)) {
                        map[y * width + x + 1] = this;
                        map[y * width + x] = null;
                        return true;
                    }
                }
            }
            case WEST -> {
                if (map[y * width + x - 1] == null) {
                    map[y * width + x - 1] = this;
                    map[y * width + x] = null;
                    return true;
                } else {
                    if (map[y * width + x - 1].moveWide(map, x - 1, y, width, dir, false)) {
                        map[y * width + x - 1] = this;
                        map[y * width + x] = null;
                        return true;
                    }
                }
            }
            case NORTH -> {
                if (!extra) {
                    if (
                        map[y * width + x - 1] != null &&
                        map[y * width + x - 1].equals(this)
                    ) {
                        map[y * width + x - 1].moveWide(map, x - 1, y, width, dir, true);
                    } else if (
                        map[y * width + x + 1] != null &&
                        map[y * width + x + 1].equals(this)
                    ) {
                        map[y * width + x + 1].moveWide(map, x + 1, y, width, dir, true);
                    }
                }
                if (map[(y - 1) * width + x] == null) {
                    map[(y - 1) * width + x] = this;
                    map[y * width + x] = null;
                    return true;
                } else {
                    if (map[(y - 1) * width + x].moveWide(map, x, y - 1, width, dir, false)) {
                        map[(y - 1) * width + x] = this;
                        map[y * width + x] = null;
                        return true;
                    }
                }
            }
            case SOUTH -> {
                if (!extra) {
                    if (
                        map[y * width + x - 1] != null &&
                        map[y * width + x - 1].equals(this)
                    ) {
                        if (!map[y * width + x - 1].moveWide(map, x - 1, y, width, dir, true)) {
                            return false;
                        }
                    } else if (
                        map[y * width + x + 1] != null &&
                        map[y * width + x + 1].equals(this)
                    ) {
                        if (!map[y * width + x + 1].moveWide(map, x + 1, y, width, dir, true)) {
                            return false;
                        }
                    }
                }
                if (map[(y + 1) * width + x] == null) {
                    map[(y + 1) * width + x] = this;
                    map[y * width + x] = null;
                    return true;
                } else {
                    if (map[(y + 1) * width + x].moveWide(map, x, y + 1, width, dir, false)) {
                        map[(y + 1) * width + x] = this;
                        map[y * width + x] = null;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return id == box.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean movable(Wall[] map, int x, int y, int width, int dir, boolean extra) {
        boolean check1 = false;
        boolean check2 = false;
        switch (dir) {
            case NORTH -> {
                if (
                    map[y * width + x - 1] != null &&
                    map[y * width + x - 1].equals(this)
                ) {
                    check1 = map[(y - 1) * width + x - 1] == null || map[(y - 1) * width + x - 1].movable(map, x - 1, y - 1, width, dir, true);
                    check2 = map[(y - 1) * width + x] == null || map[(y - 1) * width + x].movable(map, x, y - 1, width, dir, true);
                } else if (
                    map[y * width + x + 1] != null &&
                    map[y * width + x + 1].equals(this)
                ) {
                    check1 = map[(y - 1) * width + x + 1] == null || map[(y - 1) * width + x + 1].movable(map, x + 1, y - 1, width, dir, true);
                    check2 = map[(y - 1) * width + x] == null || map[(y - 1) * width + x].movable(map, x, y - 1, width, dir, true);
                }
            }
            case SOUTH -> {
                if (
                    map[y * width + x - 1] != null &&
                    map[y * width + x - 1].equals(this)
                ) {
                    check1 = map[(y + 1) * width + x - 1] == null || map[(y + 1) * width + x - 1].movable(map, x - 1, y + 1, width, dir, true);
                    check2 = map[(y + 1) * width + x] == null || map[(y + 1) * width + x].movable(map, x, y + 1, width, dir, true);
                } else if (
                    map[y * width + x + 1] != null &&
                    map[y * width + x + 1].equals(this)
                ) {
                    check1 = map[(y + 1) * width + x + 1] == null || map[(y + 1) * width + x + 1].movable(map, x + 1, y + 1, width, dir, true);
                    check2 = map[(y + 1) * width + x] == null || map[(y + 1) * width + x].movable(map, x, y + 1, width, dir, true);
                }
            }
        }
        return check1 && check2;
    }
}

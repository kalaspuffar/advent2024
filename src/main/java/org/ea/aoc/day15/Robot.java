package org.ea.aoc.day15;

public class Robot extends Box {
    public Robot(int id) {
        super(id);
    }

    @Override
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
                if (
                    map[(y - 1) * width + x] != null &&
                    !map[(y - 1) * width + x].movable(map, x, y - 1, width, dir, true)
                ) {
                    return false;
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
                if (
                    map[(y + 1) * width + x] != null &&
                    !map[(y + 1) * width + x].movable(map, x, y + 1, width, dir, true)
                ) {
                    return false;
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
}

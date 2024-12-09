package org.ea.aoc.day9;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private int size = 0;
    private int id = 0;

    public Block(int size, int id) {
        this.size = size;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Block> fit(Block work) {
        if (work.size > this.size) {
            return null;
        }
        List<Block> newList = new ArrayList<>();
        if (work.size == this.size) {
            newList.add(work);
            return newList;
        } else {
            newList.add(work);
            newList.add(new Block(this.size - work.size, - 1));
            return newList;
        }
    }

    public int getSize() {
        return size;
    }
}

package org.ea.aoc.day12;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Plant {
    private Set<Plant> plants = new HashSet<>();
    private Set<Wall> wallObjs = new HashSet<>();
    private int id;
    private String character;
    private int area = 0;
    private int walls = 0;

    public Plant(String character, int id) {
        this.plants.add(this);
        this.character = character;
        this.id = id;
    }

    public BigInteger getCalc() {
        long newArea = 0;
        long newWalls = 0;
        for (Plant p : this.plants) {
            newArea += p.area;
            newWalls += p.walls;
        }
        return BigInteger.valueOf(newArea).multiply(BigInteger.valueOf(newWalls));
    }

    public BigInteger getCalcObj() {
        long newArea = 0;
        long newWalls = 0;
        for (Plant p : this.plants) {
            newArea += p.area;
        }

        while (!wallObjs.isEmpty()) {
            Wall w = (Wall) wallObjs.toArray()[0];
            Set<Wall> toRemove = new HashSet<>();
            if (w.wallSide == Wall.TOP || w.wallSide == Wall.BOTTOM) {
                toRemove.add(w);
                toRemove.addAll(getWalls(w, 1, 0));
                toRemove.addAll(getWalls(w, -1, 0));
                newWalls++;
            } else {
                toRemove.add(w);
                toRemove.addAll(getWalls(w, 0, 1));
                toRemove.addAll(getWalls(w, 0, -1));
                newWalls++;
            }
            wallObjs.removeAll(toRemove);
        }
        return BigInteger.valueOf(newArea).multiply(BigInteger.valueOf(newWalls));
    }

    private Collection<? extends Wall> getWalls(Wall wall, int dx, int dy) {
        Set<Wall> toRemove = new HashSet<>();
        for (Wall w : wallObjs) {
            if (w.x == wall.x + dx && w.y == wall.y + dy && w.wallSide == wall.wallSide) {
                toRemove.add(w);
                toRemove.addAll(getWalls(w, dx, dy));
                break;
            }
        }
        return toRemove;
    }

    public void incArea() {
        area++;
    }

    public void incWalls() {
        walls++;
    }

    public void addWall(Wall w) {
        wallObjs.add(w);
    }

    public String getKey() {
        return character;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return id == plant.id && Objects.equals(character, plant.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character);
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public Plant merge(Plant plant) {
        if (this.id > plant.id) {
            plant.wallObjs.addAll(this.wallObjs);
            plant.plants.addAll(this.getPlants());
            return plant;
        } else {
            this.wallObjs.addAll(plant.wallObjs);
            this.plants.addAll(plant.getPlants());
            return this;
        }
    }
}

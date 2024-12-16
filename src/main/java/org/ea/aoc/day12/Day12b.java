package org.ea.aoc.day12;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day12b {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 12, false);
        int height = lines.size();
        int width = lines.get(0).length();
        Plant[] characterMap = new Plant[width * height];

        int c = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            for (String character : line.split("")) {
                characterMap[c] = new Plant(character, c);
                c++;
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < height; x++) {
                calcPos(characterMap, x, y, width, height);
            }
        }

        boolean merged = true;
        while (merged) {
            merged = false;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < height; x++) {
                    if (merge(characterMap, x, y, width, height)) {
                        merged = true;
                    }
                }
            }
        }

        Set<Plant> allPlants = new HashSet<>();
        for (Plant p : characterMap) {
            allPlants.add(p);
        }

        BigInteger count = BigInteger.ZERO;
        for (Plant p : allPlants) {
            count = count.add(p.getCalcObj());
        }

        System.out.println(count);
    }

    private static boolean merge(Plant[] characterMap, int x, int y, int width, int height) {
        boolean merged = false;
        Plant plant = characterMap[y * width + x];
        if (x > 0 && characterMap[y * width + x - 1].getKey().equals(plant.getKey())) {
            if (!characterMap[y * width + x - 1].equals(plant)) {
                plant = plant.merge(characterMap[y * width + x - 1]);
                characterMap[y * width + x] = plant;
                characterMap[y * width + x - 1] = plant;
                merged = true;
            }
        }
        if (y > 0 && characterMap[(y - 1) * width + x].getKey().equals(plant.getKey())) {
            if (!characterMap[(y - 1) * width + x].equals(plant)) {
                plant = plant.merge(characterMap[(y - 1) * width + x]);
                characterMap[y * width + x] = plant;
                characterMap[(y - 1) * width + x] = plant;
                merged = true;
            }
        }
        return merged;
    }

    private static void calcPos(Plant[] characterMap, int x, int y, int width, int height) {
        Plant plant = characterMap[y * width + x];
        plant.incArea();

        if (x < height - 1 && !characterMap[y * width + x + 1].getKey().equals(plant.getKey())) {
            plant.addWall(new Wall(Wall.RIGHT, x, y));
            plant.incWalls();
        }
        if (x > 0 && !characterMap[y * width + x - 1].getKey().equals(plant.getKey())) {
            plant.addWall(new Wall(Wall.LEFT, x, y));
            plant.incWalls();
        }
        if (y < width - 1 && !characterMap[(y + 1) * width + x].getKey().equals(plant.getKey())) {
            plant.addWall(new Wall(Wall.BOTTOM, x, y));
            plant.incWalls();
        }
        if (y > 0 && !characterMap[(y - 1) * width + x].getKey().equals(plant.getKey())) {
            plant.addWall(new Wall(Wall.TOP, x, y));
            plant.incWalls();
        }

        if (x == 0) {
            plant.addWall(new Wall(Wall.LEFT, x, y));
            plant.incWalls();
        }
        if (y == 0) {
            plant.addWall(new Wall(Wall.TOP, x, y));
            plant.incWalls();
        }
        if (x == width - 1) {
            plant.addWall(new Wall(Wall.RIGHT, x, y));
            plant.incWalls();
        }
        if (y == height - 1) {
            plant.addWall(new Wall(Wall.BOTTOM, x, y));
            plant.incWalls();
        }
    }
}

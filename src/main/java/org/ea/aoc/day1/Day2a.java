package org.ea.aoc.day1;

import org.ea.aoc.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2a {
    public static void main(String[] args) throws IOException  {
        List<String> lines = Util.readInput(false, 2, false);

        int num_safe = 0;
        for (String line : lines) {
            String[] numStr = line.split(" ");
            int currentNum = -1;
            int inc = 0;
            boolean safe = true;
            for (String num : numStr) {
                int number = Integer.parseInt(num);
                if (currentNum == -1) {
                    currentNum = number;
                    continue;
                }
                if (currentNum > number) {
                    if (inc > 0) {
                        safe = false;
                        break;
                    } else if (inc == 0) {
                        inc = -1;
                    }
                    if (Math.abs(currentNum - number) > 3) {
                        safe = false;
                        break;
                    }
                    currentNum = number;
                } else if (currentNum < number) {
                    if (inc < 0) {
                        safe = false;
                        break;
                    } else if (inc == 0) {
                        inc = 1;
                    }
                    if (Math.abs(currentNum - number) > 3) {
                        safe = false;
                        break;
                    }
                    currentNum = number;
                } else {
                    safe = false;
                    break;
                }
            }
            num_safe += safe ? 1 : 0;
        }

        System.out.println(num_safe);
    }
}

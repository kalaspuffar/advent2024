package org.ea.aoc.day2;

import org.ea.aoc.Util;

import java.io.IOException;
import java.util.List;

public class Day2b {
    public static void main(String[] args) throws IOException  {
        List<String> lines = Util.readInput(false, 2, false);

        //332 - 380

        int num_safe = 0;
        for (String line : lines) {
            String[] numStr = line.split(" ");
            int[] numArr = new int[numStr.length];
            for (int i = 0; i < numArr.length; i++) {
                numArr[i] = Integer.parseInt(numStr[i]);
            }

            int inc = 0;
            int dec = 0;
            for (int j = 0; j < numArr.length - 1; j++) {
                if (numArr[j] < numArr[j + 1]) {
                    inc++;
                }
                if (numArr[j] > numArr[j + 1]) {
                    dec++;
                }
            }

            int dir = 0;
            if (inc > dec) {
                dir = 1;
            } else {
                dir = -1;
            }

            boolean safe = true;
            boolean unsafe_fix = false;
            for (int j = 0; j < numArr.length - 1; j++) {
                if (dir > 0) {
                    if (numArr[j] >= numArr[j + 1]) {
                        if (!unsafe_fix) {
                            if (numArr.length - 2 == j) {
                                break;
                            }
                            if (
                                numArr[j] < numArr[j + 2] &&
                                Math.abs(numArr[j] - numArr[j + 2]) < 4
                            ) {
                                unsafe_fix = true;
                                j++;
                                continue;
                            }
                            if (
                                j > 0 &&
                                numArr[j - 1] < numArr[j + 1] &&
                                Math.abs(numArr[j - 1] - numArr[j + 1]) < 4
                            ) {
                                unsafe_fix = true;
                                continue;
                            }
                        }
                        safe = false;
                        break;
                    }
                } else {
                    if (numArr[j] <= numArr[j + 1]) {
                        if (numArr.length - 2 == j) {
                            break;
                        }
                        if (!unsafe_fix) {
                            if (
                                numArr[j] > numArr[j + 2] &&
                                Math.abs(numArr[j] - numArr[j + 2]) < 4
                            ) {
                                unsafe_fix = true;
                                j++;
                                continue;
                            }
                            if (
                                j > 0 &&
                                numArr[j - 1] > numArr[j + 1] &&
                                Math.abs(numArr[j - 1] - numArr[j + 1]) < 4
                            ) {
                                unsafe_fix = true;
                                continue;
                            }
                        }
                        safe = false;
                        break;
                    }
                }
                if (Math.abs(numArr[j] - numArr[j + 1]) > 3) {
                    safe = false;
                    break;
                }
            }

            num_safe += safe ? 1 : 0;
        }

        System.out.println(num_safe);
    }
}

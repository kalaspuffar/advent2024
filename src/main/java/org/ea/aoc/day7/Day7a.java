package org.ea.aoc.day7;

import org.ea.aoc.Util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Day7a {
    public static void main(String[] args) throws Exception {
        List<String> lines = Util.readInput(false, 7, false);

        BigInteger sum = BigInteger.ZERO;
        for (String line : lines) {
            String[] arr = line.split(":");
            BigInteger result = new BigInteger(arr[0]);
            String[] operandStr = arr[1].trim().split(" ");
            int[] operands = Arrays.stream(operandStr)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            BigInteger count = BigInteger.valueOf(operands[0]);
            boolean add = checkCalculation(operands, 1, count, result);
            if (add) {
                sum = sum.add(result);
            }
        }

        System.out.println(sum);
    }

    private static boolean checkCalculation(int[] operands, int index, BigInteger count, BigInteger result) {
        if (index == operands.length) {
            return count.equals(result);
        }
        BigInteger sum1 = count.add(BigInteger.valueOf(operands[index]));
        BigInteger sum2 = count.multiply(BigInteger.valueOf(operands[index]));
        BigInteger sum3 = new BigInteger(count.toString() + operands[index]);
        index++;
        boolean check1 = checkCalculation(operands, index, sum1, result);
        boolean check2 = checkCalculation(operands, index, sum2, result);
        boolean check3 = checkCalculation(operands, index, sum3, result);
        return check1 || check2 || check3;
    }
}

package org.ea.aoc.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CalPoint {
    private BigInteger val;
    private int num = 0;

    public CalPoint(BigInteger val, int num) {
        this.val = val;
        this.num = num;
    }

    public CalPoint evolve() {
        if (val.equals(BigInteger.ZERO)) {
            val = BigInteger.ONE;
            num++;
        } else if (val.toString().length() % 2 == 0) {
            int index = val.toString().length() / 2;
            String newPart = val.toString().substring(index);
            val = new BigInteger(val.toString().substring(0, index));
            num++;
            return new CalPoint(new BigInteger(newPart), num);
        } else {
            val = val.multiply(BigInteger.valueOf(2024));
            num++;
        }
        return null;
    }


    public int getNum() {
        return num;
    }
}

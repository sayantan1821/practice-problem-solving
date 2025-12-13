package com.practice.common;

import java.util.*;

/**
 * Common utility methods for array operations
 */
public class BinaryUtils {
    
    /**
     * Extracts the bits of a given integer and returns them as an array
     * in reverse order, i.e. the least significant bit is the first element of the array
     * @param num the integer to extract the bits from
     * @return the bits of the integer as an array
     */
    public static int[] extractBits(int num) {
        List<Integer> bitList = new ArrayList<>();
        while (num > 0) {
            bitList.add(num & 1);
            num >>= 1;
        }
        Collections.reverse(bitList);
        return bitList.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Converts an array of bits to a decimal integer
     * @param bits the array of bits
     * @return the decimal integer
     */
    public static int convertToDecimal(int[] bits) {
        int result = 0;
        for(int i = 0; i < bits.length; i++) {
            result = result * 2 + bits[i];
        }
        return result;
    }
}


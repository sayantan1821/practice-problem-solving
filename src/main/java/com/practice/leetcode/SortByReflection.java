package com.practice.leetcode;
import com.practice.common.BinaryUtils;
import com.practice.common.ArrayUtils;

import java.util.*;

/**
 * LeetCode Problem: Sort Array by Binary Reflection
 * Problem Number: 379
 * Difficulty: Easy
 * Problem Link: https://leetcode.com/problems/sort-integers-by-binary-reflection
 *
 * You are given an integer array nums.
 * 
 * The binary reflection of a positive integer is defined as the number obtained 
 * by reversing the order of its binary digits (ignoring any leading zeros) and 
 * interpreting the resulting binary number as a decimal.
 * 
 * Sort the array in ascending order based on the binary reflection of each element. 
 * If two different numbers have the same binary reflection, the smaller original 
 * number should appear first.
 * 
 * Return the resulting sorted array.
 * 
 * Example:
 * Input: nums = [5, 3, 10, 2]
 * - 5 in binary: "101", reverse: "101" = 5
 * - 3 in binary: "11", reverse: "11" = 3
 * - 10 in binary: "1010", reverse: "0101" -> "101" = 5
 * - 2 in binary: "10", reverse: "01" -> "1" = 1
 * Output: [2, 3, 5, 10]
 */
public class SortByReflection {
    
    /**
     * Sorts the array based on binary reflection values
     * 
     * @param nums the input array
     * @return the sorted array
     */
    public int[] sortByReflection(int[] nums) {
        // TODO: Implement your solution here
        List<Pair> pairs = new ArrayList<>();
        for(int num : nums) {
            // long binaryDigitValue = ArrayUtils.mergeDigits(ArrayUtils.reverseArray(BinaryUtils.extractBits(num)));
            int reflectedDecimalValue = BinaryUtils.convertToDecimal(ArrayUtils.reverseArray(BinaryUtils.extractBits(num)));
            pairs.add(new Pair(num, reflectedDecimalValue));
        }

        pairs.sort((p1, p2) -> {
            if (p1.reflection != p2.reflection) {
                return Integer.compare(p1.reflection, p2.reflection);
            }
            return Integer.compare(p1.original, p2.original);
        });

        int[] result = new int[nums.length];
        for (int i = 0; i < pairs.size(); i++) {
            result[i] = pairs.get(i).original;
        }
        return result;
    }

    static class Pair {
        int original;
        int reflection;

        Pair(int original, int reflection) {
            this.original = original;
            this.reflection = reflection;
        }
    }
}


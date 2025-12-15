package com.practice.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for "Absolute Difference Between Maximum and Minimum K Elements" problem.
 *
 * These tests focus on the core utility of computing the absolute difference
 * between the sum of k maximum and k minimum elements in the array.
 */
public class AbsoluteDifferenceMaxMinKElementsTest {

    @Test
    public void testSimpleIncreasingArray() {
        AbsoluteDifferenceMaxMinKElements solution = new AbsoluteDifferenceMaxMinKElements();
        int[] nums = {1, 2, 3, 4, 5};
        int k = 2;
        int result = solution.absDifference(nums, k);
        // smallest 2: 1 + 2 = 3, largest 2: 5 + 4 = 9, |9 - 3| = 6
        int expected = 6;
        assertEquals(expected, result);
    }

    @Test
    public void testAllEqualElements() {
        AbsoluteDifferenceMaxMinKElements solution = new AbsoluteDifferenceMaxMinKElements();
        int[] nums = {5, 5, 5, 5};
        int k = 2;
        int result = solution.absDifference(nums, k);
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void testWithNegativeNumbers() {
        AbsoluteDifferenceMaxMinKElements solution = new AbsoluteDifferenceMaxMinKElements();
        int[] nums = {-3, -1, 2, 4, 6};
        int k = 2;
        // smallest 2: -3 + (-1) = -4, largest 2: 6 + 4 = 10, |10 - (-4)| = 14
        int result = solution.absDifference(nums, k);
        int expected = 14;
        assertEquals(expected, result);
    }

    @Test
    public void testSingleElementArray() {
        AbsoluteDifferenceMaxMinKElements solution = new AbsoluteDifferenceMaxMinKElements();
        int[] nums = {10};
        int k = 1;
        int result = solution.absDifference(nums, k);
        // smallest 1: 10, largest 1: 10, difference = 0
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void testKEqualsArrayLength() {
        AbsoluteDifferenceMaxMinKElements solution = new AbsoluteDifferenceMaxMinKElements();
        int[] nums = {1, 3, 5, 7};
        int k = 4;
        int result = solution.absDifference(nums, k);
        // smallest 4 and largest 4 both sum to same total, so diff = 0
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void testInvalidKReturnsZero() {
        AbsoluteDifferenceMaxMinKElements solution = new AbsoluteDifferenceMaxMinKElements();
        int[] nums = {1, 2, 3};
        int result1 = solution.absDifference(nums, 0);
        int result2 = solution.absDifference(nums, -1);
        int result3 = solution.absDifference(nums, 5); // k > length
        assertEquals(0L, result1);
        assertEquals(0L, result2);
        assertEquals(0L, result3);
    }
}

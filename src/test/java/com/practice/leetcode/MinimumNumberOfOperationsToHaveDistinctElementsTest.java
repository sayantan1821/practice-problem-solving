package com.practice.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for LeetCode 3779: Minimum Number of Operations to Have Distinct Elements.
 *
 * NOTE: The solution method is currently TODO, so these tests will fail until implemented.
 */
public class MinimumNumberOfOperationsToHaveDistinctElementsTest {

    @Test
    public void testExample1() {
        MinimumNumberOfOperationsToHaveDistinctElements solution = new MinimumNumberOfOperationsToHaveDistinctElements();
        int[] nums = {3, 8, 3, 6, 5, 8};
        int expected = 1;
        assertEquals(expected, solution.minOperations(nums));
    }

    @Test
    public void testExample2() {
        MinimumNumberOfOperationsToHaveDistinctElements solution = new MinimumNumberOfOperationsToHaveDistinctElements();
        int[] nums = {2, 2};
        int expected = 1;
        assertEquals(expected, solution.minOperations(nums));
    }

    @Test
    public void testExample3() {
        MinimumNumberOfOperationsToHaveDistinctElements solution = new MinimumNumberOfOperationsToHaveDistinctElements();
        int[] nums = {4, 3, 5, 1, 2};
        int expected = 0;
        assertEquals(expected, solution.minOperations(nums));
    }
}


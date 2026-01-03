package com.practice.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for LeetCode 3788: Maximum Score of a Split.
 *
 * NOTE: The solution method is currently TODO, so these tests will fail until implemented.
 */
public class MaximumScoreOfASplitTest {

    @Test
    public void testExample1() {
        MaximumScoreOfASplit solution = new MaximumScoreOfASplit();
        int[] nums = {10, -1, 3, -4, -5};
        long expected = 17;
        long result = solution.maximumScore(nums);
        assertEquals(expected, result);
    }

    @Test
    public void testExample2() {
        MaximumScoreOfASplit solution = new MaximumScoreOfASplit();
        int[] nums = {-7, -5, 3};
        long expected = -2;
        long result = solution.maximumScore(nums);
        assertEquals(expected, result);
    }

    @Test
    public void testExample3() {
        MaximumScoreOfASplit solution = new MaximumScoreOfASplit();
        int[] nums = {1, 1};
        long expected = 0;
        long result = solution.maximumScore(nums);
        assertEquals(expected, result);
    }
}


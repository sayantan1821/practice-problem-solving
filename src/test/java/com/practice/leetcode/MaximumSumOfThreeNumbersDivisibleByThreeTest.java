package com.practice.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for LeetCode 3780: Maximum Sum of Three Numbers Divisible by Three.
 *
 * NOTE: The solution method is currently TODO, so these tests will fail until implemented.
 */
public class MaximumSumOfThreeNumbersDivisibleByThreeTest {

    @Test
    public void testExample1() {
        MaximumSumOfThreeNumbersDivisibleByThree solution = new MaximumSumOfThreeNumbersDivisibleByThree();
        int[] nums = {4, 2, 3, 1};
        int expected = 9;
        int result = solution.maximumSum(nums);
        assertEquals(expected, result);
    }

    @Test
    public void testExample2() {
        MaximumSumOfThreeNumbersDivisibleByThree solution = new MaximumSumOfThreeNumbersDivisibleByThree();
        int[] nums = {2, 1, 5};
        int expected = 0;
        int result = solution.maximumSum(nums);
        assertEquals(expected, result);
    }
}


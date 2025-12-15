package com.practice.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for "Find Maximum Balanced XOR Subarray Length" (LC 3755).
 *
 * These tests are written against the local class {@link FindMaximumBalancedXorSubarrayLength}
 * and its method {@code maxBalancedSubarray(int[] nums)}.
 *
 * NOTE: The method is currently TODO, so these tests will fail until you implement it.
 */
public class FindMaximumBalancedXorSubarrayLengthTest {

    @Test
    public void testExample1() {
        FindMaximumBalancedXorSubarrayLength solution = new FindMaximumBalancedXorSubarrayLength();
        int[] nums = {3, 1, 3, 2, 0};
        int result = solution.maxBalancedSubarray(nums);
        // The subarray [1, 3, 2, 0] has XOR 0 and 2 even / 2 odd -> length 4
        int expected = 4;
        assertEquals(expected, result);
    }

    @Test
    public void testExample2() {
        FindMaximumBalancedXorSubarrayLength solution = new FindMaximumBalancedXorSubarrayLength();
        int[] nums = {3, 2, 8, 5, 4, 14, 9, 15};
        int result = solution.maxBalancedSubarray(nums);
        // Whole array: XOR == 0, 4 even + 4 odd -> length 8
        int expected = 8;
        assertEquals(expected, result);
    }

    @Test
    public void testExample3() {
        FindMaximumBalancedXorSubarrayLength solution = new FindMaximumBalancedXorSubarrayLength();
        int[] nums = {0};
        int result = solution.maxBalancedSubarray(nums);
        // No non-empty subarray satisfies both conditions
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void testNoBalancedSubarray() {
        FindMaximumBalancedXorSubarrayLength solution = new FindMaximumBalancedXorSubarrayLength();
        int[] nums = {1, 1, 1}; // all odd, can't ever have equal even/odd in non-empty subarray
        int result = solution.maxBalancedSubarray(nums);
        int expected = 0;
        assertEquals(expected, result);
    }

//    @Test
//    public void testMultipleBalancedCandidates() {
//        FindMaximumBalancedXorSubarrayLength solution = new FindMaximumBalancedXorSubarrayLength();
//        int[] nums = {2, 3, 1, 1, 2, 2, 3, 1};
//        int result = solution.maxBalancedSubarray(nums);
//        // You can design / verify the expected length when you implement the solution.
//        // For now we keep this test as a placeholder; update expected when you solve.
//        int expected = 0; // TODO: update once implementation is done
//        assertEquals(expected, result);
//    }
}



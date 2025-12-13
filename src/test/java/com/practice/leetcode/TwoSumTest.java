package com.practice.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for TwoSum problem
 */
public class TwoSumTest {
    
    @Test
    public void testTwoSumExample1() {
        TwoSum solution = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        assertArrayEquals(new int[]{0, 1}, result);
    }
    
    @Test
    public void testTwoSumExample2() {
        TwoSum solution = new TwoSum();
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] result = solution.twoSum(nums, target);
        assertArrayEquals(new int[]{1, 2}, result);
    }
    
    @Test
    public void testTwoSumExample3() {
        TwoSum solution = new TwoSum();
        int[] nums = {3, 3};
        int target = 6;
        int[] result = solution.twoSum(nums, target);
        assertArrayEquals(new int[]{0, 1}, result);
    }
}



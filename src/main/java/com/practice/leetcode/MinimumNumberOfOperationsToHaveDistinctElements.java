package com.practice.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem: Minimum Number of Operations to Have Distinct Elements
 * Problem Number: 3779
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-number-of-operations-to-have-distinct-elements/
 *
 * You are given an integer array nums.
 *
 * In one operation, you remove the first three elements of the current array. If there are fewer than three elements remaining, all remaining elements are removed.
 *
 * Repeat this operation until the array is empty or contains no duplicate values.
 *
 * Return an integer denoting the number of operations required.
 *
 * LeetCode class structure:
 * class Solution {
 *     public int minOperations(int[] nums) { }
 * }
 *
 * This project uses a per-problem class name, and the exporter can generate a LeetCode-ready
 * Solution class when needed.
 */
public class MinimumNumberOfOperationsToHaveDistinctElements {

    /**
     * TODO: Implement minOperations solution.
     *
     * @param nums the input integer array
     * @return the number of operations required
     */
    public int minOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        int duplicateNumberIndex = -1;
        for(int i = n - 1; i >= 0; i--) {
            if(set.contains(nums[i])) {
                duplicateNumberIndex = i;
                break;
            }
            set.add(nums[i]);
        }
        if(duplicateNumberIndex == -1) return 0;
        return (int) Math.ceil((duplicateNumberIndex + 1) / 3.0);
    }
}


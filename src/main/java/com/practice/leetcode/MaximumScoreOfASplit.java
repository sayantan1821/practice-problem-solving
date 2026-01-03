package com.practice.leetcode;

/**
 * LeetCode Problem: Maximum Score of a Split
 * Problem Number: 3788
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-score-of-a-split/
 *
 * You are given an integer array nums of length n.
 *
 * Choose an index i such that 0 <= i < n - 1.
 *
 * For a chosen split index i:
 * Let prefixSum(i) be the sum of nums[0] + nums[1] + ... + nums[i].
 * Let suffixMin(i) be the minimum value among nums[i + 1], nums[i + 2], ..., nums[n - 1].
 * The score of a split at index i is defined as:
 *
 * score(i) = prefixSum(i) - suffixMin(i)
 *
 * Return an integer denoting the maximum score over all valid split indices.
 *
 * Example 1:
 * Input: nums = [10,-1,3,-4,-5]
 * Output: 17
 * Explanation:
 * The optimal split is at i = 2, score(2) = prefixSum(2) - suffixMin(2) = (10 + (-1) + 3) - (-5) = 17.
 *
 * Example 2:
 * Input: nums = [-7,-5,3]
 * Output: -2
 * Explanation:
 * The optimal split is at i = 0, score(0) = prefixSum(0) - suffixMin(0) = (-7) - (-5) = -2.
 *
 * Example 3:
 * Input: nums = [1,1]
 * Output: 0
 * Explanation:
 * The only valid split is at i = 0, score(0) = prefixSum(0) - suffixMin(0) = 1 - 1 = 0.
 *
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * LeetCode class structure:
 * class Solution {
 *     public int maxScore(int[] nums) { }
 * }
 *
 * This project uses a per-problem class name, and the exporter can generate a LeetCode-ready
 * Solution class when needed.
 */
public class MaximumScoreOfASplit {

    /**
     * TODO: Implement maximum score of a split.
     *
     * @param nums the input array of integers
     * @return the maximum score over all valid split indices
     */
    public long maximumScore(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] suffixMin = new int[n];
        long maxDiff = Long.MIN_VALUE;
        prefixSum[0] = nums[0];
        for(int i = 1; i < n; i++) prefixSum[i] = prefixSum[i - 1] + nums[i];
        suffixMin[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; i--) suffixMin[i] = Math.min(suffixMin[i+ 1], nums[i]);
        for(int i = 0; i < n - 1; i++)
            maxDiff = Math.max(maxDiff, prefixSum[i] - suffixMin[i + 1]);
        return maxDiff;
    }
}


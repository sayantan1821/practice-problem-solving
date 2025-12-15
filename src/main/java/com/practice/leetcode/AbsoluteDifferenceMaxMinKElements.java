package com.practice.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * LeetCode Problem: Absolute Difference Between Maximum and Minimum K Elements
 * Problem Number: 3774
 * Difficulty: Easy
 * Problem Link: https://leetcode.com/problems/absolute-difference-between-maximum-and-minimum-k-elements
 *
 * You are given an integer array nums and an integer k.
 *
 * Find the absolute difference between:
 *  - the sum of the k largest elements in the array; and
 *  - the sum of the k smallest elements in the array.
 *
 * Return an integer denoting this difference.
 *
 * NOTE: This class is for local practice. The method signature may differ
 * from the original LeetCode function signature.
 */
public class AbsoluteDifferenceMaxMinKElements {

    /**
     * Computes the absolute difference between the sum of the k largest elements
     * and the sum of the k smallest elements in the array.
     *
     * Constraints (from LeetCode 3774):
     * - 1 <= n == nums.length <= 100
     * - 1 <= nums[i] <= 100
     * - 1 <= k <= n
     *
     * For now this method is left as a TODO so you can implement and practice.
     *
     * @param nums the input array
     * @param k    the number of elements to consider from both ends
     * @return the absolute difference as a long
     */
    public int absDifference(int[] nums, int k) {
        int n = nums.length;
        if(k < 1 || k > n) return 0;
        Arrays.sort(nums);
        int largestKSum = 0, smallestKSum = 0;
        for(int i = 0; i < k; i++) {
            largestKSum += nums[n - i - 1];
            smallestKSum += nums[i];
        }
        return largestKSum - smallestKSum;
    }
}



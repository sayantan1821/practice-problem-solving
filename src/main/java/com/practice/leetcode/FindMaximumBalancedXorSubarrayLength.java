package com.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode Problem: Find Maximum Balanced XOR Subarray Length
 * Problem Number: 3755
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-maximum-balanced-xor-subarray-length
 *
 * Given an integer array nums, return the length of the longest subarray that has:
 *  - bitwise XOR of zero, and
 *  - an equal number of even and odd numbers.
 *
 * If no such subarray exists, return 0.
 */
public class FindMaximumBalancedXorSubarrayLength {

    /**
     * Returns the maximum length of a subarray with XOR == 0
     * and equal count of even and odd numbers.
     *
     * Original LeetCode-style signature:
     * {@code class Solution { public int maxBalancedSubarray(int[] nums) { ... } }}
     *
     * TODO: Implement this method.
     *
     * @param nums input array
     * @return maximum length of a balanced XOR-zero subarray
     */
    public int maxBalancedSubarray(int[] nums) {
        int n = nums.length;
        int[] prefixXor = new int[n + 1];
        prefixXor[0] = 0;
        for(int i = 1; i <= n; i++) {
            prefixXor[i] = prefixXor[i - 1] ^ nums[i - 1];
        }
        int[] prefixEven = new int[n + 1];
        prefixEven[0] = 0;
        for(int i = 1; i <= n; i++) {
            prefixEven[i] = prefixEven[i - 1] + (nums[i - 1] % 2 == 0 ? 1 : -1);
        }
        Map<String, Integer> prefixMap = new HashMap<>();
        prefixMap.put("0,0", 0);
        int maxLength = 0;
        for(int i = 1; i <= n; i++) {
            String key = prefixXor[i] + "," + prefixEven[i];
            if(prefixMap.containsKey(key)) maxLength = Math.max(maxLength, i - prefixMap.get(key));
            else prefixMap.put(key, i);

        }
        return maxLength;
    }
}



package com.practice.leetcode;

import java.util.Arrays;

/**
 * LeetCode Problem: Largest Prime from Consecutive Prime Sum
 * Problem Number: 3770
 * 
 * Given an integer n, return the largest prime number p such that:
 * - p ≤ n
 * - p can be written as the sum of consecutive prime numbers starting from 2
 * 
 * If no such prime number exists, return 0.
 * 
 * Example 1:
 * Input: n = 100
 * Output: 41
 * Explanation:
 * Consecutive prime sums starting from 2:
 * - 2 (prime)
 * - 2 + 3 = 5 (prime)
 * - 2 + 3 + 5 = 10 (not prime)
 * - 2 + 3 + 5 + 7 = 17 (prime)
 * - 2 + 3 + 5 + 7 + 11 = 28 (not prime)
 * - 2 + 3 + 5 + 7 + 11 + 13 = 41 (prime)
 * - 2 + 3 + 5 + 7 + 11 + 13 + 17 = 58 (not prime)
 * - 2 + 3 + 5 + 7 + 11 + 13 + 17 + 19 = 77 (not prime)
 * - 2 + 3 + 5 + 7 + 11 + 13 + 17 + 19 + 23 = 100 (not prime)
 * The largest prime among these is 41.
 * 
 * Example 2:
 * Input: n = 5
 * Output: 5
 * Explanation: 2 and 5 are both valid, 5 is larger.
 * 
 * Example 3:
 * Input: n = 1
 * Output: 0
 * Explanation: No prime numbers ≤ 1.
 */
public class LargestPrimeFromConsecutivePrimeSum {
    
    /**
     * Finds the largest prime number ≤ n that can be expressed as 
     * the sum of consecutive prime numbers starting from 2.
     * 
     * @param n the upper bound
     * @return the largest prime that satisfies the condition, or 0 if none exists
     */
    public int largestPrime(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i * i <= n; i++) {  // Better condition
            if (isPrime[i]) {  // Only process if still prime
                for(int j = i * i; j <= n; j += i) {  // Start from i*i, increment by i
                    isPrime[j] = false;
                }
            }
        }
        int maxPrimeNumber = 0;
        int primeSum = 0;
        for(int i = 0; i <= n; i++) {
            if(!isPrime[i]) continue;
            primeSum += i;
            if(primeSum > n) break;
            if(primeSum > maxPrimeNumber && isPrime[primeSum]) maxPrimeNumber = primeSum;
        }
        return maxPrimeNumber;
    }
}

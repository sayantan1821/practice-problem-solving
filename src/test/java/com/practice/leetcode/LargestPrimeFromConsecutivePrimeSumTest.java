package com.practice.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for LargestPrimeFromConsecutivePrimeSum problem
 */
public class LargestPrimeFromConsecutivePrimeSumTest {
    
    @Test
    public void testExample1() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 100;
        int result = solution.largestPrime(n);
        int expected = 41;
        assertEquals(expected, result);
    }
    
    @Test
    public void testExample2() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 5;
        int result = solution.largestPrime(n);
        int expected = 5;
        assertEquals(expected, result);
    }
    
    @Test
    public void testExample3() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 1;
        int result = solution.largestPrime(n);
        int expected = 0;
        assertEquals(expected, result);
    }
    
    @Test
    public void testSmallNumber() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 2;
        int result = solution.largestPrime(n);
        int expected = 2;
        assertEquals(expected, result);
    }
    
    @Test
    public void testNumber3() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 3;
        int result = solution.largestPrime(n);
        // Consecutive prime sums: 2, 2+3=5 (but 5 > 3)
        // So only 2 is valid, but 2 < 3, so answer is 2? Or should we check if 3 itself works?
        // Actually, 3 cannot be written as sum of consecutive primes starting from 2
        // So the answer should be 2
        int expected = 2;
        assertEquals(expected, result);
    }
    
    @Test
    public void testNumber10() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 10;
        int result = solution.largestPrime(n);
        // Consecutive prime sums: 2, 5, 10 (10 is not prime)
        // So answer is 5
        int expected = 5;
        assertEquals(expected, result);
    }
    
    @Test
    public void testNumber17() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 17;
        int result = solution.largestPrime(n);
        // Consecutive prime sums: 2, 5, 10, 17 (17 is prime)
        // So answer is 17
        int expected = 17;
        assertEquals(expected, result);
    }
    
    @Test
    public void testNumber50() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 50;
        int result = solution.largestPrime(n);
        // Consecutive prime sums: 2, 5, 10, 17, 28, 41, 58 (58 > 50)
        // Primes: 2, 5, 17, 41
        // Largest: 41
        int expected = 41;
        assertEquals(expected, result);
    }
    
    @Test
    public void testNumber1000() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 1000;
        int result = solution.largestPrime(n);
        // This is a larger test case to verify the solution works for bigger numbers
        assertTrue(result > 0, "Result should be a positive prime number");
        assertTrue(result <= n, "Result should be <= n");
    }
    
    @Test
    public void testNumber41() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 41;
        int result = solution.largestPrime(n);
        // 41 itself is a valid sum, so answer should be 41
        int expected = 41;
        assertEquals(expected, result);
    }
    
    @Test
    public void testNumber28() {
        LargestPrimeFromConsecutivePrimeSum solution = new LargestPrimeFromConsecutivePrimeSum();
        int n = 28;
        int result = solution.largestPrime(n);
        // Consecutive prime sums: 2, 5, 10, 17, 28 (28 is not prime)
        // Primes: 2, 5, 17
        // Largest: 17
        int expected = 17;
        assertEquals(expected, result);
    }
}

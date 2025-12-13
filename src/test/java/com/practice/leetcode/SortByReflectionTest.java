package com.practice.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for SortByReflection problem
 */
public class SortByReflectionTest {

    @Test
    public void testExample1() {
        SortByReflection solution = new SortByReflection();
        int[] nums = {5, 3, 10, 2};
        int[] result = solution.sortByReflection(nums);
        int[] expected = {2, 3, 5, 10};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testExample2() {
        SortByReflection solution = new SortByReflection();
        int[] nums = {1, 2, 3, 4, 5};
        int[] result = solution.sortByReflection(nums);
        // Reflections:
        // 1 -> "1" -> "1" = 1
        // 2 -> "10" -> "01" -> "1" = 1
        // 3 -> "11" -> "11" = 3
        // 4 -> "100" -> "001" -> "1" = 1
        // 5 -> "101" -> "101" = 5
        // Sorted: [1, 2, 4, 3, 5] (reflections: 1, 1, 1, 3, 5)
        int[] expected = {1, 2, 4, 3, 5};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSingleElement() {
        SortByReflection solution = new SortByReflection();
        int[] nums = {7};
        int[] result = solution.sortByReflection(nums);
        int[] expected = {7};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSameReflection() {
        SortByReflection solution = new SortByReflection();
        // 5 -> "101" -> "101" = 5
        // 10 -> "1010" -> "0101" -> "101" = 5
        // Both have reflection 5, so smaller original (5) comes first
        int[] nums = {10, 5};
        int[] result = solution.sortByReflection(nums);
        int[] expected = {5, 10};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testPowersOfTwo() {
        SortByReflection solution = new SortByReflection();
        // 1 -> "1" -> "1" = 1
        // 2 -> "10" -> "01" -> "1" = 1
        // 4 -> "100" -> "001" -> "1" = 1
        // 8 -> "1000" -> "0001" -> "1" = 1
        // All have reflection 1, so sorted by original value
        int[] nums = {8, 4, 2, 1};
        int[] result = solution.sortByReflection(nums);
        int[] expected = {1, 2, 4, 8};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testZero() {
        SortByReflection solution = new SortByReflection();
        // 0 -> "0" -> "0" = 0
        int[] nums = {5, 0, 3};
        int[] result = solution.sortByReflection(nums);
        // 0 has reflection 0, 3 has reflection 3, 5 has reflection 5
        int[] expected = {0, 3, 5};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testLargeNumbers() {
        SortByReflection solution = new SortByReflection();
        int[] nums = {15, 7, 14};
        // 15 -> "1111" -> "1111" = 15
        // 7 -> "111" -> "111" = 7
        // 14 -> "1110" -> "0111" -> "111" = 7
        // Sorted: [7, 14, 15] (reflections: 7, 7, 15)
        int[] result = solution.sortByReflection(nums);
        int[] expected = {7, 14, 15};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlreadySorted() {
        SortByReflection solution = new SortByReflection();
        int[] nums = {1, 3, 5};
        int[] result = solution.sortByReflection(nums);
        // 1 -> 1, 3 -> 3, 5 -> 5
        int[] expected = {1, 3, 5};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testReverseSorted() {
        SortByReflection solution = new SortByReflection();
        int[] nums = {10, 5, 3, 2};
        int[] result = solution.sortByReflection(nums);
        // Reflections: 5, 5, 3, 1
        int[] expected = {2, 3, 5, 10};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testDuplicateValues() {
        SortByReflection solution = new SortByReflection();
        int[] nums = {5, 5, 3, 3};
        int[] result = solution.sortByReflection(nums);
        // Reflections: 5, 5, 3, 3
        int[] expected = {3, 3, 5, 5};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testLargeNumbersCase() {
        SortByReflection solution = new SortByReflection();
        int[] nums = {947881, 309856};
        int[] result = solution.sortByReflection(nums);
        int[] expected = {309856, 947881};
        assertArrayEquals(expected, result);
    }
}


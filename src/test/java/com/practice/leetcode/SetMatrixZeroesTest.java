package com.practice.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for LeetCode 73: Set Matrix Zeroes.
 *
 * NOTE: The solution method is currently TODO, so these tests will fail until implemented.
 */
public class SetMatrixZeroesTest {

    @Test
    public void testExample1() {
        SetMatrixZeroes solution = new SetMatrixZeroes();
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        solution.setZeroes(matrix);

        int[][] expected = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        };

        assertMatrixEquals(expected, matrix);
    }

    @Test
    public void testExample2() {
        SetMatrixZeroes solution = new SetMatrixZeroes();
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };

        solution.setZeroes(matrix);

        int[][] expected = {
                {0, 0, 0, 0},
                {0, 4, 5, 0},
                {0, 3, 1, 0}
        };

        assertMatrixEquals(expected, matrix);
    }

    @Test
    public void testNoZeroes() {
        SetMatrixZeroes solution = new SetMatrixZeroes();
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };

        solution.setZeroes(matrix);

        int[][] expected = {
                {1, 2},
                {3, 4}
        };

        assertMatrixEquals(expected, matrix);
    }

    @Test
    public void testAllZeroes() {
        SetMatrixZeroes solution = new SetMatrixZeroes();
        int[][] matrix = {
                {0, 0},
                {0, 0}
        };

        solution.setZeroes(matrix);

        int[][] expected = {
                {0, 0},
                {0, 0}
        };

        assertMatrixEquals(expected, matrix);
    }

    @Test
    public void testMultipleZeroesInFirstRowAndColumn() {
        SetMatrixZeroes solution = new SetMatrixZeroes();
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 0, 7, 8},
                {0, 10, 11, 12},
                {13, 14, 15, 0}
        };

        solution.setZeroes(matrix);

        int[][] expected = {
                {0, 0, 3, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertMatrixEquals(expected, matrix);
    }

    private static void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int r = 0; r < expected.length; r++) {
            assertArrayEquals(expected[r], actual[r], "Row mismatch at r=" + r);
        }
    }
}



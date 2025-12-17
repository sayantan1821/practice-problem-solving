package com.practice.leetcode;

/**
 * LeetCode Problem: Set Matrix Zeroes
 * Problem Number: 73
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/set-matrix-zeroes/
 *
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 *
 * LeetCode class structure:
 * class Solution {
 *     public void setZeroes(int[][] matrix) { }
 * }
 *
 * This project uses a per-problem class name, and the exporter can generate a LeetCode-ready
 * Solution class when needed.
 */
public class SetMatrixZeroes {

    /**
     * TODO: Implement in-place matrix zeroing.
     *
     * @param matrix the input matrix to be modified in place
     */
    public void setZeroes(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        
        // Check if first row and first column have zeros
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        
        for (int j = 0; j < m; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        
        // Use first row and first column as markers
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;  // Mark row
                    matrix[0][j] = 0;  // Mark column
                }
            }
        }
        
        // Set zeros based on markers (excluding first row and column)
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Zero out first row if needed
        if (firstRowHasZero) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }
        
        // Zero out first column if needed
        if (firstColHasZero) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}



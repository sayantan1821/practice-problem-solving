package com.practice.common;

/**
 * Common utility methods for array operations
 */
public class ArrayUtils {
    
    /**
     * Prints an array in a readable format
     */
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * Swaps two elements in an array
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Merges the digits of an array into a single long integer
     * @param arr the array of digits
     * @return the merged integer
     */
    public static long mergeDigits(int[] arr) {
        long result = 0;
        for(int i = 0; i < arr.length; i++) {
            result = result * 10 + arr[i];
        }
        return result;
    }

    /**
     * Revers an integer array 
     */
    public static int[] reverseArray(int[] arr) {
        int i = 0, j = arr.length - 1;
        while(i < j) {
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            i++;
            j--;
        }
        return arr;
    }
}


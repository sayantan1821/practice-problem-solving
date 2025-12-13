package com.practice.common;

/**
 * Utility class for debugging in JShell and interactive testing
 */
public class DebugUtils {
    
    /**
     * Debugs the binary reflection calculation for a number
     * Shows step-by-step process
     */
    public static void debugBinaryReflection(int num) {
        System.out.println("=== Debugging Binary Reflection for: " + num + " ===");
        
        // Step 1: Convert to binary
        String binary = Integer.toBinaryString(num);
        System.out.println("Step 1 - Binary representation: " + binary);
        
        // Step 2: Reverse
        String reversed = new StringBuilder(binary).reverse().toString();
        System.out.println("Step 2 - Reversed binary: " + reversed);
        
        // Step 3: Remove leading zeros
        int firstOne = reversed.indexOf('1');
        String withoutLeadingZeros;
        if (firstOne == -1) {
            withoutLeadingZeros = "0";
        } else {
            withoutLeadingZeros = reversed.substring(firstOne);
        }
        System.out.println("Step 3 - Without leading zeros: " + withoutLeadingZeros);
        
        // Step 4: Convert to decimal
        int reflection = Integer.parseInt(withoutLeadingZeros, 2);
        System.out.println("Step 4 - Reflection value: " + reflection);
        System.out.println("---");
    }
    
    /**
     * Debugs the binary reflection for an array of numbers
     */
    public static void debugArrayReflections(int[] nums) {
        System.out.println("=== Debugging Array Reflections ===");
        for (int num : nums) {
            debugBinaryReflection(num);
        }
    }
    
    /**
     * Compares two arrays and shows differences
     */
    public static void compareArrays(int[] expected, int[] actual) {
        System.out.println("=== Comparing Arrays ===");
        System.out.print("Expected: ");
        ArrayUtils.printArray(expected);
        System.out.print("Actual:   ");
        ArrayUtils.printArray(actual);
        
        if (expected.length != actual.length) {
            System.out.println("X Length mismatch! Expected: " + expected.length + ", Actual: " + actual.length);
            return;
        }
        
        boolean match = true;
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                System.out.println("X Mismatch at index " + i + ": Expected " + expected[i] + ", Got " + actual[i]);
                match = false;
            }
        }
        
        if (match) {
            System.out.println("OK Arrays match!");
        }
    }
    
    /**
     * Shows detailed information about an array
     */
    public static void inspectArray(int[] arr, String label) {
        System.out.println("=== Inspecting Array: " + label + " ===");
        System.out.print("Values: ");
        ArrayUtils.printArray(arr);
        System.out.println("Length: " + arr.length);
        System.out.println("---");
    }
}


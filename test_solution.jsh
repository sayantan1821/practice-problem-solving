// Quick test script for SortByReflection
// Run with: jshell --class-path target/classes test_solution.jsh

import com.practice.leetcode.SortByReflection;
import com.practice.common.ArrayUtils;

System.out.println("Testing SortByReflection...");

SortByReflection solution = new SortByReflection();

// Test case 1
int[] nums1 = {5, 3, 10, 2};
System.out.print("Input: ");
ArrayUtils.printArray(nums1);
int[] result1 = solution.sortByReflection(nums1);
System.out.print("Output: ");
ArrayUtils.printArray(result1);

// Test case 2
int[] nums2 = {1, 2, 3, 4, 5};
System.out.print("\nInput: ");
ArrayUtils.printArray(nums2);
int[] result2 = solution.sortByReflection(nums2);
System.out.print("Output: ");
ArrayUtils.printArray(result2);


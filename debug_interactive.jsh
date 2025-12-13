// Interactive debugging script for JShell
// Run with: jshell --class-path target/classes debug_interactive.jsh

import com.practice.leetcode.SortByReflection;
import com.practice.common.DebugUtils;
import com.practice.common.ArrayUtils;
import java.util.Arrays;

System.out.println("=== JShell Interactive Debugging ===");
System.out.println("Use DebugUtils methods to debug your solution:");
System.out.println("  - DebugUtils.debugBinaryReflection(num)");
System.out.println("  - DebugUtils.debugArrayReflections(nums)");
System.out.println("  - DebugUtils.compareArrays(expected, actual)");
System.out.println("  - DebugUtils.inspectArray(arr, \"label\")");
System.out.println();

// Example: Debug binary reflection for a single number
System.out.println("Example 1: Debug binary reflection for 10");
DebugUtils.debugBinaryReflection(10);
System.out.println();

// Example: Debug multiple numbers
System.out.println("Example 2: Debug reflections for test array");
int[] testNums = {5, 3, 10, 2};
DebugUtils.debugArrayReflections(testNums);
System.out.println();

// Example: Test solution
System.out.println("Example 3: Test solution");
SortByReflection solution = new SortByReflection();
int[] result = solution.sortByReflection(testNums);
DebugUtils.inspectArray(result, "Result");
System.out.println();

// Example: Compare with expected
System.out.println("Example 4: Compare with expected output");
int[] expected = {2, 3, 5, 10};
DebugUtils.compareArrays(expected, result);


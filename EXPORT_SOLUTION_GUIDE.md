# Export Solution to Standalone Format

This utility converts your solution classes into standalone, pasteable format for online compilers (LeetCode, CodeChef, etc.) by inlining utility methods and collecting all imports.

## Quick Usage

### Method 1: Command Line

```bash
# Compile first
mvn compile

# Export a solution
java -cp target/classes com.practice.ExportSolution src/main/java/com/practice/leetcode/SortByReflection.java
```

### Method 2: From Java Code

```java
import com.practice.common.SimpleSolutionExporter;

// In your code or JShell
SimpleSolutionExporter.quickExport("src/main/java/com/practice/leetcode/SortByReflection.java");
```

### Method 3: In JShell

```bash
# Start JShell with classpath
jshell --class-path target/classes

# Then:
jshell> import com.practice.common.SimpleSolutionExporter;
jshell> SimpleSolutionExporter.quickExport("src/main/java/com/practice/leetcode/SortByReflection.java");
```

## What It Does

1. **Finds utility method calls** - Detects calls to `ArrayUtils.method()`, `BinaryUtils.method()`, etc.
2. **Inlines utility methods** - Copies the actual method implementations into the solution
3. **Collects imports** - Gathers all necessary imports from solution and utility classes
4. **Removes package references** - Removes `com.practice.*` package references
5. **Creates standalone class** - Outputs a single `Solution` class ready to paste

## Output

The exporter creates a file named `*_Standalone.java` in the same directory as your solution, and also prints it to the console.

## Example

**Input (SortByReflection.java):**
```java
package com.practice.leetcode;
import com.practice.common.ArrayUtils;
import com.practice.common.BinaryUtils;

public class SortByReflection {
    public int[] sortByReflection(int[] nums) {
        for(int num : nums) {
            int[] bits = BinaryUtils.extractBits(num);
            ArrayUtils.reverseArray(bits);
        }
        return nums;
    }
}
```

**Output (SortByReflection_Standalone.java):**
```java
// Standalone solution for online compilers
// Generated from: SortByReflection

import java.util.*;

class Solution {

    // Inlined from BinaryUtils
    private static int[] extractBits(int num) {
        List<Integer> bitList = new ArrayList<>();
        while (num > 0) {
            bitList.add(num & 1);
            num >>= 1;
        }
        Collections.reverse(bitList);
        return bitList.stream().mapToInt(Integer::intValue).toArray();
    }

    // Inlined from ArrayUtils
    private static int[] reverseArray(int[] arr) {
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

    // Solution method
    public int[] sortByReflection(int[] nums) {
        for(int num : nums) {
            int[] bits = extractBits(num);
            reverseArray(bits);
        }
        return nums;
    }
}
```

## Supported Utility Classes

Currently supports inlining from:
- `ArrayUtils` - printArray, swap, mergeDigits, reverseArray
- `BinaryUtils` - extractBits

To add more utility classes, edit `SimpleSolutionExporter.java` and add them to the `UTILITY_CLASSES` map.

## Tips

1. **Test the exported code** - Always test the standalone version before submitting
2. **Remove debug code** - The exporter includes all code, so remove any debug prints
3. **Check method signatures** - Make sure the inlined methods match what online compilers expect
4. **Verify imports** - Some online compilers have restrictions on imports

## Troubleshooting

**Issue: Methods not being inlined**
- Make sure the utility method is listed in `UTILITY_CLASSES` map
- Check that you're calling it as `UtilityClass.methodName()`

**Issue: Import errors**
- The exporter tries to collect all imports, but some might need manual adjustment
- Remove any unused imports from the output

**Issue: Compilation errors in standalone version**
- Check that all utility methods are properly inlined
- Verify method signatures match
- Make sure all required imports are included


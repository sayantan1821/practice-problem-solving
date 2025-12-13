# Debugging with JShell and Test Cases

## Limitations

**JShell itself doesn't support breakpoints/debugging directly**, but there are several workarounds and alternatives.

## Method 1: Manual Debugging in JShell (Print Statements)

You can add print statements and step through your code manually:

```java
jshell> import com.practice.leetcode.SortByReflection;
jshell> SortByReflection s = new SortByReflection();

// Add debug prints in your code, then test
jshell> int[] nums = {5, 3, 10, 2}
jshell> // Your code will print debug info when you call it
jshell> s.sortByReflection(nums)
```

**Modify your solution class temporarily:**
```java
public int[] sortByReflection(int[] nums) {
    System.out.println("DEBUG: Input array: " + Arrays.toString(nums));
    // ... your code with debug prints
    System.out.println("DEBUG: Reflection value: " + reflection);
    return result;
}
```

## Method 2: Use JDB (Java Debugger) with JShell

JDB is Java's command-line debugger. You can attach it to a JShell process:

### Step 1: Start JShell with debugging enabled
```bash
jshell -J-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
```

### Step 2: In another terminal, connect with JDB
```bash
jdb -attach 5005
```

### Step 3: Set breakpoints in JDB
```bash
jdb> stop at com.practice.leetcode.SortByReflection:35
jdb> cont
```

**Note:** This is complex and not very practical for quick debugging.

## Method 3: Run Tests with Debugger (Recommended)

Instead of using JShell, run your tests directly with the debugger attached:

### Using Maven with Debugger

```bash
# Run tests with debugger on port 5005
mvn test -Dmaven.surefire.debug="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```

Then attach your IDE debugger to `localhost:5005`.

### Using IDE Debugger (Best Option)

**In VS Code/Cursor:**
1. Set breakpoints in your code
2. Open the test file
3. Click "Debug Test" above the `@Test` method
4. Or press `F5` to start debugging

**In IntelliJ IDEA:**
1. Set breakpoints
2. Right-click test method → "Debug 'testMethodName'"
3. Or press `Shift+F9`

## Method 4: Interactive Testing with JShell + Manual Verification

Use JShell to test your code interactively, then verify with tests:

```java
jshell> import com.practice.leetcode.SortByReflection;
jshell> import java.util.Arrays;

jshell> SortByReflection s = new SortByReflection();

// Test step by step
jshell> int[] nums = {5, 3, 10, 2}
nums ==> int[4] { 5, 3, 10, 2 }

jshell> // Test binary reflection calculation
jshell> int num = 5
num ==> 5

jshell> String binary = Integer.toBinaryString(num)
binary ==> "101"

jshell> String reversed = new StringBuilder(binary).reverse().toString()
reversed ==> "101"

jshell> int reflection = Integer.parseInt(reversed, 2)
reflection ==> 5

// Now test the full solution
jshell> int[] result = s.sortByReflection(nums)
result ==> int[4] { 2, 3, 5, 10 }

jshell> Arrays.toString(result)
$1 ==> "[2, 3, 5, 10]"
```

## Method 5: Create a Debug Helper Class

Create a helper class that you can use in JShell for debugging:

```java
package com.practice.common;

public class DebugUtils {
    public static void debugReflection(int num) {
        String binary = Integer.toBinaryString(num);
        String reversed = new StringBuilder(binary).reverse().toString();
        int firstOne = reversed.indexOf('1');
        String withoutLeadingZeros = firstOne == -1 ? "0" : reversed.substring(firstOne);
        int reflection = Integer.parseInt(withoutLeadingZeros, 2);
        
        System.out.println("Number: " + num);
        System.out.println("Binary: " + binary);
        System.out.println("Reversed: " + reversed);
        System.out.println("Without leading zeros: " + withoutLeadingZeros);
        System.out.println("Reflection: " + reflection);
        System.out.println("---");
    }
}
```

Then in JShell:
```java
jshell> import com.practice.common.DebugUtils;
jshell> DebugUtils.debugReflection(5)
jshell> DebugUtils.debugReflection(10)
jshell> DebugUtils.debugReflection(2)
```

## Method 6: Use JShell Scripts with Print Statements

Create a JShell script that tests with detailed output:

```java
// debug_test.jsh
import com.practice.leetcode.SortByReflection;
import java.util.Arrays;

void testWithDebug(int[] nums) {
    System.out.println("=== Testing ===");
    System.out.println("Input: " + Arrays.toString(nums));
    
    SortByReflection s = new SortByReflection();
    int[] result = s.sortByReflection(nums);
    
    System.out.println("Output: " + Arrays.toString(result));
    System.out.println();
}

testWithDebug(new int[]{5, 3, 10, 2});
testWithDebug(new int[]{1, 2, 3, 4, 5});
```

Run with:
```bash
jshell --class-path target/classes debug_test.jsh
```

## Recommended Approach

**Best Practice:** Use your IDE's debugger for test cases:

1. **Set breakpoints** in your solution code
2. **Run tests in debug mode** (click "Debug Test" in VS Code)
3. **Step through code** line by line
4. **Inspect variables** in the debug panel

This gives you:
- ✅ Breakpoints
- ✅ Step over/into/out
- ✅ Variable inspection
- ✅ Call stack view
- ✅ Watch expressions

## Quick Setup for VS Code/Cursor Debugging

1. Install "Extension Pack for Java" (if not already installed)
2. Set breakpoints by clicking left of line numbers
3. Open test file
4. Click "Debug Test" above any `@Test` method
5. Use debug controls:
   - `F10` - Step Over
   - `F11` - Step Into
   - `Shift+F11` - Step Out
   - `F5` - Continue

## Summary

| Method | Breakpoints | Ease of Use | Best For |
|--------|-------------|-------------|----------|
| JShell + Prints | ❌ | ⭐⭐⭐⭐⭐ | Quick testing |
| JDB + JShell | ✅ | ⭐⭐ | Advanced debugging |
| IDE Debugger | ✅ | ⭐⭐⭐⭐⭐ | **Recommended** |
| Maven + Debugger | ✅ | ⭐⭐⭐ | CI/CD scenarios |

**Recommendation:** Use JShell for quick interactive testing, but use your IDE's debugger for actual debugging with breakpoints.


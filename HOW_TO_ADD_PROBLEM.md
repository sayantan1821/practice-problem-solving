# How to Add or Update a LeetCode Problem

## Quick Guide

### Step 1: Create Your Solution Class

Create your solution in `src/main/java/com/practice/leetcode/YourClassName.java`

Example:
```java
package com.practice.leetcode;

public class YourClassName {
    public int[] yourMethod(int[] nums) {
        // Your solution
    }
}
```

### Step 2: Register in ProblemRegistry.java

Open `src/main/java/com/practice/leetcode/ProblemRegistry.java` and add your problem in the `static` block:

```java
static {
    // ... existing problems ...
    
    registerProblem("YourClassName", "Problem Name", 
        LocalDate.of(2024, 12, 19), "Easy");  // or "Medium" or "Hard"
}
```

**Parameters:**
- `"YourClassName"` - Your Java class name (without .java)
- `"Problem Name"` - The LeetCode problem name
- `LocalDate.of(2024, 12, 19)` - The date you solved it (year, month, day)
- `"Easy"` - Difficulty: "Easy", "Medium", or "Hard"

### Step 3: Generate PROBLEMS.md

Run the generator script:
```bash
./generate_problems_md.sh
```

This automatically updates `PROBLEMS.md` with your new problem!

## Updating an Existing Problem

### To change the solved date, difficulty, or any other field:

1. Edit `src/main/java/com/practice/leetcode/problems.json`
2. Find your problem in the `problems` array
3. Update the fields you want to change
4. Run `./generate_problems_md.sh` to update PROBLEMS.md

Example - updating a date:
```json
{
  "className": "TwoSum",
  "problemName": "Two Sum",
  "problemNumber": 1,
  "difficulty": "Easy",
  "solvedDate": "2024-12-20",  // Updated date
  "tags": ["Array", "Hash Table"],
  "notes": "Classic hash map approach"
}
```

## Complete Example

Let's say you solved "Longest Substring Without Repeating Characters":

1. **Create the class**: `LongestSubstring.java`
2. **Add to problems.json**:
   ```json
   {
     "className": "LongestSubstring",
     "problemName": "Longest Substring Without Repeating Characters",
     "problemNumber": 3,
     "difficulty": "Medium",
     "solvedDate": "2024-12-19",
     "tags": ["String", "Sliding Window"],
     "notes": "Used sliding window technique"
   }
   ```
3. **Generate the markdown**:
   ```bash
   ./generate_problems_md.sh
   ```

Done! The problem is now tracked in PROBLEMS.md.

## Files Involved

- **`problems.json`** - Source of truth (edit this to add/update problems)
- **`ProblemRegistry.java`** - Automatically loads from problems.json (don't edit manually)
- **`PROBLEMS.md`** - Auto-generated from problems.json (don't edit manually)

## Tips

- Always update `problems.json` first (this is the source of truth)
- Then run `./generate_problems_md.sh` to sync PROBLEMS.md
- Use date format "YYYY-MM-DD" (e.g., "2024-12-19")
- Use `null` for problemNumber if not applicable
- Problems are automatically loaded from JSON when the program runs

## Roadmap / TODO

- Create dedicated packages for standalone LeetCode editor files (e.g. `*_Standalone.java`) so that multiple `class Solution` definitions can coexist without duplicate-class compile errors.


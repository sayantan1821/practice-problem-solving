# LeetCode Problems

This package contains solutions to LeetCode problems.

## Naming Convention
- Use descriptive class names like `TwoSum`, `LongestSubstring`, etc.
- Include problem number and difficulty in class comments

## Problem Registry

All solved problems are tracked in:
- **`problems.json`** - Source of truth (edit this to add/update problems)
- **`ProblemRegistry.java`** - Automatically loads from problems.json
- **`PROBLEMS.md`** - Auto-generated markdown table (run `./generate_problems_md.sh` to update)

### Registering a New Problem

After solving a problem, add it to `problems.json`:

```json
{
  "className": "YourClassName",
  "problemName": "Problem Name",
  "problemNumber": 123,
  "difficulty": "Easy",
  "solvedDate": "2024-12-19",
  "tags": ["Array", "Hash Table"],
  "notes": "Your solution notes"
}
```

The `ProblemRegistry` automatically loads problems from `problems.json` when the program runs.

### Listing Problems

Run the list utility:
```bash
java -cp target/classes com.practice.leetcode.ListProblems
```

## Exporting Solutions

Export solutions to LeetCode-ready format:
```bash
./export_solution.sh src/main/java/com/practice/leetcode/YourClass.java
```

The exported file will be in LeetCode `Solution` class format, ready to paste into the LeetCode editor.


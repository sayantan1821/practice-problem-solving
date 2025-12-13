# Problem Solving Practice

A Java project for practicing problem solving from platforms like LeetCode, CodeChef, HackerRank, etc.

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── practice/
│               ├── leetcode/
│               ├── codechef/
│               ├── hackerrank/
│               ├── common/
│               └── Main.java
└── test/
    └── java/
        └── com/
            └── practice/
                ├── leetcode/
                ├── codechef/
                └── hackerrank/
```

## Organization

Problems are organized by platform:
- **leetcode/**: LeetCode problems
- **codechef/**: CodeChef problems
- **hackerrank/**: HackerRank problems
- **common/**: Common utilities and helper classes

## Running the Project

### Compile
```bash
mvn compile
```

### Run Main Class
```bash
mvn exec:java -Dexec.mainClass="com.practice.Main"
```

### Run Tests
```bash
mvn test
```

### Clean and Build
```bash
mvn clean install
```

## Adding a New Problem

1. Create a new class in the appropriate platform package (e.g., `leetcode/TwoSum.java`)
2. Implement the solution
3. Add a test class in the corresponding test package
4. Document the problem description and approach in comments

## Example Problem Structure

```java
package com.practice.leetcode;

/**
 * LeetCode Problem: Two Sum
 * Problem Number: 1
 * Difficulty: Easy
 * 
 * Problem: Given an array of integers nums and an integer target, 
 * return indices of the two numbers such that they add up to target.
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // Your solution here
    }
}
```

## Requirements

- Java 17 or higher
- Maven 3.6 or higher



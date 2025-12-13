#!/bin/bash
# Generate PROBLEMS.md from ProblemRegistry.java

echo "Compiling..."
./compile.sh > /dev/null 2>&1

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo "Generating PROBLEMS.md..."
java -cp target/classes com.practice.leetcode.GenerateProblemsMD

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ PROBLEMS.md has been updated!"
    echo "Review the file: src/main/java/com/practice/leetcode/PROBLEMS.md"
else
    echo "Failed to generate PROBLEMS.md"
    exit 1
fi


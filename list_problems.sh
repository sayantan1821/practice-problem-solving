#!/bin/bash
# List all LeetCode problems solved

echo "Compiling..."
./compile.sh > /dev/null 2>&1

if [ $? -eq 0 ]; then
    echo ""
    java -cp target/classes com.practice.leetcode.ListProblems
else
    echo "Compilation failed!"
    exit 1
fi


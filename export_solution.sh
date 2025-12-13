#!/bin/bash
# Export solution to standalone format
# Git Bash compatible
# Usage: ./export_solution.sh <solution-file-path>

if [ -z "$1" ]; then
    echo "Usage: ./export_solution.sh <solution-file-path>"
    echo ""
    echo "Example:"
    echo "  ./export_solution.sh src/main/java/com/practice/leetcode/SortByReflection.java"
    exit 1
fi

echo "Compiling project..."
./compile.sh

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo "Exporting solution..."
java -cp target/classes com.practice.ExportSolution "$1"

if [ $? -ne 0 ]; then
    echo "Export failed!"
    exit 1
fi

echo ""
echo "Done!"

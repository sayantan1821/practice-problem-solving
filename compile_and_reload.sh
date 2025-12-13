#!/bin/bash
# Compile and provide JShell reload instructions

echo "Compiling project..."
mvn compile -q

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo ""
echo "✅ Compilation successful!"
echo ""
echo "In JShell, run:"
echo "  /reset"
echo "  import com.practice.leetcode.SortByReflection;"
echo ""
echo "Or use: /open reload.jsh"


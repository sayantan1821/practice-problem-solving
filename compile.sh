#!/bin/bash
# Compile Java project without Maven
# Git Bash compatible

echo "Compiling Java project..."

# Create output directory
mkdir -p target/classes

# Find all Java files (exclude standalone files)
JAVA_FILES=$(find src/main/java -name "*.java" -type f ! -name "*_Standalone.java")

if [ -z "$JAVA_FILES" ]; then
    echo "No Java files found!"
    exit 1
fi

# Compile all files
javac -d target/classes -sourcepath src/main/java $JAVA_FILES

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo ""
echo "✅ Compilation successful!"
echo "Classes are in: target/classes"

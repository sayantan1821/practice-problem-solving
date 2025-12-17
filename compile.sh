#!/bin/bash
# Compile Java project without Maven
# Git Bash compatible

echo "Compiling Java project..."

# Try to find javac
JAVAC_CMD="javac"

# Check if javac is available
if ! command -v javac &> /dev/null; then
    # Try to auto-detect from java command
    JAVA_CMD=$(command -v java 2>/dev/null || which java 2>/dev/null)
    if [ -n "$JAVA_CMD" ] && [ -f "$JAVA_CMD" ]; then
        # Extract JDK home from java path (remove /bin/java or /bin/java.exe)
        JAVA_BIN_DIR=$(dirname "$JAVA_CMD")
        DETECTED_JAVA_HOME=$(dirname "$JAVA_BIN_DIR")
        
        # Check if javac exists in the same directory as java
        if [ -f "$JAVA_BIN_DIR/javac.exe" ]; then
            JAVAC_CMD="$JAVA_BIN_DIR/javac.exe"
        elif [ -f "$JAVA_BIN_DIR/javac" ]; then
            JAVAC_CMD="$JAVA_BIN_DIR/javac"
        fi
    fi
    
    # Try to use JAVA_HOME if set and javac still not found
    if [ ! -f "$JAVAC_CMD" ] && [ -n "$JAVA_HOME" ]; then
        # Try both .exe and no extension (for Windows and Unix-like)
        if [ -f "$JAVA_HOME/bin/javac.exe" ]; then
            JAVAC_CMD="$JAVA_HOME/bin/javac.exe"
        elif [ -f "$JAVA_HOME/bin/javac" ]; then
            JAVAC_CMD="$JAVA_HOME/bin/javac"
        fi
    fi
    
    # Final check
    if [ ! -f "$JAVAC_CMD" ] && ! command -v "$JAVAC_CMD" &> /dev/null; then
        echo "Error: javac not found!"
        echo ""
        echo "Found java at: $JAVA_CMD"
        if [ -n "$DETECTED_JAVA_HOME" ]; then
            echo "Detected JAVA_HOME: $DETECTED_JAVA_HOME"
        fi
        echo ""
        echo "Please ensure Java JDK is installed and either:"
        echo "  1. Add Java bin directory to your PATH, or"
        echo "  2. Set JAVA_HOME environment variable"
        echo ""
        echo "Example (in Git Bash):"
        if [ -n "$DETECTED_JAVA_HOME" ]; then
            echo "  export JAVA_HOME=\"$DETECTED_JAVA_HOME\""
        else
            echo "  export JAVA_HOME=\"/c/Program Files/Java/jdk-17\""
        fi
        echo "  export PATH=\$JAVA_HOME/bin:\$PATH"
        exit 1
    fi
fi

# Create output directory
mkdir -p target/classes

# Find all Java files (exclude standalone files)
JAVA_FILES=$(find src/main/java -name "*.java" -type f ! -name "*_Standalone.java")

if [ -z "$JAVA_FILES" ]; then
    echo "No Java files found!"
    exit 1
fi

# Compile all files
$JAVAC_CMD -d target/classes -sourcepath src/main/java $JAVA_FILES

if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo ""
echo "✅ Compilation successful!"
echo "Classes are in: target/classes"

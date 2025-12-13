# Quick Start Guide (Without Maven)

## Compile Your Project

### Windows:
```bash
compile.bat
```

### Linux/Mac:
```bash
chmod +x compile.sh
./compile.sh
```

## Export Solution

### Windows:
```bash
export_solution.bat src\main\java\com\practice\leetcode\SortByReflection.java
```

### Linux/Mac:
```bash
./export_solution.sh src/main/java/com/practice/leetcode/SortByReflection.java
```

## Run Tests

You'll need JUnit on the classpath. For now, you can:
1. Install Maven (see INSTALL_MAVEN.md)
2. Or use VS Code's test runner (if Java Extension Pack is installed)

## Use JShell

```bash
# Compile first
compile.bat

# Start JShell
jshell --class-path target/classes

# Then in JShell:
jshell> import com.practice.leetcode.SortByReflection;
jshell> SortByReflection s = new SortByReflection();
```

## Install Maven (Optional)

If you want full Maven features (dependency management, testing, etc.):
- See `INSTALL_MAVEN.md` for instructions
- Or use Chocolatey: `choco install maven`


# How to Run Commands

## PowerShell (Windows Terminal/PowerShell)

### Compile:
```powershell
.\compile.ps1
```

### Export Solution:
```powershell
.\export_solution.ps1 src\main\java\com\practice\leetcode\SortByReflection.java
```

## Git Bash / Command Prompt

### Compile:
```bash
compile.bat
```

### Export Solution:
```bash
export_solution.bat src\main\java\com\practice\leetcode\SortByReflection.java
```

## Manual Compilation (Any Shell)

```bash
# Create output directory
mkdir -p target/classes

# Compile (Windows)
javac -d target/classes -sourcepath src/main/java src/main/java/com/practice/**/*.java

# Compile (Linux/Mac/Git Bash)
javac -d target/classes -sourcepath src/main/java $(find src/main/java -name "*.java")
```

## Export (After Compilation)

```bash
java -cp target/classes com.practice.ExportSolution src/main/java/com/practice/leetcode/SortByReflection.java
```

## Troubleshooting

### PowerShell Execution Policy Error

If you get "execution of scripts is disabled", run:
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### Permission Denied (Git Bash)

Make scripts executable:
```bash
chmod +x compile.sh export_solution.sh
```


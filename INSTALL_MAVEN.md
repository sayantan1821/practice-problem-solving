# Installing Maven on Windows

## Option 1: Install Maven (Recommended for full Maven features)

### Using Chocolatey (Easiest)
```powershell
# Install Chocolatey first if you don't have it
# Then:
choco install maven
```

### Manual Installation
1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract to `C:\Program Files\Apache\maven`
3. Add to PATH:
   - Open System Properties → Environment Variables
   - Add `C:\Program Files\Apache\maven\bin` to PATH
4. Restart terminal/PowerShell
5. Verify: `mvn --version`

## Option 2: Use Maven Wrapper (No installation needed)

If you have Maven Wrapper (`mvnw` or `mvnw.cmd`), use that instead:
```bash
# Windows
.\mvnw.cmd compile

# Linux/Mac
./mvnw compile
```

## Option 3: Compile Without Maven (Current Setup)

You can compile directly using `javac`:

### Windows:
```bash
compile.bat
```

### Linux/Mac:
```bash
chmod +x compile.sh
./compile.sh
```

### Manual javac:
```bash
# Create output directory
mkdir -p target/classes

# Compile
javac -d target/classes -sourcepath src/main/java src/main/java/com/practice/**/*.java
```

## Option 4: Use VS Code Java Extension

The Java Extension Pack for VS Code can handle compilation automatically:
1. Install "Extension Pack for Java" in VS Code
2. Open your Java file
3. VS Code will auto-compile when you save

## Quick Test

After installing Maven or using compile script:
```bash
# With Maven:
mvn compile

# Without Maven:
compile.bat  # or compile.sh
```

Then export solution:
```bash
java -cp target/classes com.practice.ExportSolution src/main/java/com/practice/leetcode/SortByReflection.java
```


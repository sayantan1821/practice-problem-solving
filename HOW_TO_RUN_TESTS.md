# How to Run Test Cases

## Method 1: Using Maven Command Line

### Run All Tests
```bash
mvn test
```

### Run a Specific Test Class
```bash
mvn test -Dtest=SortByReflectionTest
```

### Run a Specific Test Method
```bash
mvn test -Dtest=SortByReflectionTest#testExample1
```

### Run Tests with Verbose Output
```bash
mvn test -X
```

## Method 2: Using VS Code / Cursor Extensions

### Required Extensions

1. **Extension Pack for Java** (by Microsoft)
   - Includes: Language Support for Java, Debugger for Java, Test Runner for Java, Maven for Java, etc.
   - Extension ID: `vscjava.vscode-java-pack`

2. **Test Runner for Java** (included in the pack above)
   - Provides run/debug buttons above test methods
   - Extension ID: `vscjava.vscode-java-test`

### How to Install Extensions

1. Open VS Code/Cursor
2. Press `Ctrl+Shift+X` to open Extensions
3. Search for "Extension Pack for Java"
4. Click Install

### Running Tests in VS Code/Cursor

After installing the extensions:

1. **Run Individual Test Method:**
   - Open the test file
   - You'll see "Run Test" links above each `@Test` method
   - Click the link to run that specific test

2. **Run All Tests in a Class:**
   - Click "Run Tests" link above the class declaration
   - Or right-click on the test class name and select "Run Tests"

3. **Run All Tests:**
   - Open Command Palette (`Ctrl+Shift+P`)
   - Type "Java: Run All Tests"
   - Press Enter

4. **Debug Tests:**
   - Click "Debug Test" link above test methods
   - Or set breakpoints and use the debugger

## Method 3: Using IntelliJ IDEA (if you switch)

1. Right-click on the test class or method
2. Select "Run 'TestName'"
3. Or use `Ctrl+Shift+F10` (Windows/Linux) or `Ctrl+R` (Mac)

## Troubleshooting

### If tests don't run:
1. Make sure Maven is installed: `mvn --version`
2. Make sure Java 17+ is installed: `java -version`
3. Reload the Java project: `Ctrl+Shift+P` → "Java: Reload Projects"

### If extensions don't work:
1. Make sure the Java Extension Pack is installed
2. Reload the window: `Ctrl+Shift+P` → "Developer: Reload Window"
3. Check the Output panel for Java errors



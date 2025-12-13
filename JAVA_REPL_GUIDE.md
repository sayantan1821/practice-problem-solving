# Java Interactive Console / REPL Guide

## JShell - Java's Built-in REPL (Java 9+)

Java includes **JShell** (Java Shell), an interactive REPL similar to Ruby's `irb` or Python's interactive shell.

### Starting JShell

```bash
jshell
```

### Basic Usage

```java
// Simple expressions
jshell> 2 + 2
$1 ==> 4

jshell> "Hello" + " World"
$2 ==> "Hello World"

// Variables
jshell> int x = 10
x ==> 10

jshell> int y = 20
y ==> 20

jshell> x + y
$3 ==> 30

// Methods
jshell> int add(int a, int b) { return a + b; }
|  created method add(int,int)

jshell> add(5, 3)
$4 ==> 8

// Classes
jshell> class Person {
   ...>     String name;
   ...>     Person(String n) { name = n; }
   ...>     void greet() { System.out.println("Hello, " + name); }
   ...> }
|  created class Person

jshell> Person p = new Person("Alice")
p ==> Person@2f92e0f4

jshell> p.greet()
Hello, Alice

// Import statements
jshell> import java.util.*

jshell> List<String> list = new ArrayList<>()
list ==> []

jshell> list.add("Java")
$5 ==> true

jshell> list
list ==> [Java]
```

### Useful JShell Commands

```bash
# List all variables, methods, and classes
/list

# List all variables
/vars

# List all methods
/methods

# List all classes
/types

# View/edit a snippet
/edit <snippet-id>

# Save your session
/save <filename>

# Load a file
/open <filename>

# Reset JShell
/reset

# Exit JShell
/exit
```

### Running Java Files in JShell

```bash
# Load a Java file
/open SortByReflection.java

# Or use /open to load multiple files
```

### JShell with Your Project

You can use JShell to test your solutions interactively:

```bash
# Start JShell with classpath
jshell --class-path target/classes

# Then you can use your classes
jshell> import com.practice.leetcode.SortByReflection
jshell> SortByReflection s = new SortByReflection()
jshell> int[] nums = {5, 3, 10, 2}
jshell> s.sortByReflection(nums)
```

## VS Code / Cursor Extensions for Interactive Java

### 1. Java Interactive Console (Extension)

**Extension:** "Java Interactive Console" or similar
- Search in Extensions: `Ctrl+Shift+X`
- Look for Java REPL or Interactive Console extensions

### 2. Code Runner Extension

**Extension:** Code Runner (by Jun Han)
- Extension ID: `formulahendry.code-runner`
- Allows running Java code snippets
- Not a full REPL, but useful for quick testing

### 3. Using JShell in VS Code Terminal

1. Open integrated terminal: `` Ctrl+` ``
2. Type `jshell`
3. Start coding interactively

## Alternative: Online Java REPLs

If you want a web-based solution:

1. **Repl.it** - https://replit.com (supports Java)
2. **OnlineGDB** - https://www.onlinegdb.com/online_java_compiler
3. **Programiz** - https://www.programiz.com/java-programming/online-compiler/

## Example: Testing Your Solution in JShell

```bash
# 1. Compile your project first
mvn compile

# 2. Start JShell with your project's classpath
jshell --class-path target/classes

# 3. In JShell:
jshell> import com.practice.leetcode.SortByReflection
jshell> import com.practice.common.ArrayUtils

jshell> SortByReflection solution = new SortByReflection()
solution ==> SortByReflection@2f92e0f4

jshell> int[] test = {5, 3, 10, 2}
test ==> int[4] { 5, 3, 10, 2 }

jshell> int[] result = solution.sortByReflection(test)
result ==> int[4] { 2, 3, 5, 10 }

jshell> ArrayUtils.printArray(result)
[2, 3, 5, 10]
```

## Creating a JShell Script

You can create a `.jsh` file with JShell commands:

```java
// test.jsh
import com.practice.leetcode.SortByReflection;
import com.practice.common.ArrayUtils;

SortByReflection s = new SortByReflection();
int[] nums = {5, 3, 10, 2};
int[] result = s.sortByReflection(nums);
ArrayUtils.printArray(result);
```

Run it with:
```bash
jshell test.jsh
```

## Tips

1. **Tab completion** works in JShell
2. Use `/help` to see all commands
3. Use `/save` to save your session
4. Use `/open` to load files or scripts
5. JShell automatically imports `java.lang.*` and `java.util.*`


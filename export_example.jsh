// Example: Export solution to standalone format
// Run with: jshell --class-path target/classes export_example.jsh

import com.practice.common.SimpleSolutionExporter;

System.out.println("=== Exporting Solution to Standalone Format ===\n");

// Export SortByReflection solution
String solutionPath = "src/main/java/com/practice/leetcode/SortByReflection.java";

try {
    SimpleSolutionExporter.quickExport(solutionPath);
    System.out.println("\n✅ Export complete!");
    System.out.println("Check the generated *_Standalone.java file");
} catch (Exception e) {
    System.out.println("❌ Error: " + e.getMessage());
    e.printStackTrace();
}

System.out.println("\n=== Usage Tips ===");
System.out.println("1. The standalone file is ready to paste into online compilers");
System.out.println("2. All utility methods are inlined");
System.out.println("3. All imports are collected");
System.out.println("4. Package references are removed");


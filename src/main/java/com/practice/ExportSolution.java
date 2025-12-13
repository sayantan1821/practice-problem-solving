package com.practice;

import com.practice.common.SimpleSolutionExporter;
import java.io.IOException;

/**
 * Utility to export solutions to standalone format for online compilers
 * 
 * Usage:
 *   java ExportSolution <solution-file-path>
 * 
 * Example:
 *   java ExportSolution src/main/java/com/practice/leetcode/SortByReflection.java
 */
public class ExportSolution {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java ExportSolution <solution-file-path>");
            System.out.println("\nExample:");
            System.out.println("  java ExportSolution src/main/java/com/practice/leetcode/SortByReflection.java");
            System.out.println("\nOr use the quick export method in your code:");
            System.out.println("  SimpleSolutionExporter.quickExport(\"path/to/Solution.java\");");
            return;
        }
        
        String solutionPath = args[0];
        
        try {
            SimpleSolutionExporter.quickExport(solutionPath);
        } catch (IOException e) {
            System.err.println("Error exporting solution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


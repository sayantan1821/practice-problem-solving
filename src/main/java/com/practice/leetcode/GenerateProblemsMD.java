package com.practice.leetcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility to automatically generate PROBLEMS.md from ProblemRegistry
 * Run this after updating ProblemRegistry.java
 */
public class GenerateProblemsMD {
    
    public static void main(String[] args) {
        try {
            String markdown = generateMarkdown();
            String filePath = "src/main/java/com/practice/leetcode/PROBLEMS.md";
            Files.write(Paths.get(filePath), markdown.getBytes());
            System.out.println("✅ PROBLEMS.md generated successfully!");
            System.out.println("File: " + filePath);
        } catch (IOException e) {
            System.err.println("Error generating PROBLEMS.md: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static String generateMarkdown() {
        StringBuilder md = new StringBuilder();
        
        md.append("# LeetCode Problems Solved\n\n");
        md.append("This file is **automatically generated** from `problems.json`.\n");
        md.append("To update this file, edit `problems.json` and run:\n");
        md.append("```bash\n");
        md.append("./generate_problems_md.sh\n");
        md.append("```\n\n");
        
        md.append("## Problems\n\n");
        
        List<ProblemRegistry.ProblemInfo> problems = ProblemRegistry.getAllProblems();
        
        if (problems.isEmpty()) {
            md.append("No problems registered yet.\n");
            return md.toString();
        }
        
        // Table header
        md.append("| # | Problem Name | Difficulty | Solved Date | Class Name |\n");
        md.append("|---|--------------|------------|-------------|------------|\n");
        
        // Table rows
        int problemNumber = 1;
        for (ProblemRegistry.ProblemInfo problem : problems) {
            // Try to get problem number from class name or use sequential number
            String num = getProblemNumber(problem.getClassName(), problemNumber);
            
            md.append(String.format("| %s | %s | %s | %s | `%s` |\n",
                num,
                problem.getProblemName(),
                problem.getDifficulty(),
                problem.getSolvedDate(),
                problem.getClassName()
            ));
            problemNumber++;
        }
        
        md.append("\n");
        md.append("## How to Add a New Problem\n\n");
        md.append("1. Create your solution class in `src/main/java/com/practice/leetcode/`\n");
        md.append("2. Add to `problems.json`:\n");
        md.append("   ```json\n");
        md.append("   {\n");
        md.append("     \"className\": \"YourClassName\",\n");
        md.append("     \"problemName\": \"Problem Name\",\n");
        md.append("     \"problemNumber\": \"LC_123\",\n");
        md.append("     \"difficulty\": \"Easy\",\n");
        md.append("     \"solvedDate\": \"2024-12-19\",\n");
        md.append("     \"tags\": [\"Array\"],\n");
        md.append("     \"notes\": \"Your notes\"\n");
        md.append("   }\n");
        md.append("   ```\n");
        md.append("3. Run `./generate_problems_md.sh` to update this file\n\n");
        
        // Statistics
        long easy = problems.stream().filter(p -> p.getDifficulty().equals("Easy")).count();
        long medium = problems.stream().filter(p -> p.getDifficulty().equals("Medium")).count();
        long hard = problems.stream().filter(p -> p.getDifficulty().equals("Hard")).count();
        
        md.append("## Statistics\n\n");
        md.append(String.format("- **Total Problems**: %d\n", problems.size()));
        md.append(String.format("- **Easy**: %d\n", easy));
        md.append(String.format("- **Medium**: %d\n", medium));
        md.append(String.format("- **Hard**: %d\n", hard));
        md.append("\n");
        
        return md.toString();
    }
    
    /**
     * Try to extract problem number from class name (e.g., "TwoSum" -> "1")
     * Otherwise use sequential number
     */
    private static String getProblemNumber(String className, int sequential) {
        // You can add mappings here if class names don't match problem numbers
        // For now, return sequential number
        return String.valueOf(sequential);
    }
}


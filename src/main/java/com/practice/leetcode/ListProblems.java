package com.practice.leetcode;

import java.util.List;

/**
 * Utility to list all solved LeetCode problems
 */
public class ListProblems {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("LeetCode Problems Solved");
        System.out.println("=".repeat(80));
        System.out.println();
        
        List<ProblemRegistry.ProblemInfo> problems = ProblemRegistry.getAllProblems();
        
        if (problems.isEmpty()) {
            System.out.println("No problems registered yet.");
            return;
        }
        
        System.out.printf("%-25s %-40s %-10s %-12s%n", 
            "Class Name", "Problem Name", "Difficulty", "Solved Date");
        System.out.println("-".repeat(80));
        
        for (ProblemRegistry.ProblemInfo problem : problems) {
            System.out.printf("%-25s %-40s %-10s %-12s%n",
                problem.getClassName(),
                problem.getProblemName(),
                problem.getDifficulty(),
                problem.getSolvedDate());
        }
        
        System.out.println();
        System.out.println("Total: " + problems.size() + " problems");
        
        // Statistics
        long easy = problems.stream().filter(p -> p.getDifficulty().equals("Easy")).count();
        long medium = problems.stream().filter(p -> p.getDifficulty().equals("Medium")).count();
        long hard = problems.stream().filter(p -> p.getDifficulty().equals("Hard")).count();
        
        System.out.println();
        System.out.println("By Difficulty:");
        System.out.println("  Easy: " + easy);
        System.out.println("  Medium: " + medium);
        System.out.println("  Hard: " + hard);
    }
}


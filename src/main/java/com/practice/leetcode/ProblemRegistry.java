package com.practice.leetcode;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Registry to track LeetCode problems with solution dates
 * Loads problems from problems.json file
 */
public class ProblemRegistry {
    
    private static final Map<String, ProblemInfo> problems = new LinkedHashMap<>();
    
    static {
        // Load problems from problems.json
        try {
            loadFromJson();
        } catch (Exception e) {
            System.err.println("Warning: Failed to load problems from problems.json: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Load problems from problems.json file
     */
    private static void loadFromJson() throws IOException {
        List<ProblemJsonLoader.ProblemData> problemDataList = ProblemJsonLoader.loadProblems();
        
        for (ProblemJsonLoader.ProblemData data : problemDataList) {
            registerProblem(
                data.getClassName(),
                data.getProblemName(),
                data.getSolvedDate(),
                data.getDifficulty()
            );
        }
    }
    
    /**
     * Register a problem
     */
    public static void registerProblem(String className, String problemName, 
                                      LocalDate solvedDate, String difficulty) {
        problems.put(className, new ProblemInfo(className, problemName, solvedDate, difficulty));
    }
    
    /**
     * Get problem info
     */
    public static ProblemInfo getProblem(String className) {
        return problems.get(className);
    }
    
    /**
     * Get all problems sorted by date
     */
    public static List<ProblemInfo> getAllProblems() {
        List<ProblemInfo> list = new ArrayList<>(problems.values());
        list.sort(Comparator.comparing(ProblemInfo::getSolvedDate).reversed());
        return list;
    }
    
    /**
     * Get problems by difficulty
     */
    public static List<ProblemInfo> getProblemsByDifficulty(String difficulty) {
        return problems.values().stream()
            .filter(p -> p.getDifficulty().equalsIgnoreCase(difficulty))
            .sorted(Comparator.comparing(ProblemInfo::getSolvedDate).reversed())
            .toList();
    }
    
    /**
     * Problem information
     */
    public static class ProblemInfo {
        private final String className;
        private final String problemName;
        private final LocalDate solvedDate;
        private final String difficulty;
        
        public ProblemInfo(String className, String problemName, 
                          LocalDate solvedDate, String difficulty) {
            this.className = className;
            this.problemName = problemName;
            this.solvedDate = solvedDate;
            this.difficulty = difficulty;
        }
        
        public String getClassName() { return className; }
        public String getProblemName() { return problemName; }
        public LocalDate getSolvedDate() { return solvedDate; }
        public String getDifficulty() { return difficulty; }
        
        @Override
        public String toString() {
            return String.format("%s - %s (%s) - Solved: %s", 
                className, problemName, difficulty, solvedDate);
        }
    }
}


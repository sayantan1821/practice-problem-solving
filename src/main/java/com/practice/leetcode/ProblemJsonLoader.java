package com.practice.leetcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple JSON loader for problems.json
 * Loads problem data from JSON file
 */
public class ProblemJsonLoader {
    
    private static final String JSON_FILE = "src/main/java/com/practice/leetcode/problems.json";
    
    /**
     * Load problems from JSON file
     */
    public static List<ProblemData> loadProblems() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(JSON_FILE)));
        return parseProblems(jsonContent);
    }
    
    /**
     * Simple JSON parser for problems array
     */
    private static List<ProblemData> parseProblems(String json) {
        List<ProblemData> problems = new ArrayList<>();
        
        // Pattern to match each problem object
        Pattern problemPattern = Pattern.compile(
            "\\{\\s*" +
            "\"className\"\\s*:\\s*\"([^\"]+)\"\\s*," +
            "\\s*\"problemName\"\\s*:\\s*\"([^\"]+)\"\\s*," +
            "\\s*\"problemNumber\"\\s*:\\s*(\\d+|null)\\s*," +
            "\\s*\"difficulty\"\\s*:\\s*\"([^\"]+)\"\\s*," +
            "\\s*\"solvedDate\"\\s*:\\s*\"([^\"]+)\"\\s*," +
            "\\s*\"tags\"\\s*:\\s*\\[([^\\]]+)\\]\\s*," +
            "\\s*\"notes\"\\s*:\\s*\"([^\"]+)\"\\s*" +
            "\\}", 
            Pattern.DOTALL
        );
        
        Matcher matcher = problemPattern.matcher(json);
        
        while (matcher.find()) {
            String className = matcher.group(1);
            String problemName = matcher.group(2);
            String problemNumber = matcher.group(3);
            String difficulty = matcher.group(4);
            String solvedDate = matcher.group(5);
            String tags = matcher.group(6);
            String notes = matcher.group(7);
            
            // Parse date
            LocalDate date = LocalDate.parse(solvedDate, DateTimeFormatter.ISO_DATE);
            
            // Parse tags
            List<String> tagList = parseTags(tags);
            
            problems.add(new ProblemData(
                className,
                problemName,
                "null".equals(problemNumber) ? null : Integer.parseInt(problemNumber),
                difficulty,
                date,
                tagList,
                notes
            ));
        }
        
        return problems;
    }
    
    /**
     * Parse tags array from JSON
     */
    private static List<String> parseTags(String tagsJson) {
        List<String> tags = new ArrayList<>();
        if (tagsJson == null || tagsJson.trim().isEmpty()) {
            return tags;
        }
        
        // Remove quotes and split by comma
        Pattern tagPattern = Pattern.compile("\"([^\"]+)\"");
        Matcher matcher = tagPattern.matcher(tagsJson);
        while (matcher.find()) {
            tags.add(matcher.group(1));
        }
        
        return tags;
    }
    
    /**
     * Problem data from JSON
     */
    public static class ProblemData {
        private final String className;
        private final String problemName;
        private final Integer problemNumber;
        private final String difficulty;
        private final LocalDate solvedDate;
        private final List<String> tags;
        private final String notes;
        
        public ProblemData(String className, String problemName, Integer problemNumber,
                          String difficulty, LocalDate solvedDate, List<String> tags, String notes) {
            this.className = className;
            this.problemName = problemName;
            this.problemNumber = problemNumber;
            this.difficulty = difficulty;
            this.solvedDate = solvedDate;
            this.tags = tags;
            this.notes = notes;
        }
        
        public String getClassName() { return className; }
        public String getProblemName() { return problemName; }
        public Integer getProblemNumber() { return problemNumber; }
        public String getDifficulty() { return difficulty; }
        public LocalDate getSolvedDate() { return solvedDate; }
        public List<String> getTags() { return tags; }
        public String getNotes() { return notes; }
    }
}


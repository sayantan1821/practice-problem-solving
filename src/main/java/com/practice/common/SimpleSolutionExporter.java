package com.practice.common;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/**
 * Simple utility to convert solution classes into standalone format for online compilers.
 * Inlines utility methods and collects all imports.
 */
public class SimpleSolutionExporter {
    
    private static final Map<String, ClassInfo> UTILITY_CLASSES = new HashMap<>();
    
    static {
        // Define utility classes and their methods
        UTILITY_CLASSES.put("ArrayUtils", new ClassInfo(
            "src/main/java/com/practice/common/ArrayUtils.java",
            Arrays.asList("printArray", "swap", "mergeDigits", "reverseArray")
        ));
        UTILITY_CLASSES.put("BinaryUtils", new ClassInfo(
            "src/main/java/com/practice/common/BinaryUtils.java",
            Arrays.asList("extractBits", "convertToDecimal")
        ));
    }
    
    /**
     * Exports solution to standalone format
     */
    public static String export(String solutionFilePath) throws IOException {
        String solution = readFile(solutionFilePath);
        return convertToStandalone(solution);
    }
    
    /**
     * Converts solution to standalone format
     */
    private static String convertToStandalone(String solution) throws IOException {
        StringBuilder output = new StringBuilder();
        
        // Remove comments from solution
        solution = removeComments(solution);
        
        // Extract information
        Set<String> imports = extractImports(solution);
        String className = extractClassName(solution);
        String classContent = extractClassContent(solution);
        
        // Find which utility classes are used
        Set<String> usedUtils = findUsedUtilityClasses(classContent);
        
        // Collect all imports
        Set<String> allImports = new LinkedHashSet<>(imports);
        for (String util : usedUtils) {
            ClassInfo info = UTILITY_CLASSES.get(util);
            if (info != null) {
                String utilContent = readFile(info.filePath);
                allImports.addAll(extractImports(utilContent));
            }
        }
        
        // Remove common package imports
        allImports.removeIf(imp -> imp.contains("com.practice.common"));
        
        // Build output - LeetCode format
        output.append("// LeetCode Solution\n");
        output.append("// Standalone format ready to paste into LeetCode editor\n\n");
        
        // Add imports
        for (String imp : allImports) {
            output.append(imp).append("\n");
        }
        output.append("\n");
        
        // Add class
        output.append("class Solution {\n\n");
        
        // Add inlined utility methods
        for (String util : usedUtils) {
            ClassInfo info = UTILITY_CLASSES.get(util);
            if (info != null) {
                String utilContent = readFile(info.filePath);
                utilContent = removeComments(utilContent);
                String inlined = inlineUtilityMethods(utilContent, classContent, util);
                if (!inlined.isEmpty()) {
                    output.append(inlined).append("\n");
                }
            }
        }
        
        // Add solution method (replace utility class calls)
        String solutionMethod = replaceUtilityCalls(classContent, usedUtils);
        // Normalize and indent solution method
        solutionMethod = normalizeIndentation(solutionMethod);
        output.append(indent(solutionMethod, 4));
        
        output.append("}\n");
        
        return output.toString();
    }
    
    /**
     * Inlines utility methods that are actually used
     */
    private static String inlineUtilityMethods(String utilContent, String solutionContent, String utilClass) {
        StringBuilder result = new StringBuilder();
        ClassInfo info = UTILITY_CLASSES.get(utilClass);
        if (info == null) return "";
        
        // Extract method bodies
        for (String methodName : info.methods) {
            if (solutionContent.contains(utilClass + "." + methodName + "(")) {
                String method = extractMethod(utilContent, methodName);
                if (!method.isEmpty()) {
                    // Make it private static
                    method = method.replace("public static", "private static");
                    // Normalize indentation (remove base indent, preserve relative)
                    method = normalizeIndentation(method);
                    // Method signature should be at 4 spaces, body at 8 spaces
                    // After normalization: signature at 0, body should be at 4 (relative)
                    // Then indent(4) makes: signature at 4, body at 8
                    result.append(indent(method, 4)).append("\n\n");
                }
            }
        }
        
        return result.toString();
    }
    
    /**
     * Extracts a method from class content using brace matching
     */
    private static String extractMethod(String classContent, String methodName) {
        // Find method signature
        Pattern sigPattern = Pattern.compile(
            "public\\s+static\\s+[^\\{]*" + methodName + "\\s*\\([^\\)]*\\)\\s*\\{",
            Pattern.DOTALL
        );
        Matcher sigMatcher = sigPattern.matcher(classContent);
        
        if (sigMatcher.find()) {
            int start = sigMatcher.start();
            int braceStart = sigMatcher.end() - 1; // Position of opening brace
            
            // Find matching closing brace
            int braceCount = 0;
            int end = braceStart;
            
            for (int i = braceStart; i < classContent.length(); i++) {
                char c = classContent.charAt(i);
                if (c == '{') {
                    braceCount++;
                } else if (c == '}') {
                    braceCount--;
                    if (braceCount == 0) {
                        end = i + 1;
                        break;
                    }
                }
            }
            
            if (end > start) {
                String extracted = classContent.substring(start, end);
                // Trim leading/trailing whitespace but preserve internal structure
                extracted = extracted.trim();
                // Ensure it starts with the method signature (no leading newlines)
                if (extracted.startsWith("\n")) {
                    extracted = extracted.substring(1);
                }
                return extracted;
            }
        }
        
        return "";
    }
    
    /**
     * Replaces utility class method calls with direct calls
     */
    private static String replaceUtilityCalls(String content, Set<String> usedUtils) {
        String result = content;
        for (String util : usedUtils) {
            result = result.replaceAll(util + "\\.(\\w+)\\s*\\(", "$1(");
        }
        return result;
    }
    
    /**
     * Finds which utility classes are used
     */
    private static Set<String> findUsedUtilityClasses(String content) {
        Set<String> used = new HashSet<>();
        for (String util : UTILITY_CLASSES.keySet()) {
            if (content.contains(util + ".")) {
                used.add(util);
            }
        }
        return used;
    }
    
    /**
     * Extracts imports
     */
    private static Set<String> extractImports(String content) {
        Set<String> imports = new LinkedHashSet<>();
        Pattern pattern = Pattern.compile("^import\\s+([^;]+);", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            imports.add("import " + matcher.group(1) + ";");
        }
        return imports;
    }
    
    /**
     * Extracts class name
     */
    private static String extractClassName(String content) {
        Pattern pattern = Pattern.compile("(?:public\\s+)?class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "Solution";
    }
    
    /**
     * Extracts class content (body)
     */
    private static String extractClassContent(String content) {
        // Remove package and imports
        String body = content.replaceAll("package\\s+[^;]+;", "");
        body = body.replaceAll("import\\s+[^;]+;", "");
        body = body.trim();
        
        // Extract between first { and last }
        int firstBrace = body.indexOf('{');
        int lastBrace = body.lastIndexOf('}');
        
        if (firstBrace != -1 && lastBrace != -1) {
            return body.substring(firstBrace + 1, lastBrace).trim();
        }
        
        return body;
    }
    
    /**
     * Reads file content
     */
    private static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            // Try relative to current directory
            path = Paths.get(System.getProperty("user.dir"), filePath);
        }
        return new String(Files.readAllBytes(path));
    }
    
    /**
     * Removes comments from Java code
     * Handles single-line (//), multi-line (/* *\/), and Javadoc (/** *\/) comments
     */
    private static String removeComments(String code) {
        StringBuilder result = new StringBuilder();
        boolean inString = false;
        boolean inChar = false;
        boolean inSingleLineComment = false;
        boolean inMultiLineComment = false;
        boolean inJavadoc = false;
        
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            char next = (i + 1 < code.length()) ? code.charAt(i + 1) : '\0';
            
            // Handle string literals
            if (!inSingleLineComment && !inMultiLineComment && !inJavadoc) {
                if (c == '"' && (i == 0 || code.charAt(i - 1) != '\\')) {
                    inString = !inString;
                    result.append(c);
                    continue;
                }
                if (c == '\'' && (i == 0 || code.charAt(i - 1) != '\\')) {
                    inChar = !inChar;
                    result.append(c);
                    continue;
                }
            }
            
            // Skip processing if inside string or char literal
            if (inString || inChar) {
                result.append(c);
                continue;
            }
            
            // Handle single-line comments
            if (c == '/' && next == '/' && !inMultiLineComment && !inJavadoc) {
                inSingleLineComment = true;
                i++; // Skip next character
                continue;
            }
            
            if (inSingleLineComment) {
                if (c == '\n') {
                    inSingleLineComment = false;
                    result.append(c); // Keep newline
                }
                continue;
            }
            
            // Handle multi-line comments and Javadoc
            if (c == '/' && next == '*' && !inMultiLineComment && !inJavadoc) {
                if (i + 2 < code.length() && code.charAt(i + 2) == '*') {
                    inJavadoc = true;
                } else {
                    inMultiLineComment = true;
                }
                i++; // Skip next character
                continue;
            }
            
            if (inMultiLineComment || inJavadoc) {
                if (c == '*' && next == '/') {
                    inMultiLineComment = false;
                    inJavadoc = false;
                    i++; // Skip next character
                }
                continue;
            }
            
            // Regular character
            result.append(c);
        }
        
        // Clean up extra whitespace and blank lines
        String cleaned = result.toString();
        // Remove multiple consecutive blank lines (keep max 1)
        cleaned = cleaned.replaceAll("(?m)^[ \t]*\r?\n[ \t]*\r?\n", "\n");
        // Remove trailing whitespace from lines
        cleaned = cleaned.replaceAll("(?m)[ \t]+$", "");
        
        return cleaned.trim();
    }
    
    /**
     * Normalizes indentation: first non-empty line becomes column 0, relative indentation preserved
     * Converts tabs to spaces (1 tab = 4 spaces)
     */
    private static String normalizeIndentation(String code) {
        if (code == null || code.isEmpty()) return code;
        
        String[] lines = code.split("\n", -1);
        if (lines.length == 0) return code;
        
        // Convert tabs to spaces
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].replace("\t", "    ");
        }
        
        // Find indentation of first non-empty line (this becomes our base = 0)
        int baseIndent = -1;
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            int indent = 0;
            for (int i = 0; i < line.length() && line.charAt(i) == ' '; i++) {
                indent++;
            }
            baseIndent = indent;
            break; // Use first non-empty line as base
        }
        
        if (baseIndent == -1) {
            // All lines are empty
            return code.trim();
        }
        
        // Remove baseIndent from all lines (preserves relative indentation)
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();
            
            if (trimmed.isEmpty()) {
                if (i < lines.length - 1) {
                    result.append("\n");
                }
            } else {
                // Count leading spaces
                int leadingSpaces = 0;
                for (int j = 0; j < line.length() && line.charAt(j) == ' '; j++) {
                    leadingSpaces++;
                }
                
                // Remove baseIndent (first line becomes 0, others maintain relative)
                int toRemove = Math.min(baseIndent, leadingSpaces);
                result.append(line.substring(toRemove));
                if (i < lines.length - 1) {
                    result.append("\n");
                }
            }
        }
        
        return result.toString().trim();
    }
    
    /**
     * Indents text
     */
    private static String indent(String text, int spaces) {
        if (text == null || text.isEmpty()) return text;
        String indent = " ".repeat(spaces);
        // Don't indent empty lines
        return text.replaceAll("(?m)^(?!$)", indent);
    }
    
    /**
     * Helper class to store utility class info
     */
    private static class ClassInfo {
        String filePath;
        List<String> methods;
        
        ClassInfo(String filePath, List<String> methods) {
            this.filePath = filePath;
            this.methods = methods;
        }
    }
    
    /**
     * Quick export - prints to console and saves to file
     */
    public static void quickExport(String solutionFilePath) throws IOException {
        String standalone = export(solutionFilePath);
        
        // Save to file
        String outputPath = solutionFilePath.replace(".java", "_Standalone.java");
        Files.write(Paths.get(outputPath), standalone.getBytes());
        
        // Print to console
        System.out.println("=".repeat(80));
        System.out.println("STANDALONE SOLUTION (Ready for Online Compilers)");
        System.out.println("=".repeat(80));
        System.out.println(standalone);
        System.out.println("=".repeat(80));
        System.out.println("\nOK Saved to: " + outputPath);
    }
}


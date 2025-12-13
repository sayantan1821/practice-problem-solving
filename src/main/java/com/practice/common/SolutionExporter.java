package com.practice.common;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

/**
 * Utility class to convert solution classes into standalone, pasteable format
 * for online compilers. Inlines utility methods and collects all imports.
 */
public class SolutionExporter {
    
    private static final String COMMON_PACKAGE = "com.practice.common";
    private static final Map<String, String> utilityClasses = new HashMap<>();
    
    static {
        // Map of utility class names to their file paths
        utilityClasses.put("ArrayUtils", "src/main/java/com/practice/common/ArrayUtils.java");
        utilityClasses.put("BinaryUtils", "src/main/java/com/practice/common/BinaryUtils.java");
        utilityClasses.put("DebugUtils", "src/main/java/com/practice/common/DebugUtils.java");
    }
    
    /**
     * Exports a solution class to a standalone format ready for online compilers
     * 
     * @param solutionFilePath Path to the solution class file
     * @param outputFilePath Path where the standalone class will be written
     * @return The standalone class content as a string
     */
    public static String exportSolution(String solutionFilePath, String outputFilePath) throws IOException {
        String solutionContent = readFile(solutionFilePath);
        String standaloneContent = convertToStandalone(solutionContent);
        
        // Write to file
        Files.write(Paths.get(outputFilePath), standaloneContent.getBytes());
        
        return standaloneContent;
    }
    
    /**
     * Converts solution content to standalone format
     */
    public static String convertToStandalone(String solutionContent) throws IOException {
        StringBuilder result = new StringBuilder();
        
        // Extract package and imports from solution
        Set<String> imports = extractImports(solutionContent);
        String className = extractClassName(solutionContent);
        String classBody = extractClassBody(solutionContent);
        
        // Find utility method calls
        Set<String> usedUtilityMethods = findUsedUtilityMethods(classBody);
        
        // Collect all imports from utility classes
        Set<String> allImports = new LinkedHashSet<>(imports);
        
        // Inline utility methods
        StringBuilder inlinedMethods = new StringBuilder();
        for (Map.Entry<String, String> entry : utilityClasses.entrySet()) {
            String utilClass = entry.getKey();
            String utilPath = entry.getValue();
            
            if (classBody.contains(utilClass + ".")) {
                String utilContent = readFile(utilPath);
                Set<String> utilImports = extractImports(utilContent);
                allImports.addAll(utilImports);
                
                // Extract only used methods from utility class
                List<String> methods = extractMethods(utilContent, usedUtilityMethods, utilClass);
                for (String method : methods) {
                    inlinedMethods.append(method).append("\n\n");
                }
            }
        }
        
        // Build final standalone class
        result.append("// Standalone solution ready for online compilers\n");
        result.append("// Generated from: ").append(className).append("\n\n");
        
        // Add imports (excluding package declarations)
        for (String imp : allImports) {
            if (!imp.contains(COMMON_PACKAGE)) {
                result.append(imp).append("\n");
            }
        }
        result.append("\n");
        
        // Add class declaration
        result.append("class Solution {\n");
        
        // Add inlined utility methods
        if (inlinedMethods.length() > 0) {
            result.append("    // Inlined utility methods\n");
            result.append(inlinedMethods.toString().replaceAll("(?m)^", "    "));
        }
        
        // Add solution method (replace class name with Solution if needed)
        String solutionMethod = classBody;
        solutionMethod = solutionMethod.replaceAll("public class " + className, "// Original class: " + className);
        solutionMethod = solutionMethod.replaceAll("class " + className, "// Original class: " + className);
        
        // Remove utility class references and make methods non-static if needed
        solutionMethod = removeUtilityClassReferences(solutionMethod);
        
        result.append("    // Solution method\n");
        result.append(solutionMethod.replaceAll("(?m)^", "    "));
        
        result.append("}\n");
        
        return result.toString();
    }
    
    /**
     * Extracts import statements from Java source
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
     * Extracts class name from Java source
     */
    private static String extractClassName(String content) {
        Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        pattern = Pattern.compile("class\\s+(\\w+)");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "Solution";
    }
    
    /**
     * Extracts class body (everything inside the class)
     */
    private static String extractClassBody(String content) {
        // Remove package and imports
        String body = content.replaceAll("package\\s+[^;]+;", "");
        body = body.replaceAll("import\\s+[^;]+;", "");
        body = body.trim();
        
        // Extract content between first { and last }
        int firstBrace = body.indexOf('{');
        int lastBrace = body.lastIndexOf('}');
        
        if (firstBrace != -1 && lastBrace != -1) {
            return body.substring(firstBrace + 1, lastBrace).trim();
        }
        
        return body;
    }
    
    /**
     * Finds utility method calls in the code
     */
    private static Set<String> findUsedUtilityMethods(String code) {
        Set<String> methods = new HashSet<>();
        
        for (String utilClass : utilityClasses.keySet()) {
            Pattern pattern = Pattern.compile(utilClass + "\\.(\\w+)\\s*\\(");
            Matcher matcher = pattern.matcher(code);
            while (matcher.find()) {
                methods.add(matcher.group(1));
            }
        }
        
        return methods;
    }
    
    /**
     * Extracts specific methods from a utility class
     */
    private static List<String> extractMethods(String utilContent, Set<String> methodNames, String className) {
        List<String> methods = new ArrayList<>();
        
        // Remove package and class declaration
        String body = extractClassBody(utilContent);
        
        // Extract each method
        for (String methodName : methodNames) {
            Pattern pattern = Pattern.compile(
                "(public\\s+static\\s+[^\\{]+" + methodName + "\\s*\\([^\\)]*\\)[^\\{]*\\{[^\\}]*\\{[^\\}]*\\}[^\\}]*\\})",
                Pattern.DOTALL
            );
            Matcher matcher = pattern.matcher(body);
            while (matcher.find()) {
                String method = matcher.group(1);
                // Remove static and make it private
                method = method.replace("public static", "private static");
                methods.add(method);
            }
        }
        
        // If no specific methods found, try simpler pattern
        if (methods.isEmpty()) {
            Pattern pattern = Pattern.compile(
                "(public\\s+static\\s+[^\\{]+" + String.join("|", methodNames) + "\\s*\\([^\\)]*\\)[^\\{]*\\{.*?\\})",
                Pattern.DOTALL
            );
            Matcher matcher = pattern.matcher(body);
            while (matcher.find()) {
                String method = matcher.group(1);
                method = method.replace("public static", "private static");
                methods.add(method);
            }
        }
        
        return methods;
    }
    
    /**
     * Removes utility class references and makes methods callable directly
     */
    private static String removeUtilityClassReferences(String code) {
        for (String utilClass : utilityClasses.keySet()) {
            // Replace ArrayUtils.method() with method()
            code = code.replaceAll(utilClass + "\\.(\\w+)\\s*\\(", "$1(");
        }
        return code;
    }
    
    /**
     * Reads a file and returns its content
     */
    private static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
    
    /**
     * Quick export method - exports to console and file
     */
    public static void quickExport(String solutionFilePath) throws IOException {
        String outputPath = solutionFilePath.replace(".java", "_Standalone.java");
        String standalone = exportSolution(solutionFilePath, outputPath);
        
        System.out.println("=".repeat(80));
        System.out.println("STANDALONE SOLUTION (Ready for Online Compilers)");
        System.out.println("=".repeat(80));
        System.out.println(standalone);
        System.out.println("=".repeat(80));
        System.out.println("\nAlso saved to: " + outputPath);
    }
}


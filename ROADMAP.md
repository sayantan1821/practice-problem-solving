# Project Roadmap – Problem Solving Practice (Java)

This roadmap focuses on **automation and tooling** around your LeetCode/CodeChef practice. Items are grouped into phases, with completion status noted.

---

## Phase 1 – Core Project & Build Setup ✅

- **[x] Project structure**
  - Java packages under `com.practice.leetcode`, `com.practice.common`, `com.practice.codechef`, `com.practice.hackerrank`.
- **[x] Build & compile tooling**
  - `pom.xml` for Maven-based builds (when Maven is available).
  - Direct `javac` scripts for environments without Maven:
    - `compile.sh`, `compile.bat`, `compile.ps1`.
- **[x] Git ignore configuration**
  - `.gitignore` excludes build artifacts, IDE files, and generated `*_Standalone.java` solution exports.

---

## Phase 2 – LeetCode Workflow Automation ✅

- **[x] Solution exporter to LeetCode format**
  - `SimpleSolutionExporter` + `ExportSolution` CLI.
  - Scripts: `export_solution.sh`, `export_solution.bat`, `export_solution.ps1`.
  - Handles:
    - Collecting imports.
    - Inlining selected utility methods.
    - Stripping comments.
    - Producing a `Solution` class ready to paste into LeetCode.

- **[x] JShell / REPL workflow helpers**
  - Guides: `JAVA_REPL_GUIDE.md`, `DEBUGGING_WITH_JSHELL.md`.
  - Example JShell scripts: `test_solution.jsh`, `debug_interactive.jsh`, `export_example.jsh`.

- **[x] Problem registry and metadata**
  - `problems.json` as the **single source of truth** for problems (class name, LC number, difficulty, tags, date, notes).
  - `ProblemJsonLoader` to parse JSON.
  - `ProblemRegistry` to expose problems in Java.

- **[x] Problem listing & stats**
  - `ListProblems` class + `list_problems.sh` script to show:
    - All problems.
    - Counts by difficulty.

- **[x] Auto-generated problem summary**
  - `GenerateProblemsMD` builds `PROBLEMS.md` from `problems.json`.
  - Script: `generate_problems_md.sh`.
  - `PROBLEMS.md` is now *generated*, not edited by hand.

- **[x] Documentation for the workflow**
  - `README.md`, `HOW_TO_RUN.md`, `HOW_TO_RUN_TESTS.md`, `HOW_TO_ADD_PROBLEM.md`, `EXPORT_SOLUTION_GUIDE.md`, `QUICK_START.md`, `INSTALL_MAVEN.md`.

---

## Phase 3 – Problem Skeleton & Test Automation (In Progress)

- **[x] Per-problem tests for existing problems**
  - `TwoSumTest`, `SortByReflectionTest`, `LargestPrimeFromConsecutivePrimeSumTest`, `AbsoluteDifferenceMaxMinKElementsTest`.

- **[ ] One-command “create new problem” flow**
  - **Goal:** A script (e.g. `create_problem.sh` / `.bat`) that:
    - Asks for: `className`, `problemName`, LeetCode number, difficulty, tags.
    - Appends a new entry to `problems.json`.
    - Generates:
      - `src/main/java/com/practice/leetcode/<ClassName>.java` with:
        - Problem Javadoc.
        - LeetCode link.
        - TODO method signature (no solution).
      - `src/test/java/com/practice/leetcode/<ClassName>Test.java` with:
        - JUnit test skeleton (empty tests or example from prompt).
    - Runs `./generate_problems_md.sh` to refresh `PROBLEMS.md`.

- **[ ] Test template support**
  - Maintain a simple test template (e.g. `templates/ProblemTestTemplate.java`) used by the `create_problem` script to ensure consistent structure.

---

## Phase 4 – Safer Export & Test Integration (Planned)

- **[ ] Problem-specific export with test guardrails**
  - Add a script like `export_problem.sh <ClassName>` that:
    - Compiles the project (`./compile.sh`).
    - Runs only `<ClassName>Test`.
    - If tests pass:
      - Calls the exporter for `<ClassName>` (produces `<ClassName>_Standalone.java`).
    - If tests fail:
      - Shows the failures.
      - Skips export to avoid pushing incorrect solutions to LeetCode.

- **[ ] Optional: auto-open exported file**
  - After a successful export, automatically open the generated standalone file in the editor to make copy–paste to LeetCode faster.

---

## Phase 5 – CI / Git Integration (Planned)

- **[ ] Continuous Integration (GitHub Actions)**
  - On each push:
    - Run `./compile.sh`.
    - Run all tests.
    - Optionally:
      - Run `GenerateProblemsMD` in a “check” mode to confirm `PROBLEMS.md` matches `problems.json`.

- **[ ] Pre-commit / pre-push hooks (local)**
  - Pre-commit hook ideas:
    - Run `./compile.sh` and tests for changed problem files.
    - If `problems.json` changed, run `./generate_problems_md.sh`.

---

## Phase 6 – Developer Experience & Editor Integration (Planned)

- **[ ] VS Code / Cursor tasks**
  - Define tasks for:
    - “Compile project”.
    - “Run tests”.
    - “Create new problem”.
    - “Export current problem to LeetCode format”.

- **[ ] Optional formatting / linting**
  - Integrate a Java formatter (e.g. `google-java-format`) or simple Checkstyle config.
  - Add a script like `format.sh` to reformat the codebase.
  - Optionally run format check as part of CI.

---

## Phase 7 – Extra Automation Ideas (Optional / Future)

- **[ ] Problem dashboard enhancements**
  - Extend `ListProblems` to:
    - Filter by difficulty, tags, or date ranges.
    - Show which problems have tests vs. missing tests.

- **[ ] LeetCode metadata enrichment**
  - Extend `problems.json` to store:
    - Runtime/Memory beats (manually recorded).
    - Links to discussion posts or solutions.
    - Company tags (for interview prep).

---

### How to Use This Roadmap

- Treat **Phases 1–2** as your “foundation” (already done).
- Focus next on **Phase 3**: the `create_problem` flow gives you the biggest productivity win.
- When you’re comfortable, pick items from later phases (export guardrails, CI, editor tasks) to make your workflow even smoother.



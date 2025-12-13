# Export solution to standalone format
# PowerShell script
# Usage: .\export_solution.ps1 <solution-file-path>

param(
    [Parameter(Mandatory=$true)]
    [string]$SolutionPath
)

Write-Host "Compiling project..." -ForegroundColor Cyan
& .\compile.ps1

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed!" -ForegroundColor Red
    exit 1
}

Write-Host "Exporting solution..." -ForegroundColor Cyan
java -cp target/classes com.practice.ExportSolution $SolutionPath

if ($LASTEXITCODE -ne 0) {
    Write-Host "Export failed!" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "Done!" -ForegroundColor Green


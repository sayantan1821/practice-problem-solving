# Compile Java project without Maven
# PowerShell script

Write-Host "Compiling Java project..." -ForegroundColor Cyan

# Create output directory
if (-not (Test-Path "target\classes")) {
    New-Item -ItemType Directory -Path "target\classes" | Out-Null
}

# Find all Java files
$javaFiles = Get-ChildItem -Path "src\main\java" -Filter "*.java" -Recurse

if ($javaFiles.Count -eq 0) {
    Write-Host "No Java files found!" -ForegroundColor Red
    exit 1
}

# Compile
$files = $javaFiles | ForEach-Object { $_.FullName }
javac -d target\classes -sourcepath src\main\java $files

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed!" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "✅ Compilation successful!" -ForegroundColor Green
Write-Host "Classes are in: target\classes" -ForegroundColor Green


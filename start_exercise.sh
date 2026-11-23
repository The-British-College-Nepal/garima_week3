#!/usr/bin/env bash
# Clone script for SSD Week 3 Lab: Code Documentation, Structure & Refactoring
# Organization: The-British-College-Nepal
# Naming Convention: <your-name>-ssd-2026-week3-exercise

set -e

echo "============================================================"
echo "🚀 SSD Week 3 Lab: Documentation, Structure & Refactoring"
echo "============================================================"

# If student name is provided as argument, use it; otherwise prompt
if [ -n "$1" ]; then
  STUDENT_NAME="$1"
else
  echo ""
  echo "Enter your name to clone your personal repository:"
  echo "(Example: sudan, crystal, garima, sujal)"
  read -p "Your Name: " STUDENT_NAME
fi

# Clean up input (lowercase, trimmed)
STUDENT_NAME=$(echo "$STUDENT_NAME" | tr '[:upper:]' '[:lower:]' | tr -d '[:space:]')

if [ -z "$STUDENT_NAME" ]; then
  echo "❌ Error: Name cannot be empty. Please run ./start_exercise.sh <your-name>"
  exit 1
fi

REPO_NAME="${STUDENT_NAME}-ssd-2026-week3-exercise"
REPO_URL="https://github.com/The-British-College-Nepal/${REPO_NAME}.git"
TEMPLATE_URL="https://github.com/The-British-College-Nepal/ssd-2026-week3-exercise.git"

echo ""
echo "🔍 Checking for repository: $REPO_NAME..."

if [ -d "$REPO_NAME" ]; then
  echo "⚠️ Directory '$REPO_NAME' already exists locally."
  exit 1
fi

# Try cloning the student's repository first
if git clone "$REPO_URL" "$REPO_NAME" 2>/dev/null; then
  echo "✅ Successfully cloned your repository: $REPO_NAME"
else
  echo "ℹ️ Personal repository '$REPO_NAME' not found yet."
  echo "   Cloning the root template into '$REPO_NAME'..."
  git clone "$TEMPLATE_URL" "$REPO_NAME"
fi

echo ""
echo "============================================================"
echo "👉 Next Steps:"
echo "   1. cd $REPO_NAME"
echo "   2. git checkout -b feature/step1-clean-code"
echo "   3. Open the project in IntelliJ IDEA or Eclipse."
echo "============================================================"

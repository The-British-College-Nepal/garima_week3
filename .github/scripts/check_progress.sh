#!/usr/bin/env bash
# Automated Evaluation & Step Advancement Script for SSD Week 3 Lab
# Evaluates Unit Tests and advances README.md upon successful merge to main

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"
STEP_FILE="$REPO_ROOT/.github/current_step.txt"
STEPS_DIR="$REPO_ROOT/.github/steps"
README_FILE="$REPO_ROOT/README.md"

cd "$REPO_ROOT"

# Determine current step (defaults to 1)
CURRENT_STEP=1
if [ -f "$STEP_FILE" ]; then
  CURRENT_STEP=$(cat "$STEP_FILE" | tr -d '[:space:]')
fi

echo "============================================================"
echo "🤖 SSD Interactive Lab Evaluator — Week 3 (Documentation)"
echo "   Current Step: $CURRENT_STEP"
echo "============================================================"

ADVANCED=false

if [ "$CURRENT_STEP" = "1" ]; then
  echo "🔍 Evaluating Step 1: Clean Code, Naming Conventions & Encapsulation..."
  if mvn -B test -Dtest=Step1CleanCodeTest; then
    echo "✅ Step 1 tests passed!"
    echo "2" > "$STEP_FILE"
    cp "$STEPS_DIR/step-2.md" "$README_FILE"
    ADVANCED=true
    NEXT_STEP="Step 2: Core Statistical Logic & Array Bounds"

    if [ -n "$GITHUB_STEP_SUMMARY" ]; then
      echo "## 🎉 Step 1 Passed!" >> "$GITHUB_STEP_SUMMARY"
      echo "Great job refactoring legacy names and enforcing encapsulation! Your repository has been updated with instructions for **Step 2: Core Statistical Logic & Array Bounds**." >> "$GITHUB_STEP_SUMMARY"
      echo "👉 Run \`git pull origin main\` locally to get the updated README in your IDE!" >> "$GITHUB_STEP_SUMMARY"
    fi
  else
    echo "❌ Step 1 tests failed."
    if [ -n "$GITHUB_STEP_SUMMARY" ]; then
      echo "## ❌ Step 1 Tests Failed" >> "$GITHUB_STEP_SUMMARY"
      echo "Review \`src/main/java/stats/Stats.java\`:" >> "$GITHUB_STEP_SUMMARY"
      echo "* Ensure fields \`numbers\` and \`count\` are declared \`private\`." >> "$GITHUB_STEP_SUMMARY"
      echo "* Rename legacy methods: \`getA()\` -> \`getMax()\`, \`getB()\` -> \`getMin()\`, \`get()\` -> \`getAverage()\`." >> "$GITHUB_STEP_SUMMARY"
      echo "* In \`getCount()\`, return \`this.count\` (not \`numbers.length\`)." >> "$GITHUB_STEP_SUMMARY"
    fi
    exit 1
  fi

elif [ "$CURRENT_STEP" = "2" ]; then
  echo "🔍 Evaluating Step 2: Core Statistical Calculations..."
  if mvn -B test -Dtest=Step1CleanCodeTest,Step2StatisticsTest; then
    echo "✅ Step 2 tests passed!"
    echo "3" > "$STEP_FILE"
    cp "$STEPS_DIR/step-3.md" "$README_FILE"
    ADVANCED=true
    NEXT_STEP="Step 3: Javadoc Documentation & Defensive Checks"

    if [ -n "$GITHUB_STEP_SUMMARY" ]; then
      echo "## 🎉 Step 2 Passed!" >> "$GITHUB_STEP_SUMMARY"
      echo "Great work fixing statistical loops and array boundary handling! Your repository has been updated with instructions for **Step 3: Javadoc Documentation & Defensive Checks**." >> "$GITHUB_STEP_SUMMARY"
      echo "👉 Run \`git pull origin main\` locally to get the updated README in your IDE!" >> "$GITHUB_STEP_SUMMARY"
    fi
  else
    echo "❌ Step 2 tests failed."
    if [ -n "$GITHUB_STEP_SUMMARY" ]; then
      echo "## ❌ Step 2 Tests Failed" >> "$GITHUB_STEP_SUMMARY"
      echo "Review your statistical calculation logic in \`src/main/java/stats/Stats.java\`:" >> "$GITHUB_STEP_SUMMARY"
      echo "* Make sure loops iterate up to \`count\` (NOT \`numbers.length\`)." >> "$GITHUB_STEP_SUMMARY"
      echo "* In \`getAverage()\`, divide the sum by \`count\` as a \`double\`." >> "$GITHUB_STEP_SUMMARY"
      echo "* In \`getMax()\` and \`getMin()\`, only inspect elements between index 0 and \`count - 1\`." >> "$GITHUB_STEP_SUMMARY"
    fi
    exit 1
  fi

elif [ "$CURRENT_STEP" = "3" ]; then
  echo "🔍 Evaluating Step 3: Javadoc Documentation & Defensive Programming..."
  if mvn -B test; then
    echo "✅ Step 3 tests passed! Exercise fully completed!"
    echo "completed" > "$STEP_FILE"
    cp "$STEPS_DIR/completed.md" "$README_FILE"
    ADVANCED=true
    NEXT_STEP="Exercise Completed"

    if [ -n "$GITHUB_STEP_SUMMARY" ]; then
      echo "## 🏆 100% Complete! All Steps Passed!" >> "$GITHUB_STEP_SUMMARY"
      echo "You have successfully completed Week 3: Code Documentation, Structure & Refactoring! Your README has been updated to the completion badge." >> "$GITHUB_STEP_SUMMARY"
    fi
  else
    echo "❌ Step 3 tests failed."
    if [ -n "$GITHUB_STEP_SUMMARY" ]; then
      echo "## ❌ Step 3 Tests Failed" >> "$GITHUB_STEP_SUMMARY"
      echo "Review your documentation and defensive checks in \`src/main/java/stats/Stats.java\`:" >> "$GITHUB_STEP_SUMMARY"
      echo "* Add \`@author\` to class header." >> "$GITHUB_STEP_SUMMARY"
      echo "* Add \`@param\` tags to methods/constructors with parameters." >> "$GITHUB_STEP_SUMMARY"
      echo "* Add \`@return\` tags to methods returning values." >> "$GITHUB_STEP_SUMMARY"
      echo "* Throw \`IllegalStateException(\"No values have been added\")\` in \`getMax()\`, \`getMin()\`, and \`getAverage()\` if \`count == 0\`." >> "$GITHUB_STEP_SUMMARY"
    fi
    exit 1
  fi

else
  echo "🎉 Lab was already completed. Verifying all tests remain green..."
  mvn -B test
  if [ -n "$GITHUB_STEP_SUMMARY" ]; then
    echo "## ✅ All Tests Passing (Lab Complete)" >> "$GITHUB_STEP_SUMMARY"
  fi
fi

# If the step advanced, commit and push changes back to the student repo
if [ "$ADVANCED" = "true" ]; then
  echo "🚀 Committing updated README and advancing step to: $NEXT_STEP"
  git config user.name "github-actions[bot]"
  git config user.email "github-actions[bot]@users.noreply.github.com"
  git add "$README_FILE" "$STEP_FILE"
  git commit -m "🤖 [GitHub Skills] Advance to $NEXT_STEP [skip ci]"
  git push origin main
  echo "✅ Step successfully unlocked!"
fi

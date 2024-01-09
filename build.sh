#!/bin/bash

# Create the output directory if it doesn't exist
mkdir -p out

# Compile all Java files within the src directory and its subdirectories
find src/java/com/ -name "*.java" -print | xargs javac -d out

echo "Compilation completed."



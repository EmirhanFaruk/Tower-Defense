# Create the output folder if it doesn't exist
mkdir -p out

# Compile all source files in src/java/com and its subfolders
find src/java/com/ -name "*.java" -print | xargs javac -d out

echo "Compilation finished."



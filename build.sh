#!/bin/sh
set -eu

export PATH="/usr/lib/jvm/java-8-openjdk/bin/:$PATH"

ROOT=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)

cd "$ROOT"

rm -r build
mkdir -p "build/classes"

# ----------------------------------------------------------------------

echo "==> Compiling"

javac -source 1.3 -target 1.1 -bootclasspath "lib/cldcapi11.jar:lib/midpapi20.jar" \
    -d "build/classes" $(find src -name "*.java")

cp -r resources/* build/classes

jar cfm build/input.jar resources/META-INF/MANIFEST.MF -C "build/classes" .

# ----------------------------------------------------------------------

echo "==> Preverifying with ProGuard"

java -jar "lib/proguard.jar" \
    -injars "build/input.jar" -outjars "app.jar" \
    -libraryjars "lib/cldcapi11.jar" \
    -libraryjars "lib/midpapi20.jar" \
    -dontshrink -dontoptimize -dontobfuscate \
    -microedition

# ----------------------------------------------------------------------

echo "==> Build complete"

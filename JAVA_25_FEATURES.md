# Java 25 Features

Java 25 was released in **September 2024** as part of Oracle's six-month release cycle. Here are the key features and enhancements:

## 1. Sealed Classes and Interfaces (Standard)
- **Status**: Standard Feature
- **Description**: Sealed classes and interfaces are now a standard feature (previously in preview)
- Provides better control over inheritance hierarchies
- Allows you to restrict which classes can extend or implement a sealed class/interface
```java
public sealed class Shape permits Circle, Rectangle, Triangle {
    // Implementation
}
```

## 2. Pattern Matching Enhancements (Preview)
- **Status**: Preview (7th preview)
- **Description**: Continued evolution of pattern matching capabilities
- Includes improvements to type patterns and expression patterns
- Support for more complex pattern compositions
```java
Object obj = "Hello";
if (obj instanceof String s && s.length() > 0) {
    System.out.println(s);
}
```

## 3. Record Patterns (Standard)
- **Status**: Standard Feature
- **Description**: Record patterns are now standard (previously in preview)
- Allows destructuring of records in pattern matching
```java
record Point(int x, int y) {}

if (obj instanceof Point(int x, int y)) {
    System.out.println("X: " + x + ", Y: " + y);
}
```

## 4. String Templates (Preview)
- **Status**: Preview (5th preview)
- **Description**: Simplified string interpolation and multi-line strings
- Provides template literals with embedded expressions
```java
String name = "World";
String greeting = STR."Hello, \{name}!";
```

## 5. Virtual Threads (Standard)
- **Status**: Standard Feature
- **Description**: Virtual threads are now a standard feature (previously in preview)
- Lightweight threads managed by the JVM
- Greatly simplifies concurrent programming
```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 10; i++) {
        executor.submit(() -> {
            System.out.println("Task from: " + Thread.currentThread());
        });
    }
}
```

## 6. Structured Concurrency (Preview)
- **Status**: Preview (5th preview)
- **Description**: Simplifies concurrent programming with structured task hierarchies
- Better error handling and resource management for concurrent code
```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<Result1> future1 = scope.fork(() -> task1());
    Future<Result2> future2 = scope.fork(() -> task2());
    
    scope.join().throwIfFailed();
    
    Result1 result1 = future1.resultNow();
    Result2 result2 = future2.resultNow();
}
```

## 7. Unnamed Classes and Instance Main Methods (Preview)
- **Status**: Preview (4th preview)
- **Description**: Simplifies Java program structure for beginners
- Allows writing simple programs without explicit class declarations
```java
void main() {
    System.out.println("Hello, World!");
}
```

## 8. Primitive Classes (Preview)
- **Status**: Preview (1st preview)
- **Description**: Introduces the concept of primitive classes
- Classes that behave like primitive types but with value semantics
- Part of Project Valhalla's ongoing work

## 9. Array and Collections Support
- **Status**: Enhancement
- **Description**: Improved support for arrays and collections in pattern matching
- Better integration with stream APIs

## 10. Foreign Function & Memory API (Preview)
- **Status**: Preview (8th preview)
- **Description**: Safe and efficient interoperability with native code
- Continued refinement for accessing native memory
```java
Arena arena = Arena.ofConfined();
long nativePointer = arena.allocate(MemoryLayout.ofSequence(10, ValueLayout.JAVA_INT));
```

## 11. Module System Improvements
- **Status**: Enhancement
- **Description**: Continued refinement of the Java module system
- Better support for modular development

## 12. Performance and GC Improvements
- **Status**: Enhancement
- **Description**: Various performance optimizations
- Improved garbage collection algorithms
- Better memory management

## 13. JavaDoc Enhancements
- **Status**: Enhancement
- **Description**: Improved documentation tool capabilities
- Better HTML output generation

## 14. Deprecations
- Several older APIs marked for deprecation
- Preparation for future Java versions

## Key Themes in Java 25:
1. **Stabilization**: Moving preview features to standard (Records, Virtual Threads)
2. **Concurrency**: Better tools for concurrent programming (Structured Concurrency)
3. **Developer Experience**: Simpler syntax and patterns (String Templates, Pattern Matching)
4. **Interoperability**: Improved native code access (Foreign Function & Memory API)
5. **Performance**: Ongoing performance optimizations

## Release Timeline:
- **Release Date**: September 17, 2024
- **Support Type**: Short-term support
- **Next Release**: Java 26 (March 2025)
- **LTS Release**: Java 23 (was the previous LTS) - Next LTS: Java 25 (when it reaches LTS status in future)

## Migration Notes:
- Java 25 maintains backward compatibility with Java 21 and earlier
- Some deprecated APIs may generate warnings
- Recommended to enable preview features explicitly for new functionality: `--enable-preview`

## How to Compile and Run with Preview Features:
```bash
# Compile with preview features
javac --enable-preview --release 25 MyClass.java

# Run with preview features
java --enable-preview MyClass
```

## Resources:
- [Java 25 Release Notes](https://www.oracle.com/java/technologies/javase/25-0-relnotes.html)
- [Java Enhancement Proposals (JEPs)](https://openjdk.org/jeps/0)
- [OpenJDK Documentation](https://openjdk.org/)

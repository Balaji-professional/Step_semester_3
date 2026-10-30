# Step_semester_3

## Session 9 — Data Structures

`Session9Programs.java` contains the five coding exercises from the Session 9 assignment. Problems 3 and 4 describe the same unsorted-array pair-sum task, so they share the same optimized implementation; both the brute-force and hash-set approaches are included.

Compile and run the examples:

```sh
javac Session9Programs.java
java Session9Programs
```

### Complexity Summary

- **Problem 1 — Library Catalog Lookup:** Binary search takes `O(log n)` time and `O(1)` additional space.
- **Problem 2 — Warehouse Grid Summary:** Scanning an `m × n` grid takes `O(mn)` time and `O(1)` additional space.
- **Problems 3 and 4 — Pair With Target Sum:** The brute-force method takes `O(n²)` time and `O(1)` additional space. The hash-set method takes expected `O(n)` time and `O(n)` additional space.
- **Problem 5 — Maximize Area Between Two Boundaries:** Brute force takes `O(n²)` time. The two-pointer method takes `O(n)` time and `O(1)` additional space.

### Quiz Answers

1. B — `O(1)`.
2. C — Insertion.
3. C — Match operations and constraints to the strengths of data structures.
4. C — Time-space trade-off.
5. B — `O(m + n)`.
6. C — Linear data structure.
7. C — `O(n)`.
8. D — Linked list.
9. B — `O(m × n)`.
10. C — Organize, store, and access data efficiently for specific operations.

### Concept Answers

1. **Searching in unsorted and sorted arrays:** An unsorted array generally requires a linear scan, so searching takes `O(n)` time. A sorted array can use binary search, which repeatedly halves the remaining search interval and takes `O(log n)` time. The ordering makes it possible to discard half of the candidates at each step.

2. **Dropping constant multipliers:** Big-O describes how an algorithm's growth changes as input size increases, rather than its exact operation count. The factor of two does not change the linear growth rate, so `2n` is classified as `O(n)`.

3. **Traversing linear and non-linear structures:** Linear structures can be visited in sequence, moving from one element to the next. Non-linear structures branch into relationships such as parent-child links, so traversal follows those links using strategies such as depth-first or breadth-first search.
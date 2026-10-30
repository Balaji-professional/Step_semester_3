import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Problem3StudentClubRegistration {
    private static final class Student {
        private final String rollNumber;
        private final String name;

        private Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Student)) {
                return false;
            }
            Student student = (Student) other;
            return rollNumber.equals(student.rollNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rollNumber);
        }
    }

    public static void main(String[] args) {
        Set<Student> members = new LinkedHashSet<>();
        List<String> addResults = new ArrayList<>();
        List<String> containsResults = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+", 3);
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Operation format: <ADD|CONTAINS> <RollNumber> <Name>");
                }
                Student student = new Student(parts[1], parts[2]);
                switch (parts[0]) {
                    case "ADD":
                        addResults.add(members.add(student) ? "Added" : "duplicate rejected");
                        break;
                    case "CONTAINS":
                        containsResults.add("contains: " + members.contains(student));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation: " + parts[0]);
                }
            }
        }

        addResults.forEach(System.out::println);
        System.out.println("member count " + members.size());
        containsResults.forEach(System.out::println);
    }
}
